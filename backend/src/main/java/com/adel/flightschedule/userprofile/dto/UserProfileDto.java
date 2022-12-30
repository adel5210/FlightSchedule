package com.adel.flightschedule.userprofile.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
