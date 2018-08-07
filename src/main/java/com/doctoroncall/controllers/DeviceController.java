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
@RequestMapping("device")
public class DeviceController  extends BaseController{
	@Autowired
	private RoleRepository roleRepository;
	private static final String PATH = "device";
	
	@GetMapping("/details")
	String details() {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		return PATH + "/details";
	}
	
	
	@GetMapping("/nero-list")
	public String neroList(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role = roleRepository.findByName("PATIENT");

		model.addAttribute("devices", userService.findByName(name,pageable));
		return PATH + "/nero-list";
	}
}
