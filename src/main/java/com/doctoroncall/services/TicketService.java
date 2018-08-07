package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Ticket;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public void save(Ticket ticket) {
		ticketRepository.save(ticket);

	}

	public Iterable<Ticket> findAll() {

		return ticketRepository.findAll();
	}

	public Iterable<Ticket> findByTitle(String title) {
		return ticketRepository.findAllByTitle(title);
	}

	public Ticket findOne(Long id) {
		return ticketRepository.findOne(id);
	}

	public Iterable<Ticket> findByTitleAndUser(String title, User user) {

		return ticketRepository.findAllByTitleAndUser(title, user);
	}

	public Page<Ticket> findAll(String name, Pageable pageable) {
		return ticketRepository.findByTitleLike("%" + name + "%", pageable);
	}

	public void deleteticket(Long id) {
		// TODO Auto-generated method stub
		ticketRepository.delete(id);
	}

	

	


	

	


	
}
