package com.adel.flightschedule.userprofile.service;

public interface OTPService {

    Integer generateOTP(final Long data);

    boolean isValidOTP(final Long data, final Integer otp);

}
