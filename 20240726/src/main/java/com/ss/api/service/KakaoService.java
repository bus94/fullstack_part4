package com.ss.api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {
	public String getToken(String code) {
		// 로그인을 하는 두 가지 과정 중에 한 가지는 동의 화면으로 동의함 → 인가코드 발급
		// 정상적으로 로그인하기 위해서 토큰 발급 해야한다.
		String host = "https://kauth.kakao.com/oauth/token";
		String token = "";

		try {
			// 위에 있는 String을 url 객체로 생성
			URL url = new URL(host);

			// 웹이랑 연결할 수 있는 객체 생성
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // URL 객체를 HttpURLConnection로 형변환

			// 전송방식 설정
			conn.setRequestMethod("POST");

			// Http 요청을 통해 서버에 데이터를 전송할 때 사용하는 설정 (get 요청은 setDoOutput(true) 필요 없다)
			// 데이터 기록 알려주기
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			StringBuilder sb = new StringBuilder();

			sb.append("grant_type=authorization_code");
			sb.append("&client_id=1ce4c3f2b8e5c07cc59f83248ea25d31");
			sb.append("&redirect_uri=http://localhost:8080/api/kakao/login");
			sb.append("&code=" + code);
			sb.append("&client_secret=as7ZUTjRhSrutIzwdp1m0VGYaVy4SdbW");

			System.out.println(sb.toString());
			bw.write(sb.toString());
			bw.flush(); // 보내기 (전송)

			// 응답 코드 확인하기
			int responseCode = conn.getResponseCode();
			System.out.println("응답 코드: " + responseCode);

			// 데이터가 응답되었다면 응답된 데이터를 스프링 안으로 가지고 들어와서 안에 있는 내용을 꺼내야한다.
			// 꺼낼 때 쓰는 Json 타입으로 키와 값으로 꺼낸다.
			// InputStreamReader 가지고 오는 방향 설정
			// getInputStream connection을 통해 가지고 온다 1바이트씩
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			// BufferedReader 클래스를 이용하면 한 줄 씩 읽어오는 기능
			String line = "";
			String result = "";

			// line이 br의 내용 한 줄을 읽어서 저장 (만약 없으면 false)
			while ((line = br.readLine()) != null) {
				result += line;
			}

			System.out.println("result: " + result);

			// json parsing
			JSONParser parser = new JSONParser();
			JSONObject elem = (JSONObject) parser.parse(result);

			String access_token = elem.get("access_token").toString();
			token = access_token;

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 위에서 토큰 변수를 생성
		// 토큰 값을 컨트롤러에게 전달해서 로그인되면 사용자 정보에 다시 한 번 접근해서 정보를 가져올 수 있도록 돌려준다(리턴한다).
		return token;
	}

	public String getUserInfo(String token) {
		System.out.println("getUserInfo() 실행");

		String host = "https://kapi.kakao.com/v2/user/me";
		String nickname = "";

		try {
			URL url = new URL(host);
			// url 이용해서 통신해야하므로 객체 생성 (캐스팅까지)
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Bearer " + token);

			int responseCode = con.getResponseCode();
			System.out.println("응답 코드: " + responseCode);
			System.out.println(con.getResponseMessage());

			if (responseCode == 200) {
				// json parsing
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("result2: " + result);

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(result);

				JSONObject pro = (JSONObject) obj.get("properties");

				// 닉네임 뽑기
				nickname = pro.get("nickname").toString();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nickname;
	}

	public void unlink(String token) {
		System.out.println("unlink() 실행");

		String host = "https://kapi.kakao.com/v1/user/unlink";
		String nickname = "";

		try {
			URL url = new URL(host);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Bearer " + token);

			int responseCode = con.getResponseCode();
			System.out.println("응답 코드: " + responseCode);
			
			// 응답을 할 때 종료된 id값이 반환된다.
			// id값을 확인
			 //Response: {"id":3638009163}
			
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}
}
