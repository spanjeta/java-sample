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
@RequestMapping("payment")
public class PaymentController extends BaseController {
	@Autowired
	private RoleRepository roleRepository;
	private static final String PATH = "payment";

	@GetMapping("/failed")
	public String failed(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role = roleRepository.findByName("DOCTOR");
		// model.addAttribute("fail", userService.findByNameAndRole(name, role));
		model.addAttribute("fail", userService.findByName(name,pageable));
		//model.addAttribute("fail", userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE));
		return PATH + "/failed";
	}


}
