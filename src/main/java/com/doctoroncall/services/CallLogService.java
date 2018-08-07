package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.CallLog;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.CallLogRepository;

@Service
public class CallLogService {

	@Autowired
	private CallLogRepository callLogRepository;
	
	public CallLog findOne(Long id) {
		return callLogRepository.findOne(id);
	}


	public void save(CallLog callLog) {
		callLogRepository.save(callLog);
		
	}
	
	public Iterable<CallLog> findAll() {
		return callLogRepository.findAll();
	}

	public CallLog findByPatientAndStateId(User user, Integer stateId) {
		return callLogRepository.findByPatientAndStateId(user,stateId);
	}

	public CallLog findFirstByOrderByIdDesc() {
		return callLogRepository.findFirstByOrderByIdDesc();
	}


	public CallLog findFirstByPatientAndStateIdOrderByIdDesc(User user, Integer stateId) {
		return callLogRepository.findFirstByPatientAndStateIdOrderByIdDesc(user,stateId);
	}
	
	
}
