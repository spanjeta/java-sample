package com.doctoroncall.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextListener;

import com.doctoroncall.entities.User;
import com.doctoroncall.push.AndroidPushNotificationsService;
import com.doctoroncall.services.AuthCodeService;
import com.doctoroncall.services.UserService;

public class BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(AndroidPushNotificationsService.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	protected UserService userService;

	@Autowired
	protected AuthCodeService authCodeService;

	private User loggedinUser = null;

	public User getLoggedinUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		loggedinUser = userService.findByEmail(name);
		if (loggedinUser == null) {
			return null;
		}

		return loggedinUser;
	}

	public User getLoggedinUser(String token) {

		token = request.getParameter("token");

		if (loggedinUser == null) {

			loggedinUser = authCodeService.findUserByToken(token);
			if (loggedinUser == null) {
				throw new InternalAuthenticationServiceException(
						"Unable to authenticate  User for provided credentials");
			}
		}

		return loggedinUser;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
