package com.ss.mybatis.board.dao;

import com.ss.mybatis.board.dto.MvcBoardDTO;

// mapper 연결에 사용하는 인터페이스
// 이 인터페이스의 풀 패키지 이름은 mvcboard.xml
// 파일의 namespace를 이용해서 dao 인터페이스에서 sql이 담긴 xml 파일을 연결해서 실행 할 수 있도록 변경
public interface MyBatisDAO {
	// mapper로 사용되는 인터페이스의 추상 메서드 형식으로 resultType id(parameter Type)와 같은 형식으로 만든다.
	
	void insert(MvcBoardDTO dto);
}
