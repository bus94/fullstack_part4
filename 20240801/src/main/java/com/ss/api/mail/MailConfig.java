package com.ss.api.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// 설정하는 파일을 동적으로 사용하고 싶을 때
// @Configuration
// - 클래스 하나 이상이 @Bean 메서드를 포함하고 IOC 컨테이너 빈 정의로 사용된다.
// - 스프링의 설정을 하는 클래스 역할
// @Configuration
public class MailConfig {
	// 자바에서 제공하는 메일 클래스를 먼저 등록
	// 메서드가 Spring 컨텍스트에서 관리하는 빈 객체를 반환한다.
	@Bean
	public JavaMailSender javaMailSender() {
		return new JavaMailSenderImpl();
	}
	
	// 동적으로 이메일 계정을 설정하기 위해서 사용하는 메서드
	public JavaMailSender configMail(String username, String password) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		
		// 자바 객체에서 JavaMail 속성 객체를 가져온다.
		Properties props = mailSender.getJavaMailProperties();
		
		// smtp 서버에서 사용자 인증을 요구하는 여부를 설정하는 key, value
		props.put("mail.smtp.auth", "true");
		// smtp에서 TLS통신규칙을 이용해서 암호화할지 여부를 설정하는 key, value
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.timeout", "5000");
		
		return mailSender;
	}
}
