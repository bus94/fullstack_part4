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
	<h1>카카오페이 성공 결제 내역 출력</h1>
	<p>AID: ${dto.aid}</p>
	<p>TID: ${dto.tid}</p>
	<p>CID: ${dto.cid}</p>
	<p>상품명: ${dto.item_name}</p>
</body>
</html>