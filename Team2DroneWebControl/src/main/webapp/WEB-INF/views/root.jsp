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
	function login_check(event) {
		event.preventDefault();
		var formModal = $('.cd-user-modal');
		var formLogin = formModal.find('#cd-login');
		$
				.ajax({
					'url' : "login",
					'data' : {
						'mid' : $("#mid").val(),
						'mpassword' : $("#mpassword").val()
					},
					'type' : "POST",
					'async' : false,
					'success' : function(data) {
						if (data.result == 0) {
							formLogin.find('input[type="email"]').addClass(
									'has-error').next('span').addClass(
									'is-visible');
							formLogin.find('input[type="password"]')
									.removeClass('has-error').next().next(
											'span').removeClass('is-visible')
						} else if (data.result == 1) {
							formLogin.find('input[type="email"]').removeClass(
									'has-error').next('span').removeClass(
									'is-visible');
							formLogin.find('input[type="password"]').addClass(
									'has-error').next().next('span').addClass(
									'is-visible');
						} else {
							document.getElementById('loginForm').submit();
						}

					}
				});
	}

	function pwchange_check(event) {
		$("#btn_pwChange").attr("disabled", true);
		event.preventDefault();
		$('#success_pwChange').html(
				"<img width='30px' src='resources/image/loading.gif' />");
		var formModal = $('.cd-user-modal');
		var formPWChange = formModal.find('#cd-reset-password');
		formPWChange.find('input[type="email"]').removeClass('has-error').next(
				'span').removeClass('is-visible');
		formPWChange.find('input[type="email"]').removeClass('has-error')
				.next().next('span').removeClass('is-visible');
		$
				.ajax({
					'url' : "passwdChange",
					'data' : {
						'reset_mid' : $("#reset_mid").val()
					},
					'type' : "POST",
					'success' : function(data) {
						if (data.result == "fail") {
							$('#success_pwChange').html("");
							formPWChange.find('input[type="email"]')
									.removeClass('has-error').next().next(
											'span').removeClass('is-visible');
							formPWChange.find('input[type="email"]').addClass(
									'has-error').next('span').addClass(
									'is-visible');
							$("#btn_pwChange").attr("disabled", false);
						} else if (data.result == "already") {
							$('#success_pwChange').html("");
							formPWChange.find('input[type="email"]')
									.removeClass('has-error').next('span')
									.removeClass('is-visible');
							formPWChange.find('input[type="email"]').addClass(
									'has-error').next().next('span').addClass(
									'is-visible');
							$("#btn_pwChange").attr("disabled", false);
						} else {
							formPWChange.find('input[type="email"]')
									.removeClass('has-error').next('span')
									.removeClass('is-visible');
							formPWChange.find('input[type="email"]')
									.removeClass('has-error').next().next(
											'span').removeClass('is-visible');

							$("#btn_pwChange").attr("disabled", false);
							$('#success_pwChange').html(
									"<div class='alert alert-success'>");
							$('#success_pwChange > .alert-success')
									.html(
											"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
									.append("</button>");
							$('#success_pwChange > .alert-success').append(
									"<strong>메일이 성공적으로 발송 되었습니다. </strong>");
							$('#success_pwChange > .alert-success').append(
									'</div>');

							$('#pwChangeForm').trigger("reset");
						}

					}
				});
	}

	function joinRequest() {
		location.href = "join";
	}

	function communityRequest() {
		location.href = "community";
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
				<a class="navbar-brand" href="#page-top">Garfish Project</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li class="page-scroll"><a href="join" onclick="joinRequest()">회원가입</a></li>
					<li class="page-scroll"><a class="cd-signin" href="#0">로그인</a></li>
					<li class="page-scroll"><a href="comunity"
						onclick="communityRequest()">Community</a></li>

				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->


	<div class="cd-user-modal">
		<!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container">
			<!-- this is the container wrapper -->
			<ul
				style="text-align: center; background-color: #FF625D; color: white; height: 65px; font-weight: bold; font-size: 40px; padding-top: 10px; padding-bottom: 10px">
				<li>로그인</li>
			</ul>

			<div id="cd-login">
				<!-- log in form -->
				<form id="loginForm" method="post"
					action="<%=application.getContextPath()%>/login" class="cd-form"
					onsubmit="login_check(event)">
					<p class="fieldset">
						<label class="image-replace cd-email" for="signin-email">이메일</label>
						<input class="full-width has-padding has-border" id="mid"
							name="mid" type="email" placeholder="이메일"> <span
							class="cd-error-message">이메일을 확인해주세요!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">비밀번호</label>
						<input class="full-width has-padding has-border" id="mpassword"
							name="mpassword" type="password" placeholder="비밀번호"><a
							href="#0" class="hide-password">보기</a> <span
							class="cd-error-message">비밀번호를 확인해주세요!</span>

					</p>



					<p class="fieldset">
						<input class="full-width" type="submit" value="로그인">
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">비밀번호가 기억안나세요?</a>
				</p>
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>
			<!-- cd-login -->

			<div id="cd-signup">
				<!-- sign up form -->
				<form class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Username</label>
						<input class="full-width has-padding has-border"
							id="signup-username" type="text" placeholder="Username">
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-email" for="signup-email">E-mail</label>
						<input class="full-width has-padding has-border" id="signup-email"
							type="email" placeholder="E-mail"> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signup-password">Password</label>
						<input class="full-width has-padding has-border"
							id="signup-password" type="text" placeholder="Password">
						<a href="#0" class="hide-password">Hide</a> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input type="checkbox" id="accept-terms"> <label
							for="accept-terms">I agree to the <a href="#0">Terms</a></label>
					</p>

					<p class="fieldset">
						<input class="full-width has-padding" type="submit"
							value="Create account">
					</p>
				</form>

				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>
			<!-- cd-signup -->

			<div id="cd-reset-password">
				<!-- reset password form -->
				<p class="cd-form-message">비밀번호를 잊으셨나요? 이메일을 입력하시면 해당 메일로 비밀번호변경
					링크를 보내드립니다.</p>

				<form id="pwChangeForm" class="cd-form" method="post"
					action="<%=application.getContextPath()%>/passwdChange"
					onsubmit="pwchange_check(event)">
					<p class="fieldset">
						<label class="image-replace cd-email" for="reset-email">E-mail</label>
						<input class="full-width has-padding has-border" id="reset_mid"
							name="reset_mid" type="email" placeholder="E-mail"> <span
							class="cd-error-message">존재하지 않는 회원입니다.</span><span
							class="cd-error-message">이미 링크가 발송되었습니다.</span>
					</p>
					<div align="center" id="success_pwChange"></div>
					<p class="fieldset">
						<input id="btn_pwChange" class="full-width has-padding"
							type="submit" value="비밀번호 변경 링크보내기">
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">로그인으로 돌아가기</a>
				</p>
			</div>
			<!-- cd-reset-password -->
			<a href="#0" class="cd-close-form">Close</a>
		</div>
		<!-- cd-user-modal-container -->
	</div>
	<!-- cd-user-modal -->




	<header class="cd-slider-wrapper">
		<ul class="cd-slider">
			<li class="visible">
				<div>
					<h2>Garfish Project</h2>
					<hr class="star-light1">
					<p>Garfish Project는 RC 드론이 인터넷을 통해 IoT장비가 될 수 있도록 도와줍니다.</p>
					<p>지금바로 시작해보세요!</p>
					<div class="pc_view">
						<br>
						<div style="display: inline-block;">
							<img height="130px"
								src="<%=application.getContextPath()%>/resources/image/drone-2.png">
							<p>드론</p>
						</div>

						<div style="display: inline-block;">
							<img height="130px" style="margin-left: 50px; margin-right: 50px"
								src="<%=application.getContextPath()%>/resources/image/ram-memory.png">
							<p>통신모듈</p>
						</div>

						<div style="display: inline-block;">
							<img height="130px"
								src="<%=application.getContextPath()%>/resources/image/wifi.png">
							<p>IoT</p>
						</div>
					</div>


				</div>
			</li>

			<li>
				<div>
					<h2>다운로드</h2>
					<hr class="star-light2">
					<p>일반사용자를 위한 설치파일과 개발자를 위한 라이브러리를 다운로드 받으실 수 있습니다.</p>
					<p>모바일 이용자는 PC에서 시도해 주세요.</p>
					<div class="pc_view">
						<br>
						<div style="display: inline-block;">
							<a class="cd-btn" href="#"><img height="130px"
								src="<%=application.getContextPath()%>/resources/image/settings.png">
								설치파일</a>
						</div>

						<div style="display: inline-block;">
							<a href="http://codyhouse.co/?p=854" class="cd-btn"><img
								height="130px"
								src="<%=application.getContextPath()%>/resources/image/library.png">

								라이브러리</a>
						</div>

					</div>

				</div>
			</li>

			<li>
				<div>
					<h2>사용 설명서</h2>
					<hr class="star-light3">
					<p>설치파일 및 플랫폼 사용 설명서입니다.</p>
					<p>하드웨어 구축부터 설치까지 모든 과정을 손쉽게 따라하고 플랫폼을 통해 여러분의 장비를 제어해 보세요.</p>
					<div class="pc_view">
						<a href="http://codyhouse.co/?p=854" class="cd-btn">
							<div>
								<div style="display: inline-block;">
									<img height="100px"
										src="<%=application.getContextPath()%>/resources/image/sound-card.png">
								</div>

								<div style="display: inline-block;">
									<img height="100px"
										style="margin-left: 30px; margin-right: 30px"
										src="<%=application.getContextPath()%>/resources/image/software.png">
								</div>

								<div style="display: inline-block;">
									<img height="100px"
										src="<%=application.getContextPath()%>/resources/image/device.png">
								</div>
							</div> Learn More
						</a>
					</div>
				</div>
			</li>

			<li>
				<div>
					<h2>라이브러리 도큐먼트</h2>
					<hr class="star-light4">
					<p>개발자들을 위한 라이브러리입니다.</p>
					<p>본인만의 모듈을 개발해서 다양한 장비에 응용하고 이용해보세요.</p>
					<div class="pc_view">
						<a href="http://codyhouse.co/?p=854" class="cd-btn"> <img
							height="100px"
							src="<%=application.getContextPath()%>/resources/image/file.png">
							Learn More
						</a>
					</div>
				</div>
			</li>
		</ul>
		<!-- .cd-slider -->

		<ol class="cd-slider-navigation">
			<li class="selected"><a href="#0"><em>홈</em></a></li>
			<li><a href="#0"><em>다운로드</em></a></li>
			<li><a href="#0"><em>사용설명서</em></a></li>
			<li><a href="#0"><em>개발자</em></a></li>
		</ol>

		<!-- .cd-slider-navigation -->

		<div class="cd-svg-cover"
			data-step1="M1402,800h-2V0.6c0-0.3,0-0.3,0-0.6h2v294V800z"
			data-step2="M1400,800H383L770.7,0.6c0.2-0.3,0.5-0.6,0.9-0.6H1400v294V800z"
			data-step3="M1400,800H0V0.6C0,0.4,0,0.3,0,0h1400v294V800z"
			data-step4="M615,800H0V0.6C0,0.4,0,0.3,0,0h615L393,312L615,800z"
			data-step5="M0,800h-2V0.6C-2,0.4-2,0.3-2,0h2v312V800z"
			data-step6="M-2,800h2L0,0.6C0,0.3,0,0.3,0,0l-2,0v294V800z"
			data-step7="M0,800h1017L629.3,0.6c-0.2-0.3-0.5-0.6-0.9-0.6L0,0l0,294L0,800z"
			data-step8="M0,800h1400V0.6c0-0.2,0-0.3,0-0.6L0,0l0,294L0,800z"
			data-step9="M785,800h615V0.6c0-0.2,0-0.3,0-0.6L785,0l222,312L785,800z"
			data-step10="M1400,800h2V0.6c0-0.2,0-0.3,0-0.6l-2,0v312V800z">
			<svg height='100%' width="100%" preserveAspectRatio="none"
				viewBox="0 0 1400 800">
				<title>SVG cover layer</title>
				<desc>an animated layer to switch from one slide to the next one</desc>
				<path id="cd-changing-path"
					d="M1402,800h-2V0.6c0-0.3,0-0.3,0-0.6h2v294V800z" />
			</svg>
		</div>
		<!-- .cd-svg-cover -->
	</header>
	<!-- .cd-slider-wrapper -->




	<!-- Portfolio Grid Section -->
	<section id="portfolio">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Garfish 소개</h2>
					<hr class="star-primary">
					<div style="margin-bottom: 50px" align="center">
						<p>garfish로 IoT장비를 구축하세요.</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal1" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/image/iot.png" class="img-responsive"
						alt="Cabin"><br>
						<div align="center" style="color: #343434">
							<h2>IoT</h2>
							<br> garfish는 별도의 RC조종기 필요없이 인터넷을 통하여 드론을 제어할 수 있도록 도와줍니다.
							이는 기존의 RC 조종기를 완전히 대체하는 것을 의미하며 IoT장비로 인터넷이 되는 곳이라면 어디서든 드론을 제어할
							수 있음을 의미합니다.

						</div>
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal2" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/image/many.png" class="img-responsive"
						alt="Slice of cake"><br>
						<div align="center" style="color: #343434">
							<h2>범용성</h2>
							<br> garfish는 드론의 종류, 항법장치, 오픈소스에 구애를 받지 않고 RC를 이용하여 조종하는
							드론이라면 모든 곳에 이용될 수 있습니다. 또한 드론 뿐만 아니라 RC 신호로 제어하는 모든 종류의 장비에서 사용될
							수 있습니다.

						</div>
					</a>
				</div>
				<div class="col-sm-4 portfolio-item">
					<a href="#portfolioModal3" class="portfolio-link"
						data-toggle="modal">
						<div class="caption">
							<div class="caption-content">
								<i class="fa fa-search-plus fa-3x"></i>
							</div>
						</div> <img src="resources/image/gear.png" class="img-responsive"
						alt="Circus tent"><br>
						<div align="center" style="color: #343434">
							<h2>응용성</h2>
							<br> garfish는 회원가입과 설치만으로 장비를 제어할 수 있도록 플랫폼을 제공 합니다. 컴퓨터를 잘
							모르는 사람도 설명서를 통해 쉽고 간편하게 이용할수 있습니다. 또한 개발자를 위한 라이브러리를 제공해 다양한 분야에
							응용할 수 있습니다.

						</div>
					</a>
				</div>

			</div>
		</div>
	</section>

	<!-- About Section -->
	<section class="success" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>About Developer</h2>
					<hr class="star-light1">
				</div>
			</div>
			<div class="row">
				<div align="center">
					<p>garfish를 개발한 개발자를 소개합니다.</p>
				</div>
				<div class="row">
					<div class="col-sm-4 portfolio-item" align="center"
						style="padding-top: 50px">
						<img style="height: 200px" src="resources/image/jsPhoto.jpg"
							class="img-responsive" alt="Slice of cake"><br>
						<div align="center" style="color: #FFFFFF">
							<h2>이정수</h2>
							<br> H.P. 010-9895-5986<br> E-mail.
							quintessence6083@gmail.com

						</div>
					</div>
					<div class="col-sm-4 portfolio-item" align="center"
						style="padding-top: 50px">
						<img src="resources/image/hkPhoto.jpg" class="img-responsive"
							style="height: 200px" alt="Slice of cake"><br>
						<div align="center" style="color: #FFFFFF">
							<h2>강현규</h2>
							<br> H.P. 010-8994-9346<br> E-mail. yoyo238@naver.com
						</div>
					</div>
					<div class="col-sm-4 portfolio-item" align="center"
						style="padding-top: 50px">
						<img style="height: 200px" src="resources/image/jhPhoto.JPG"
							class="img-responsive" alt="Slice of cake"><br>
						<div align="center" style="color: #FFFFFF">
							<h2>조재훈</h2>
							<br> H.P. 010-7923-6932<br> E-mail.
							whwogns1122@naver.com
						</div>
					</div>

				</div>
				<div style="padding-top: 50px"
					class="col-lg-8 col-lg-offset-2 text-center">
					<h3>개발자의 이력서를 다운받아보세요!</h3>
					<a href="#" class="btn btn-lg btn-outline"> <i
						class="fa fa-download"></i> Download Resume
					</a>
				</div>
			</div>
		</div>
	</section>

	<!-- Contact Section -->
	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Contact Us</h2>
					<hr class="star-primary">
					<p>문의사항이 생기시면 언제든지 연락하세요!</p>
					<br>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
					<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
					<form name="sentMessage" id="contactForm" novalidate>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label for="name">이름</label> <input style="font-size: 20px"
									class="form-control" type="text" placeholder="이름" id="name"
									required data-validation-required-message="이름을 입력해 주세요.">
								<p style="color: red" class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label for="email">이메일</label> <input style="font-size: 20px"
									type="email" class="form-control" placeholder="이메일" id="email"
									required data-validation-required-message="이메일을 입력해 주세요.">
								<p style="color: red" class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label for="phone">전화번호</label> <input style="font-size: 20px"
									type="tel" class="form-control" placeholder="전화번호" id="phone"
									required data-validation-required-message="전화번호를 입력해 주세요.">
								<p style="color: red" class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label for="message">문의사항</label>
								<textarea rows="5" class="form-control" placeholder="문의사항"
									id="message" required
									data-validation-required-message="문의사항을 입력해 주세요."></textarea>
								<p style="color: red" class="help-block text-danger"></p>
							</div>
						</div>
						<br>
						<div id="success"></div>
						<div class="row">
							<div class="form-group col-xs-12">
								<button id="btnSubmit" type="submit"
									class="btn btn-success btn-lg">보내기</button>
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
							<li><a href="http://www.facebook.com"
								class="btn-social btn-outline"><span class="sr-only">Facebook</span><i
									class="fa fa-fw fa-facebook"></i></a></li>
							<li><a href="http://www.google.com"
								class="btn-social btn-outline"><span class="sr-only">Google
										Plus</span><i class="fa fa-fw fa-google-plus"></i></a></li>
							<li><a href="http://www.twitter.com"
								class="btn-social btn-outline"><span class="sr-only">Twitter</span><i
									class="fa fa-fw fa-twitter"></i></a></li>
							<li><a href="http://www.linkedin.com"
								class="btn-social btn-outline"><span class="sr-only">Linked
										In</span><i class="fa fa-fw fa-linkedin"></i></a></li>
							<li><a href="http://www.dribble.com"
								class="btn-social btn-outline"><span class="sr-only">Dribble</span><i
									class="fa fa-fw fa-dribbble"></i></a></li>
						</ul>
					</div>
					<div class="footer-col col-md-4">
						<h3>About Project</h3>
						<p>본 사이트는 한국소프트웨어산업협회 IoT엔지니어 양성과정 최종프로젝트 프로토타입입니다.</p>
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
	<div class="portfolio-modal modal fade" id="portfolioModal1"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Project Title</h2>
							<hr class="star-primary">
							<img src="resources/image/portfolio/cabin.png"
								class="img-responsive img-centered" alt="">
							<p>
								Use this area of the page to describe your project. The icon
								above is part of a free icon set by <a
									href="https://sellfy.com/p/8Q9P/jV3VZ/">Flat Icons</a>. On
								their website, you can download their free set with 16 icons, or
								you can purchase the entire set with 146 icons for only $12!
							</p>
							<ul class="list-inline item-details">
								<li>Client: <strong><a
										href="http://startbootstrap.com">Start Bootstrap</a> </strong>
								</li>
								<li>Date: <strong><a
										href="http://startbootstrap.com">April 2014</a> </strong>
								</li>
								<li>Service: <strong><a
										href="http://startbootstrap.com">Web Development</a> </strong>
								</li>
							</ul>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="portfolio-modal modal fade" id="portfolioModal2"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Project Title</h2>
							<hr class="star-primary">
							<img src="resources/image/portfolio/cake.png"
								class="img-responsive img-centered" alt="">
							<p>
								Use this area of the page to describe your project. The icon
								above is part of a free icon set by <a
									href="https://sellfy.com/p/8Q9P/jV3VZ/">Flat Icons</a>. On
								their website, you can download their free set with 16 icons, or
								you can purchase the entire set with 146 icons for only $12!
							</p>
							<ul class="list-inline item-details">
								<li>Client: <strong><a
										href="http://startbootstrap.com">Start Bootstrap</a> </strong>
								</li>
								<li>Date: <strong><a
										href="http://startbootstrap.com">April 2014</a> </strong>
								</li>
								<li>Service: <strong><a
										href="http://startbootstrap.com">Web Development</a> </strong>
								</li>
							</ul>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="portfolio-modal modal fade" id="portfolioModal3"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Project Title</h2>
							<hr class="star-primary">
							<img src="resources/image/portfolio/circus.png"
								class="img-responsive img-centered" alt="">
							<p>
								Use this area of the page to describe your project. The icon
								above is part of a free icon set by <a
									href="https://sellfy.com/p/8Q9P/jV3VZ/">Flat Icons</a>. On
								their website, you can download their free set with 16 icons, or
								you can purchase the entire set with 146 icons for only $12!
							</p>
							<ul class="list-inline item-details">
								<li>Client: <strong><a
										href="http://startbootstrap.com">Start Bootstrap</a> </strong>
								</li>
								<li>Date: <strong><a
										href="http://startbootstrap.com">April 2014</a> </strong>
								</li>
								<li>Service: <strong><a
										href="http://startbootstrap.com">Web Development</a> </strong>
								</li>
							</ul>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="portfolio-modal modal fade" id="portfolioModal4"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Project Title</h2>
							<hr class="star-primary">
							<img src="resources/image/portfolio/game.png"
								class="img-responsive img-centered" alt="">
							<p>
								Use this area of the page to describe your project. The icon
								above is part of a free icon set by <a
									href="https://sellfy.com/p/8Q9P/jV3VZ/">Flat Icons</a>. On
								their website, you can download their free set with 16 icons, or
								you can purchase the entire set with 146 icons for only $12!
							</p>
							<ul class="list-inline item-details">
								<li>Client: <strong><a
										href="http://startbootstrap.com">Start Bootstrap</a> </strong>
								</li>
								<li>Date: <strong><a
										href="http://startbootstrap.com">April 2014</a> </strong>
								</li>
								<li>Service: <strong><a
										href="http://startbootstrap.com">Web Development</a> </strong>
								</li>
							</ul>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="portfolio-modal modal fade" id="portfolioModal5"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Project Title</h2>
							<hr class="star-primary">
							<img src="resources/image/portfolio/safe.png"
								class="img-responsive img-centered" alt="">
							<p>
								Use this area of the page to describe your project. The icon
								above is part of a free icon set by <a
									href="https://sellfy.com/p/8Q9P/jV3VZ/">Flat Icons</a>. On
								their website, you can download their free set with 16 icons, or
								you can purchase the entire set with 146 icons for only $12!
							</p>
							<ul class="list-inline item-details">
								<li>Client: <strong><a
										href="http://startbootstrap.com">Start Bootstrap</a> </strong>
								</li>
								<li>Date: <strong><a
										href="http://startbootstrap.com">April 2014</a> </strong>
								</li>
								<li>Service: <strong><a
										href="http://startbootstrap.com">Web Development</a> </strong>
								</li>
							</ul>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="portfolio-modal modal fade" id="portfolioModal6"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<h2>Project Title</h2>
							<hr class="star-primary">
							<img src="resources/image/portfolio/submarine.png"
								class="img-responsive img-centered" alt="">
							<p>
								Use this area of the page to describe your project. The icon
								above is part of a free icon set by <a
									href="https://sellfy.com/p/8Q9P/jV3VZ/">Flat Icons</a>. On
								their website, you can download their free set with 16 icons, or
								you can purchase the entire set with 146 icons for only $12!
							</p>
							<ul class="list-inline item-details">
								<li>Client: <strong><a
										href="http://startbootstrap.com">Start Bootstrap</a> </strong>
								</li>
								<li>Date: <strong><a
										href="http://startbootstrap.com">April 2014</a> </strong>
								</li>
								<li>Service: <strong><a
										href="http://startbootstrap.com">Web Development</a> </strong>
								</li>
							</ul>
							<button id="btnSubmit" type="button" class="btn btn-default"
								data-dismiss="modal">
								<i class="fa fa-times"></i> Close
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

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