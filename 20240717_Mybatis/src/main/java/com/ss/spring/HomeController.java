package com.ss.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Controller 컨트롤러임을 알리는 어노테이션
//  Servlet class의 기능을 대체한다. 
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 스프링은 각각 1:1로 매핑 할 수 있다.
	// 핸들러 메소드(Handler Method)
	// - URL매핑이 되어 있는 메소드를 말한다.
	// - 특정 단위 하나의 기능을 담당하는 것
	// - return 값이 문자열일 경우 기본적으로
	// forward 되는 jsp 페이지를 지징한다.

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	// URL을 매핑시키는 가장 간단한 방법
	@RequestMapping("home2.do")
	public String home2() {
		return "home2"; // /WEB-INF/views/home2.jsp
	}
	

	// URL을 매핑시키는 가장 간단한 방법
	// home2의 메서드 이름과 가장 유사한 jsp
	// 찾는다. 실행!
//	@RequestMapping("home2.do")
//	public void home2() {}

	// Redirect로 home2.do로 전달하는 방법
	// 기본적으로 forward되는 방식이지만
	// 새로운 요청을 바로 할 수 있다.
	// /WEB-INF/views/home2.do.jsp
	// 주소 왜곡이 난다. 
	
	@RequestMapping("home3.do")
	public String home3() {
		return "redirect:home2.do";
	}
	
	
	
	
}
/*
 * 
 * 프레임워크(Frameworkd)란? 뼈대나 근간을 이루는 코드들의 묶음. 개발자는 각 개개인의 능력차이가 큰 직종이고, 개발자 구성에 따라
 * 프로젝트 결과 역시 큰 차이를 낳는다. 이런 상황을 극복하기 위한 코드의 결과물이 바로 프레임워크이다. 프로그램의 기본 흐름이나 구조를
 * 정하고 모든 팀원이 이 구조에 자신의 코드를 추가하는 방식으로 개발하게 된다.
 * 
 * 프레임워크의 장점 개발에 필요한 구조를 이미 코드로 만들어 놓았기 때문에, 실력이 부족한 개발자라 하더라도 반쯤 완성한 상태에서 필요한
 * 부분을 조립하는 형태의 개발이 가능하다. 회사 입장에서는 프레임워크를 사용하면 일정한 품질이 보장되는 결과물을 얻을 수 있고, 개발자
 * 입장에서는 완성된 구조에 자신이 맡은 코드를 개발해서 넣기 때문에 개발 시간을 단축할 수 있다.
 * 
 * 스프링 프레임워크의 장점 경량 프레임워크(light-weight). 예전 프레임워크는 다양한 경우를 처리할 수 있는 다양한 기능을 가지도록
 * 만들다 보니 하나의 기능을 위해서 너무 많은 구조가 필요했다. 기술이 너무나 복잡하고 방대했기 때문에, 전체를 이해하고 개발하기에는
 * 어려움이 많았다. 그래서 스프링 프레임워크가 등장했고, 특정 기능을 위주로 간단한 JAR파일 등을 이용해서 모든 개발이 가능하도록 구성되어
 * 있다.
 * 
 * 
 * 스프링 프로젝트 실행을 하면 톰캣 구동! Context Path 루트 컨테이너 생성 url 매핑
 * 
 */
