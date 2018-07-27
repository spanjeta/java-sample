package com.doctoroncall.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.Ticket;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.TicketService;

@Controller
@RequestMapping("ticket")
public class TicketController extends BaseController {
	
	@Autowired
	private TicketService ticketService;

	private static final String PATH = "ticket";

	@GetMapping({ "/", "/list" })
	public String list(Model model, @RequestParam(defaultValue = "")  String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Page<Ticket> list = ticketService.findAll(name,pageable);
		model.addAttribute("tickets", list);

		return PATH + "/list";
	}

	// @GetMapping({ "/", "/list" })
		// public String list(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		// 
		// User user = getLoggedinUser();
		//
		// if (user == null || !user.isAdmin())
		// return "/";
		//
		// Iterable<Ticket> list = ticketService.findAll(name, pageable);
		// model.addAttribute("tickets", list);
		//
		// return PATH + "/list";
		// }

	// Report Not filled
		
	@GetMapping("/add")
	public String add(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		model.addAttribute("ticket", new Ticket());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid Ticket ticket, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		ticket.setUser(user);
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		ticketService.save(ticket);

		return "redirect:/" + PATH + "/list";

	}

	@GetMapping("update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Ticket ticket = ticketService.findOne(id);
		if (ticket == null) {
			ticket = new Ticket();
		}
		model.addAttribute("ticket", ticket);
		return PATH + "/add";
	}

	@PostMapping("update")
	String add(@Valid Ticket ticket, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}
		Ticket ticketOld = ticketService.findOne(id);
		ticket.setId(ticketOld.getId());
		ticketService.save(ticket);

		return "redirect:/" + PATH + "/list/";
	}

	@GetMapping(value = "/{id}/delete")
	public String delete(@PathVariable long id) {
		ticketService.deleteticket(id);
		return ("redirect:/" + PATH + "/list");
	}

}
