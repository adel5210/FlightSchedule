package com.adel.flightschedule.security.service;

import com.adel.flightschedule.security.config.JwtPropertiesConfig;
import com.adel.flightschedule.security.exception.TokenRefreshException;
import com.adel.flightschedule.security.model.AuthToken;
import com.adel.flightschedule.security.repository.AuthTokenRepository;
import com.adel.flightschedule.userprofile.exception.UserProfileException;
import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    private final JwtPropertiesConfig jwtPropertiesConfig;
    private final AuthTokenRepository authTokenRepository;
    private final UserProfileDaoRepository userProfileDaoRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional(readOnly = true)
    public Optional<AuthToken> findByToken(final String token) {
        return authTokenRepository.findByToken(token);
    }

    @Transactional
    public AuthToken createRefreshToken(final String username) throws UserProfileException {

        final UserProfileDao userProfile = userProfileDaoRepository.findByUsername(username);

        if (null == userProfile) {
            throw new UserProfileException("User not found");
        }

        AuthToken authToken = authTokenRepository.findByUserProfile_Username(userProfile.getUsername())
                .orElse(AuthToken.builder().build());

        authToken = authToken.toBuilder()
                .lastTimestamp(OffsetDateTime.now())
                .userProfile(userProfile)
                .expiryDate(Instant.now().plusMillis(jwtPropertiesConfig.getRefreshExpireMs()))
                .refreshToken(UUID.randomUUID().toString())
                .accessToken(jwtTokenUtil.generateToken(userProfile.getUsername()))
                .build();
        authTokenRepository.save(authToken);
        return authToken;
    }

    public AuthToken verifyExpiryAndRefresh(AuthToken token) throws TokenRefreshException {

        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            authTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token has expired");
        }

        token = token.toBuilder()
                .expiryDate(Instant.now().plusMillis(jwtPropertiesConfig.getRefreshExpireMs()))
                .refreshToken(UUID.randomUUID().toString())
                .accessToken(jwtTokenUtil.generateToken(token.getUserProfile().getUsername()))
                .build();
        authTokenRepository.save(token);
        return token;
    }

    @Transactional
    public void deleteByUsername(final String username) {
        final UserProfileDao userProfile = userProfileDaoRepository.findByUsername(username);
        authTokenRepository.deleteByUserProfile_Username(userProfile.getUsername());
    }

}
