package com.doctoroncall.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.User;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	
	@GetMapping({ "/", "/home" })
	String index(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		model.addAttribute("user", user);

		return "admin/index";
	}

	@GetMapping("/slot")
	String slot() {

		return "/admin/slot";

	}
	@GetMapping("/demo")
	String demo() {

		return "/doctor/reshedule-slot";

	}

	@GetMapping("/demo1")
	String demo1() {

		return "/doctor/reshedule-form";

	}

}
