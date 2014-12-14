package org.wpattern.frameworks.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.wpattern.frameworks.spring.mvc.model.ContactBean;
import org.wpattern.frameworks.websocket.beans.ContactWSBean;

@ServerEndpoint(value = "/ws/contact")
public class ContactWS {

	private static final Logger LOGGER = Logger.getLogger(ContactWS.class);

	private static final Set<Session> SESSIONS;

	private static final ObjectMapper JSON_MAPPER;

	static {
		SESSIONS = Collections.synchronizedSet(new HashSet<Session>());
		JSON_MAPPER = new ObjectMapper();
	}

	@OnOpen
	public void onOpen(Session session) {
		LOGGER.info(String.format("Open session [%s].", session.getId()));

		SESSIONS.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		LOGGER.info(String.format("Close session [%s].", session.getId()));

		SESSIONS.remove(session);
	}

	public static void sendContact(ContactBean contact, String action) {
		try {
			ContactWSBean contactWS = new ContactWSBean(contact, action);
			String json = JSON_MAPPER.writeValueAsString(contactWS);

			for (Session currentSession : SESSIONS) {
				currentSession.getBasicRemote().sendText(json);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
