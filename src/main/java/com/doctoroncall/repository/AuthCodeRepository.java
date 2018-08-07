package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.AuthCode;
import com.doctoroncall.entities.User;


public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {

	AuthCode findByToken(String token);

	AuthCode findByUser(User patientUser);

	void deleteByDeviceId(String device);

	Iterable<AuthCode> findAllByUser(User patientUser);

	AuthCode findByDeviceId(String device);

	AuthCode findByTokenAndStateId(String token, Integer stateId);

	AuthCode findByDeviceIdAndStateId(String device, Integer stateId);

}
