package com.ss.spring.member.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.spring.member.dao.MemberDAO;
import com.ss.spring.member.dto.Member;

@Service // 서비스임을 알리고 MemberService 객체를 자동으로 생성하는 어노테이션
public class MemberService implements MemberServiceImp{
	@Autowired
	private MemberDAO dao;
	
	// sql 접근을 위해서 mapper 정보를 가지고 온다.
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public Member login(Member member) {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("id", member.getId());
		if(member.getPassword() != null) {
			hmap.put("password", member.getPassword());
		}
		return dao.selectMemberById(session, hmap);
	}

	@Override
	public int joinMember(Member member) {
		return dao.insertMember(session, member);
	}

	@Override
	public Member searchMember(String id) {
		return dao.selectById(session, id);
	}

	@Override
	public int deleteMember(String id) {
		System.out.println("MemberService의 deleteMember() 실행");
		return dao.deleteMemberById(session, id);
	}

	@Override
	// 전체 조회를 실행하는 dao를 호출하는 메서드
	public List<Member> getMemberList() {
		return dao.selectMemberAll(session);
	}

	@Override
	public Member login(String id) {
		return null;
	}
	
}
