package com.ss.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoutubeController {
	private String apiKey = "";
	
	// 클라이언트 아이디
	// 클라이언트 비밀번호 저장
	
	
	@RequestMapping("/youtube")
	public String youtubeMain() {
		// 1. 구글 라이브러리 설정
		// pom.xml
		
		// 2. 구글 설정
		//	구글 콘솔(클라우드 플랫폼)
		// 프로젝트 생성
		// 유튜브에만 접속 할거면 구글에서 제공하는 API키를 생성
		
		return "youtubePage";
	}
}
