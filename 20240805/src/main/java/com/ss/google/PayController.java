package com.ss.google;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ss.google.dto.PaymentSuccessDTO;

@Controller
public class PayController {

	// 개발 진행할 때 사용하는 key
	private String key = "DEV0618E1C56E8D28A5D06093A0CD6AE1BB8E7AF";
	private String tid = ""; // 카카오가 보내준 승인 번호

	// 뷰페이지를 먼저 실행할 매핑
	@RequestMapping("/kakao")
	public String kakaoMain() {
		System.out.println("kakaoMain()");
		return "kakao/kakaoPay";
	}

	// 인가코드를 전송해서 성공하면 pg_token
	// 토큰값을 매개변수로 전송해준다. (리다이렉트)
	@RequestMapping("/kakao/success")
	public String kakaoSuccess(String pg_token, Model model) {
		System.out.println("카카오페이 성공");
		System.out.println("결제 token: " + pg_token);
		// 결제 승인 요청을 해서 페이지 보여주고 승인이 되면 결제 승인된 페이지로 이동!
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY " + key);
		headers.setContentType(MediaType.APPLICATION_JSON);

		String url = "https://open-api.kakaopay.com/online/v1/payment/approve";

		Map<String, Object> request = new HashMap<String, Object>();

		request.put("cid", "TC0ONETIME");
		request.put("tid", tid);
		request.put("partner_order_id", "1001");
		request.put("partner_user_id", "candoit");
		request.put("pg_token", pg_token);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(request, headers);

		ResponseEntity<String> response = rest.exchange(url, HttpMethod.POST, entity, String.class);

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());

		// JSON
		JSONParser par = new JSONParser();
		try {
			JSONObject json = (JSONObject) par.parse(response.getBody());
			String aid = (String) json.get("aid");
			tid = (String) json.get("tid");
			String cid = (String) json.get("cid");
			String partner_order_id = (String) json.get("partner_order_id");
			String item_name = (String) json.get("item_name");

			PaymentSuccessDTO dto = new PaymentSuccessDTO();
			dto.setAid(aid);
			dto.setCid(cid);
			dto.setItem_name(item_name);
			dto.setTid(tid);
			dto.setPartnerOrderId(partner_order_id);

			model.addAttribute("dto", dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "kakao/pay_success";
	}

	@RequestMapping("/kakao/pay")
	public String kakaoPayRe(Model model) {
		System.out.println("단건 결제 요청");

		// 1. 통신 클래스 객체 생성
		RestTemplate rest = new RestTemplate();

		// 2. 웹 통신에 헤더를 설정하는 객체
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY " + key);
		headers.setContentType(MediaType.APPLICATION_JSON);

		String url = "https://open-api.kakaopay.com/online/v1/payment/ready";

		Map<String, Object> request = new HashMap<String, Object>();

		request.put("cid", "TC0ONETIME");
		request.put("partner_order_id", "1001");
		request.put("partner_user_id", "candoit");
		// 상품명
		request.put("item_name", "초코파이");
		// 수량
		request.put("quantity", "1");
		request.put("total_amount", "2200");
		request.put("vat_amount", "200");
		request.put("tax_free_amount", "0");
		request.put("approval_url", "http://localhost:8080/google/kakao/success");
		request.put("fail_url", "http://localhost:8080/google/kakao/fail");
		request.put("cancel_url", "http://localhost:8080/google/kakao/cancel");

		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(request, headers);

		ResponseEntity<String> response = rest.exchange(url, HttpMethod.POST, entity, String.class);

		System.out.println("getStatusCode: " + response.getStatusCode());
		System.out.println("getBody: " + response.getBody());

		// JSON
		JSONParser par = new JSONParser();

		try {
			JSONObject json = (JSONObject) par.parse(response.getBody());
			String nextUrl = (String) json.get("next_redirect_pc_url");
			tid = (String) json.get("tid");
			model.addAttribute("nextUrl", nextUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "kakao/kakaoPay";
	}
}
