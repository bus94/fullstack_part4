<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.request.contextPath }" />

<jsp:include page="/WEB-INF/views/common/header.jsp" />
<c:if test="${not empty msg}">
	<script>
		alert('${msg}');
	</script>
	<c:remove var="msg" />
</c:if>

<section id="content">
	<div class="row">
		<div class="col-12">
			<ul class="nav nav-tabs justify-content-center">
				<li class="nav-item col-4 text-center"><a
					class="nav-link active" href="${path}/member/login.do">로그인</a></li>
				<li class="nav-item col-4 text-center"><a class="nav-link"
					href="${path}/member/index.do">회원가입</a></li>
				<li class="nav-item col-4 text-center"><a class="nav-link"
					href="${path}/member/forgot.do">아이디/비번찾기</a></li>
			</ul>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title text-center">로그인</h3>
				</div>
				<div class="card-body">
					<form action="${path}/member/loginPro.do" method="post">
						<div class="form-group">
							<label for="loginId">아이디</label> <input type="text"
								class="form-control" id="loginId" name="id" required>
						</div>
						<div class="form-group">
							<label for="loginPassword">비밀번호</label> <input type="password"
								class="form-control" id="loginPassword" name="password"
								required>
						</div>
						<button type="submit" class="btn btn-primary btn-block">로그인</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />