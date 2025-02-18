package com.ss.rest;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
}

// Spring에서 컨트롤러를 지정할 때
// 1. @Controller
//      return 뷰페이지 View를 반환하기 위해 사용
// 2. @RestController
//      return 값이 Data를 반환해야되는 경우
//		@responseBody 어노테이션을 이용해서 @Controller json 형태로 데이터를 반환
// 		비동기 통신을 할 때 많이 사용
//		Rest 요청을 받는 Controller view를 반환 안하고, 요청과 응답을 처리한다
