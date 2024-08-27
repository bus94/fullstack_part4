package com.ss.api;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ss.api.service.KakaoService;

@SessionAttributes("loginMember")
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// KakaoService 객체 가져오기
	@Autowired
	private KakaoService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// 카카오 로그인시 동의화면을 띄우기 위해서 인가코드를 돌려받는 매칭
	@RequestMapping("/kakao/login")
	public String kakaoLogin(String code, Model model) {
		System.out.println("인가코드: " + code);
		
		// 인가 코드로 동의화면 보고 동의까지 했으니 다시 한 번 카카오한테 사용자 정보 요청을 해야한다.
		String token = service.getToken(code);
		System.out.println("컨트롤러 토큰: " + token);
		
		// 사용자 정보 받아오기
		// 위에 받아온 access_token 값을 이용해서 정보 받기 위해 매개변수로 넘겨준다.
		String nickname = service.getUserInfo(token);
//		System.out.println("닉네임: " + nickname);
		
		// 받아온 정보를 jsp로 전송
		model.addAttribute("nickname", nickname);
		
		// access_token을 종료해서 들어갈 때 마다 동의하면 띄우기
		// 연결 끊기
		// 연결 끊기를 하기 위해선 access_token을 매개변수로 보낸다.
//		service.unlink(token);
		
		return "home";
	}
	
	@RequestMapping("/naver/login")
	public String naverLogin(String code) {
		System.out.println("naver code: " + code);
		return "home";
	}
}
