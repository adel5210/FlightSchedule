package com.adel.flightschedule.security.controller;

import com.adel.flightschedule.security.dto.AuthResponse;
import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;
import com.adel.flightschedule.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final UserProfileService userProfileService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("SignUp for user: " + userProfileDto);
        userProfileService.signUp(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<Void> resendOtp(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("Resend OTP for user: " + userProfileDto);
        userProfileService.sendOTP(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<Void> validateOtp(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("Check OTP for user: " + userProfileDto);
        userProfileService.validateOtp(userProfileDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("SignIn for user: " + userProfileDto);
        return ResponseEntity.ok(userProfileService.signIn(userProfileDto));
    }

    @PostMapping("/signout")
    public ResponseEntity<AuthResponse> signOut(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("SignOut for user: " + userProfileDto);
        return ResponseEntity.ok(userProfileService.signOut(userProfileDto));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("Refresh token for user: " + userProfileDto);
        return ResponseEntity.ok(userProfileService.refreshToken(userProfileDto));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody UserProfileDto userProfileDto) throws UserProfileException {
        log.info("Reset password for user: " + userProfileDto);
        userProfileService.resetPassword(userProfileDto);
        return ResponseEntity.ok(null);
    }

}
