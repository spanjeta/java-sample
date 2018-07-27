package com.doctoroncall.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.Ticket;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.TicketService;

@RequestMapping("api/support")
@RestController

public class SupportRestController extends BaseController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/add")
	Ticket add(@Valid Ticket ticket, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ticket;
		}
		User user = getLoggedinUser();
		if (user != null) {
			ticket.setUser(user);
		}

		ticketService.save(ticket);

		return ticket;
	}

	@GetMapping("/view/{id}")
	public Ticket view(Model model, @PathVariable Long id) {
		return ticketService.findOne(id);
	}

	@GetMapping("/list")
	public Iterable<Ticket> list(Model model, @RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "0") Integer page) {
		User user = getLoggedinUser();
		return ticketService.findByTitleAndUser(title,user);
	}

}
