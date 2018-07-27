package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.TicketReply;
import com.doctoroncall.repository.TicketReplyRepository;
@Service
public class TicketReplyService {

	@Autowired
	private TicketReplyRepository ticketReplyRepository;

	public  TicketReply findOne(Long id) {
		return ticketReplyRepository.findOne(id);
	}

	
	
	
	
}
