<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta name="description" content="">
<meta name="author" content="">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Garfish Project</title>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>


<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/reset.css">
<!-- CSS reset -->
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/style.css">

<!-- CSS reset -->
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/style_3.css">
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/style_4.css">
<!-- Resource style -->


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


<script
	src="<%=application.getContextPath()%>/resources/js/modernizr.js"></script>
	<script
	src="<%=application.getContextPath()%>/resources/js/modernizr_3.js"></script>
<!-- Modernizr -->

<script>
	$(function() {
		var pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}/;
		$("#mPasswordCheck")
				.keyup(
						function() {
							if ($("#mPasswordCheck").val() == ''
									|| $("#mpassword").val() == '') {
								$("#pwcheck").removeClass("alert-danger");
								$("#pwcheck").removeClass("alert-info");
								$("#pwcheck").text('비밀번호를 한번 더 입력해주세요.');
							} else if ($("#mPasswordCheck").val() == $(
									"#mpassword").val()) {

								if (pwReg.test($("#mpassword").val()) && $("#mpassword").val().length<=20) {
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

		$("#mpassword")
				.keyup(
						function() {
							if ($("#mPasswordCheck").val() == ''
									|| $("#mpassword").val() == '') {
								$("#pwcheck").removeClass("alert-danger");
								$("#pwcheck").removeClass("alert-info");
								$("#pwcheck").text('비밀번호를 한번 더 입력해주세요.');
							} else if ($("#mPasswordCheck").val() == $(
									"#mpassword").val()) {
								if (pwReg.test($("#mpassword").val()) && $("#mpassword").val().length<=20) {
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
		if ($("#pwcheck").text() != "비밀번호가 일치합니다.") {
			alert('비밀번호불일치');
			return false;
		} else {
			alert('비밀번호 변경이 완료되었습니다.');
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
				<a class="navbar-brand" href="#page-top">Garfish Project</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->


	<!-- Contact Section -->
	<section id="contact" style="padding-top: 150px">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>비밀번호 변경</h2>
					<hr class="star-primary">
					<p>해당링크로 비밀번호 변경시 해당링크는 폐쇄됩니다.</p><br>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
					<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
					<form action="<%=application.getContextPath()%>/updatePasswd" method="post" onsubmit="return check()" enctype="multipart/form-data"  novalidate>
						<input type="hidden" id="mid" name="mid" value="${member.mid}">
						<input type="hidden" id="mname" name="mname" value="${member.mname}">
						<input type="hidden" id="moriginalfilename" name="moriginalfilename" value="${member.moriginalfilename}">
						<input type="hidden" id="msavedfilename" name="msavedfilename" value="${member.msavedfilename}">
						<input type="hidden" id="mfiletype" name="mfiletype" value="${member.mfiletype}">
						<input style="display: none;" type="file" id="mattach" name="mattach" value="">
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label for="name">비밀번호</label> <input style="font-size: 20px" class="form-control"  type="password"
									 placeholder="비밀번호" id="mpassword" name="mpassword" required
									data-validation-required-message="비밀번호를 입력해 주세요.">
								<p style="color: red" class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label for="email">비밀번호 확인</label> <input  style="font-size: 20px" type="password"
									class="form-control" placeholder="비밀번호 확인" id="mPasswordCheck"
									required
									data-validation-required-message="비밀번호 확인을 입력해 주세요.">
								<p style="color: red" class="help-block text-danger"></p>
							</div>
						</div>
						
						<br>
						<div id="success"></div>
						<label
							style="margin-top: 10px; margin-bottom: 20px" id="pwcheck" name="pwcheck" class="">비밀번호를
							한번 더 입력해주세요.</label>
						<div align="center" class="row">
							<div class="form-group col-xs-12">
								<button type="submit" class="btn btn-success btn-lg">변경하기</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer class="text-center">
		<div class="footer-above">
			<div class="container">
				<div class="row">
					<div class="footer-col col-md-4">
						<h3>위치</h3>
						<p>
							(05717) 서울시 송파구 중대로 135, IT벤처타워 서관 12층 <br>한국 소프트웨어 산업협회
						</p>
					</div>
					<div class="footer-col col-md-4">
						<h3>Around the Web</h3>
						<ul class="list-inline">
							<li><a href="http://www.facebook.com" class="btn-social btn-outline"><span
									class="sr-only">Facebook</span><i class="fa fa-fw fa-facebook"></i></a>
							</li>
							<li><a href="http://www.google.com" class="btn-social btn-outline"><span
									class="sr-only">Google Plus</span><i
									class="fa fa-fw fa-google-plus"></i></a></li>
							<li><a href="http://www.twitter.com" class="btn-social btn-outline"><span
									class="sr-only">Twitter</span><i class="fa fa-fw fa-twitter"></i></a>
							</li>
							<li><a href="http://www.linkedin.com" class="btn-social btn-outline"><span
									class="sr-only">Linked In</span><i class="fa fa-fw fa-linkedin"></i></a>
							</li>
							<li><a href="http://www.dribble.com" class="btn-social btn-outline"><span
									class="sr-only">Dribble</span><i class="fa fa-fw fa-dribbble"></i></a>
							</li>
						</ul>
					</div>
					<div class="footer-col col-md-4">
						<h3>About Project</h3>
						<p>
							본 사이트는 한국소프트웨어산업협회 IoT엔지니어 양성과정 최종프로젝트 프로토타입입니다.
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="footer-below">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">Copyright &copy; Garfish 2017</div>
				</div>
			</div>
		</div>
	</footer>

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div
		class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- Portfolio Modals -->
	

	<!-- Plugin JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script
		src="<%=application.getContextPath()%>/resources/js/jqBootstrapValidation.js"></script>
	<script
		src="<%=application.getContextPath()%>/resources/js/contact_me.js"></script>

	<!-- Theme JavaScript -->
	<script
		src="<%=application.getContextPath()%>/resources/js/freelancer.min.js"></script>

	<script
		src="<%=application.getContextPath()%>/resources/js/snap.svg-min.js"></script>
	<script src="<%=application.getContextPath()%>/resources/js/main_2.js"></script>
	<script src="<%=application.getContextPath()%>/resources/js/main_3.js"></script>
	<!-- Resource jQuery -->

</body>

</html>