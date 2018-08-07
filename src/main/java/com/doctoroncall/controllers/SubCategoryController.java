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
import com.doctoroncall.entities.SubCategory;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.SubCategoryRepository;
import com.doctoroncall.services.SubCategoryService;

@Controller
@RequestMapping("subcategory")
public class SubCategoryController extends BaseController {

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	private static final String PATH = "subcategory";

	@GetMapping({ "/list" })
	public String subCategoryList(Model model, @RequestParam(defaultValue = "") String name, Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Page<SubCategory> list = subCategoryRepository.findAll( pageable);
		model.addAttribute("subcategories", list);

		return PATH + "/list";
	}



	@GetMapping("/add")
	public String subcategoryAdd(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		model.addAttribute("subCategory", new SubCategory());

		return PATH + "/add";
	}

	@PostMapping("/add")
	String subcategoryAdd(@Valid SubCategory subCategory, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		subCategoryService.save(subCategory);

		return "redirect:/" + PATH + "/list";
	}

	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		SubCategory subCategory = subCategoryService.findOne(id);
		if (subCategory == null) {
			subCategory = new SubCategory();
		}
		model.addAttribute("subCategory", subCategory);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid SubCategory subCategory, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		SubCategory subCategoryOld = subCategoryService.findOne(id);
		subCategory.setId(subCategoryOld.getId());

		subCategoryService.save(subCategory);

		return "redirect:/" + PATH + "/view/" + subCategory.getId();
	}

	@GetMapping(value = "/{id}/delete")
	public String delete(@PathVariable long id) {
		subCategoryService.deletesubcategory(id);
		return ("redirect:/" + PATH + "/list");
	}
}
