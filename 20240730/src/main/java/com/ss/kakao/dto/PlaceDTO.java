package com.ss.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
	private String placeName;
	private String distance;
	private String placeUrl;
	private String categoryName;
	private String addressName;
	private String roadAddressName;
	private String id;
	private String phone;
	private String categoryGroupCode;
	private String categoryGroupName;
	private double x;
	private double y;
}
