<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<!-- 자바스크립트를 이용해서 결제API 쉽게 구현할 수 있도록 사용하는 라이브러리 포함 
	IMP.init() 초기화 메서드
	IMP.request_pay() 결제 메서드
-->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
	<!-- 아임포트 결제 시 라이브러리 받기 -->
	<h1>아임포트 결제</h1>
	<button onclick="requestPay()">결제하기</button>
</body>
</html>