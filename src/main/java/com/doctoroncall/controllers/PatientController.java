package com.doctoroncall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.RoleRepository;

@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {

	@Autowired
	private RoleRepository roleRepository;

	private static final String PATH = "patient";

	@GetMapping("/pending-list")
	public String pendingList(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		Role role = roleRepository.findByName("PATIENT");
		model.addAttribute("patients", userService.findByNameAndRoleAndStateId(name, role, User.STATE_INACTIVE,pageable));
		return PATH + "/pending-list";
	}

	@GetMapping("/requested-list")
	public String updation(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		Role role = roleRepository.findByName("PATIENT");
		// model.addAttribute("patients", userService.findByNameAndRole(name, role));
		//model.addAttribute("patients", userService.findByName(name));
		model.addAttribute("patients", userService.findByNameAndRoleAndStateId(name, role, User.STATE_INACTIVE,pageable));
		return PATH + "/requested-list";
	}

}
