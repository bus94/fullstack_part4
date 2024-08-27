package com.ss.spring.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ss.spring.member.dto.Member;

@Repository // 실제 동작은 dao를 알리는
			// 어노테이션이다! 데이터베이스 접근
			// 클래스, 자동으로 객체를
			// 생성
public class MemberDAO implements MemberDAOImp {

	// 저장소 사용
	private List<Member> list = new ArrayList<Member>();

	public MemberDAO() {
		list.add(new Member("1", "이익준", 30, "남성", "서울시 강남구", new String[] { "Java", "Python" }));
		list.add(new Member("2", "김준완", 35, "남성", "서울시 서초구", new String[] { "JavaScript", "C" }));
		list.add(new Member("3", "채송화", 20, "여성", "경기도 과천시", new String[] { "C++", "Ruby" }));
		list.add(new Member("4", "양석형", 25, "남성", "경기도 화성시", new String[] { "Swift", "Kotlin" }));
		list.add(new Member("5", "안정원", 28, "여성", "경기도 성남시", new String[] { "Go", "Rust" }));
	}

	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> selectAll() {

		return list;
	}

	@Override
	public Member selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
