package com.ss.mvc.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ss.mvc.member.dto.Member;

// @Mapper : DAO없이 자동으로 Mapper 객체를 생성해주고 sql.xml 파일을 바로 호출하기 위해서 사용하는 것
// mapper의 id와 동일한 메서드 네이밍을 맞춰준다. (id와 메서드명) 똑같이 맞춰야 실행할 수 있다.
@Mapper
public interface MemberMapper {
	Member selectMember(String id);
}
