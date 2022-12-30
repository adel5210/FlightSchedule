package com.adel.flightschedule.security.repository;

import com.adel.flightschedule.security.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>, JpaSpecificationExecutor<AuthToken> {
    @Modifying
    @Query("delete from AuthToken a where a.userProfile.username = :username")
    int deleteByUserProfile_Username(@Param("username") String username);

    @Query("select a from AuthToken a where a.token = :token")
    AuthToken findByToken(@Param("token") String token);


}