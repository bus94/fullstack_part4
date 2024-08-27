package com.ss.api;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * // 네이버
	 * 동적으로 사용하고 싶을 때
	 * @Autowired 
	 * private JavaMailSenderImpl mailSender;
	 */
	
	// 구글 로그인하는 객체 생성
	// @Qualifier("아이디값")
	@Autowired
	private JavaMailSenderImpl googleMail;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		// 동적으로 파일을 생성하고 싶을 경우 동적 메일 설정 파일의 객체를 생성
//		JavaMailSender mailSender = mailConfig.configMail(username, password);
		
		Random random = new Random();
		int checkNum = random.nextInt(8888) + 1111; 
		
		// 이메일 보낼 양식 설정
		// 발신자 이메일 주소
		String setFrom = "q9tkd27@gmail.com";
		
		// 수신자 이메일 주소
		String toMail = "lbs927@naver.com";
		
		// 이메일 제목
		String title = "회원 가입 인증 메일입니다.";
		
		String content = "인증 코드는 " + checkNum + "입니다." + "<br>" + "해당 인증 코드를 인증 코드 확인란에 작성하세요.";
		
		try {
			// 자바 메일 API 클래스 중에 하나
			// 복합형 이메일 메시지 (텍스트, html, 첨부파일 등)를 생성하는데 사용된다.
			MimeMessage message = googleMail.createMimeMessage();
			
			// 위의 MimeMessage 클래스에 설정을 하는 클래스
			// message 첫번째 매개변수 위에 메일 생성
			// true 이메일 메시지를 html, 텍스트, 첨부파일 등을 함께 포함해서 전송
			// false 무조건 텍스트만 간다.
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(setFrom); // 발신자 설정
			helper.setTo(toMail); // 수신자 설정
			helper.setSubject(title); // 제목 설정
			// 내용설정(true는 html 포맷을 사용한다는 뜻)
			helper.setText(content, true);
			
			// 이메일 전송
			googleMail.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("auth", checkNum);
		
		return "home";
	}
	
	/*
	 * 네이버에 관한 메서드
	 * 
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * System.out.println("email: " + "lbs927@naver.com"); // 난수의 범위 111111 ~ 999999
	 * (6자리 난수) - 원하는 자리수만큼 지정하기 Random random = new Random(); int checkNum =
	 * random.nextInt(888888) + 111111;
	 * 
	 * // 이메일 보낼 양식 설정 // 발신자 이메일 주소 String setFrom = "lbs927@naver.com";
	 * 
	 * // 수신자 이메일 주소 String toMail = "lbs927@naver.com";
	 * 
	 * // 이메일 제목 String title = "회원 가입 인증 메일입니다.";
	 * 
	 * String content = "인증 코드는 " + checkNum + "입니다." + "<br>" +
	 * "해당 인증 코드를 인증 코드 확인란에 작성하세요.";
	 * 
	 * try { // 자바 메일 API 클래스 중에 하나 // 복합형 이메일 메시지 (텍스트, html, 첨부파일 등)를 생성하는데 사용된다.
	 * MimeMessage message = mailSender.createMimeMessage();
	 * 
	 * // 위의 MimeMessage 클래스에 설정을 하는 클래스 // message 첫번째 매개변수 위에 메일 생성 // true 이메일
	 * 메시지를 html, 텍스트, 첨부파일 등을 함께 포함해서 전송 // false 무조건 텍스트만 간다. MimeMessageHelper
	 * helper = new MimeMessageHelper(message, true, "UTF-8");
	 * helper.setFrom(setFrom); // 발신자 설정 helper.setTo(toMail); // 수신자 설정
	 * helper.setSubject(title); // 제목 설정 // 내용설정(true는 html 포맷을 사용한다는 뜻)
	 * helper.setText(content, true);
	 * 
	 * // 이메일 전송 mailSender.send(message);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return "home"; }
	 */
	
	// 입력을 이용해서 메일을 작성
	// ajax 통신을 하려면 @ResponseBody
	@RequestMapping("/email")
	@ResponseBody
	public String email(String email) {
		// 위에서 사용하는 것을 서비스 클래스를 생성해서 실행할 수 있도록
		
		
		return "웹 페이지명";
	}
	
}
