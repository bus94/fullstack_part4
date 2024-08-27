package com.ss.mvc.member.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int no;
	private String id;
	private String password;
	private String role;		// 관리자, 일반 사용자 구분
	private String name;
	private String phone;
	private String email;
	private String address;
	private String hobby;
	private String status;
	private Date enrollDate;	// 회원 가입 날짜
	private Date modifyDate;	// 정보 수정된 날짜
}
