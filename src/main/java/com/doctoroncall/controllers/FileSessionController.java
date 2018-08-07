package com.doctoroncall.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.FileSession;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.FileSessionService;

@Controller
public class FileSessionController  extends BaseController{
	
	@Autowired
	private FileSessionService fileSessionService;
	
	private static final String PATH = "filesession";
	@GetMapping("/filesession")
	public String fileSession(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";

		model.addAttribute("filesesssion", new FileSession());

		return PATH + "/file-session";

	}
	
	@PostMapping("/filesession")
	String filesession(@Valid FileSession filesesssion, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/file-session";
		}

		fileSessionService.save(filesesssion);

		return "redirect:/filesession";

	}

}
