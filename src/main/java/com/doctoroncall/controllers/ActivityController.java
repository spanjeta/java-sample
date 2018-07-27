package com.doctoroncall.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.Activity;
import com.doctoroncall.entities.Request;
import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.RoleRepository;
import com.doctoroncall.services.ActivityService;
import com.doctoroncall.services.CategoryService;
import com.doctoroncall.services.DisorderService;
import com.doctoroncall.services.RequestService;
import com.doctoroncall.services.SubCategoryService;

@Controller
@RequestMapping("/activity")

public class ActivityController extends BaseController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ActivityService activityService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private DisorderService disorderService;

	private static final String PATH = "activity";

	@GetMapping({ "/", "/list" })
	public String list(Model model, @RequestParam(defaultValue = "")  String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Page<Activity> list = activityService.findAll(name,pageable);
		model.addAttribute("activities", list);

		return PATH + "/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		model.addAttribute("activity", new Activity());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subcategories", subCategoryService.findAll());
		model.addAttribute("disorders", disorderService.findAll());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid Activity activity, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		activityService.save(activity);

		return "redirect:/" + PATH + "/list";
	}

	@GetMapping("/report")
	public String report(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		Role role = roleRepository.findByName("PATIENT");
		model.addAttribute("patients",
				userService.findByNameAndRoleAndStateId(name, role, User.STATE_ACTIVE, pageable));
		// model.addAttribute("patients", userService.findByName(name));
		return PATH + "/reportlist";
	}

	@GetMapping("/new-activity")
	String newActivity(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		Page<Request> list = requestService.findAlll(name, pageable);
		model.addAttribute("request", list);
		// model.addAttribute("request", requestService.findAlll(name,pageable));
		return PATH + "/add-activity";

	}
//	@GetMapping("/new-activity")
//	String newActivity(Model model) {
//
//		model.addAttribute("request", requestService.findAll());
//		return PATH + "/add-activity";
//
//	}


	

	@GetMapping("/request")
	String requestactivity(Model model) {
		model.addAttribute("request", new Request());
		return PATH + "/request";

	}

	@PostMapping("/request")
	String add(@Valid Request request, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return PATH + "/request";
		}

		requestService.save(request);

		return "redirect:/" + PATH + "/new-activity";

	}

	@GetMapping("/assign")
	public String assign(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";

		model.addAttribute("assign", new Activity());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subcategories", subCategoryService.findAll());
		model.addAttribute("disorders", disorderService.findAll());
		return PATH + "/assign";
	}

	@PostMapping("/assign")
	String assign(@Valid Activity activity, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isDoctor())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		activityService.save(activity);

		return "redirect:/filesession";
	}

	@RequestMapping("/add")
	public String demo() {
		return "doctor/assign";
	}
}
