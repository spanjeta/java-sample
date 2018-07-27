package com.doctoroncall.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.TimeSlot;
import com.doctoroncall.entities.User;
import com.doctoroncall.services.AppointmentService;
import com.doctoroncall.services.AuthCodeService;
import com.doctoroncall.services.TimeSlotService;

@RestController
@RequestMapping("api/availability")
public class AvailabilityRestController extends BaseController {

	@Autowired
	private AuthCodeService authCodeService;

	@Autowired
	private AppointmentService appointmentService;


	@Autowired
	private TimeSlotService timeSlotService;

	@PostMapping(value = "/get-slot")
	public Map<String, Object> getAvailable(@RequestParam String token, @RequestParam(defaultValue = "") Date date) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = authCodeService.findUserByToken(token);

		if (user == null) {
			map.put("status", HttpStatus.FORBIDDEN);
			map.put("error", "Unauthorized User");

			return map;
		}

		Iterable<TimeSlot> list = timeSlotService.findByDate(date);
		if (list != null) {
			map.put("status", HttpStatus.OK);
			map.put("value", list);

		} else {
			map.put("status", HttpStatus.NOT_FOUND);
			map.put("error", "Slot Not Found");

		}
		return map;

	}

	@PostMapping(value = "/get-schedule-session")
	public Map<String, Object> getSession(@RequestParam String token, @RequestParam(defaultValue = "") String date) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = authCodeService.findUserByToken(token);

		if (user == null) {
			map.put("status", HttpStatus.FORBIDDEN);
			map.put("error", "Unauthorized User");

			return map;
		}
		Appointment app = appointmentService.findNext(user);

		if (app != null) {
			map.put("status", HttpStatus.OK);

			map.put("doctor_details", app.getDoctor());

			map.put("bookng_details", app);
		} else {
			map.put("status", HttpStatus.NOT_FOUND);
			map.put("bookng_details", "");
		}
		map.put("my_records", 0);

		return map;

	}

}
