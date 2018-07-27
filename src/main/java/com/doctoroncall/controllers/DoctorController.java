package com.doctoroncall.controllers;

import java.util.Map;

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
import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.AuthCode;
import com.doctoroncall.entities.CallLog;
import com.doctoroncall.entities.Doctor;
import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.User;
import com.doctoroncall.errors.ForbiddenException;
import com.doctoroncall.push.AndroidPushNotificationsService;
import com.doctoroncall.repository.RoleRepository;
import com.doctoroncall.services.AppointmentService;
import com.doctoroncall.services.AuthCodeService;
import com.doctoroncall.services.CallLogService;
import com.doctoroncall.services.DoctorService;
import com.doctoroncall.services.TokboxService;
import com.doctoroncall.services.UserService;

@Controller
@RequestMapping("doctor")
public class DoctorController extends BaseController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TokboxService tokboxService;

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private CallLogService callLogService;

	@Autowired
	private AuthCodeService authCodeService;

	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;

	private static final String PATH = "doctor";

	@RequestMapping({ "/", "/home" })
	String homes() throws ForbiddenException {
		User doctor = getLoggedinUser();
		if (doctor == null || !doctor.isDoctor()) {
			throw new ForbiddenException();
		}
		return PATH + "/index";

	}

	@GetMapping("/videocall/{id}")
	public String videocall(Model model, @PathVariable Long id) {

		User doctor = getLoggedinUser();

		Appointment appointment = appointmentService.findOne(id);

		User patientUser = appointment.getPatient();

		Map<String, Object> data = tokboxService.setup();
		CallLog calllog = new CallLog(tokboxService.getToken(), tokboxService.getSessionId());
		calllog.setPatient(patientUser);
		calllog.setDoctor(doctor);
		callLogService.save(calllog);
		data.put("id", calllog.getId());

		Iterable<AuthCode> clients = authCodeService.findAllByUser(patientUser);
		for (AuthCode authCode : clients) {
			androidPushNotificationsService.sendTo(authCode.getDeviceId(), calllog.getId(),
					"Call from :" + doctor.getName());
		}

		model.addAllAttributes(data);
		return "doctor/video-call";
	}

	@GetMapping("/videocall-end")
	public String videocallend(@RequestParam Long id) {

		CallLog calllog = callLogService.findOne(id);
		if (calllog != null) {
			calllog.setStateId(CallLog.STATE_DELETED);
			callLogService.save(calllog);
		}

		return "redirect:/doctor/generate-report?id=" + id;
	}

	@GetMapping("/revenue")
	String revenue() {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		return PATH + "/revenue";
	}

	@GetMapping("/reshedule")
	String reshedule() {

		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		return PATH + "/reshedule";

	}

	@GetMapping("/edit-profile")
	String editProfile(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		model.addAttribute("user", user);
		return PATH + "/editprofile";
	}

	@PostMapping("/edit-profile")
	String editProfile(@Valid User userProfile, BindingResult bindingResult) {

		User user = getLoggedinUser();

		userProfile.setId(user.getId());
		if (bindingResult.hasErrors()) {
			return "/doctor/edit-profile";
		}

		User userProfileUpdated = userService.save(userProfile);

		return "redirect:/user/profile";
	}

	@RequestMapping("/assign")
	String assignDoctor(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		Role role = roleRepository.findByName("DOCTOR");
		model.addAttribute("doctors", userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable));
		return PATH + "/assign";
	}

	@RequestMapping("/session")

	public String session(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		model.addAttribute("session", userService.findByName(name, pageable));
		return PATH + "/session";

	}

	@RequestMapping("/generate-report")
	String generateReport() {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";

		return PATH + "/generatereport";
	}

	@GetMapping("/patient-profile")
	public String patientProfile() {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";

		return PATH + "/profile";
	}

	@GetMapping("/diagnostic")
	public String diagnostic(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Role role = roleRepository.findByName("PATIENT");
		// model.addAttribute("patients", userService.findByNameAndRole(name, role));
		model.addAttribute("doctors", userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable));
		return PATH + "/diagnostic";

	}

	// Consulting Doctor
	@GetMapping("/consulting")
	public String consulting(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Role role = roleRepository.findByName("DOCTOR");
		model.addAttribute("doctors", userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable));
		return PATH + "/consulting";

	}

	// Report Not filled
	@GetMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		Role role = roleRepository.findByName("DOCTOR");
		model.addAttribute("doctors", userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable));
		return PATH + "/list";
	}

	@RequestMapping("/view")
	public String view(Model model, @RequestParam(defaultValue = "") Long id) {
		model.addAttribute("doctor", userService.findOne(id));
		return PATH + "/profile";
	}

	@GetMapping("/add")
	String add(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		model.addAttribute("doctor", new User());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid User doctor, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}
		Role role = roleRepository.findByName("DOCTOR");
		userService.createUser(doctor, role);
		return "redirect:/" + PATH + "/list";
	}

	@GetMapping("/{id}/details")
	public String details(Model model, @PathVariable Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		model.addAttribute("user", id);
		model.addAttribute("doctor", new Doctor());
		return PATH + "/details";
	}

	@PostMapping("/{id}/details")
	String details(@Valid Doctor doctor, BindingResult bindingResult, Model model, @PathVariable Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		User userDoctor = userService.findOne(id);
		doctor.setUser(userDoctor);
		if (bindingResult.hasErrors()) {
			return PATH + "/details";
		}

		doctorService.save(doctor);
		return "redirect:/" + PATH + "/list";

	}

	@GetMapping("/update")
	String update(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		User doctor = userService.findOne(id);
		model.addAttribute("doctor", doctor);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String update(@Valid User doctor, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		userService.save(doctor);
		return "redirect:/" + PATH + "/list" + doctor.getId();
	}

	@GetMapping(value = "/{id}/delete")
	public String delete(@PathVariable long id) {
		userService.deleteuser(id);
		return ("redirect:/" + PATH + "/list");
	}
}
