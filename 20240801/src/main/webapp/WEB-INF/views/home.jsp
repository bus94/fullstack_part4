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

	<!-- SMTP(Simple Mail Transfer Protocol) : 이메일을 인터넷을 통해서 보내고 받는데 사용하는 프로토콜(규칙) 
		SMTPS (암호화 프로토콜을 사용해서 메일 암호화하여 전송하는 것)
		
		IMAP (Internet Messaging Access Protocol) : 서버에서 이메일을 읽는 프로토콜
		- 어떤 상황에서든 동일한 내용을 읽어올 수 있다.
		
		POP3 (Post Office Protocol) : 사용자의 기기로 이메일을 다운로드하여 읽는 프로토콜 
		- 다운로드한 내용은 서버에서 삭제되기 때문에 동일한 기기에서만 이메일 확인 가능
		
		1. 설정
		아이디 : 네이버 로그인 아이디
		비밀번호 : 네이버 로그인 비밀번호
		SMTP 서버명 : smtp.naver.com
		SMTP 포트 : 465
		
		2. 위의 설정 정보를 객체로 생성해서 보내기 위해 bean 등록을 해야된다.
			root-context.xml
			
		// 구글은 2단계 인증까지 하고 앱 비밀번호 발급받아서 사용할 수 있다.
		// 구글 비밀번호 (앱 비밀번호)
		// qjmf oxeu kzzm rmnm
		// 구글 아이디 q9tkd27@gmail.com
	-->
	<h2>이메일 인증</h2>
</body>
</html>
