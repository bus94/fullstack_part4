package com.ss.google.service;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ss.google.dto.YoutubeDTO;

@Service
public class GoogleService {
	private String clientId = "247891547030-il40nn552qmdk40327uc9h3vufj3of2k.apps.googleusercontent.com";
	private String clientPw = "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";
	private String api = "AIzaSyB3wiEL_gN_C1Dn5Ml8ZEoQLUfD95nUfcc";
	
	public ArrayList<YoutubeDTO> youtube() {
		ArrayList<YoutubeDTO> list = new ArrayList<YoutubeDTO>();
		
		try {
			// url 작성시 q 속성은 변수를 이용해서 가져오기 (현재 '뉴스'로 설정)
			String url = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=뉴스&key=" + api;
			
			// Restful 웹 서비스
			// - Http를 통해 데이터와 기능을 제공
			// - 웹 서비스 중 하나로 보통 json 형식으로 데이터를 주고 받는다.
			// http 메서드
			// get 요청 : 서버에서 데이터를 가져올 때
			// post 요청 : 서버에 데이터를 보내는 요청
			// put 요청 : 서버에 있는 데이터를 업데이트 요청
			// delete 요청 : 서버에 데이터를 삭제하는 요청
			RestTemplate rest = new RestTemplate();
			
			// http get 요청을 보내고 응답을 문자열로 반환한다.
			String resp = rest.getForObject(url, String.class);
			// System.out.println(resp);
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(resp);
			JSONArray items = (JSONArray) jsonObject.get("items");
			
			for(Object item : items) {
				JSONObject video = (JSONObject) item;
				JSONObject snippet = (JSONObject) video.get("snippet");
				JSONObject ids = (JSONObject) video.get("id");
				
				YoutubeDTO you = new YoutubeDTO();
				you.setVideoId((String) ids.get("videoId"));
				you.setTitle((String) snippet.get("title"));
				you.setDescription((String) snippet.get("description"));
				
				JSONObject thu = (JSONObject) snippet.get("thumbnails");
				if(thu != null) {
					JSONObject def = (JSONObject) thu.get("high");
					 you.setThumbnails((String) def.get("url"));
				}
				list.add(you);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
