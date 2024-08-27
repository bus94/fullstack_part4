<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Home</title>

<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
iframe {
	border: none;
}

.thumbnail-img {
	cursor: pointer
}
</style>

</head>
<body>
	http://localhost:8080/google/board/fileUpload
	<%-- <div class="container">
		<div class="row">
			<!-- foreach 문을 작성할 때 배열의 상태 혹은 정보를 저장해서 사용할 수 있다. - varStatus="변수명" -->
			<c:forEach var="video" items="${list}" varStatus="status">
				<c:if test="${status.index < 4}">
					<div class="col-md-3 mb-4">
						<div class="card">
							<img src="${video.thumbnails}" class="card-img-top thumbnail-img"
								alt="${video.title}" data-toggle="modal"
								data-target="#videoModal${status.index}">
							<div class="card-body">
								<h5 class="card-title">
									<c:choose>
										<c:when test="${fn:length(video.title) > 30}">
											${fn:substring(video.title, 0, 30)}...
										</c:when>
										<c:otherwise>
											${video.title}
										</c:otherwise>
									</c:choose>
								</h5>
							</div>
						</div>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="videoModal${status.index}"
						tabindex="-1" role="dialog"
						aria-labelledby="videoModalLabel${status.index}"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="videoModalLabel${status.index}">${video.title}</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<div class="modal-body">
									<iframe id="videoFrame${status.index}" width="100%"
										height="320" src="" allowfullscreen></iframe>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div> --%>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(
				function() {
					<c:forEach var="video" items="${list}" varStatus="status">
					$('#videoModal${status.index}').on(
							'show.bs.modal',
							function(e) {
								var videoId = '${video.videoId}';
								var videoSrc = 'https://www.youtube.com/embed/'
										+ videoId + '?autoplay=1';
								$('#videoFrame${status.index}').attr('src',
										videoSrc);
							});
					$('#videoModal${status.index}')
							.on(
									'hide.bs.modal',
									function(e) {
										$('#videoFrame${status.index}').attr(
												'src', '');
									});
					</c:forEach>
				});
	</script>
</body>
</html>

<!-- 
	<h1>유튜브 조회하기</h1>
	<%-- <p>${list}</p> --%>
	<c:forEach var="video" items="${list}">
		<h5>${video.title}</h5>
		<img src="${video.thumbnails}">
	</c:forEach>
 -->