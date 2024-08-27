<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>카카오 로그인</h1>
	<a
		href="https://kauth.kakao.com/oauth/authorize?client_id=1ce4c3f2b8e5c07cc59f83248ea25d31&redirect_uri=http://localhost:8080/api/kakao/login&response_type=code">카카오
		로그인</a>

	<c:if test="${nickname != null}">
		<h2>${nickname}의 kakao 로그인 완료</h2>
	</c:if>
	
	
	<h1>네이버 로그인</h1>
	<a
		href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=Vrb_8gFvQuYhvSpSlidB&redirect_uri=http://localhost:8080/api/naver/login">네이버
		로그인</a>
	<c:if test="${navernickname != null}">
		<h2>${navernickname}의 naver 로그인 완료</h2>
	</c:if>
</body>
</html>
