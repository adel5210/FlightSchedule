package com.adel.flightschedule.userprofile.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Table(name = "user_profile", indexes = {
        @Index(name = "user_profile_username_key", columnList = "email", unique = true)
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class UserProfileDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "last_timestamp")
    private OffsetDateTime lastTimestamp;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_registered")
    private Boolean isRegistered;

    @Column(name = "username")
    private String username;

}