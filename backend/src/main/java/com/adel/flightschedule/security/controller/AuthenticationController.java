package com.adel.flightschedule.security.controller;

import com.adel.flightschedule.security.dto.AuthResponse;
import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;
import com.adel.flightschedule.userprofile.service.UserProfileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserProfileService userProfileService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserProfileDto userProfileDto){
        log.info("SignUp for user: "+userProfileDto);
        userProfileService.signUp(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<Void> resendOtp(@RequestBody UserProfileDto userProfileDto) {
        log.info("Resend OTP for user: "+userProfileDto);
        userProfileService.sendOTP(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<Void> validateOtp(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("Check OTP for user: "+userProfileDto);
        userProfileService.validateRegistration(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody UserProfileDto userProfileDto){
        return ResponseEntity.ok(userProfileService.signIn(userProfileDto));
    }

    @PostMapping("/signout")
    public ResponseEntity<AuthResponse> signOut(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        return ResponseEntity.ok(userProfileService.signOut(userProfileDto));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        return ResponseEntity.ok(userProfileService.refreshToken(userProfileDto));
    }

}
