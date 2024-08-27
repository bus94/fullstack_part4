package com.ss.mvc.member.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ss.mvc.member.dto.Member;

// DB에 접근하는 클래스이기 때문에 미리 객체 생성해달라는 어노테이션 설정
@Repository
public class MemberDAO {

	// 1. HomeController에서 MemberService 클래스의 login 메서드를 호출하기
	
	// 2. login 호출 시 매개변수는 admin, 1234 넘겨주기
	
	// 3. MemberService 클래스의 login 메서드가 DAO 클래스를 호출해서 member -mapper.xml 파일에 sql 실행 후 
	
	// 4. 돌아온 결과값을 Service에서 리턴 값으로 HomeController로 보내면 내장 객체 또는 모델에 저장하기 (현재는 session 내장객체에 직접 저장하는 방법 사용해야한다.) - home.jsp로 갈 수 있도록 설정
	// 세션 저장 변수이름: loginMember
	
}
