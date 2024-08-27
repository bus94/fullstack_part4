package com.ss.spring.member.service;

import java.util.List;

import com.ss.spring.member.dto.Member;

// 공통적으로 MemberServic에서
// 사용할 메서드 명을 정해놓고 
// 인터페이스 구현하자!
public interface MemberServiceImp {

	Member login(String id);
	int joinMember(Member member);
	List<Member> getAllList();
	
}



