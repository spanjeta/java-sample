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
import com.doctoroncall.entities.Disorder;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.DisorderRepository;
import com.doctoroncall.services.DisorderService;

@Controller
@RequestMapping("disorder")
public class DisorderController extends BaseController {

	@Autowired
	private DisorderRepository disorderRepository;
	@Autowired
	private DisorderService disorderService;
	private static final String PATH = "disorder";


	@GetMapping({ "/", "/list" })
	public String list(Model model, @RequestParam(defaultValue = "0") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Page<Disorder> list = disorderRepository.findAll(pageable);
		model.addAttribute("disorders", list);

		return PATH + "/list";
	}

	
	@GetMapping("/add")
	public String disorderAdd(Model model) {

		model.addAttribute("disorder", new Disorder());
		return PATH + "/add";
	}

	@PostMapping("/add")
	String disorderAdd(@Valid Disorder disorder, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		disorderService.save(disorder);

		return "redirect:/" + PATH + "/list";

	}
	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		Disorder disorder = disorderService.findOne(id);
		if (disorder == null) {
			disorder = new Disorder();
		}
		model.addAttribute("disorder", disorder);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid Disorder disorder, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		Disorder disorderOld = disorderService.findOne(id);
		disorder.setId(disorderOld.getId());

		disorderService.save(disorder);

		return "redirect:/" + PATH + "/view/" + disorder.getId();
	}
	 @GetMapping(value = "/{id}/delete")
	    public String delete(@PathVariable long id) {
	        disorderService.deletedisorder(id);
	        return( "redirect:/" + PATH + "/list");
	    }
}
