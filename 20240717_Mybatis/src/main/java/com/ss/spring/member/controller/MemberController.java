package com.ss.spring.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.spring.member.dto.Member;
import com.ss.spring.member.service.MemberService;

/*
 *  Controller 주요 개념 정리 
 *   1. 핸들러 메서드의 URL 매핑관련
 *     어노테이션을 정리 
 *     
 *   1)@RequestMapping
 *    - 개념: 가장 기본적인 Spring의 url 매핑
 *        어노테이션, get/post 둘다 처리 가능
 *    - value : 매핑할 URL을 지정하는 속성
 *    -        "/"를 생략 가능 여러개의 url지정
 *    - method: get,post중  속성 지정! 
 * 
 * 	   @RequestMapping("home.do")
 *     @RequestMapping(value="home.do")
 *     @RequestMapping(value="home.do", method="RequestMethod.POST/GET")
 *     @RequestMapping(value={"home.do", "index.do"}, method={RequestMethod.GET, RequestMethod.POST})
 * 
 * 
 */


@Controller
public class MemberController {
	
	// Spring에서 자동으로 객체를 생성해서
	// 저장해주는 어노테이션
	// MemberService servie = new MemberServie()
	// 사용자가 별도의 객체를 생성하지 않고
	// 객체들을 관리하는 BeanFactory로 
	// 부터 객체의 관리를 위임하는 어노테이션 
	
	private MemberService service = new MemberService();
	

	// @RequestMapping: method를 정의
	// 하지 않으면 get/post 둘다 처리 가능
	// 서블릿 스타일
	//  - 장점: 서블릿을 하던 사람이나.
	//         서블릿 프로젝트의 호환성을 유지할 
	//         수 있다. 
	//  - 단점 : 안쓴다.
	@RequestMapping("/member/index.do")
	public String index() {
		return "member/index";
	}
	
	@RequestMapping("/member/memberServlet.do")
	public String memberServlet(
				HttpServletRequest req,
				HttpServletResponse resp,
				HttpSession session){
		
		Member member = new Member();
		member.setId(req.getParameter("id"));
		member.setName(req.getParameter("name"));
		member.setGender(req.getParameter("gender"));
		member.setAge(Integer.parseInt(req.getParameter("age")));
		member.setDevLang(req.getParameterValues("devLang"));
		
		// jsp 페이지로 보내는 방법 
		req.setAttribute("member", member);
		
		// 1. 세션 사용법 
		req.getSession()
		   .setAttribute("id"
				   	    , member.getId());
		// 2.spring session 사용법
		session.setAttribute("id", 
						member.getId());
		
		return "member/memberView";
	} 
	
	// form-method 매핑하는 방법
	// getParameter 자동으로 매핑 시켜주는
	// 어노테이션 존재한다.
	// RequestParam 어노테이션!
	// form-name과 핸들러메서드의 매개변수와
	// 매핑시켜서 저장하는 방법
	// @RequestParam(value="form에서 넘어오는 name속성")
	//  
	
	@RequestMapping("/member/memberParams2.do")
	public String memberParams2(Model model,
			@RequestParam(value="id") String userId,
			@RequestParam(value="name") String userName,
			@RequestParam(value="gender") String userGener,
			@RequestParam(value="age") int userAge,
			@RequestParam(value="devLang") String[] devLang
			) {
		
		Member member = new Member();
		member.setId(userId);
		member.setName(userName);
		member.setGender(userGener);
		member.setAge(userAge);
		member.setDevLang(devLang);
		
		// model  객체를 이용해서 view로
		// 파라미터를 넘기는 방법 
		// Model 
		//  컨트롤러에서 데이터를 뷰로 전달할 떄
		//  사용하는 Spring Mvc 에서 사용함
		//  addAttribute(key,value)
		model.addAttribute("member", member);
		
		return "member/memberView";
	}
	
	// form의 이름과 메서드의 매개변수명이
	// 같은 경우에는 @RequestParam 생략 가능
	// Spring 버전이 업 되면서 어노테이션 생략!
	
	@RequestMapping("/member/memberParams.do")
	public String memberParams(Model model,
			 String id,
			 String name,
			 String gender,
			 int age,
			 String[] devLang
			) {
		
		Member member = new Member(id,
					name, age, gender,
					gender, devLang);		
		
		model.addAttribute("member", member);
		
		return "member/memberView";
	}
	
	// 3. 객체를 이용해서 데이터를 받을 수 있다.
	// - 사용자가 지정한 dto,vo의 멤버 변수의
	//   이름하고 form넘어오는 name속성을 일치
	//   시키면 자동 매핑된다.
	
	// 주의: dto에 기본 자료형(문자열 포함)만 지원
	//   이외에 객체 자료형이 있는 경우 처리가 
	//   안될 수 있다. 
	
	// 클라이언트에게 응답으로 돌려주는 작업의
	// 처리 결과 데이터를 Model 
	@RequestMapping("/member/memberCommand.do")
	public String memberCommand(
						Model model,
						Member member) {
		 
		model.addAttribute("member", member);
		
		return "member/memberView";
	}
	
	// @ModelAttribute("key")
	// view에서 model의 정보를 name
	// 으로 넘기는 방법 
	@RequestMapping("/member/memberCommand2.do")
	public String memberCommand2(
						Model model,						
			@ModelAttribute("member")
						Member member) {
	
		return "member/memberView";
	}
	
	// 4. 컬랙션의 Map을 활용해서 사용
	//  - form 파라메터를 map으로 가져오는방법
	//  - 장점: VO,dto 상관없이 모든 파라메터를
	//    처리할 수 있다. 
	//  - 단점: value's'에 해당되는 배열이
	//      잘 처리 되지 않음 
	//       vaules는 각각 매개변수를 만들어서
	//       처리한다. 
	@RequestMapping("/member/memberMap.do")
	public String memberMap(Model m,
				@RequestParam Map<String,Object> param
				,String[] devLang) {
		System.out.println("memberMap: " 
						  + param);
		System.out.println("배열:"+ devLang);
		
		// 만약 여러개의 데이터가 들어온다면
		// 각각 처리하고 Map에 추가하기 
		param.put("devLang", devLang);
		
		// 컨트롤러에서 뷰로 데이터 전송한다.
		m.addAttribute("member", param);
		return "member/memberView";
	}
	
	// 회원 조회를 하는 컨트롤러 매핑
	@RequestMapping("/member/memberList.do")
	public String memberList(Model model) {
		// service 시작하는 메서드 
		List<Member> list = service
						   .getAllList();
		System.out.println(list);
		model.addAttribute("list", list);
		
		if(list == null) {
			return "redirect:member/index.do";
		}
				
		return "member/memberList";
	}
	
	
	
	
	
}
