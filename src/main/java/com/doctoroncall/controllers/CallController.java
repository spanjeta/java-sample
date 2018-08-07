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
@RequestMapping("call")
public class CallController extends BaseController{
	
	@Autowired
	private RoleRepository roleRepository;

	

	private static final String PATH = "call";
	@GetMapping("/list")
	// String upcoming() {

	public String list(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role = roleRepository.findByName("DOCTOR");
		//model.addAttribute("upcoming", userService.findByNameAndRoleAndStateId(name, role, User.STATE_INACTIVE));
		model.addAttribute("upcoming", userService.findByName(name,pageable));

		return PATH + "/list";
	}
	

}
