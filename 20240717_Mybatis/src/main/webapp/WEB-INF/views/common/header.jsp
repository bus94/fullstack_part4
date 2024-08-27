<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--  프로젝트에 경로를 포함할 때 
프로젝트 명 경로가 필요할 때 jstl core를 이용해서
한 번 저장 후 사용한다. --> 

<c:set var = "path" 
	   value="${pageContext
	   		   .request
	   		   .contextPath}"/>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!--  내가 가지고 있는 파일 포함하기  -->
<link rel ="stylesheet"
	  href="${path}/resources/css/style.css"
	  type="text/css">
	  
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>

</head>
<body>
	<header>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="${path}/member/index.do">
					<img src="${path}/resources/images/logo-spring.png" 
					alt="스프링로고" width="80px"/>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" 
				data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active">
							<a class="nav-link" href="${path}/member/index.do">Home</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">게시판</a>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
								demo
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="${path}/member/index.do"> 회원 가입 </a>
								<a class="dropdown-item" href="${path}/member/memberList.do"> 회원 조회 </a>
							</div>
						</li>
					</ul>
				</div>
			</nav>
		</header>




