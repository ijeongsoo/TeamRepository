<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!-- ----------------------------------------------------------------------------------------------------- -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,700" rel="stylesheet" type="text/css">
<!-- CSS reset -->
<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/reset.css" type="text/css">
<!-- Resource style -->
<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/style.css"type="text/css">

<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/style_4.css"type="text/css">
<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/style_5.css" type="text/css">
<!-- Modernizr -->
<script src="<%=application.getContextPath()%>/resources/js/modernizr.js" type="text/javascript"></script>

<!-- ----------------------------------------------------------------------------------------------------- -->
<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>

<!-- Theme CSS -->
<link
	href="<%=application.getContextPath()%>/resources/css/freelancer.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom Fonts -->
<link
	href="<%=application.getContextPath()%>/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">



<title>Garfish Project</title>
<script>
$(document).ready(
		function() {
			$("#mid").keyup(function() {
				$("#canUse").removeClass("alert-danger");
				$("#canUse").removeClass("alert-warning");
				$("#canUse").removeClass("alert-info");
				$("#canUse").text('중복확인해주세요');
			});

			$("#checkButton").click(
					function() {
						var idReg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
						
						if (idReg.test($("#joinMid").val()) && $("#joinMid").val().length <= 100) {
							$.ajax({
										'url' : "check_id",
										'data' : {
											'mid' : $("#joinMid").val()
											   },
										'type' : "POST",
										'success' : function(data) {
											if (data.result == 1) {
												alert('사용 가능한 아이디 입니다.');
												$("#canUse").removeClass(
														"alert-danger");
												$("#canUse").removeClass(
														"alert-warning");
												$("#canUse").addClass(
														"alert-info");
												$("#canUse").text(
														'중복확인 되었습니다.');

											} else if (data.result == 0) {
												alert('사용 불가능한 아이디 입니다. ');
												$("#canUse").removeClass(
														"alert-info");
												$("#canUse").removeClass(
														"alert-warning");
												$("#canUse").addClass(
														"alert-danger");
												$("#canUse").text(
														'다시 입력해주세요');
											} else {
												alert('아이디를 입력해 주세요 ');
												$("#canUse").removeClass(
														"alert-info");
												$("#canUse").removeClass(
														"alert-danger");
												$("#canUse").addClass(
														"alert-warning");
												$("#canUse").text(
														'아이디를 입력해주세요.');
											}
										}
									});
						} else {
							alert('ID는 Email형식에 맞춰 작성해주세요.');
							$("#canUse").addClass("alert-danger");
							$("#canUse").text('ID는 Email형식에 맞춰 작성해주세요.');
						}
					});
		});

	$(function() {
		var pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}/;
		$("#mPasswordCheck")
				.keyup(
						function() {
							if ($("#mPasswordCheck").val() == ''
									|| $("#joinMpassword").val() == '') {
								$("#pwcheck").removeClass("alert-danger");
								$("#pwcheck").removeClass("alert-info");
								$("#pwcheck").text('비밀번호를 한번 더 입력해주세요.');
							} else if ($("#mPasswordCheck").val() == $(
									"#joinMpassword").val()) {

								if (pwReg.test($("#joinMpassword").val())
										&& $("#joinMpassword").val().length <= 20) {
									$("#pwcheck").removeClass("alert-danger");
									$("#pwcheck").removeClass("alert-info");
									$("#pwcheck").addClass("alert-info");
									$("#pwcheck").text('비밀번호가 일치합니다.');
								} else {
									$("#pwcheck").addClass("alert-danger");
									$("#pwcheck")
											.text(
													'비밀번호는 영어, 숫자, 특수문자 조합 8~20자리 이어야 합니다.');
								}
							} else {
								$("#pwcheck").addClass("alert-danger");
								$("#pwcheck").text('비밀번호가 일치하지 않습니다.');
							}
						});

		$("#joinMpassword")
				.keyup(
						function() {
							if ($("#mPasswordCheck").val() == ''
									|| $("#joinMpassword").val() == '') {
								$("#pwcheck").removeClass("alert-danger");
								$("#pwcheck").removeClass("alert-info");
								$("#pwcheck").text('비밀번호를 한번 더 입력해주세요.');
							} else if ($("#mPasswordCheck").val() == $(
									"#joinMpassword").val()) {
								if (pwReg.test($("#joinMpassword").val())
										&& $("#joinMpassword").val().length <= 20) {
									$("#pwcheck").removeClass("alert-danger");
									$("#pwcheck").removeClass("alert-info");
									$("#pwcheck").addClass("alert-info");
									$("#pwcheck").text('비밀번호가 일치합니다.');
								} else {

									$("#pwcheck").addClass("alert-danger");
									$("#pwcheck")
											.text(
													'비밀번호는 영어, 숫자, 특수문자 조합 8~20자리 이어야 합니다.');
								}
							} else {
								$("#pwcheck").addClass("alert-danger");
								$("#pwcheck").text('비밀번호가 일치하지 않습니다.');
							}

						});

	});

	
</script>


<script>
	function check() {
		if ($("#canUse").text() != "중복확인 되었습니다.") {
			alert('중복확인 해주시기 바랍니다.');
			return false;
		} else if ($("#pwcheck").text() != "비밀번호가 일치합니다.") {
			alert('비밀번호불일치');
			return false;
		} else {
			alert('회원가입이 완료되었습니다.');
			return true;
		}
	}
</script>



<script type="text/javascript">
	function fileChange(event) {
		if ($("#mattach")[0].files.length != 0) {
			var moriginalfilename = $("#mattach")[0].files[0].name;
			$("#imgFileView")[0].src = URL.createObjectURL(event.target.files[0]);
			$("#imgFileView").attr("style", "height:100px");
			$("#sattachBtn").text("수정");
			$("#imgcheck").removeClass("alert-danger");
			$("#imgcheck").addClass("alert-info");
			$("#imgcheck").text('사진이 선택되었습니다.');
		}
	}
</script>


<script>
	function check() {
		if ($("#canUse").text() != "사용가능합니다.") {
			alert('중복 및 통신 확인부탁드립니다.');
			return false;
		} else if ($("#imgcheck").text() != "사진이 선택되었습니다.") {
			alert('사진을 선택해 주세요.');
			return false;
		} else {
			alert('등록이 완료되었습니다.');
			return true;
		}
	}
</script>
</head>

<body id="page-top" class="index">
	<div id="skipnav">
		<a href="#maincontent">Skip to main content</a>
	</div>

	<!-- Navigation -->
	<nav id="mainNav"
		class="navbar navbar-default navbar-fixed-top navbar-custom main-nav">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse" 
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand" href="<%=application.getContextPath()%>/" >Garfish Project</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	
	<!-- Header -->
	<header>
		<div class="container" id="maincontent" tabindex="-1">
			<div class="row">
				<div class="col-lg-12">
					<img class="img-responsive" src="resources/image/profile.png"
						alt="">
					<div class="intro-text">
						<div class="col-lg-12 text-center">
							<h2>회원가입</h2>
							<hr class="star-light1">
						</div>
						
						<span class="skills">회원가입 후, 드론에 첫 발을 내딛어보세요!</span>
					</div>
				</div>
			</div>
		</div>
	</header>




	<form id="formjoin" class="cd-form floating-labels"
		action="<%=application.getContextPath()%>/joinPost" method="post"
		enctype="multipart/form-data">
		<!--중복확인하려면 form안에 옆에 것 넣어주기 onsubmit="return check()" -->
		<fieldset>
			<legend>회원가입 양식</legend>
			<div class="icon">
				<label class="cd-label" for="joinMid">ID (E-mail)</label>
				<input class="user" name="mid" id="joinMid" type="text" required>
				<a id="checkButton" class="btn btn-warning">ID 중복확인</a>
			</div>
			

			<div class="icon">
				<label class="cd-label" for="joinMpassword11">PW</label>
				<input class="key" id="joinMpassword" name="mpassword" type="password" required>
			</div>


			<div class="icon">
				<label class="cd-label"id="pwcheck" name="pwcheck" for="mPasswordCheck">비밀번호를 한번 더 입력해주세요.</label>
				<input class="key" id="mPasswordCheck" name="mPasswordCheck" type="password"  required>
			</div>
			

			<div class="icon">
				<label class="cd-label" for="mname">이름</label>
				<input class="user" id="mname" name="mname" type="text"  required>
			</div>
			
			

			<div class="icon">
				<span>
				<img id="imgFileView"/> 
				<label for="mattach" id="checkButton" class="btn btn-warning">사진등록</label> 
				<input type="file" id="mattach" style="visibility: hidden" onchange="fileChange(event)" name="mattach" /> 
				</span>
				<span>
				<label class="help-block" id="imgcheck" name="imgcheck">사진을 반드시 첨부해 주세요.</label>
				</span>
			</div>


			<span>
				
					<input id="join" name="join" value="회원가입" class="btn btn-lg btn-outline" type="submit" />
				
				
					<input type="reset" class="btn btn-lg btn-outline" value="Reset" />
			
			</span>
		</fieldset>
	</form>
<script src="<%=application.getContextPath()%>/resources/js/jquery-2.1.1.js" type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/js/main.js" type="text/javascript"></script>
</body>
</html>






