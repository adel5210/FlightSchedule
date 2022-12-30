package com.adel.flightschedule.security.service;

import com.adel.flightschedule.security.config.JwtPropertiesConfig;
import com.adel.flightschedule.security.exception.TokenRefreshException;
import com.adel.flightschedule.security.model.AuthToken;
import com.adel.flightschedule.security.repository.AuthTokenRepository;
import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    private final JwtPropertiesConfig jwtPropertiesConfig;
    private final AuthTokenRepository authTokenRepository;
    private final UserProfileDaoRepository userProfileDaoRepository;

    @Transactional(readOnly = true)
    public AuthToken findByToken(final String token){
        return authTokenRepository.findByToken(token);
    }

    @Transactional
    public AuthToken createRefreshToken(final Long userId){

        final UserProfileDao userProfile = userProfileDaoRepository.findByUserId(userId);

        final AuthToken authToken = AuthToken.builder()
                .userProfile(userProfile)
                .expiryDate(Instant.now().plusMillis(jwtPropertiesConfig.getRefreshExpireMs()))
                .token(UUID.randomUUID().toString())
                .build();

        return authTokenRepository.save(authToken);
    }

    public AuthToken verifyExpiry(final AuthToken token) throws TokenRefreshException {
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            authTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token has expired");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(final Long userId){
        final UserProfileDao userProfile = userProfileDaoRepository.findByUserId(userId);
        return authTokenRepository.deleteByUserProfile_Username(userProfile.getUsername());
    }

}
