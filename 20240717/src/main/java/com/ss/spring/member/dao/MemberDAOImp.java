package com.ss.spring.member.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.ss.spring.member.dto.Member;

public interface MemberDAOImp {
	int insertMember(SqlSessionTemplate mapper, Member member);
	int deleteMemberById(SqlSessionTemplate session, String id);
	List<Member> selectMemberAll(SqlSessionTemplate session);
	Member selectMemberById(SqlSessionTemplate session, HashMap<String, String> hmap);
	Member selectById(SqlSessionTemplate session, String id);
}
