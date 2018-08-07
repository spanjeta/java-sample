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
import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.AppointmentService;

@Controller
@RequestMapping("appointment")
public class AppointmentController extends BaseController {

	private static final String PATH = "appointment";

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/upcoming")

	public String appointment(Model model,@RequestParam(defaultValue = "") String name,Pageable pageable) {

		User doctor = getLoggedinUser();

		Page<Appointment> list = appointmentService.findAllByDoctor(doctor,pageable);
		model.addAttribute("appointments", list);

		return "appointment/upcoming";
	}

	@GetMapping({ "/list" })
	public String list(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {

		Iterable<Appointment> list = appointmentService.findAll(name, pageable);
		model.addAttribute("appointments", list);

		return PATH + "/list";
	}

	@GetMapping("/add")
	String add(Model model) {
		model.addAttribute("appointment", new Appointment());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid Appointment appointment, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		appointmentService.add(appointment);

		return "redirect:/" + PATH + "/view/" + appointment.getId();
	}


	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		Appointment appointment = appointmentService.findOne(id);
		if (appointment == null) {
			appointment = new Appointment();
		}
		model.addAttribute("appointment", appointment);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid Appointment appointment, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		Appointment appointmentOld = appointmentService.findOne(id);
		appointment.setId(appointmentOld.getId());

		appointmentService.save(appointment);

		return "redirect:/" + PATH + "/view/" + appointment.getId();
	}
	@GetMapping("/view")
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("appointment", appointmentService.findOne(id));
		return PATH + "/view";
	}

	@GetMapping("/next")

	public String nextAppointment(Model model, @RequestParam(defaultValue = "") String name) {
		User doctor = getLoggedinUser();

		model.addAttribute("appointment", appointmentService.findNext(doctor));

		return PATH + "/next";
	}


}
