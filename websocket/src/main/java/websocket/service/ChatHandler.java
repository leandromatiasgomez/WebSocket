package websocket.service;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler{
	
	 private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
	 
	 @Override
	 public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		 sessions.add(session); 
	 }
	 
	 @Override
	 public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		 sessions.remove(session); 
	 }
	 
	 @Override
	 public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		 for(WebSocketSession webSocketSession : sessions) {
			 webSocketSession.sendMessage(message);
		 }
	 }
	
}
