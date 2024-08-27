package com.ss.api.service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class NaverService {
	private String Client_ID = "Vrb_8gFvQuYhvSpSlidB";
	private String Client_Secret = "td9mhgcax4";
	
	public String getToken(String code) {
		String host = "https://nid.naver.com/oauth2.0/token";
		String token = "";
		try {
			URL url = new URL(host);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			
			sb.append("");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return token;
	}
}
