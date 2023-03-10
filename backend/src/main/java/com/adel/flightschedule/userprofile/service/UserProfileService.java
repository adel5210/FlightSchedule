package com.adel.flightschedule.userprofile.service;

import com.adel.flightschedule.security.dto.AuthResponse;
import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;

public interface UserProfileService {

    void signUp(final UserProfileDto userProfileDto) throws UserProfileException;

    void sendOTP(final UserProfileDto userProfileDto) throws UserProfileException;

    void validateOtp(final UserProfileDto userProfileDto) throws UserProfileException;

    AuthResponse signIn(UserProfileDto userProfileDto) throws UserProfileException;

    AuthResponse signOut(UserProfileDto userProfileDto) throws UserProfileException;

    AuthResponse refreshToken(UserProfileDto userProfileDto) throws UserProfileException;

    void resetPassword(UserProfileDto userProfileDto) throws UserProfileException;
}
