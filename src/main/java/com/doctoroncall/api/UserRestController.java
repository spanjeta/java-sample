package com.doctoroncall.api;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.AuthCode;
import com.doctoroncall.entities.CallLog;
import com.doctoroncall.entities.User;
import com.doctoroncall.push.AndroidPushNotificationsService;
import com.doctoroncall.services.CallLogService;

@RestController
@RequestMapping("api/user")
public class UserRestController extends BaseController {

	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;

	@Autowired
	private CallLogService callLogService;

	@GetMapping({ "/", "/list" })
	public Page<User> listUsers(Model model, Pageable pageable) {
		return userService.findAll(pageable);
	}

	@GetMapping("/profile")
	User profile(@RequestParam String token) {
		return authCodeService.findUserByToken(token);

	}

	@PostMapping("/changepassword")
	User changepassword(@RequestParam String token, @RequestParam String password) {
		User user = authCodeService.findUserByToken(token);

		userService.savePassword(user, password);
		return user;
	}

	@PostMapping(value = "/signup", consumes = "multipart/form-data")
	public Map<String, Object> signup(User model, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		if (model == null) {
			map.put("status", HttpStatus.BAD_REQUEST);
			map.put("url", request.getRequestURL().toString());
			return map;
		}
		User user = userService.findByNumber(model.getContactNumber());
		if (user != null) {
			map.put("status", HttpStatus.CONFLICT);
			map.put("error", "User is already exist");
			map.put("url", request.getRequestURL().toString());

			return map;

		}
		//model.setPassword("123456");

		model.setStateId(User.STATE_INACTIVE);

	/*	if (uploadfile != null) {
			String path = "./";
			String filename = uploadfile.getOriginalFilename();
			try {
				byte barr[] = uploadfile.getBytes();
				BufferedOutputStream bout = new BufferedOutputStream(
						new FileOutputStream(path + "/uploads/" + filename));
				bout.write(barr);
				bout.flush();
				bout.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}*/

		userService.createUser(model);

		map.put("status", HttpStatus.OK);

		map.put("profile", model);
		map.put("message", "Signup Successfully");

		map.put("url", request.getRequestURL().toString());
		return map;
	}

	@Transactional
	@RequestMapping("/login")
	Map<String, Object> login(@RequestParam String username, @RequestParam String password, @RequestParam String device,
			HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.findByEmail(username);

		if (user != null && userService.authenticate(username, password)) {

			authCodeService.deleteByDevice(device);
			AuthCode authCode = new AuthCode(user, UUID.randomUUID().toString(), device);
			authCodeService.save(authCode);
			map.put("status", HttpStatus.OK);
			map.put("auth_code", authCode.getToken());

			map.put("profile", user);

			map.put("message", "Signup Successfully");

			return map;
		}
		map.put("status", HttpStatus.FORBIDDEN);

		map.put("message", "Signup Failed");

		return map;
	}

	@GetMapping("/logout")
	Map<String, Object> logout(@RequestParam String token, HttpServletRequest request) {
		AuthCode authCode = authCodeService.findByToken(token);

		authCodeService.delete(authCode);
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", HttpStatus.OK);
		map.put("message", "Logout Successful");

		map.put("url", request.getRequestURL().toString());
		return map;
	}

	@RequestMapping(value = "/check")
	public Map<String, Object> check(@RequestParam String token) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = authCodeService.findUserByToken(token);

		if (user != null) {

			map.put("status", HttpStatus.OK);
			map.put("user", user);
			map.put("token", token);
			return map;
		}

		map.put("status", HttpStatus.FORBIDDEN);
		return map;
	}

	@RequestMapping(value = "/call-log")
	public Map<String, Object> call(@RequestParam String token, @RequestParam(defaultValue = "0") Long id) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = authCodeService.findUserByToken(token);

		if (user == null) {

			map.put("status", HttpStatus.FORBIDDEN);
			return map;
		}

		CallLog callog;
		if (id != 0) {
			callog = callLogService.findOne(id);
		} else {
			callog = callLogService.findFirstByPatientAndStateIdOrderByIdDesc(user, CallLog.STATE_ACTIVE);
		}
		if (callog != null) {
			map.put("status", HttpStatus.OK);
			map.put("user", user);
			map.put("callog", callog);
			return map;
		}
		map.put("status", HttpStatus.NOT_FOUND);
		return map;

	}

	@GetMapping(value = "/test")
	public Map<String, Object> test(@RequestParam(required = false) String token) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = authCodeService.findUserByToken(token);

		Boolean status;
		if (user != null) {
			Iterable<AuthCode> authCodes = authCodeService.findAllByUser(user);
			for (AuthCode authCode : authCodes) {
				status = androidPushNotificationsService.sendTo(authCode.getDeviceId());
			}

			map.put("status", HttpStatus.OK);
			map.put("user", user);
			map.put("token", token);
			return map;
		} else {
			AndroidPushNotificationsService push = new AndroidPushNotificationsService();

			status = push.sendTo(
					"fQWsQ-oyHdI:APA91bECBOujcQtbF2ts9ZhBjAkwXwEkVc4__5GwGc5ihk0MrKNO35WzDEvY4xHMkV8_a78L-B8Z00NRpXCqZwrhn1w7Dh_Dzr0dy1klmFjKbj7f2gGl6oKQWpPlOmMI5lYeJcoaSIL3");

		}

		map.put("status", status ? HttpStatus.OK : HttpStatus.FORBIDDEN);
		return map;

	}
}
