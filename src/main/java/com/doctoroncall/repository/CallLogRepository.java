package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.CallLog;
import com.doctoroncall.entities.User;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {

	CallLog findByPatientAndStateId(User user, Integer stateId);

	CallLog findFirstByOrderByIdDesc();

	CallLog findFirstByPatientAndStateIdOrderByIdDesc(User user, Integer stateId);

}
