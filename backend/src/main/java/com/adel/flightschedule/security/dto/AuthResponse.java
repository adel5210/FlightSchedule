package com.adel.flightschedule.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class AuthResponse {

    private String username;
    private String accessToken;
    private String refreshToken;

}
