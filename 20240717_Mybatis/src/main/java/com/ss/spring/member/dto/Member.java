package com.ss.spring.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// get/setter ,toString
// 까지 자동으로 생성해주는 것!
@Data
// 기본생성자 
@NoArgsConstructor
// 매개변수를 모두 받는 생성자
@AllArgsConstructor
public class Member {

	// 클래스 멤버 변수는 작성!
	private String id;
	private String name;
	private int age;
	private String gender;
	private String address; 
	private String[] devLang; 
	
	

	                
	
	
	
}
