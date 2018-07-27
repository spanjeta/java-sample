package com.doctoroncall.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctoroncall.base.BaseController;
import com.doctoroncall.entities.Activity;
import com.doctoroncall.entities.Answer;
import com.doctoroncall.services.ActivityService;
import com.doctoroncall.services.AnswerService;
import com.doctoroncall.services.AuthCodeService;
import com.doctoroncall.services.QuestionService;

@RequestMapping("api/activity")
@RestController

public class ActivityRestController extends BaseController {

	@Autowired
	private AuthCodeService authCodeService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService activityQuestionService;
	// @Autowired
	// private AppointmentService appointmentService;

	@PostMapping(value = "/get-activity-list")
	public Map<String, Object> getActivities(@RequestParam int appointment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", activityService.findAll());
		map.put("status", HttpStatus.OK);
		return map;
	}

	@PostMapping(value = "/get-activity-question")
	public Map<String, Object> getQuestions(@RequestParam Long id) {
		Activity activity = activityService.findOne(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", activityQuestionService.findAllByActivity(activity));
		map.put("status", HttpStatus.OK);
		return map;

	}

	@PostMapping(value = "/get-activity-answer/{id}")
	public Map<String, Object> getActivityAnswerAudio(Answer ans, @PathVariable Long id) {
		Activity activity = activityService.findOne(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", activityQuestionService.findAllByActivity(activity));
		map.put("status", HttpStatus.OK);
		return map;

	}

}
