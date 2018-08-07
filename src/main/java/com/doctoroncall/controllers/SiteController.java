package com.doctoroncall.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.User;
import com.doctoroncall.push.AndroidPushNotificationsService;
import com.doctoroncall.services.EmailService;
import com.doctoroncall.services.UserService;

@Controller
public class SiteController extends BaseController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(AndroidPushNotificationsService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@RequestMapping({ "/", "/home" })
	String index() {

		User user = getLoggedinUser();

		if (user == null || user.isPatient())
			return "site/index";

		if (user.isAdmin()) {
			return "redirect:/admin/home";
		} else if (user.isModerator()) {
			return "redirect:/moderator/home";
		}
		return "redirect:/doctor/home";

	}

	@GetMapping(value = { "/login" })
	String login(Model model, @RequestParam(required = false) String error) {

		User user = getLoggedinUser();

		if (user != null) {
			return "redirect:/";
		}
		if (error != null)
			model.addAttribute("error", "Your username and password are invalid.");

		return "site/login";
	}

	@PostMapping(value = { "/login" })
	String loginPost(@RequestParam String username, @RequestParam String password) {

		User user = getLoggedinUser();
		if (user != null) {
			logger.debug("User successfully authenticated");
		}
		return "redirect:/";

	}

	@RequestMapping("/logout")
	String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.clearContext();

		return "redirect:/";
	}

	@GetMapping("/forgot")
	String forgotForm() {
		return "site/forgot";
	}

	@PostMapping("/forgot")
	String forgot(Model model, @RequestParam String email, HttpServletRequest request) {

		User user = userService.findByEmail(email.trim());

		if (user != null) {
			user.setConfirmationToken();

			userService.save(user);
			String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			String resetUrl = serverUrl + "/verification?token=" + user.getConfirmationToken();
			model.addAttribute("resetUrl", resetUrl);
			emailService.sendPasswordResetMail(email.trim(), resetUrl);

			model.addAttribute("success", "Link has been  sent to your email.");

		}

		else {

			model.addAttribute("error", "We could not find an account for that e-mail address.");

		}
		return "site/forgot";
	}

	@GetMapping("/signup")
	String register(Model model) {
		model.addAttribute("user", new User());
		return "site/signup";
	}

	@PostMapping("/signup")
	String signup(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "site/signup";
		}
		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);

			return "site/signup";

		}
		userService.createUser(user);

		return "redirect:/login";
	}

	@RequestMapping("/verification")
	String verification(@PathVariable String token, Model model) {

		User user = userService.findByConfirmationToken(token);

		if (user != null) {
			user.setConfirmationToken(null);
			userService.save(user);
			model.addAttribute("message", "Email verification successful");
			return "redirect:/";
		}

		model.addAttribute("message", "Email verification failed");

		return "site/verification";
	}

	@RequestMapping("/access-denied")
	String denied(Model model) {
		model.addAttribute("code", "403");
		return "site/error";
	}

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	@ResponseBody
	public Map<String, Object> handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("code", "404");
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("code", "500");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status.toString());
		return map;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	private static final String DIRECTORY = "C:/PDF";
	private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";

	@RequestMapping(value = "download", method = RequestMethod.GET)
	public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"doc.pdf\"");
		InputStream inputStream = new FileInputStream(new File("/home/local/TOXSL/devendra.kumar/Desktop/doc.pdf"));
		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				System.out.println("Writing some bytes..");
				outputStream.write(data, 0, nRead);
			}
		};
	}
}
