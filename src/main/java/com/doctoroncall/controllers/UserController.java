package com.doctoroncall.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.User;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	private static final String PATH = "user";

	@GetMapping(value = { "/profile" })
	String profile(Model model) {

		User user = getLoggedinUser();

		if (user == null)
			return "redirect:/";
		model.addAttribute("user", user);
		if (user.isModerator()) {
			return "moderator/profile";
		} else if (user.isDoctor()) {
			return "doctor/profile";
		}
		return "user/profile";

	}

	@GetMapping("/edit-profile")
	String editProfile(Model model) {
		User user = getLoggedinUser();
		model.addAttribute("user", user);
		return "user/edit-profile";
	}

	@PostMapping("/edit-profile")
	String editProfile(@Valid User userProfile, BindingResult bindingResult) {

		User user = getLoggedinUser();

		userProfile.setId(user.getId());
		if (bindingResult.hasErrors()) {
			return "/user/edit-profile";
		}

		User userProfileUpdated = userService.save(userProfile);

		return "redirect:/user/profile";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		userService.save(user);

		return "redirect:/" + PATH + "/list";
		// return PATH + ;
	}

	@GetMapping("/list")
	public String listUsers(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		model.addAttribute("users", userService.findByName(name,pageable));
		return "user/list";
	}

	@GetMapping("/changepassword")
	String changepassword() {
		return "/";
	}
	
}
