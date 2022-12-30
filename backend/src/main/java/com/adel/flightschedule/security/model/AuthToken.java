package com.adel.flightschedule.security.model;

import com.adel.flightschedule.userprofile.model.UserProfileDao;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.OffsetDateTime;

@Table(name = "auth_token", indexes = {
        @Index(name = "auth_token_token_key", columnList = "token", unique = true),
        @Index(name = "auth_token_user_profile_id_key", columnList = "user_profile_id", unique = true)
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "last_timestamp")
    private OffsetDateTime lastTimestamp;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileDao userProfile;

    @Column(name = "token", nullable = false)
    private String refreshToken;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @Transient
    private String accessToken;


}