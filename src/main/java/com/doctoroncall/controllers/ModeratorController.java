package com.doctoroncall.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.RoleRepository;
import com.doctoroncall.services.AppointmentService;
import com.doctoroncall.services.UserService;

@Controller
@RequestMapping("moderator")
public class ModeratorController extends BaseController {
	

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;

	private static final String PATH = "moderator";

	@GetMapping(value = { "/", "/home" })
	String index() {
		return PATH + "/index";
	}

	@GetMapping("/appointment")
	// public String appointment() {

	public String appointment(Model model, @RequestParam(defaultValue = "") Date date) {
		// Appointment appointment = appointmentRepository.findByDate("date");
		// model.addAttribute("patients", userService.findByNameAndRole(name, role));
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		model.addAttribute("appointments", appointmentService.findAll());

		return PATH + "/appointment";
	}

	@GetMapping("/edit-profile")
	String editProfile(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		model.addAttribute("user", user);
		return "moderator/editprofile";
	}
	
	

	@PostMapping("/edit-profile")
	String editProfile(@Valid User moderatorProfile, BindingResult bindingResult) {

		User user = getLoggedinUser();

		moderatorProfile.setId(user.getId());
		if (bindingResult.hasErrors()) {
			return "/moderator/editprofile";
		}

		User userProfileUpdated = userService.save(moderatorProfile);

		return "redirect:/user/profile";
	}
	@GetMapping("/mod-patient")
	public String modPatient(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role = roleRepository.findByName("PATIENT");
		// model.addAttribute("patients", userService.findByNameAndRole(name, role));
		model.addAttribute("modPatient", userService.findByName(name,pageable));

		return PATH + "/mod-patient-list";
	}

	@GetMapping("/mod-updation")
	public String modUpdation(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role = roleRepository.findByName("PATIENT");

		model.addAttribute("modupdation", userService.findByName(name,pageable));
		return PATH + "/mod-updation";
	}

	@GetMapping("/next-appointment")
	String nextAppointment() {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";

		return PATH + "/next-appointment";
	}

	@GetMapping("/patient-list")

	String patientList() {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		return PATH + "/patient-list";
	}

	@GetMapping("/reshedule")
	String reshedule() {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		return PATH + "/reshedule";
	}

	@GetMapping("/revenue")
	String revenue() {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		return PATH + "/revenue";
	}

	@GetMapping("/report")
	public String report(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role2 = roleRepository.findByName("PATIENT");

		model.addAttribute("report", userService.findByName(name,pageable));
		return PATH + "/session-report";
	}

	@GetMapping("/updation")

	public String updation(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role2 = roleRepository.findByName("PATIENT");

		model.addAttribute("users", userService.findByName(name,pageable));

		return PATH + "/updation";
	}

	@GetMapping("/performance")
	// String moderatorPerformanceList() {
	public String performance(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isModerator())
			return "/";
		Role role = roleRepository.findByName("PATIENT");
		// model.addAttribute("patients", userService.findByNameAndRole(name, role));
		model.addAttribute("patients", userService.findByName(name,pageable));

		return PATH + "/performance";
	}

	@GetMapping("/performance-list")
	public String analystPerformanceList(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Role role = roleRepository.findByName("PATIENT");
		model.addAttribute("patients", userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable ));
		return PATH + "/performance-list";

	}


	@GetMapping({ "/list" })
	public String list(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		Role role = roleRepository.findByName("MODERATOR");
		model.addAttribute("moderators",
				userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable ));
		return PATH + "/list";
		
	}
	
	

	@GetMapping("/add")
	String add(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		model.addAttribute("moderator", new User());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid User moderator, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {

			return PATH + "/add";
		}
		Role role = roleRepository.findByName("MODERATOR");
		userService.createUser(moderator, role);
		return "redirect:/" + PATH + "/list";

	}

	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		User moderator = userService.findOne(id);
		if (moderator == null) {
			moderator = new User();
		}
		model.addAttribute("moderator", moderator);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid User moderator, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		User moderatorOld = userService.findOne(id);
		moderator.setId(moderatorOld.getId());

		userService.save(moderator);

		return "redirect:/" + PATH + "/list/" + moderator.getId();
	}

	@GetMapping(value = "/{id}/delete")
	public String delete(@PathVariable long id) {
		userService.deleteuser(id);
		return ("redirect:/" + PATH + "/list");
	}
}
