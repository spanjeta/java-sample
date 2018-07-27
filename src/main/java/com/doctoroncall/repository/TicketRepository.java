package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Ticket;
import com.doctoroncall.entities.User;

public interface TicketRepository extends JpaRepository<Ticket, Long>{


	Iterable<Ticket> findAllByTitle(String title);

	Iterable<Ticket> findAllByTitleAndUser(String title, User user);

	Page<Ticket> findByTitleLike(String name, Pageable pageable);

	

	

	

	

	

	
	
}
