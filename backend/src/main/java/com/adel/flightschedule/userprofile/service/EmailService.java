package com.adel.flightschedule.userprofile.service;

public interface EmailService {

    void sendMessage(final String toEmail,
                     final String subject,
                     final String message);

}
