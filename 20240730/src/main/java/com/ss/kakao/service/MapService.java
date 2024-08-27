package com.ss.kakao.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ss.kakao.dto.PlaceDTO;

// 스프링은 직접 객체를 생성하지 않고 스프링이 서블릿 컨테이너 공간 안에 Ioc 컨테이너  안에 객체들을 가지고 있다.
// 어노테이션을 이용하거나 <bean> 태그를 이용해서 등록을 시켜준다.
@Service
public class MapService {
	public List<PlaceDTO> place(String query) {
		List<PlaceDTO> places = new ArrayList<PlaceDTO>();

		// 네트워크 통신은 예외가 발생할 수 있다.
		try {
			// 스프링 프레임 워크에서 rest api를 이용해서 웹 서비스 통신할 때 사용되는 클래스
			// Http 요청을 보내고 응답을 받을 수 있다.
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			// 요청을 할 때 헤더를 설정
			// 헤더 설정은 인증 api 번호를 같이 보내야된다.
			headers.set("Authorization", "KakaoAK 1ce4c3f2b8e5c07cc59f83248ea25d31");

			String url = "https://dapi.kakao.com/v2/local/search/keyword.json?";
			url += "y=37.514322572335935&x=127.06283102249932&radius=20000";
			url += "&query=" + query;

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			// 실제 전송해서 응답 받는 클래스
			ResponseEntity<String> resp = rest.exchange(url, HttpMethod.GET, entity, String.class); // (url, 전송방식, 요청할때의
																									// 객체, 응답받는 타입)

			// 응답을 받아오면 String 타입으로 반환되기 때문에 String 타입으로 꺼내온다. 그때 getBody()로 꺼내온다.
			String responseBody = resp.getBody();
			System.out.println("resp: " + resp);

			// json 타입으로 파싱
			JSONParser par = new JSONParser();
			JSONObject json = (JSONObject) par.parse(responseBody);
			JSONArray doc = (JSONArray) json.get("documents");

			for (Object obj : doc) {
				JSONObject placeJson = (JSONObject) obj;
				PlaceDTO place = new PlaceDTO();
				place.setPlaceName((String) placeJson.get("place_name"));
				place.setDistance((String) placeJson.get("distance"));
				place.setPlaceUrl((String) placeJson.get("place_url"));
				place.setCategoryName((String) placeJson.get("category_name"));
				place.setAddressName((String) placeJson.get("address_name"));
				place.setRoadAddressName((String) placeJson.get("road_address_name"));
				place.setId((String) placeJson.get("id"));
				place.setPhone((String) placeJson.get("phone"));
				place.setCategoryGroupCode((String) placeJson.get("category_group_code"));
				place.setCategoryGroupName((String) placeJson.get("category_group_name"));
				place.setX(Double.parseDouble((String) placeJson.get("x")));
				place.setY(Double.parseDouble((String) placeJson.get("y")));
				
				// 리스트에 추가하기
				places.add(place);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return places;
	}
}
