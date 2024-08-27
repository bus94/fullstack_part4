package com.ss.mybatis;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.mybatis.board.dao.MyBatisDAO;
import com.ss.mybatis.board.dto.MvcBoardDTO;

// Web Application 개발을 위한 풀스택 개발 기술
// Spring Framework기반 백엔드 개발 기술

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// root-context.xml 파일에서 생성한 mybatis bean(sqlsession)을 사용하기 위해서 SqlSession 인터페이스 타입의 객체를 생성한다.
	// 생성한 객체를 @Autowired를 이용해서 sqlSession 변수에 저장한다.
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("home()");
		
		return "list";
	}
	
	// inserOK 들어오면 DB로 바로 저장할 수 있도록 설정하는 메서드
	// form 태그로 넘어오는 값을 자동으로 MvcBoardDTO로 매칭시켜서 자동 저장해준다.
	@RequestMapping("/insertOK")
	public String insertOK(Model model, MvcBoardDTO dto) {
		logger.info("insertOK() : {}", dto);
		System.out.println(dto);
		
		// mapper : 자바와 xml 파일 연결시켜주는 역할
		// mapper로 사용할 인터페이스의 객체에 mybatis mapper를 얻어와서 넣어준다.
		MyBatisDAO mapper = sqlSession.getMapper(MyBatisDAO.class);
		
		// 메인 글을 저장하는 insert sql 명령을 실행한다.
		mapper.insert(dto);
		
		return "list";
	}
	
}
// 프로젝트 생성시
// 1. 외부라이브러리 다운 받기 (메이븐)
// 2. DB 정보 가져오는 파일 생성
// 3. sql을 저장하는 파일 생성
// 4. 2,3번 가지고 경로 설정 및 DB정보 저장 - config 파일
