package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.DeviceDetail;
import com.doctoroncall.repository.DeviceDetailRepository;
@Service
public class DeviceDetailService {
	@Autowired
	private DeviceDetailRepository deviceDetailRepository;
	public long count() {
		// TODO Auto-generated method stub
		return deviceDetailRepository.count();
	}

	public void save(DeviceDetail deviceDetail) {
		// TODO Auto-generated method stub
		deviceDetailRepository.save(deviceDetail);
	}

}
