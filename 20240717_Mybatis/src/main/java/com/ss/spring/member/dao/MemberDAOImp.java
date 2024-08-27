package com.ss.spring.member.dao;

import java.util.List;

import com.ss.spring.member.dto.Member;

public interface MemberDAOImp {
	int insertMember(Member member);
	List<Member> selectAll();
	Member selectById(String id);
}





