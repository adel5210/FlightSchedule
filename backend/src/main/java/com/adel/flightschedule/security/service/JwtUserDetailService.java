package com.adel.flightschedule.security.service;

import com.adel.flightschedule.userprofile.model.UserProfileDao;
import com.adel.flightschedule.userprofile.repository.UserProfileDaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserProfileDaoRepository userProfileDaoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserProfileDao userProfile = userProfileDaoRepository.findByUsername(username);

        if(null == userProfile){
            throw new UsernameNotFoundException("User not found :"+username);
        }

        return new User(userProfile.getUsername(),
                        userProfile.getPassword(),
                        Collections.emptyList());
    }
}
