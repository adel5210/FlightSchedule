package com.adel.flightschedule.security.repository;

import com.adel.flightschedule.security.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>, JpaSpecificationExecutor<AuthToken> {
}