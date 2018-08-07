package com.doctoroncall.push;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {
	private static final Logger log = LoggerFactory.getLogger(AndroidPushNotificationsService.class);
	private static final String FIREBASE_SERVER_KEY = "AAAAmCRYA6g:APA91bEKZcdN0kWFukKSn3P3YoTPwogqsU9oUI8GiqsmP24IXV38oABv6luu0g0JgR8B4D-jrTvXRSD5c6MMCkW7CvD_2WNblUX3WekZ3-8uwKff-2e6CzWxwo0XC1CgTPhTeM2Zox4Q";

	public Boolean sendTo(String to) {
		return sendTo(to , 0L,"Test");
	}
	public Boolean sendTo(String to, Long call_id, String title) {

		try {
			JSONObject body = new JSONObject();
			// JsonArray registration_ids = new JsonArray();
			// body.put("registration_ids", registration_ids);
			body.put("to", to);
			body.put("priority", "high");
			// body.put("dry_run", true);

			JSONObject notification = new JSONObject();
			notification.put("body",title);
			notification.put("title", title);
			// notification.put("icon", "myicon");

			JSONObject data = new JSONObject();
			data.put("call_id", call_id);

			body.put("notification", notification);
			body.put("data", data);

			HttpEntity<String> request = new HttpEntity<>(body.toString());

			CompletableFuture<FirebaseResponse> pushNotification = send(request);
			CompletableFuture.allOf(pushNotification).join();

			try {
				FirebaseResponse firebaseResponse = pushNotification.get();
				if (firebaseResponse.getSuccess() == 1) {
					log.info("push notification sent ok!");
				} else {
					log.error("error sending push notifications: " + firebaseResponse.toString());
				}
				return true;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Async
	public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		FirebaseResponse firebaseResponse = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity,
				FirebaseResponse.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}

}
