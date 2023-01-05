package com.adel.flightschedule.userprofile.service;

import com.adel.flightschedule.security.dto.AuthResponse;
import com.adel.flightschedule.security.exception.TokenRefreshException;
import com.adel.flightschedule.security.model.AuthToken;
import com.adel.flightschedule.security.model.UserDetailsImpl;
import com.adel.flightschedule.security.service.AuthTokenService;
import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;
import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import com.adel.flightschedule.utils.ValidatorUtil;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileDaoRepository userProfileDaoRepository;
    private final OTPService otpService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthTokenService authTokenService;

    @Override
    @Transactional
    public void signUp(final UserProfileDto userProfileDto) throws UserProfileException {

        ValidatorUtil.builder()
                .param("First name", userProfileDto.getFirstName()).notNull()
                .param("Last name", userProfileDto.getLastName()).notNull()
                .param("Email", userProfileDto.getEmail()).notNull()
                .param("Username", userProfileDto.getUsername()).notNull()
                .param("Password", userProfileDto.getPassword()).notNull();

        UserProfileDao userProfile = userProfileDaoRepository.findByUsername(userProfileDto.getUsername());
        if (null != userProfile) {
            throw new UserProfileException("Username already registered");
        }

        userProfile = userProfileDaoRepository.findByEmail(userProfileDto.getEmail());
        if (null != userProfile) {
            throw new UserProfileException("Email already registered");
        }

        userProfileDaoRepository.saveAndFlush(UserProfileDao.builder()
                .email(userProfileDto.getEmail())
                .firstName(userProfileDto.getFirstName())
                .lastName(userProfileDto.getLastName())
                .password(passwordEncoder.encode(userProfileDto.getPassword()))
                .isRegistered(false)
                .createdAt(OffsetDateTime.now())
                .lastTimestamp(OffsetDateTime.now())
                .username(userProfileDto.getUsername())
                .build());

        sendOTP(userProfileDto);
    }

    @Override
    @Transactional(readOnly = true)
    public void sendOTP(UserProfileDto userProfileDto) throws UserProfileException {

        final String paramRequired = Optional.ofNullable(userProfileDto.getEmail())
                .orElse(userProfileDto.getUsername());

        ValidatorUtil.builder()
                .param("username/email", paramRequired).notNull();

        UserProfileDao profileDao = Optional.ofNullable(userProfileDto.getUsername())
                .map(userProfileDaoRepository::findByUsername).orElse(null);

        if (null == profileDao && !StringUtils.isEmpty(userProfileDto.getEmail())) {
            profileDao = userProfileDaoRepository.findByEmail(userProfileDto.getEmail());
        }

        if (null == profileDao) {
            throw new UserProfileException("Invalid username/email, cannot process OTP");
        }

        final Integer generatedOtp = otpService.generateOTP(profileDao.getId());

        final StringBuilder message = new StringBuilder("Hi ").append(profileDao.getFirstName()).append(", \n")
                .append("Please enter the following OTP to the site.\n\n")
                .append("Your verification code: ").append(generatedOtp);

        emailService.sendMessage(profileDao.getEmail(),
                "Dummy FlightSchedule",
                message.toString());

    }

    @Override
    @Transactional
    public void validateOtp(UserProfileDto userProfileDto) throws UserProfileException {

        ValidatorUtil.builder()
                .param("OTP", userProfileDto.getOtp()).notNull();

        final String paramRequired = Optional.ofNullable(userProfileDto.getEmail())
                .orElse(userProfileDto.getUsername());

        ValidatorUtil.builder()
                .param("username/email", paramRequired).notNull();

        UserProfileDao profileDao = Optional.ofNullable(userProfileDto.getUsername())
                .map(userProfileDaoRepository::findByUsername).orElse(null);

        if (null == profileDao && !StringUtils.isEmpty(userProfileDto.getEmail())) {
            profileDao = userProfileDaoRepository.findByEmail(userProfileDto.getEmail());
        }

        if (null == profileDao) {
            throw new UserProfileException("Email not yet registered");
        }

        if (!otpService.isValidOTP(profileDao.getId(), userProfileDto.getOtp())) {
            throw new UserProfileException("Incorrect OTP");
        }

        userProfileDaoRepository.saveAndFlush(profileDao.toBuilder()
                .lastTimestamp(OffsetDateTime.now())
                .isRegistered(true)
                .build());

    }

    @Override
    public AuthResponse signIn(UserProfileDto userProfileDto) throws UserProfileException {

        ValidatorUtil.builder()
                .param("Username", userProfileDto.getUsername()).notNull()
                .param("Password", userProfileDto.getPassword()).notNull();

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userProfileDto.getUsername(), userProfileDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        final AuthToken authToken = authTokenService.createRefreshToken(userDetails.getUsername());

        return AuthResponse.builder()
                .username(userDetails.getUsername())
                .accessToken(authToken.getAccessToken())
                .refreshToken(authToken.getRefreshToken())
                .build();
    }

    @Override
    public AuthResponse signOut(UserProfileDto userProfileDto) throws UserProfileException {
        ValidatorUtil.builder()
                .param("Username", userProfileDto.getUsername()).notNull();

        final Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principle.toString().equals("anonymousUser")) {
            final String username = ((UserDetailsImpl) principle).getUsername();
            authTokenService.deleteByUsername(username);
            return AuthResponse.builder()
                    .username(userProfileDto.getUsername())
                    .build();
        }

        throw new UserProfileException("Anonymous user sign-out attempt");

    }

    @Override
    public AuthResponse refreshToken(UserProfileDto userProfileDto) throws UserProfileException {
        ValidatorUtil.builder()
                .param("Username", userProfileDto.getUsername()).notNull()
                .param("Token", userProfileDto.getToken()).notNull();

        final UserProfileDao userProfile = userProfileDaoRepository.findByUsername(userProfileDto.getUsername());

        if (null == userProfile) {
            throw new UserProfileException("Unknown user attempt refresh token");
        }

        if (null != userProfileDto.getToken() && userProfileDto.getToken().length() > 0) {
            return authTokenService.findByToken(userProfileDto.getToken())
                    .map(m -> {
                        try {
                            return authTokenService.verifyExpiryAndRefresh(m);
                        } catch (TokenRefreshException e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .map(m -> AuthResponse.builder()
                            .username(m.getUserProfile().getUsername())
                            .accessToken(m.getAccessToken())
                            .refreshToken(m.getRefreshToken())
                            .build())
                    .orElseThrow(() -> new UserProfileException("Token not exist in db"));
        }

        return null;
    }

    @Override
    public void resetPassword(UserProfileDto userProfileDto) throws UserProfileException {
        ValidatorUtil.builder()
                .param("Email", userProfileDto.getEmail()).notNull()
                .param("new password", userProfileDto.getNewPassword()).notNull();

        final UserProfileDao userProfile = userProfileDaoRepository.findByEmail(userProfileDto.getEmail());

        if (null == userProfile) {
            throw new UserProfileException("Unknown user attempt reset password");
        }

        userProfileDaoRepository.saveAndFlush(userProfile.toBuilder()
                .password(passwordEncoder.encode(userProfileDto.getNewPassword()))
                .lastTimestamp(OffsetDateTime.now())
                .build());
    }


}
