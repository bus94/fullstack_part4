package com.ss.spring.member.service;

import java.util.List;

import com.ss.spring.member.dto.Member;

// 공통적으로 MemberServic에서 사용할 메서드명을 정해놓고 인터페이스 구현하자! - 규칙(꼭 필요한 기능)을 정해주기 위해 인터페이스 사용
public interface MemberServiceImp {

	Member login(String id);

	int joinMember(Member member);

	Member searchMember(String id);
	
	int deleteMember(String id);

	List<Member> getMemberList();

	Member login(Member member);
	
	// 위의 추상메서드들은 클래스에 구현을 하면 무조건 오버라이딩(재정의) 해야한다.
	// 재정의를 하지 않으면 구현하는 클래스도 추상 클래스가 되어 객체를 생성할 수 없다.
	// 아래의 메서드처럼 default 제어자를 작성하면 오버라이딩 대상에서 제외된다.
	// 함수는 선언부 + 구현부
	// 인터페이스 안에서도 정상적인 함수를 구현할 수 있다.
	// 인터페이스는 조건이 추상메서드만 작성할 수 있다.
	// 인터페이스 안에는 선언부의 함수만 적을 수 있다.
	/* default void searchMember(String id) {}; */
}