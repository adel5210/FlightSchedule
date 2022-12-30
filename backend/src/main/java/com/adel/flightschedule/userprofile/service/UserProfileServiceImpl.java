package com.adel.flightschedule.userprofile.service;

import com.adel.flightschedule.security.dto.AuthResponse;
import com.adel.flightschedule.security.model.UserDetailsImpl;
import com.adel.flightschedule.security.service.AuthTokenService;
import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;
import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import com.adel.flightschedule.utils.ValidatorUtil;
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
    public void signUp(final UserProfileDto userProfileDto) {

        ValidatorUtil.builder()
                .param("First name", userProfileDto.getFirstName()).notNull()
                .param("Last name", userProfileDto.getLastName()).notNull()
                .param("Email", userProfileDto.getEmail()).notNull()
                .param("Username", userProfileDto.getUsername()).notNull()
                .param("Password", userProfileDto.getPassword()).notNull();

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
    public void sendOTP(UserProfileDto userProfileDto) {
        ValidatorUtil.builder()
                .param("Username", userProfileDto.getUsername()).notNull();

        final UserProfileDao profileDao = userProfileDaoRepository.findByUsername(userProfileDto.getUsername());

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
    public void validateRegistration(UserProfileDto userProfileDto) throws UserProfileException {

        ValidatorUtil.builder()
                .param("Username", userProfileDto.getUsername()).notNull()
                .param("OTP", userProfileDto.getOtp()).notNull();

        final UserProfileDao profileDao = userProfileDaoRepository.findByUsername(userProfileDto.getUsername());

        if(null == profileDao){
            throw new UserProfileException("Email not yet registered");
        }

        if(!otpService.isValidOTP(profileDao.getId(), userProfileDto.getOtp())){
            throw new UserProfileException("Incorrect OTP");
        }

        userProfileDaoRepository.saveAndFlush(profileDao.toBuilder()
                .lastTimestamp(OffsetDateTime.now())
                .isRegistered(true)
                .build());

    }

    @Override
    public AuthResponse signIn(UserProfileDto userProfileDto) {

        ValidatorUtil.builder()
                .param("Username", userProfileDto.getUsername()).notNull()
                .param("Password", userProfileDto.getPassword()).notNull();

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userProfileDto.getUsername(), userProfileDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();




        return null;
    }

}
