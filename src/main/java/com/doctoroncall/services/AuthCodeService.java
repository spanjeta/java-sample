package com.doctoroncall.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.AuthCode;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.AuthCodeRepository;

@Service
public class AuthCodeService {

	@Autowired
	private AuthCodeRepository authCodeRepository;

	public User findUserByToken(String token) {

		AuthCode authCode = authCodeRepository.findByTokenAndStateId(token, AuthCode.STATE_ACTIVE);
		if (authCode == null) {
			return null;

		}
		return authCode.getUser();
	}

	public AuthCode save(AuthCode authCode) {
		return authCodeRepository.save(authCode);

	}

	public AuthCode findByToken(String token) {
		return authCodeRepository.findByTokenAndStateId(token, AuthCode.STATE_ACTIVE);
	}

	public void delete(AuthCode authCode) {
		authCodeRepository.delete(authCode);
	}

	public Iterable<AuthCode> findAllByUser(User patientUser) {
		return authCodeRepository.findAllByUser(patientUser);
	}

	public AuthCode findByDevice(String device) {
		return authCodeRepository.findByDeviceIdAndStateId(device, AuthCode.STATE_ACTIVE);

	}
	@Transactional
	public void deleteByDevice(String device) {
		AuthCode authCode = authCodeRepository.findByDeviceIdAndStateId(device, AuthCode.STATE_ACTIVE);
		if (authCode != null) {
			authCode.setStateId(AuthCode.STATE_DELETED);
			authCodeRepository.save(authCode);
		}

	}

	public Iterable<AuthCode> findAll() {
		return authCodeRepository.findAll();
	}

}
