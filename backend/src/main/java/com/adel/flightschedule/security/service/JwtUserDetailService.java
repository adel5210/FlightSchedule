package com.adel.flightschedule.security.service;

import com.adel.flightschedule.security.model.UserDetailsImpl;
import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserProfileDaoRepository userProfileDaoRepository;

    @Override
    public UserDetails loadUserByUsername(String userParam) throws UsernameNotFoundException {

        final UserProfileDao userProfile = userProfileDaoRepository.findByUsernameOrEmail(userParam);

        if (null == userProfile) {
            throw new UsernameNotFoundException("User not found :" + userParam);
        }

        return UserDetailsImpl.builder()
                .id(userProfile.getId())
                .username(userProfile.getUsername())
                .password(userProfile.getPassword())
                .authorities(Collections.emptyList())
                .email(userProfile.getEmail())
                .build();
    }
}
