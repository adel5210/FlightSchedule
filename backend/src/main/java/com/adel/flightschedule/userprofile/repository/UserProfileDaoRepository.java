package com.adel.flightschedule.userprofile.repository;

import com.adel.flightschedule.userprofile.model.UserProfileDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileDaoRepository extends JpaRepository<UserProfileDao, Long>, JpaSpecificationExecutor<UserProfileDao> {
    @Query("select u from UserProfileDao u where u.email = ?1")
    UserProfileDao findByEmail(String email);


}