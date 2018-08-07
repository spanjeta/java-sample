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
@RequestMapping("session")
public class SessionController  extends BaseController{
	@Autowired
	private RoleRepository roleRepository;

	

	private static final String PATH = "session";
	// Session Report
		@GetMapping("/report")
		public String report(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
			User user = getLoggedinUser();

			if (user == null || !user.isModerator())
				return "/";
			Role role2 = roleRepository.findByName("PATIENT");

			model.addAttribute("report", userService.findByName(name,pageable));
			return PATH + "/report";
		}
}
