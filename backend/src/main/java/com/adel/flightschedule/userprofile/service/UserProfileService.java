package com.adel.flightschedule.userprofile.service;

import com.adel.flightschedule.userprofile.dto.UserProfileDto;
import com.adel.flightschedule.userprofile.exception.UserProfileException;

public interface UserProfileService {

    void signUp(final UserProfileDto userProfileDto);

    void validateRegistration(final UserProfileDto userProfileDto) throws UserProfileException;

}
