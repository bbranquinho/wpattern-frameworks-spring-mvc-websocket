package org.wpattern.frameworks.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/ws/hello")
public class HelloWebSocket {

	private static final Logger LOGGER = Logger.getLogger(HelloWebSocket.class);

	public HelloWebSocket() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hello WebSocket instantiaded.");
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Session [%s] opened.", session.getId()));
		}
	}

	@OnClose
	public void onClose(Session session) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Session [%s] closed.", session.getId()));
		}
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		LOGGER.error(String.format("Error on session [%s].", session.getId()), throwable);
	}

	@OnMessage
	public String onHelloMessage(String message) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Message received [%s].", message));
		}

		return "Message received = " + message;
	}
}
