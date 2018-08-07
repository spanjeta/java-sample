package com.doctoroncall.controllers;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.TimeSlot;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.TimeSlotService;

@Controller
@RequestMapping("/slot")
public class TimeSlotController extends BaseController {

	@Autowired
	private TimeSlotService timeSlotService;

	private static final String PATH = "slot";

	@GetMapping({ "/list" })
	public String list(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		Iterable<TimeSlot> list = timeSlotService.findAll(name, pageable);
		model.addAttribute("timeslots", list);

		return PATH + "/list";
	}

	@GetMapping("/add")
	public String add(Model model, @RequestParam(required = false) Date date, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		if (user == null) {
			throw new InternalAuthenticationServiceException("Unable to authenticate  User for provided credentials");
		}

		if (date == null)
			date = new Date();

		TimeSlot timeSlot = null;
		if (timeSlot == null)
			timeSlot = new TimeSlot();

		timeSlot.setDate(date);
		model.addAttribute("timeSlot", timeSlot);

		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid TimeSlot timeSlot, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		timeSlotService.save(timeSlot);

		return "redirect:/" + PATH + "/list";
	}

	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		TimeSlot timeslot = timeSlotService.findOne(id);
		if (timeslot == null) {
			timeslot = new TimeSlot();
		}
		model.addAttribute("timeslot", timeslot);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid TimeSlot timeSlot, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		TimeSlot timeSlotOld = timeSlotService.findOne(id);
		timeSlot.setId(timeSlotOld.getId());

		timeSlotService.save(timeSlot);

		return "redirect:/" + PATH + "/view/" + timeSlot.getId();
	}

	@GetMapping("/view")
	public String view(Model model, @RequestParam Long id) {
		model.addAttribute("timeslot", timeSlotService.findOne(id));
		return PATH + "/view";
	}

}
