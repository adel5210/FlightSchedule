package com.adel.flightschedule;

import com.adel.flightschedule.userprofile.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitTestRunService implements CommandLineRunner {

//    private final EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
//        emailService.sendMessage("axsadek@gmail.com",
//                "TEST_FLIGHT_SCHED",
//                "TEST123");
    }
}
