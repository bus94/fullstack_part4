<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section id = "container"> 
	<h2 style="text-align:center;">회원가입</h2>
	<div id="demo-contanier">
		<form id="devFrm" name="devFrm" method="post">
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label align-self-center text-center">아이디</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="id" name="id" value="test">
				</div>
			</div>
			
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label align-self-center text-center">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" value="홍길동">
				</div>
			</div>
			
			
			<div class="form-group row">
				<label for="devAge" class="col-sm-2 col-form-label align-self-center text-center">나이</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="age" name="age"  value="21">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label align-self-center text-center">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" checked="checked"
							id="gender" value="M"> <label 
							class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							id="gender" value="F"> <label
							class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			
				<div class="form-group row">
				<label class="col-sm-2 col-form-label">좋아하는 언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" checked="checked" type="checkbox" name="devLang" id="devLang0" value="Java">
					<label class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input"  type="checkbox" name="devLang" id="devLang1" value="C">
					<label class="form-check-label" for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" checked="checked" type="checkbox" name="devLang" id="devLang2" value="Javascript">
					<label class="form-check-label"  for="devLang2">JavaScript</label>
					</div>
				</div>
			</div>
		</form>
		<div class="list-group">
			<button type="button"
			onclick="demo('memberServlet.do')"
			class="list-group-item list-group-item-action"
			>
			 Servlet HttpReuqest로 파라메터 처리
			</button>		
		</div>
		<div class="list-group">
			<button type="button"
			onclick="demo('memberParams.do')"
			class="list-group-item list-group-item-action"
			>
			@ReqeustParam 어노테이션 활용 파라메터 처리
			</button>		
		</div>
		<div class="list-group">
			<button type="button"
			onclick="demo('memberCommand2.do')"
			class="list-group-item list-group-item-action"
			>
			클래스타입을 활용해서 파라메터 처리
			</button>		
		</div>
		
		<div class="list-group">
			<button type="button"
			onclick="demo('memberMap.do')"
			class="list-group-item list-group-item-action"
			>
			Map을 활용해서 파라메터 처리
			</button>		
		</div>
		
	</div>
</section>

<script>
	function demo(url) {
		//console.log(url);
		// 여러개의 버튼에서 url이 넘어오기 
		// 때문에 submit 하기 전에 변경된
		// url들을 각각 처리할 수있도록! 
		$("#devFrm").attr("action",url);
		$("#devFrm").submit();
	}

</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>




