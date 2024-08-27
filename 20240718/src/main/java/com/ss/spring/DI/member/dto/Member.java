package com.ss.spring.DI.member.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@ToString
//@Getter
//@Setter
@Data // get/setter와 toString 모두 생성해준다
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String id;
	private String password;
	private String name;
	private int age;
	private String gender;
	private String phone;
	private String address;
	private String email;
	private String[] hobby; // 축구,야구,영화보기
	private Date enrollDate;
}
