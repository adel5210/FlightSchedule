package com.adel.flightschedule.userprofile.service;

import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import com.adel.flightschedule.utils.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileDaoRepository userProfileDaoRepository;
    private final OTPService otpService;
    private final EmailService emailService;

    @Override
    public void signUp(final UserProfileDto userProfileDto) {

        ValidatorUtil.builder()
                .param("First name", userProfileDto.getFirstName()).notNull()
                .param("Last name", userProfileDto.getLastName()).notNull()
                .param("Email", userProfileDto.getEmail()).notNull()
                .param("Password", userProfileDto.getPassword()).notNull();

        final UserProfileDao profileDao = userProfileDaoRepository.saveAndFlush(UserProfileDao.builder()
                .email(userProfileDto.getEmail())
                .firstName(userProfileDto.getFirstName())
                .lastName(userProfileDto.getLastName())
                .password(userProfileDto.getPassword()) //to be encrypt
                .isRegistered(false)
                .build());

        final Integer generatedOtp = otpService.generateOTP(profileDao.getId());
        final StringBuilder message = new StringBuilder("Hello ").append(userProfileDto.getFirstName()).append(", \n")
                .append("Please enter the following OTP to the site.\n\n")
                .append("Your verification code: ").append(generatedOtp);

        emailService.sendMessage(userProfileDto.getEmail(),
                "Dummy FlightSchedule",
                message.toString());

    }

}
