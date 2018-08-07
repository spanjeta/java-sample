package com.doctoroncall.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.doctoroncall.entities.CallLog;
import com.opentok.Archive;
import com.opentok.Archive.OutputMode;
import com.opentok.ArchiveMode;
import com.opentok.ArchiveProperties;
import com.opentok.MediaMode;
import com.opentok.OpenTok;
import com.opentok.Session;
import com.opentok.SessionProperties;
import com.opentok.exception.OpenTokException;

@Service
public class TokboxService {

	private int apiKey = 46039772; // YOUR API KEY
	private String apiSecret = "8282c6ad634865c6572e5d41b6e08be3e46d81b3";
	private OpenTok opentok;
	private String sessionId;
	private String token;
	private Session session;


	public TokboxService() {
		super();

	}

	public TokboxService(int apiKey, String apiSecret) {
		super();
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}


	public int getApiKey() {
		return apiKey;
	}

	public void setApiKey(int apiKey) {
		this.apiKey = apiKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Map<String, Object> setup() {

		opentok = new OpenTok(apiKey, apiSecret);

		try {
			session = opentok.createSession(new SessionProperties.Builder()
					  .mediaMode(MediaMode.ROUTED)
					  .archiveMode(ArchiveMode.ALWAYS)
					  .build());

			sessionId = session.getSessionId();
			try {
				token = session.generateToken();
			} catch (OpenTokException e) {
				e.printStackTrace();
			}
		} catch (OpenTokException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CallLog call = new CallLog( token, sessionId);
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("apiKey", apiKey);
		attributes.put("sessionId", sessionId);
		attributes.put("token", token);
		return attributes;
	}

	public Archive record(HttpServletRequest request) {
		Archive archive;
		boolean hasAudio = request.getParameterMap().containsKey("hasAudio");
		boolean hasVideo = request.getParameterMap().containsKey("hasVideo");
		OutputMode outputMode = OutputMode.COMPOSED;
		if (request.getParameter("outputMode").equals("individual")) {
			outputMode = OutputMode.INDIVIDUAL;
		}
		try {
			ArchiveProperties properties = new ArchiveProperties.Builder().name("Java Archiving Sample App")
					.hasAudio(hasAudio).hasVideo(hasVideo).outputMode(outputMode).build();
			archive = opentok.startArchive(sessionId, properties);
		} catch (OpenTokException e) {
			e.printStackTrace();
			return null;
		}
		return archive;
	}

}
