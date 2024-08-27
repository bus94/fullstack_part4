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
 * @Controller 주요 개념 정리
 * 1. 핸들러 메서드의 URL 매핑관련 어노테이션을 정리
 *  1) @RequestMapping: 가장 기본적인 Spring의 url 매핑 어노테이션 
 *  	- method를 정의하지 않으면 get/post 둘 다 처리가능
 *  	- value : 매핑할 URL을 지정하는 속성
 *  			"/"를 생략 가능, 여러 개의 url 지정
 *  	- method: get, post 중 속성 지정 (안하면 둘 다 처리 가능)
 *  
 *  ex)
 * 	   @RequestMapping("home.do")
 *     @RequestMapping(value="home.do")
 *     @RequestMapping(value="home.do", method="RequestMethod.GET") - GET 요청만 가능 (POST면 POST 요청만 가능)
 *     @RequestMapping(value={"home.do", "index.do"}, method={RequestMethod.GET, RequestMethod.POST}) - 각각의 메서드에 속성을 지정해주는 경우
 * 
 *  2) 
 * 
 */

@Controller()
public class MemberController {
	// Spring에서 자동으로 객체를 생성해서 저장해주는 어노테이션
	// MemberService service = new MemberService();
	// 사용자가 별도의 객체를 생성하지 않고 객체들을 관리하는 BeanFactory로부터 객체의 관리를 위임하는 어노테이션
	@Autowired
	private MemberService service;

	// @RequestMapping: method를 정의하지 않으면 get/post 둘 다 처리가능
	// 서블릿 스타일 ex. ("/member/index.do") - 이렇게 url을 적어두는 것
	// - 장점: 서블릿을 하던 사람이나 서블릿 프로젝트의 호환성을 유지할 수 있다.
	// - 단점: 많이 안쓴다.
	@RequestMapping("/member/index.do")
	public String index() {
		return "member/index";
	}
	
	// 회원가입 버튼을 클릭했을 때 매칭시키는 핸들러 메서드 생성하기
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll(Model model, Member m) {
		System.out.println("가입 정보: " + "m");
		int result = service.joinMember(m);
		
		if(result > 0) {
			model.addAttribute("msg", "회원가입이 완료되었습니다.");
		} else {
			model.addAttribute("msg", "회원가입이 실패하였습니다.");
		}
		return "member/index";
	}

	// 단일 검색 (조회하기)
	// 메서드의 반환 타입이 문자열이면 return 했을 때 리턴문자가 jsp 페이지의 파일명이 된다.
	@RequestMapping("/member/memberSearch.do")
	public String memberSearch(Model model, String id) { // @RequestParam("id") String userId → String id (id와 같다면 그 변수값을 쓰겠다)
		// 위에서 넘어온 id가 정상적으로 들아왔는지 로그
		System.out.println("조회할 id: " + id);
		
		// 1. 멤버 정보를 반환
		Member member = service.searchMember(id);
		
		// 2. view 페이지 출력
		if(member != null) {
			model.addAttribute("member", member);
			return "member/memberView";
		}else {
			return "redirect:error.do";
			
			// redirect: 상대경로로 해석
			// /member/searchMember.do
		}
		
	}
	
	// error.do url 요청이 들어오면 핸들러메서드로 연결하기
	// 뷰페이지 error.jsp 페이지 이동하기
	// 1. request 매핑
	@RequestMapping("/member/error.do")
	// 2. 메서드 작성
	public String error() {
		// 3. 뷰페이지 설정
		return "common/error";
	}
	
	// index.jsp에서 요청이 들어오면 요청 들어온 url이랑 요청을 처리할 메서드(핸들러메서드)를 실행한다.
	/*
	 * @RequestMapping("/member/memberDel.do") public String memberDel(String id) {
	 * System.out.println("id: " + id);
	 * 
	 * int result = service.deleteMember(id);
	 * 
	 * if(result > 0) { System.out.println("정상적으로 탈퇴되었습니다."); return "member/index";
	 * } else { return "redirect:error.do"; } }
	 */
	
	@RequestMapping("/member/memberDel.do")
	public String memberDel(Model model, String id) {
		System.out.println("삭제할 id: " + id);

		int result = service.deleteMember(id);
		
		if(result > 0) { // 실행되는 쿼리문의 갯수가 반환되는 result - 삭제되면 0보다 큰 수가 반환된다.
			System.out.println("정상적으로 탈퇴되었습니다.");
			System.out.println(" '" + id + "'님의 회원 탈퇴가 정상적으로 완료되었습니다.");
			model.addAttribute("msg",id + "님의 회원 탈퇴가 정상적으로 완료되었습니다.");
		} else {
			System.out.println("탈퇴에 실패하였습니다.");
			System.out.println(" '" + id + "'님의 회원 탈퇴가 실패하였습니다.");
			model.addAttribute("msg", id + "님의 회원 탈퇴가 실패하였습니다.");
		}
		
		return "member/index";
	}
	
	// 회원을 조회했을 때 웹 요청을 처리하는 핸들러 메서드를 매핑시켜야된다.
	@RequestMapping("/member/memberList.do")
	public String memberList(Model model) {
		
		// 1. service 호출하기
		List<Member> list = service.getMemberList();
		
		// 2. dao 호출하기
		
		// 3. xml 호출해서 sql 실행
		
		// 4. 리턴된 값 모델에 저장하기
		model.addAttribute("list", list);
		
		// 5. 웹 페이지로 전송
		return "member/memberList";
	}
	
	// 회원 로그인을 클릭했을 때 웹에서 요청이 들어오면 매핑시킬 핸들어 메서드 생성하기
	@RequestMapping("/member/login.do")
	public String login(Model model) {
		return "member/login";
	}
	
	// login.jsp 페이지에서 입력하면 처리하는 핸들러메서드 실행
	@RequestMapping("/member/loginPro.do")
	public String loginPro(Model model, Member member) {
		// 1. service 클래스
		Member m = service.login(member);
		
		// 2. dao 클래스 호출
		// 3. xml 파일 실행 후 sql 실행
		// 4. 리턴값 확인 후 조건문 생성
		// 5. 조건문에 의해 웹페이지 return 하기
		if(m != null) {
			model.addAttribute("msg", m.getId() + "님, 환영합니다.");
			return "member/index"; // jsp 페이지 명
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 다시 확인해주세요.");
			return "member/login";
		}
			
	}
		
	
	/*
	 * MyBatis Mapper 인터페이스란? 여러 Mapper 인터페이스를 작성할 수있는 설정
	 * 
	 * Mapper 인터페이스란? 
	 * - 매핑파일에 기재된 sql을 호출하기 위해서 만들어진 인터페이스! 
	 * - Mybatis 3.0부터 생성 
	 * - 매핑 파일을 sql을 실행할 수있도록 호출 하는 기능
	 * 
	 * Mapper 인터페이스를 구현하지 않으면 DAO클래스를 만들어서 메서드를 만들어서 그 안에 mapper.selectOnt(아이디,파라미터) 문자열로 작성을 하다보니깐 예외(오타,경로) 버그가 생길 수있다.
	 * 
	 * Mapper 인터페이스를 사용했을 때
	 * - 개발자가 직접 메서드명과 id값을 일치 시켜서 작성을 하면 불필요한 문자열 예외를 피할 수 있고, 불필요한 클래스를 만들어서 메서드를 중복해서 만들 필요가 없다. 
	 * 
	 * MyBatis 연결
	 * 1. sql 명령문을 실행하는 mapper폴더 만들어서 mapper.xml 파일 생성하기
	 * 	src/main/resources → classpath: --> #{classpath}/mapper/*.xml
	 * 
	 */

	/*
	 * @RequestMapping("/member/memberServlet.do") public String
	 * memberServlet(HttpServletRequest req, HttpServletResponse resp, HttpSession
	 * session) {
	 * 
	 * Member member = new Member(); member.setId(req.getParameter("id"));
	 * member.setName(req.getParameter("name"));
	 * member.setGender(req.getParameter("gender"));
	 * member.setAge(Integer.parseInt(req.getParameter("age")));
	 * member.setDevLang(req.getParameterValues("devLang"));
	 * 
	 * // jsp 페이지로 보내는 방법 req.setAttribute("member", member);
	 * 
	 * // 1. 세션 사용법 req.getSession().setAttribute("id", member.getId());
	 * 
	 * // 2. spring session 사용법 session.setAttribute("id", member.getId());
	 * 
	 * return "member/memberView"; }
	 * 
	 * // form-method 매핑하는 방법 // getParameter 자동으로 매핑 시켜주는 어노테이션 존재한다. //
	 * RequestParam 어노테이션 // form-name과 핸들러메서드의 매개변수와 매핑시켜서 저장하는 방법
	 * // @PequestParam(value="form에서 넘어오는 name 속성")
	 * 
	 * @RequestMapping("/member/memberParams2.do") public String memberParams2(Model
	 * model, @RequestParam(value = "id") String userId,
	 * 
	 * @RequestParam(value = "name") String userName, @RequestParam(value =
	 * "gender") String userGender,
	 * 
	 * @RequestParam(value = "age") int userAge, @RequestParam(value = "devLang")
	 * String[] devLang) { Member member = new Member(); member.setId(userId);
	 * member.setName(userName); member.setGender(userGender);
	 * member.setAge(userAge); member.setDevLang(devLang);
	 * 
	 * // model 객체를 이용해서 view로 파라미터를 넘기는 방법 // Model 컨트롤러에서 데이터를 뷰로 전달할 때 사용하는
	 * Spring Mvc에서 사용함 // addAttribute(key, value) model.addAttribute("member",
	 * member);
	 * 
	 * return "member/memberView"; }
	 * 
	 * // form의 이름과 메서드의 매개변수명이 같은 경우에는 @RequestParam 생략 가능 // Spring 버전이 업그레이드 되면서
	 * 어노테이션 생략 가능해졌다.
	 * 
	 * @RequestMapping("/member/memberParams.do") public String memberParams(Model
	 * model, String id, String name, String gender, int age, String[] devLang) {
	 * Member member = new Member(id, name, age, gender, gender, devLang);
	 * model.addAttribute("member", member); return "member/memberView"; }
	 * 
	 * // 3. 객체를 이용해서 데이터를 받을 수 있다. // - 사용자가 지정한 dto, vo의 멤버 변수의 이름하고 form 넘어오는
	 * name 속성을 일치 시키면 자동 매핑된다. // !!주의!! dto에 기본 자료형(문자열 포함)만 지원 - 이외의 객체 자료형이 있는
	 * 경우 처리가 안될 수도 있다. // 클라이언트에게 응답으로 돌려주는 작업의 처리 결과 데이터를 Model 이라 하는 것
	 * 
	 * @RequestMapping("/member/memberCommand.do") public String memberCommand(Model
	 * model, Member member) { model.addAttribute("member", member); return
	 * "member/memberView"; }
	 * 
	 * // @ModelAttribute("key") // view에서 model의 정보를 name으로 넘기는 방법
	 * 
	 * @RequestMapping("/member/memberCommand2.do") public String
	 * memberCommand2(Model model, @ModelAttribute("member") Member member) { return
	 * "member/memberView"; }
	 * 
	 * // 4. 컬렉션의 Map을 활용해서 사용 // - form 파라메터를 map으로 가져오는 방법 // - 장점: vo, dto 상관없이
	 * 모든 파라메터를 처리할 수 있다. // - 단점: value's'에 해당되는 배열은 잘 처리되지 않는다. values처럼 여러 개는 각각
	 * 매개변수를 만들어서 처리해야 한다.
	 * 
	 * @RequestMapping("/member/memberMap.do") public String memberMap(Model
	 * model, @RequestParam Map<String, Object> param, String[] devLang) {
	 * System.out.println("memberMap: " + param); System.out.println("배열: " +
	 * devLang);
	 * 
	 * // 만약 여러 개의 데이터가 들어온다면 각각 처리하고 Map에 추가하기 param.put("devLang", devLang);
	 * 
	 * // 컨트롤러에서 뷰로 데이터 전송한다. model.addAttribute("member", param); return
	 * "member/memberView"; }
	 * 
	 * // 회원 조회를 하는 컨트롤러 매핑
	 * 
	 * @RequestMapping("/member/memberList.do") public String memberList(Model m) {
	 * // service 시작하는 메서드 List<Member> list = service.getAllList();
	 * System.out.println(list); m.addAttribute("list", list);
	 * 
	 * if(list == null) { return "redirect:member/index.do"; }
	 * 
	 * return "member/memberList"; }
	 * 
	 * // 회원 추가를 하는 컨트롤러
	 * 
	 * @RequestMapping("/member/memberJoin.do") public String memberJoin(Model
	 * model, Member member) { int result = service.joinMember(member); if(result ==
	 * 0) { return "redirect:member/index.do"; }
	 * 
	 * return "redirect:/member/memberList.do"; }
	 */

}
