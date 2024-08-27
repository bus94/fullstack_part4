package com.ss.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// 설정 클래스
// 빈 설정에 사용된다.
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		System.out.println("웹 소켓 실행!");
		
		// 웹 소켓으로 경로를 설정해서 MyWebSocketHandler
		// 모든 웹 사이트(출처)에서 이 서버로 웹 소켓을 연결 허용하겠다
		registry.addHandler(new MyWebSocketHandler(), "/myHandler").setAllowedOrigins("*");
	}
	
}
