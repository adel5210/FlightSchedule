package com.adel.flightschedule;

import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.service.EmailService;
import com.adel.flightschedule.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitTestRunService implements CommandLineRunner {

//    private final UserProfileService userProfileService;

    @Override
    public void run(String... args) throws Exception {

//        userProfileService.signUp(UserProfileDto.builder()
//                .email("axsadek@gmail.com")
//                .firstName("Adel")
//                .lastName("Albediwy")
//                .password("1234")
//                .build());

    }
}
