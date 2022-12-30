package com.adel.flightschedule.userprofile.controller;

import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {



    @PostMapping("/api/v1/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserProfileDto userProfileDto){

        return ResponseEntity.ok(null);
    }

}
