package com.ss.kakao;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.kakao.dto.MapInfo;
import com.ss.kakao.dto.PlaceDTO;
import com.ss.kakao.service.MapService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 서비스 객체 생성
	@Autowired
	private MapService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "home";
	}

	@RequestMapping(value = "/kakao1", method = RequestMethod.GET)
	public String kakao1() {
		logger.info("kakao 지도 마커 생성");

		return "kakao1";
	}

	@RequestMapping(value = "/kakao2", method = RequestMethod.GET)
	public String kakao2() {
		logger.info("kakao 접속 위치 생성");

		return "kakao2";
	}

	@RequestMapping(value = "/kakao3", method = RequestMethod.GET)
	public String kakao3(Model model) {
		logger.info("kakao 여러 마커 생성");

		List<MapInfo> mapList = new ArrayList<MapInfo>();

		// 1. map 들어갈 정보들을 저장
		mapList.add(new MapInfo("카카오", "제주시 어쩌구", 33.450705, 126.570677));
		mapList.add(new MapInfo("생태연못", "제주시 어쩌구", 33.450936, 126.569477));
		mapList.add(new MapInfo("텃밭", "제주시 어쩌구", 33.450879, 126.569940));
		mapList.add(new MapInfo("근린공원", "제주시 어쩌구", 33.451393, 126.570738));
		
		double x = 0.0;
		double y = 0.0;

		for(MapInfo map : mapList) {
			x += map.getX();
			y += map.getY();
		}
		
		// mapList의 크기로 나누어 평균 값을 구하도록 설정
		x = x / mapList.size();
		y = y / mapList.size();
		
		model.addAttribute("mapList", mapList);
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		
		return "kakao3";
	}
	
	@RequestMapping(value = "/kakao4")
	public String kakao4(String address, Model model) {
		logger.info("kakao4");
		
		model.addAttribute("address", address);
		
		return "kakao4";
	}
	
	@RequestMapping(value = "/kakao5")
	public String kakao5(@RequestParam("query") String query, Model model) {
		logger.info("kakao5");
		System.out.println("query: " + query);
		
		List<PlaceDTO> list = service.place(query);
		
		model.addAttribute("query", query);
		model.addAttribute("list", list);
		
		return "kakao5";
	}

}
