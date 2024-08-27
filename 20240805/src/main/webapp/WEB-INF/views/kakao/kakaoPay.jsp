<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 1. 카카오페이 개발자 센터 -->
	<h1>카카오페이 결제 기능</h1>
	<h4>결제 진행</h4>
	<form action="${path}/kakao/pay">
		<input type="submit" value="결제요청" />
	</form>
	
	<h4>결제 페이지</h4>
	<p>결제 준비가 완료 되었습니다. 아래 링크 클릭</p>
	 <a href="${nextUrl}" >결제</a>
</body>
</html>