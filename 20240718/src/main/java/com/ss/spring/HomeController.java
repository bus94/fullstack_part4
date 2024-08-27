package com.ss.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.spring.DI.member.dto.Member;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// Member 객체 생성
	// 1. bean이 단일 id로 선언된 경우
	@Autowired
//	@Qualifier("testMember1")
	Member member1; // 이익준으로 초기화
	
	@Autowired
//	@Qualifier("testMember2")
	Member member2; // 채송화로 초기화
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("member1", member1);
		model.addAttribute("member2", member2);
		
		return "home";
	}
	
}

/* 
 * Spring MVC 개념
 * - Spring은 서블릿 대비 어노테이션(@Annotation)의 활용도 높아졌다. 
 * - Spring 프레임 워크는 상속 설계를 지양하고 interface 기반으로 설계가 권장된다.
 * - 의존성 주입 (Defendence Injection) 을 통해 객체를 직접 생성하지 않고, Spring에서 생성 주입 시키기 편하기 위해 new 지양하고 Spring 객체를 저장해주는 프레임워크로 변경된다.
 *  - Spring MVC에서는 철저하게 MVC2 패턴을 활용하도록 설계 되어있다.
 *    -> Request(URL+파라메터) -> Controller(Java객체) + Model -> View(JSP or Thymleaf)
 *  - 서블릿 Class 단위로 URL을 맵핑 했던 기능이 @Controller에 핸들러 메소드(Handler method)로 이관
 *    -> Spring은 핸들러 메소드로 URL 맵핑 가능, 상위 패키지(ex: Member, Board)의 세부 기능을 하나의 Class에서 설계 
 *  - WEB-INF구조(보안 기능)으로 인해 URL로 JSP, HTML에 직접 접근이 불가하고, 반드시 Controller->View 접근이 필요
 *  - ViewResolver를 통해 View로 접근하는 URL 단축 시킬수 있다.
 *    -> ex) return "home" -> "/WEB-INF/views/home.jsp"로 jsp 페이지가 호출 됨
 *  - return의 경우 (=View name을 문자열 지정), forward로 전달 되도록 설계됨 -> 파라메터 물고 전달됨!
 *  - 만일 redirect로 다른 핸들러 메소드를 호출하기 위해선 return 값 앞에 'redirect:' + URL을 붙이면 된다.
 *    -> ex) home2.do redirect로 전달하는 방법 : "redirect:home2.do"
 * 
 * - 의존성이란? 하나의 객체가 다른 객체 없이 제대로 된 역할을 할 수 없다는 것을 의미한다.
 * - 주입(Injection)이란? 외부에서 밀어 넣어주는 것
 * - IOC Contaioner
 * 	@어노테이션이 붙은 객체들 중에서 자동으로 객체를 생성하는 어노테이션들의 객체를 저장하고 있는 공간이다.
 * 	service, repository, controller
 * - Bean
 *  Spring에서 자동으로 객체 생성되고 그 컨테이너 안에 저장되는 객체들을 Bean이라고 한다.
 * 
 * - 자바에서 new를 써서 직접 객체를 생성했다면 Spring은 개발자가 직접 객체를 생성하지 않고 필요한 내용을 컨테이너한테 어노테이션을 이용해서 달라고 한다.
 * 
 * ■ DI를 활용하기 위한 MVC 주요 레이어에 붙는 어노테이션
 *  @Controller : URL처리가 가능한 Controller에 붙는 어노테이션
 *  @Service : 비지니스 로직 처리를 위한 서비스 계층에 붙는 어노테이션
 *  @Repository : DB나 Data처리가 가능한 계층(주로 DAO)에 붙는 어노테이션
 *  @Component : 계층이 아닌 일반 객체를 생성할때 사용하는 어노테이션
 *  
 * ■ DI를 통한 객체 생성 시 활용 어노테이션
 * 	@Autowired : new 대신 객체 생성할 때
 *  @Value : 단순 값을 주입할 때
 * 
// DI 설계 - Spring은 interface 설계를 권장, 이를 상속 받아 동작하는 클래스를 다시 정의가 필요함 -> XXXimpl
//
// 사유 
// 1. 설계 이점이 존재, 개발하기 전에 기능을 클래스에서 정의 가능하고 미리 정리하기 위해 작성
// 	-> 협업시 개발 되지 않은 부분도 interface를 통해 미리 작동하도록 코드 구현이 가능
// 2. 느슨한 결합으로 실제 코드부가 변경되어 다른 코드가 영향을 받지 않는다. 
// 3. 같은 interface를 상속 받았지만, 다른 기능을 가진 객체를 설계할수 있다.
// 	-> 관례적인 기법이기도 하다. 그래 안쓰는 곳은 안쓴다.
// 	참고로 Spring에서는 impl을 쓰지 않아도 잘 동작된다. -> 프로젝트 할때는 interface 설계 안해도 무관
 * 
 *
 */