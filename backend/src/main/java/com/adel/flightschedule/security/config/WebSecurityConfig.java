package com.adel.flightschedule.security.config;

import com.adel.flightschedule.security.service.JwtAuthenticationEntryPoint;
import com.adel.flightschedule.security.service.JwtRequestFilter;
import com.adel.flightschedule.security.service.JwtUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtUserDetailService jwtUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(jwtUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final List<String> permittedHttp = new ArrayList<>();
//        permittedHttp.add("/api/v1/auth/signin");
//        permittedHttp.add("/api/v1/auth/signout");
//        permittedHttp.add("/api/v1/auth/signup");
//        permittedHttp.add("/api/v1/auth/resend-otp");
//        permittedHttp.add("/api/v1/auth/validate-otp");
//        permittedHttp.add("/api/v1/auth/refresh-token");
//        permittedHttp.add("/api/v1/auth/reset-password");
        permittedHttp.add("/api/v1/auth/**");
        final List<String> authorizedHttps = new ArrayList<>();
        authorizedHttps.add("/api/v1/aviation/**");

        http
                .cors().and()
                .csrf().disable()
                .headers().frameOptions().deny().and()
                .authorizeHttpRequests()
//                .requestMatchers(permittedHttp.toArray(new String[0])).permitAll()
                .requestMatchers(authorizedHttps.toArray(new String[0])).authenticated()
                .anyRequest().permitAll().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.authenticationProvider(daoAuthenticationProvider());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
