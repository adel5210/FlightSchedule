package com.adel.flightschedule.userprofile.repository;

import com.adel.flightschedule.userprofile.model.UserProfileDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserProfileDaoRepository extends JpaRepository<UserProfileDao, Long>, JpaSpecificationExecutor<UserProfileDao> {
}