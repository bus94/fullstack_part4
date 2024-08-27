package com.ss.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MapInfo {
	// 지도에 대한 정보를 저장하는 클래스
	private String name;
	private String address;
	private double x;
	private double y;
	
}
