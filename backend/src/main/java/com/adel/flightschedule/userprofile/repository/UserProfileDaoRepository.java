package com.adel.flightschedule.userprofile.repository;

import com.adel.flightschedule.userprofile.model.UserProfileDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileDaoRepository extends JpaRepository<UserProfileDao, Long>, JpaSpecificationExecutor<UserProfileDao> {
    @Query("select u from UserProfileDao u where u.email = ?1")
    UserProfileDao findByEmail(String email);

    @Query("select u from UserProfileDao u where u.username = ?1")
    UserProfileDao findByUsername(String username);

    @Query("select u from UserProfileDao u where u.id = :id")
    UserProfileDao findByUserId(@Param("id") Long id);

    @Query("select u from UserProfileDao u " +
            "where u.username = :user " +
            "or u.email = :user")
    UserProfileDao findByUsernameOrEmail(@Param("user") String user);



}