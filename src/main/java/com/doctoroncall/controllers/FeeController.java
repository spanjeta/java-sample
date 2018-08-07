package com.doctoroncall.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.doctoroncall.entities.CityFee;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.CityFeeService;

@Controller
@RequestMapping("/fee")
public class FeeController extends BaseController {
	
	@Autowired
	private CityFeeService cityfeeService;

	private static final String PATH = "fee";

	@GetMapping({ "/", "/list" })
	public String list(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Page<CityFee> list = cityfeeService.findAll(name,pageable);
		model.addAttribute("cityfees", list);

		return PATH + "/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		model.addAttribute("cityfee", new CityFee());

		return PATH + "/add";
	}

	@PostMapping("/add")
	String add(@Valid CityFee cityfee, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		cityfeeService.save(cityfee);

		return "redirect:/" + PATH + "/list";

	}

	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		CityFee cityfee = cityfeeService.findOne(id);
		if (cityfee == null) {
			cityfee = new CityFee();
		}
		model.addAttribute("cityfee", cityfee);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid CityFee cityfee, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		CityFee cityfeeOld = cityfeeService.findOne(id);
		cityfee.setId(cityfeeOld.getId());

		cityfeeService.save(cityfee);

		return "redirect:/" + PATH + "/view/" + cityfee.getId();
	}

	@GetMapping("/view")
	public String view(Model model, @RequestParam Long id) {
		model.addAttribute("cityfee", cityfeeService.findOne(id));
		return PATH + "/view";
	}
	 @GetMapping(value = "/{id}/delete")
	    public String delete(@PathVariable long id) {
		 cityfeeService.deletefee(id);
	        return( "redirect:/" + PATH + "/list");
	    }
}
