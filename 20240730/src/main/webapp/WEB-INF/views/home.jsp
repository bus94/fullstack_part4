<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>카카오 Map</h1>

	<a href="${path}/kakao1">kakao1</a>
	<a href="${path}/kakao2">kakao2</a>

	<div id="map" style="width: 500px; height: 400px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bbc6368132321b71187affbe4461b4d"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(33.450701, 126.570667),
			level : 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
	
	<h3>카카오 맵 데모</h3>
	1. 좌표로 마커 표기하기 (일반) <a href="${path}/kakao3">kakao3</a><br>
	2. 주소-좌표 변환 
	<form action="${path}/kakao4" method="get" style="width:400px">
		주소 <input type="text" style="width:100%" name="address" value="서울특별시 서초구 서초대로74길 45">
			<input type="submit" value="확인">
	</form><br>
	3. 키워드를 이용해서 장소 출력
	<form action="${path}/kakao5" method="get">
		키워드: <input type="text" name="query" value="강남 약국" id="keyword" size="15" />
		<button type="submit">검색하기</button>
	</form>
	
</body>
</html>
