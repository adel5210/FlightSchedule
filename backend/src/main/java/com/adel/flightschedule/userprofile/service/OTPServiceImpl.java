package com.adel.flightschedule.userprofile.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPServiceImpl implements OTPService {

    private final Cache<Long, Integer> mainOtp = new Cache2kBuilder<Long, Integer>() {
    }
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    @Override
    public Integer generateOTP(@NonNull Long data) {
        final SecureRandom secureRandom = new SecureRandom();
        final int newOtp = secureRandom.nextInt(100000, 999999);
        mainOtp.put(data, newOtp);
        return newOtp;
    }

    @Override
    public boolean isValidOTP(@NonNull Long data, @NonNull Integer otp) {
        return mainOtp.removeIfEquals(data, otp);
    }
}
