package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.CityFee;
import com.doctoroncall.repository.CityFeeRepository;

@Service
public class CityFeeService {
	
	
	@Autowired
	private CityFeeRepository cityFeeRepository;
	
	
	
	public CityFee findOne(Long id) {
		return cityFeeRepository.findOne(id);
	}

	public void save(CityFee cityfee) {
		cityFeeRepository.save(cityfee);
		
	}
	
	public Iterable<CityFee> findAll() {
		
		return cityFeeRepository.findAll();
	}

	public Page<CityFee> findAll(String name, Pageable pageable) {
		return cityFeeRepository.findByCityLike("%" + name + "%",pageable);
	}

	public void deletefee(long id) {
		// TODO Auto-generated method stub
		cityFeeRepository.delete(id);
	}


	
	
	

}
