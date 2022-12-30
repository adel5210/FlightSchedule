package com.adel.flightschedule.userprofile.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"password"})
public class UserProfileDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer otp;
    private String username;

}
