package com.adel.flightschedule.userprofile.controller;

import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;
import com.adel.flightschedule.userprofile.service.UserProfileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserProfileService userProfileService;

    @PostMapping("/api/v1/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserProfileDto userProfileDto){
        log.info("SignUp for user: "+userProfileDto);
        userProfileService.signUp(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/api/v1/otp")
    public ResponseEntity<Void> otp(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("Check OTP for user: "+userProfileDto);
        userProfileService.validateRegistration(userProfileDto);
        return ResponseEntity.ok(null);
    }

}
