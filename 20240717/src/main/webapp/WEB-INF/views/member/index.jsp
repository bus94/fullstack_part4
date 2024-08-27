<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<!-- 삭제가 되거나 추가가 되거나 하니, alert창으로 결과를 출력할 수 있는 코드 -->
<c:if test="${not empty msg}">
	<script>
		alert('${msg}');
	</script>
	<c:remove var="msg" />
</c:if>

<section id="container">
	<h2 style="text-align: center;">회원가입</h2>
	<div id="demo-container">
		<form id="devFrm" method="post">
			<input type="text" class="form-control" placeholder="아이디 (4글자이상)"
				name="id" id="id" required> <input type="text"
				class="form-control" placeholder="비밀번호" name=password id="password"
				required> <input type="text" class="form-control"
				placeholder="이름" name="name" id="name" required> <input
				type="number" class="form-control" placeholder="나이" name="age"
				id="age"> <input type="email" class="form-control"
				placeholder="이메일" name="email" id="email" required> <input
				type="tel" class="form-control" placeholder="전화번호 (예:01012345678)"
				name="phone" id="phone" maxlength="11" required> <input
				type="text" class="form-control" placeholder="주소" name="address"
				id="address"> <select class="form-control" name="gender"
				required>
				<option value="" disabled selected>성별</option>
				<option value="M">남</option>
				<option value="F">여</option>
			</select>
			<div class="form-check-inline form-check">
				취미 : <input type="checkbox" class="form-check-input" name="hobby"
					id="hobby0" value="운동"><label for="hobby0"
					class="form-check-label">운동</label> <input type="checkbox"
					class="form-check-input" name="hobby" id="hobby1" value="등산"><label
					for="hobby1" class="form-check-label">등산</label> <input
					type="checkbox" class="form-check-input" name="hobby" id="hobby2"
					value="독서"><label for="hobby2" class="form-check-label">독서</label>
				<input type="checkbox" class="form-check-input" name="hobby"
					id="hobby3" value="게임"><label for="hobby3"
					class="form-check-label">게임</label> <input type="checkbox"
					class="form-check-input" name="hobby" id="hobby4" value="여행"><label
					for="hobby4" class="form-check-label">여행</label>
			</div>
			<br /> <input type="reset" class="btn btn-outline-success"
				value="취소">
		</form>

		<div class="list-group">
			<button type="button" onclick="demo('memberEnroll.do');"
				class="list-group-item list-group-item-action">가입하기</button>
		</div>
		<hr>
		<br>

		<h4>조회 하기</h4>
		<form action="${path}/member/memberSearch.do" method="get">
			<input type="text" class="form-control" placeholder="아이디 (4글자이상)"
				name="id" id="id" required> <br> <input type="submit"
				class="btn btn-outline-success" value="조회">
		</form>
		<hr>
		<br>

		<h4>탈퇴 하기</h4>
		<form action="${path}/member/memberDel.do" method="post">
			<input type="text" class="form-control" placeholder="아이디 (4글자이상)"
				name="id" id="id" required> <br> <input type="submit"
				class="btn btn-outline-success" value="탈퇴">
		</form>

	</div>
</section>

<!-- <section id="container">
	<h2 style="text-align: center;">회원가입</h2>
	<div id="demo-contanier">

		<form id="devFrm" name="devFrm" method="post">
			<div class="form-group row">
				<label for="devName"
					class="col-sm-2 col-form-label align-self-center text-center">아이디</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="id" name="id"
						value="test">
				</div>
			</div>

			<div class="form-group row">
				<label for="devName"
					class="col-sm-2 col-form-label align-self-center text-center">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name"
						value="홍길동">
				</div>
			</div>


			<div class="form-group row">
				<label for="devAge"
					class="col-sm-2 col-form-label align-self-center text-center">나이</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="age" name="age"
						value="21">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label align-self-center text-center">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							checked="checked" id="gender" value="M"> <label
							class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							id="gender" value="F"> <label class="form-check-label"
							for="devGender1">여</label>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">좋아하는 언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" checked="checked" type="checkbox"
							name="devLang" id="devLang0" value="Java"> <label
							class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
							id="devLang1" value="C"> <label class="form-check-label"
							for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" checked="checked" type="checkbox"
							name="devLang" id="devLang2" value="Javascript"> <label
							class="form-check-label" for="devLang2">JavaScript</label>
					</div>
				</div>
			</div>
		</form>

		<div class="list-group">
			<button type="button" onclick="demo('memberServlet.do')"
				class="list-group-item list-group-item-action">Servlet
				HttpRequest로 파라메터 처리</button>
		</div>

		<div class="list-group">
			<button type="button" onclick="demo('memberParams2.do')"
				class="list-group-item list-group-item-action">
				@RequestParam 2 어노테이션 활용 파라메터 처리</button>
		</div>
		
		<div class="list-group">
			<button type="button" onclick="demo('memberParams.do')"
				class="list-group-item list-group-item-action">
				@RequestParam 어노테이션 활용 파라메터 처리</button>
		</div>
		
		<div class="list-group">
			<button type="button" onclick="demo('memberCommand.do')"
				class="list-group-item list-group-item-action">
				클래스타입을 활용해서 파라메터 처리</button>
		</div>
		
		<div class="list-group">
			<button type="button" onclick="demo('memberCommand2.do')"
				class="list-group-item list-group-item-action">
				클래스타입2을 활용해서 파라메터 처리</button>
		</div>
		
		<div class="list-group">
			<button type="button" onclick="demo('memberMap.do')"
				class="list-group-item list-group-item-action">
				Map을 활용해서 파라메터 처리</button>
		</div>
		
		<div class="list-group">
			<button type="button" onclick="demo('memberJoin.do')"
				class="list-group-item list-group-item-action">
				회원가입 하기</button>
		</div>

	</div>
</section> -->

<script>
	function demo(url) {
		// 여러 개의 버튼에서 url이 넘어오기 때문에 submit을 하기 전에 변경된 url들을 각각 처리할 수 있도록!
		/* $("#devFrm").attr("action","${path}/member/" + url); */
		$("#devFrm").attr("action", url);
		$("#devFrm").submit(); // input에 입력받은 값을 컨트롤러로 보냄
	}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />