package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.FileSession;
import com.doctoroncall.repository.FileSessionRepository;
@Service
public class FileSessionService {

	@Autowired
	private FileSessionRepository fileSessionRepository;

	public void save(FileSession fileSession) {
		fileSessionRepository.save(fileSession);
		
	}
	
	
}
