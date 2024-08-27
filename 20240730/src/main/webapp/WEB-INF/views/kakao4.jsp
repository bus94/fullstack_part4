<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 지도 표시할 공간 -->
	<div id="map" style="width: 100%; height: 350px;"></div>

	<!-- appKey -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bbc6368132321b71187affbe4461b4d&libraries=services"></script>

	<!-- 주소를 이용해서 지도에 표시하도록 설정 -->
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 주소 - 좌표 변환 객체를 생성
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder
				.addressSearch(
						'${address}',
						function(result, status) {
							// 정상적으로 검색이 완료되었다면
							if (status === kakao.maps.services.Status.OK) {
								var coords = new kakao.maps.LatLng(result[0].y,
										result[0].x);

								// 결과값을 이용해서 받은 위치를 마커로 표시한다.
								var marker = new kakao.maps.Marker({
									map : map,
									position : coords
								});

								// 인포윈도우로 장소에 대한 설명을 표시합니다
								var infowindow = new kakao.maps.InfoWindow(
										{
											content : '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
										});
								infowindow.open(map, marker);

								// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
								map.setCenter(coords);
							}

						});
	</script>

</body>
</html>