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
import com.doctoroncall.entities.Category;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.CategoryRepository;
import com.doctoroncall.services.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	private static final String PATH = "category";



	@GetMapping({ "/", "/list" })
	public String list(Model model, @RequestParam(defaultValue = "") String name,Pageable pageable) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		Page<Category> list = categoryRepository.findAll(pageable);
		model.addAttribute("categories", list);

		return PATH + "/list";
	}

	@GetMapping("/add")
	public String addCategory(Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		model.addAttribute("category", new Category());

		return PATH + "/add";

	}

	@PostMapping("/add")
	String add(@Valid Category category, BindingResult bindingResult, Model model) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		categoryService.save(category);

		return "redirect:/" + PATH + "/list";

	}

	@GetMapping("/update")
	String add(Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";
		Category category = categoryService.findOne(id);
		if (category == null) {
			category = new Category();
		}
		model.addAttribute("category", category);
		return PATH + "/add";
	}

	@PostMapping("/update")
	String add(@Valid Category category, BindingResult bindingResult, Model model, @RequestParam Long id) {
		User user = getLoggedinUser();

		if (user == null || !user.isAdmin())
			return "/";

		if (bindingResult.hasErrors()) {
			return PATH + "/add";
		}

		Category categoryOld = categoryService.findOne(id);
		category.setId(categoryOld.getId());

		categoryService.save(category);

		return "redirect:/" + PATH + "/view/" + category.getId();
	}
	 @GetMapping(value = "/{id}/delete")
	    public String delete(@PathVariable long id) {
	        categoryService.deletecategory(id);
	        return( "redirect:/" + PATH + "/list");
	    }
}
