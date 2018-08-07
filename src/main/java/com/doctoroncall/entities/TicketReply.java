package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class TicketReply extends BaseEntity {

	@NotEmpty
	private String reply;

	@OneToOne
	private Ticket ticket;

	@OneToOne
	private User user;

	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public TicketReply() {

	}

	public TicketReply(String reply) {
		super();
		this.reply = reply;
	}

	public TicketReply(String reply, Ticket ticket, User user) {
		super();
		this.reply = reply;
		this.ticket = ticket;
		this.user = user;
	}

	
}
