package com.ss.mvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ss.mvc.member.dto.Member;
import com.ss.mvc.member.service.MemberService;

// Model에서 저장할 때 loginMember 단어로 저장을 하면 세션으로 자동 저장될 수 있도록 처리해주는 어노테이션
@SessionAttributes("loginMember")
@Controller
public class MemberController {
	// Member들을 관리하는 Service
	@Autowired
	private MemberService service;
	
}
