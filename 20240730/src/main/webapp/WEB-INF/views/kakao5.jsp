<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.place-list {
	list-style-type: none;
	padding: 0;
}

.place-item {
	border-bottom: 1px solid #ddd;
	padding: 10px 0;
}

.place-item:last-child {
	border-bottom: none;
}

.place-title {
	font-size: 1.2em;
	font-weight: bold;
}

.place-address, .place-road-address, .place-phone {
	margin: 5px 0;
}
</style>

</head>
<body>

	<h1>검색 키워드: ${query}</h1>

	<!-- 지도 표시할 공간 -->
	<div id="map" style="width: 600px; height: 350px;"></div>

	<!-- appKey -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bbc6368132321b71187affbe4461b4d&libraries=services"></script>

	<!-- 지도를 이용해서 마커를 생성해서 띄우기 -->
	
	<!-- 주소를 이용해서 지도에 표시하도록 설정 -->
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(${x}, ${y}), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열을 생성
		var positions = [
			// var: 변수이름
			<c:forEach var="place" items="${list}" > 
				{
					content:'<div>${place.placeName}</div>',
					latlng:new kakao.maps.LatLng(${place.x}, ${place.y})
				},
			</c:forEach>
		];
		
		// 마커를 생성한다.
		for (var i = 0; i < positions.length; i ++) {
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
  				map: map, // 마커를 표시할 지도
   				position: positions[i].latlng // 마커의 위치
 			});
			
			// 인포윈도우 (InfoWindow) : 지도에 표시되는 작은 팝업창
			// - 마커나 특정 위치에 대한 추가 정보를 제공한다.
			// - 카카오에서 인포윈도우를 사용하면 지도를 클릭하거나 마우스가 마커 위치에 올라갈 때 정보를 표시해준다.	
			var infowindow = new kakao.maps.InfoWindow({
 			content :positions[i].content 
 			}); 
			
			// 마우스가 올라가고 내려갈 때 이벤트 추가
			// 클로저 : 함수가 호출될 때마다 새로운 변수를 생성하지 않고 이전 상태를 기억할 수 있는 것
			kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			
			 //인포윈도우를 표시하는 클로저를 만드는 함수입니다 
			 function makeOverListener(map, marker, infowindow) {
			 	return function() {
			    	infowindow.open(map, marker);
			 	};
			 }
			 
			 //인포윈도우를 닫는 클로저를 만드는 함수입니다 
			 function makeOutListener(infowindow) {
			 	return function() {
			    	infowindow.close();
				};
			 }
		}
		
	</script>

	<ul class="place-list">
		<c:forEach var="place" items="${list}">
			<li class="place-item">
				<div class="place-title">${place.placeName}</div>
				<div class="place-address">주소: ${place.addressName}</div>
				<div class="place-road-address">도로명 주소:
					${place.roadAddressName}</div>
				<div class="place-phone">전화번호: ${place.phone}</div>
			</li>
		</c:forEach>
	</ul>

</body>
</html>