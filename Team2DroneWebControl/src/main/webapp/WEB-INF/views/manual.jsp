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


<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/reset_manual.css">
<!-- CSS reset -->
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/style_manual.css">
<!-- Resource style -->
<script
	src="<%=application.getContextPath()%>/resources/js/modernizr_manual.js"></script>
<!-- Modernizr -->
<title>FAQ Template | CodyHouse</title>



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
				<a class="navbar-brand" href="<%=application.getContextPath()%>/">Garfish Project</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li class="page-scroll"><a href="join" onclick="joinRequest()">회원가입</a></li>
					<li class="page-scroll"><a class="cd-signin" href="#0">로그인</a></li>
					<li class="page-scroll"><a href="comunity" onclick="communityRequest()">Community</a></li>

				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	

	

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


<!-- Header -->

	<header>
		<h1>드론 사용설명서</h1>
		
	</header>
			

	<section class="cd-faq">
	
		
		<ul class="cd-faq-categories" style="top:30">
		<li><a class="selected" href="#basics">RC 소개</a></li>
			<li><a href="#mobile">드론 소개</a></li>
			<li><a href="#account">하드웨어 설정</a></li>
			<li><a href="#payments">소프트웨어 설정</a></li>
			<li><a href="#privacy">웹페이지 안내</a></li>
			<li><a href="#delivery">기타</a></li>
			
		</ul>
		<!-- cd-faq-categories -->

		<div class="cd-faq-items">
		<ul id="basics" class="cd-faq-group">
			<li class="cd-faq-title"><h2>RC 소개</h2></li>
				<li><a class="cd-faq-trigger" href="#0">RC 개요</a>
					<div class="cd-faq-content">
						<p>
							<span style="font-size:20px">[ RC란 ]</span> <br><br>
							 Remote Control로도 해석하는데, RC세계에서는 (전파를 통한) 무선조종을 말하기에 Remote(원격)보다는 Radio(무선)이라는 단어를 사용합니다. 
							 우리가 생각하는 RC는 Radio Control에 더 가깝다 생각하면 좋습니다.
							RC는 단순히 Car 뿐만 아니라 헬기, 비행기, 보트, 탱크 최근에는 드론까지 나오고 있습니다.
							또한 RC는 크게 완구와 하비로 나눌수도 있습니다. 완구는 어린이 장난감 수준의 RC 이며, 하비는 어른들의 RC라고 보면 됩니다.
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">RC 종류</a>
					<div class="cd-faq-content">
						<p>
							<span style="font-size:20px">[ RC Drone ]</span> <br><br>
							드론에 대해서는 '드론 개요'에서 자세히 서술하도록 하겠습니다.<br><br>
							<span style="font-size:20px">[ RC Rover ]</span> <br><br>
								전동RC카 : 전기+모터<br>
								엔진RC카 : 연료+엔진<br>
								전기를 먹고 달리냐, 유류를 먹고 달리냐의 차이점인데 각각의 장단점이 있습니다.
								엔진 RC카는 전동 RC카에 비해 속도도 빠르고, 주유만 하면 달릴 수 있다는 장점이 있지만, 상대적으로 주행을 위한 절차나 비싼 유류비와 소음 등은 초보 입문자가 다루기에는 어려운 부분이 많습니다.
								초보 입문자에게는 전동RC카를 권하는 경우가 많습니다. 우선 엔진 보다 접근성이 용이하고 소음도 없습니다. 또한 크게 장소에 구애 받지 않습니다. 집안, 주차장, 공원, 운동장등 모든 장소가 RC카의 놀이터가 될 수 있습니다.
								전동RC카를 어느정도 다룬후에 강력한 엔진소리를 뿜어내는 엔진RC카의 매력에 빠집니다. 매연을 날리며 박진감을 즐길 수 있는 장점이 있으니
								장소가 한정적인 단점이 있습니다. 또한 연료구입비등 비용이 부담되기도 합니다.
								<br><br>
							<span style="font-size:20px">[ RC Plane ]</span> <br><br>
								무선 조종 항공기 (종종 불리는 RC 항공기 또는 RC 평면 ) 휴대용 무선하여 지상에 조작자에 의해 원격 제어되는 비행 송신기입니다.
								송신기 는 조종 장치 내의 조이스틱 의 위치에 따라 조종면 을 움직이는 서보 장치 (서보)에 신호를 보내는 선박 내의 수신기 와 통신 합니다. 컨트롤 서페이스는 평면의 방향에 영향을 줍니다.
								모터 사이클, 배터리 및 전자 제품 의 비용, 무게, 성능 및 기능이 향상되어 취미 로 RC 항공기가 급속히 성장했습니다 . 다양한 모델과 스타일을 사용할 수 있습니다.
								과학, 정부 및 군대에서도 기상 관측, 공기 역학 모델링 및 테스트를 위해 RC 항공기를 실험에 사용 하고 있습니다. 무인 공중 차량 (무인 항공기) 또는 스파이 비행기는 비디오 또는 자율 기능을 추가하며 무장 할 수 있습니다. 
								<br><br>
							<span style="font-size:20px">[ 기타 RC ]</span> <br><br>
								RC로봇  <br>
								RC탱크
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">Garfish 프로젝트 방향</a>
					<div class="cd-faq-content">
						<p>
							Garfish 프로젝트의 경우 모든 RC에 대한 제어가 가능하도록 RC의 수신단의 범용성을 구현한 것이 핵심입니다. <br>
							해당 프로젝트의 도큐먼트는 RC드론을 위주로 서술되어 있으며, 이를 참고하시어 어떠한 RC인지를 불문하고 커스터마이징 할 수 있을 것입니다.<br>
						
						</p>
					</div>
					</li>
			</ul>
			
			
			
			
			
			<ul id="mobile" class="cd-faq-group">
				<li class="cd-faq-title"><h2>드론 소개</h2></li>
				<li><a class="cd-faq-trigger" href="#0">드론 개요</a>
					<div class="cd-faq-content">
						<p>
							<span style="font-size:20px">[ RC드론 이란 ]</span> <br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/Drone_1.jpg"><br><br>
							
							드론(Drone)이란 사람이 직접 타지 않으며 임무를 수행하는 무인비행기(UAV)로서 원격지에서 무인비행기에 설치된 카메라 영상을 통하여 조종을 하는데
							최근에는 GPS시스템과 연계하여 지상에서 컴퓨터로도 완벽한 조종이 가능하게 되었습니다. <br><br>
							기존의 단순 원격조종(RC)이라는 개념을 탈피한 드론은 관련 첨단 기술이 군사부분에서 민간부문으로 이관되었고 고가의 정밀 제어장치 등이 싼가격으로
							 보급되면서 급속도로 발전을 거듭하고 있습니다. 각종 첨담기술이 집약된 드론을 즐기는 매니아들은 최근 스마트폰과도 같아 누구나 쉽게 드론을 배우고
							  즐길 수 있다고 알려집니다. 최근에 3D 로보틱스사에서는 약 500달러 정도이면 누구나 구입하여 15분내에 즐길 수 있는 수준이 되었다고 합니다.<br><br>
							  최근 미국 오바마 대통령은 2015년경에 드론의 상업적 이용에 대한 법안에 서명하면서 미 관련전문가들은 5년내에 7500개의 상업적 드론이 운용될 것이고
							  약 7만개의 새로운 일자리가 창출되어 새로운 미래산업으로 자리매김할 것으로 기대합니다. <br><br>
							  하지만 가장 도입을 서두르는 경찰치안 유지 및 민간 분야의 각종 사업에 무분별한 사용은 사생활 침해와 시민의 안전을 위협할 것이라는 우려의 목소리도 커지고 있습니다. 
							  간단한 장치의 추가로 이 드론은 하늘의 감시자로서 군림하며 정해진 곳으로 이동하여 지상의 사람 얼굴을 인신하고 사진을 촬영하며 물건도 전해줄 수 있기 때문입니다.
							  초기에 대테러 목적이나 군사적으로 사용된 만큼 첨단 드론이 각종 시위현장에서 사용된다면 개인 사생활은 물론이고 차후에 인권 침해의 논란도 잠식시킬 수 없을 것입니다.<br><br><br>
							<span style="font-size:20px">[ 쿼드콥터 ]</span> <br><br>
							   쿼드콥터란 영어 접두사 quad(4개)를 의미하며 모터가 4개로 돌아가는 드론을 말합니다.
							  따라서 앞에 붙는 어원에 따라[Tri(3개), quad(4개), hexa(6개)]날개의 개수를
							  알 수 있습니다. 그 다음 콥터 뒤에 나오는 +,x,H등은 드론의 모양을 설명해주는 것이므로
							  날개의 배치 모양을 통해 +인지 x인지 기호를 써서 알려줍니다.<br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/quadcopter_2.png"><br>
							[쿼드콥터의 모식도]<br><br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/quadcopter.jpg"><br>
							[쿼드콥터의  4채널 Transmitter]
						
							
							
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">드론의 작동방식</a>
					<div class="cd-faq-content">
						<p>
							- Throttle(Altitude) : 드론 차체의 상승, 하강을 나타냄
							<br>
							- Pitch : 드론 차체의 전진과 후진
							<br>
							- Roll : 드론 차체의 좌진 또는 우진으로 변경
							<br>
							- Yaw : 좌향변경 또는 우향변경
							<br>
							<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/throttlePitchYawRoll.png">
							<br>
							<br>
							드론에서 모터의 프로펠러가 돌아갈 경우 모두 같은 방향으로 돌지 않습니다.
							모든 모터들이 같은 방향으로 돌면 드론 자체를 외부의 영향에 의해 조종하기가 쉽지 않습니다.
							<br>
							따라서 홀수 모터의 드론을 제외하고 짝수 모터의 드론은 한 날개를 기준으로 서로 대각선으로 같은 방향으로 돌아갑니다.
						
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">드론 용어 정리</a>
					<div class="cd-faq-content">
						<p>
							- FPV
							<br>First Person View로 1인칭 시점의 시야의 약자로써 비행자가 실시간으로 기체에 달린 카메라의 영상만을
							보며 비행하는 것을 말함.
							<br>
							<br>
							- UAV <br> 드론 별칭. Unmanned aerial vehicle의 약자.
							<br>
							<br>
							- 바인딩(Binding) <br> 수신기가 조종기와 서로 매칭하여 송수신을 시작하도록 신호를 맞추는 것.
							<br>
							<br>
							- 자이로(Gyro) <br> 기체가 수평을 잡도록 도와주는 센서장치.
							<br>
							<br>
							- 캘리브레이션(calibration) <br> 드론에 사용된 센서의 초기값에 문제가 있을 경우, 이를 정상으로 복구해주는 교정작업을 의미.
							<br>
							<br>
							- 호버링(Hovering) <br> 드론이 일정한 고도를 유지한 채 정지해있는 상태를 말함. 드론 컨트롤의 기본.
							<br>
							<br>
							- Armed / Disarmed <br> 드론의 시동(armed) 및 시동해제(disarmed).
							<br>
							<br>
							- 자동복귀(Return to home) <br> 비행하던 드론이 최초 이륙 위치로 돌아오는 기능을 말함. 조종기 전원 -> 기체전원을 켜면, 
							각 기체별로 고유 신호를 내면서, 홈포인트 인식 동작을 하게 된다.
							<br>
							<br>
							- 시뮬레이터 <br> 헬기나 비행기의 실제 비행 전에 미리 컴퓨터에 조종기를 연결하여 게임처럼 연습할 수 있게 해주는 프로그램 및 장치. 
							미션플래너, 리얼플라이트 등.
							<br>
							<br>
							- 채널(Channel) <br> 채널은 드론이 움직일 수 있는 방향을 명령하는 신호를 인식할 수 있는 숫자를 나타냅니다.
							<br>4채널 [ 상하(1채널) / 좌우(1채널) / 기울기(1채널) / 전후진(1채널) ]
							<br>6채널 [ 상하(1채널) / 좌우(1채널) / 기울기(1채널) / 전후진(1채널)+배면비행 ]
							<br>
							<br>
							-PWM(Pulse Width Modulation) <br> 아날로그 신호를 디지털 형태로 나타낸 것으로 디지털 신호에 대해서 주파수를 설정하고 pulse width(duty cycle)는 아날로그 신호의 진폭에 따라 변합니다.
							즉 이때의 Duty ratio를 조절하여 출력 전압을 제어하게 됩니다.
							<br>
							<br>
							-PPM(Pulse Position Modulation) <br> 입력 아날로그 신호의 진폭에 따라 펄스의 시간적 위치(위상)를 변화시키는 펄스 변조 방식입니다. 드론의 수신기에 따라 
							위상차가 있는 PWM신호를 하나의 PPM신호로 변환(SUM)하여 신호를 수신하도록 합니다. 
						</p>
					</div> <!-- cd-faq-content --></li>

			</ul>
			<!-- cd-faq-group -->

			<ul id="account" class="cd-faq-group">
				<li class="cd-faq-title"><h2>하드웨어 설정</h2></li>
				<li><a class="cd-faq-trigger" href="#0">구성 부품</a>
					<div class="cd-faq-content">
						<p>

							<span style="font-size:20px"> [ 드론의 기본 구성 ] </span><br>
							 프레임틀 / 변속기(ESC) / 브러시리스모터(brushless motor) / 드론배터리(리포배터리) / 프로펠러 / 배터리 전압분배 케이블<br>  
							<img src="<%=application.getContextPath()%>/resources/image/manual/component_1.png"><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/component_2.png"><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/component_3.png"><br><br><br>
							<span style="font-size:20px"> Navio2(항법쉴드) </span><br><br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/navio.png"><br><br><br>
							<span style="font-size:20px"> 라즈베리파이 </span><br><br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/raspberryPi.jpg"><br><br><br>
							
							<span style="font-size:20px">[ 기타 추가 구성품 ]</span><br><br>

							배터리 체커<br>
						    <img src="<%=application.getContextPath()%>/resources/image/manual/checker.png"><br><br><br>
							리시버(원격제어 수신기)<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/receiver.png"><br><br><br>
							PPM encoder<br>

							<img src="<%=application.getContextPath()%>/resources/image/manual/ppm.jpg"><br><br><br>
							PCA9685 <br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/PCA9685.jpg"><br><br>
	
						</p>
					</div> <!-- cd-faq-content --></li>
					
					<li><a class="cd-faq-trigger" href="#0">주요 부품 제원</a>
					<div class="cd-faq-content">
						<p>

							<span style="font-size:20px">[ 1.Navio2 ]</span><br><br>

							드론 제어에 필수적인 센서와 gps등을 내장한 모듈로써 라즈베리파이에 쉴드로써 장착됩니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/Navio2.png"><br><br>
							
							

							<span style="font-size:20px">[ 2.라즈베리 파이 ]</span><br><br>

							드론을 제어하기 위한 모듈로써 역할을 수행합니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/raspberryPi.jpg"><br><br>
	
	
	
							<span style="font-size:20px">[ 3.브러쉬리스 모터(brushless motor) - BLDC ]</span><br><br>

							
							1. 장점 <br>
							- 브러쉬가 없어서 수명이 길다.<br>
							- 브로쉬로 인한 마찰이 없어 발열이 적다.<br>
							- 효율이 높아 크기가 작다.<br>
							- 부하로 인한 회전수 변동이 적다.<br><br>
							2. 단점 <br>
							- 가격이 비싸다.<br>
							- 변속기 셋팅이 비교적 어렵다.<br><br>
							3. 모터 동작 원리<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/brushless.gif">       
							<img src="<%=application.getContextPath()%>/resources/image/manual/brushless.jpg"><br><br>
							DC모터와 달리 도선측이 고정되어 있고, 영구자석측이 회전하는 구조입니다. 자력선이 공기중에는 통과하기 어려우나 금속은
							통과하기 쉽기때문에 회전력을 더욱 강하게 할 수 있습니다. 위 이미지처럼 바깥 축이 회전하고 가운데 축은 전자회로로 전극을 
							빠르게 바꿔주며 회전력이 발생합니다.
							<br><br><br><br>
							

							<span style="font-size:20px">[ 4.전자변속기(ESC) ]</span><br><br>
							 <img src="<%=application.getContextPath()%>/resources/image/manual/esc_1.jpg"><br><br>
							1. 전자변속기 종류<br><br>
							- OPTO type : Optoisolator의 약자로 전자 신호를 전기로 연결하지 않고 전달할 수 있는 전자 회로입니다.
							입력 전자 신호는 일련의 빛으로 변환된다. 이 빛을 반대편에 있는 광 검출기가 감지를 합니다. 일종의 모르스 부호를 통신하는 것으로 볼 수 있습니다.
							따라서 신호가 전기가 아닌 빛으로 전송된다.<br><br>
							- BEC/UBEC type : 배터리 제거회로(Battery Eliminator Circuit/Universal Battery Eliminator Circuit)로써
							 멀티로더 배터리(일반적으로 11.1V)의 고전압전기를 저전압(5V)로 변환하는 것으로써, 비행컨트롤러나 서보와 같은 저전압 장치를 구동시키는데 사용됩니다.
							 만약 비행컨트롤러를 배터리와 직접 연결하면 비행컨트롤러가 타버리게 됩니다. 따라서 배터리와 비행컨트롤러 사이에 BEC/UBEC를 두어 적절한 수준으로 전압을
							  떨어뜨려야 합니다.<br><br>
							2. 전자변속기(BEC/UBEC)의 원리<br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/ESC_1.png"><br><br>
							전자변속기는 모터와 배터리, 수신모듈 사이에 전선으로 연결하여 사용되는데, 모터(BLDC)쪽으로는 3선으로 연결하며, 배터리로는 전원을 받고 수신모듈쪽에서는 3선(VCC, GND, DATA)이 공급됩니다.
							수신모듈쪽으로 공급되는 3선(VCC, GND, DATA)은 전원은 5V이며 DATA는 PWM 2ms 주기의 아날로그 신호(수신기에서 전자변속기로의 신호)입니다.<br>
							즉, 배터리에서 전력을 공급받고 수신기의 명령을 받아 BLDC모터의 속도 및 방향을 제어하는 것입니다.<br><br>
							전자변속기는 무선수신모듈에서 제공되는 PWM신호를 가지고 모터를 제어할 수 있고, 전류제어로 변환하여 서보모터나 BLDC를 제어합니다.
							수신기에서 변속기쪽으로 유입되는 선이 있는데, 여기로 PWM출력이 나오며 또한 변속기에 붙은 배터리의 전원이 변속기를 지나 수신모듈에 공급됩니다.
							<br><br><br><br>
							

							<span style="font-size:20px"> [ 4-1.브러쉬리스 모터와 ESC의 호환 ]</span><br><br>
							보통 ESC: 20~30A 이렇게 표시하는데 ESC가 허용가능한 전류량입니다.
							모터스펙에서 제시하는 ESC 전류량 이상의 ESC 스펙의 것을 선택해야 합니다.<br><br><br><br>	
							
							<span style="font-size:20px">[ 5.배터리 ]</span><br><br>

							1. 배터리 종류<br><br>
							니켈수소, 리튬철, 리튬폴리머 등이 있으며, 드론이나 멀티콥터에 가장 적합한 배터리는 단연 리튬폴리머(LiPo)배터리입니다. 왜냐하면 배터리 자체 무게 대비 전압 및 방전율이 월등하기 때문입니다.
							<br><br>
							2. 배터리 제원 보는 방법<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/battery.png"><br><br>
							리튬폴리머 배터리는 1Cell 즉 2S라고 표기하며, 1셀당 3.7V 전압을 가집니다. 이 셀을 여러 개로 직렬 또는 병렬로 연결하여 사용합니다.
							멀티콥터에서 보통 동력원으로 3셀, 4셀, 5셀, 6셀 정도로 사용됩니다. 3셀이면 3.7V x 3s = 11.1v가 됩니다.
							배터리에는 용량이 표기되어 있는데, 예를 들어 3700mAh라고 표시되어 있으면 이것은 3.7A(=3700mA)로 뽑아 쓸 때 1(1hour)시간 쓸 수 있는 양입니다.<br><br>
							셀들이 3셀 직렬로 연결되어 1팩으로 되어 있다면 각 셀들의 용량과 전압이 모두 같습니다. 혹시나 용량이 다른 셀들을 직렬로 연결해서 쓰시면 안됩니다.
							이것은 방전율과 관계가 있는데, 위 사진에서 15C로 표시되어 있습니다. 다른 배터리를 보면 20C, 25C, 35C, 40C, 50C 등으로 적혀 있습니다.<br>
							1C란 자기용량의 1배만큼 전류를 출력할 수 있다는 의미입니다. 따라서 위 사진에서 배터리가 3S 2200mhA의 15배만큼 출력할 수 있다는 것입니다. 
							즉, 2200*15=33A 까지 방전할 수 있다라는 의미입니다.
							
							만약 동일한 방전율인데 배터리 용량이 5000mhA 짜리라면 어떻게 될까요? 5000*15=75A입니다. 같은 방전율이라도 배터리 용량이 다르니 방전량이 달라집니다. 따라서 용량이 다른 배터리를 혼용하면 배터리끼리 충전을 하게 되고 배터리가 망가지고 효율이 급격히 떨어집니다.
							리튬폴리머 배터리의 특성은 기본 셀당 전압3.7v에서 완충하면 4.2v를 띠게 되고 부하가 걸리면 서서히 전압이 떨어지게 됩니다.
							
							만약 셀당 전압이 2.8v아래로 떨어지면 배터리는 전력공급을 멈춥니다. 
							리튬폴리머 건전지의 특징입니다. 
							
							<br><br>
							<span style="font-size:20px">[5-1.배터리 사용 주의사항]</span><br><br>
							리튬계열의 배터리를 쓰는 핸드폰, 노트북 등 여러가지 전자기기들이 있는데, 거기엔 일정 전압이 내려가면 컷오프시키는 회로가 내장되어 있습니다. 그렇기 때문에 배터리가 보호됩니다.							
							그러나 멀티콥터나 RC(Remote Controller)에 쓰이는 이러한 배터리는 그런 보호회로가 없습니다. 
							왜냐하면 일반 전자기기에 사용하는 방전율과 비교가 되지 않을 만큼 엄청난 방전율로 소비하기 때문에 그런 회로를 넣으면 배터리가 제 성능을 낼 수 없고 성능을 낸다 해도 비효율적이기 때문입니다.
							
							<br>
							리튬롤리머는 3.7v에서 가장 안정화 되어 있는데 
							그 이하나 그 이상의 전압에서는 배터리가 손상된다고 생각하면 됩니다. 
							또 4.2v이상, 2.8v이하에서는 급격히 손상됩니다.
							방전시에는 열이 발생하게 됩니다. 미지근한 상태이면 상관없는데 성능이상의 방전을 시키면 배터리는 열이 많이 올라가며 배부름 증상이 생깁니다. 
							이때는 이미 배터리가 손상된 것입니다.
							<br>
							그러니까 모터, 프로펠러와 함께 컴포넌트 조합이 제대로 안된 상태에서 비행하면 
							이렇게 배터리가 상처를 입습니다. 
							이렇게 비싼 리폴 배터리 구매해서 한번 비행하고 완전 사망시키는 일이 발생하지 않도록 너무 욕심 내면 안됩니다.
							<br><br><br>

							<span style="font-size:20px">[ 6.프로펠러 ]</span><br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/prop.jpg"><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/prop_2.jpg"><br>
							프로펠러에 대한 제원을 보기 위해서 위의 예를 살펴봅니다.
							재질은 섬유유리 나일론 프로펠러입니다.
							치수와 피치는 5040입니다. 길이가 5인치, 피치가 4.5인치인 프로펠러입니다.<br>
							cf) 피치는 프로펠러가 1회전할 때 나가는 길이(추력)이라고 합니다.
							쉽게 말하면 날개이 날이 휜 정도라고 생각하면 됩니다. 
							<br><br>
							<span style="font-size:20px">[ 6-1.프로펠러 선택 주의사항 ]</span><br><br>
							멀티콥터가 호버링(정지비행) 상태에서 프롭의 상태와 비행중 또는 맞바람이 불 때 프롭의 상태는 다릅니다. 
							따라서 일정 고도의 호버링 상태는 안정된 상태로 프롭의 부하가 아주 적습니다. 
							비행 중일 때는 프롭에 가해지는 부하로 인해 약한 재질의 프롭은 여기저기 휘어지고 밸런싱이 틀어져 기체에 진동을 발생시킵니다. 
							물론 기체의 무게에 비해 약한 재질이면 호버링 상태에서도 진동이 발생할 수 있습니다.
							프로펠러도 결국은 모터와 기체의 무게와의 조합입니다. 최적의 조함을 위해선 최종적으로 데이터 시트(주로 모터의 데이터시트)를 살펴보는 것이 좋습니다.
	
							
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">드론 조립 및 주의사항</a>
					<div class="cd-faq-content">
						<p>
							<span style="font-size:20px">[ 회로 구성도 ]</span> <br><br>
							Garfish 프로젝트의 드론 연결 구성도는 다음과 같습니다.
							<img src="<%=application.getContextPath()%>/resources/image/manual/hardware.png"><br><br>
							
							<span style="font-size:20px">[ 드론 조립 ]</span> <br><br>
							1. 드론 프레임에 모터를 고정시킵니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_1.png"><br><br>
							2. 모터를 모두 틀에 연결시킵니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_2.png"><br><br>
							3. 프로펠러를 모터에 연결 시켜 줍니다.<br>
							[주의: 후에 캘리브레이션 설정시 제거해야 하므로 굳이 지금 달 필요는 없습니다. 또한 드론의 앞 뒤 분간을 위해 위와같이 앞뒤로
							나눠 프로펠러를 색깔로 달아주면 좋습니다. 프로펠러를 거꾸로 달지 않도록 주의하세요! 거꾸로 달면 날개를 뒤집어서 돌리는 것과 같이 바람이
							밑이 아닌 위로 향합니다.]<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_3.png"><br><br>
							4. 모터에 베어링을 조립합니다.<br>
							[주의: 프로펠러의 기울기가 2쌍씩 다르게 생겼습니다. 이는 모터의 회전방향이 CW 2개, CCW 2개 이므로 이에 따라 바람을 밑으로 보내기 
							위하여 기울기가 다릅니다. 어떻게 끼는지 모르겠다 싶으면 후에 모터를 돌려난 후 끼면 됩니다. ]<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_4.png"><br><br>
							5. ESC를 모터에 연결시켜 줍니다. 모터의 3개의 케이블선에 임의로 연결해도 상관없습니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_5.png"><br><br>
							6. ESC는 모터의 기능선, +선, -선을 자기가 알아서 처리해줍니다. 따라서 아무렇게나 연결해도 CW, CWW방향으로 돌아가게 해줍니다.
							또한 모터의 방향을 바꿔주고 싶을 땐 임의의 2선을 교차해서 꼽아주면 방향이 거꾸로 돌아갑니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_6.png"><br><br>
							7. 프레임틀에 거치대를 연결해줍니다. 후에 비행 시 거치대가 잘 떨어지므로 본드나 접착제로 고정시켜줍니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_7.png"><br><br>
							8. 라즈베리 파이에 Navio2 연결하기<br>
							- 라스베리 파이의 윗면에 스페이서를 설치하고 바닥에서 나사로 고정하십시오.<br>
							- 확장 헤더를 40 핀 gpio 포트에 연결하십시오.<br>
							- Navio2를 확장 헤더에 연결하십시오.<br>
							- 나사를 사용하여 Navio2를 고정하십시오.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_8.png"><br><br>

							9. Navio2의 Power 포트에 연결된 전원 모듈로 전원을 공급받습니다. 또한 Navio2는 결합된 라즈베리파이에 전원을 공급합니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_9.png"><br><br>
							10. 서보 전원 공급하기<br>
							- 서보 레일에 전원을 공급하여 무인 항공기의 BEC를 서보 레일의 자유 채널에 연결합니다.<br> 
							- 4.8-5.3V 범위의 전압을 제공하는 BEC를 사용하도록 합니다. <br>
							- 고전압 서보를 사용하려면 전원 분리 보드를 사용하십시오.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_10.png"><br><br>
							11. GNSS 안테나가 Navio2 상단의 MCX포트에 연결됩니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_11.png"><br><br>
							12. Navio2의 수신단 연결<br>

							이는 중요한 부분으로써, 자세한 내용은 아래 "PPM 수신단"에서 서술되어 있습니다.<br><br>
							13. 1~7에서의 조립(드론 차체)과 8~12에서의 조립(Navio2+RaspberryPi)을 결합합니다.<br><br>
							14. 배터리를 전원모듈에 연결하여 조립을 마무리 합니다.<br>

							
							
						
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">PPM 수신단</a>

					<div class="cd-faq-content">
						<p>
							<span style="font-size:20px">[ Navio2의 수신단의 범용성 ]</span> <br><br>
							PPM수신단은 Garfish의 드론 프로젝트의 핵심 파트(Core Part)입니다.<br>
							먼저 기존의 4채널 RC transmitter를 통해 제어되는 원리를 살펴보면 throttle, pitch, roll, yaw는 채널
							PWM신호
							이는 수신단의 범용성을 구현한 것으로 자세한 내용은 아래 그림을 참고해 봅니다.<br><br>
							<span style="font-size:20px">[ Navio2의 수신단 연결 ]</span> <br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/assembly_12.png"><br><br>
							- 기존의 수신기(Receiver)대신 드론 제어 수신단의 범용성을 구현할 목적입니다.<br>
							- 라즈베리파이에서 pwm신호를 발생시켜 pca9685로 전송되도록 연결합니다.<br>(이는 기존의 RC transmitter에서 발생되는 원격제어 채널신호인 pwm 신호를 그대로 구현한 신호)<br>
							- 라즈베리파이에서 발생된 pwm신호가 pca9685에서 ppm encoder로 전달되도록 연결합니다.<br>
							- ppm encoder에서 ppm송신단과 Navio2의 ppm수신단을 연결합니다.<br>
						</p>
					</div> 
					</li>
					
					<li><a class="cd-faq-trigger" href="#0">하드웨어 설정</a>

					<div class="cd-faq-content">
						<p>
							<span style="font-size:20px">[ 1. Raspbian 설치  ]</span> <br><br>
							- Emlid raspbian 이미지 다운로드<br>
							Navio는 Emlid에서 제공하는 Raspbian을 실행해야 합니다.
							Emild는 Raspberry Pi 2 및 3에 대한 통합 SD 카드 이미지를 제공합니다. 
							OS는 headless이므로 무인 항공기 애플리케이션에 필요하지 않으므로 GUI없이 제공됩니다.<br>
							아래 링크를 통해 image파일을 다운로드 하시기 바랍니다.<br>
							<a href="https://docs.emlid.com/navio2/common/ardupilot/configuring-raspberry-pi/">SD카드 이미지 제공페이지로 가기</a><br><br>
							- SD카드에 이미지 쓰기<br>
							 USB카드 리더기에  MicroSD 카드 삽입<br>
							 win32DiskImager 설치파일 다운로드 후 설치<br>
							 win32DiskImager 실행후 이미지를 MicroSD카드에 쓰기<br>
							 아래 참고 영상을 보면 더 쉽게 이해하실 수 있습니다.<br>
							<a href="https://youtu.be/i8_TFYWYt_M">SD카드에 이미지 넣기</a><br><br><br>
							
							
							<span style="font-size:20px">[ 2. 무선 wifi 설정  ]</span> <br><br>
							Raspberry Pi3에는 내부 wi-fi모듈이 있고 Raspberry Pi2에는 외부 USB wi-fi동글이 필요합니다.<br>
							wi-fi 네트워크는 SD카드(/boot파티션)에 있는 wpa_supplicant.conf파일을 편집하여 구성할 수 있습니다.
							
							<br><br>
							네트워크를 추가하려면 다음 행을 추가하십시오.
							
								<span style="background-color: #F7F7F7; height: 100px; width: 100%;">
									<br>
									network={<br>
									ssid="yourssid"<br>
									psk="yourpasskey"<br>
									key_mgmt=WPA-PSK<br>
									}
								</span><br><br>
								
								wpa_supplicant.conf 파일에 액세스하려면 다음 방법 중 하나를 사용하십시오<br><br>
								# SD 카드의 구성 편집 <br>
								컴퓨터에 SD 카드를 꽂기 만하면됩니다.
								SD 카드 내용에 액세스 한 후  boot 파티션에있는 wpa_supplicant.conf를 열고 (Linux의 경우 루트 권한으로) 위에서 설명한대로 파일을 편집하십시오.
								<br><br>						
								# 모니터와 키보드 사용<br>
								HDMI 모니터와 USB 키보드를 라즈베리에 연결하고 전원을 켜면 콘솔에 액세스 할 수 있습니다. 여기서 텍스트 편집기를 사용하여 wpa_supplicant를 수정할 수 있습니다.
								wpa_supplicant.conf파일은 다음 명령을 통해 접근할 수 있습니다.<br><br>
							
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
								sudo nano /etc/wpa_supplicant/wpa_supplicant.conf
								</span>
								<br><br><br>
								
								
							<span style="font-size:20px">[ 3. System upgrade ]</span> <br><br>
							필요한 경우 다음을 실행하여 시스템을 업그레이드 할 수 있습니다.<br><br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
									sudo apt-get update && sudo apt-get dist-upgrade
								</span>
								
								<br><br><br>
								
								
								
							<span style="font-size:20px">[ 4. 아두파일럿 setting ]</span> <br><br>
							ArduPilot은 Navio와 함께 Raspberry Pi 3 또는 2에서 실행할 수 있습니다. 자동 조종 장치의 코드는 Raspberry Pi에서 직접 작동합니다. 
							ArduPilot이 제대로 작동하려면 Emlid가 제공하는 구성된 Raspbian 배포판을 사용해야 합니다.<br>
							Emlid Raspbian은  ArduPilot을 사전 설치하였습니다. 다음은 각 RC의 버젼입니다.<br>
							ArduPlane : 3.7.1<br>
							ArduRover : 3.1.2<br>
							ArduCopter : 3.4.6<br><br>
							라즈베리 파이로 ssh하면 다음과 같은 메세지로 greeted됩니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/ardu_1.png"><br><br>
							
							# RC의 버전 및 보드 선택<br><br>
							아래의 예제에서 우리는 arducopter를 사용 하겠지만 arduplane 또는 ardurover 일 수도 있습니다.<br><br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
									pi@navio: ~ sudo update-alternatives --config arducopter
								</span><br><br>
								위의 명령어를 입력하면 다음과 같은 arducopter의 버전 및 보드 선택창이 실행됩니다.<br><br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/ardu_2.png"><br>
							첫 번째 줄에는 기본적으로 실행되는 차량이 표시됩니다. 다른 프레임, 보드 또는 버전을 선택하려면 해당 프레임, 보드 또는 버전을 선택해야합니다.
							예를 들어 Navio 2에서 쿼드를 작성하고 ArduCopter-3.4를 사용하려면 15를 입력 한 다음  Enter키를 누릅니다. (*표시된 것은 default)<br><br><br>
							
							<span style="font-size:20px">[ 5. Launching option ]</span> <br><br>
							- ardupilot 설정 <br><br>
							ardupilot을 실행하기 위해 파일을 엽니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
										pi@navio: ~ $ sudo nano /etc/default/arducopter
								</span><br><br>
								다음을 통해 현재 GCS(Ground Control Station)의 IP를 지정할 수 있습니다.<br>
								<img src="<%=application.getContextPath()%>/resources/image/manual/ardu_3.png"><br>
								('#'으로 표시된 모든 줄은 주석이며 아무런 효과가 없습니다.)<br>
								위 설정창은 GCS가 필요로 하는 Navio2의 data를 지정된 GCS의 IP로 보내줄 것을 설정하는 것입니다.<br>
								예를 들어, 다음과 같이 IP를 가리 키도록 TELEM1을 수정해야합니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
										TELEM1= "- udp : 192.168.1.2 : 14550"
								</span><br>
								"192.168.1.2"는 GCS가 있는 장치(노트북, 스마트 폰 등)의 IP 주소입니다.<br><br>
								
								<span style="color: #DE5246; font-weight:bold">
										[ 주의사항 ]
								</span>
								<br>
								 여러개 TELEM을 등록하는 것은 가능하지만 실제 사용시, 등록된 TELEM을 처음으로 접속한 TELEM에게만 data가 전송됩니다.
								 이와 같은 특성을 고려하여, 우리의 프로그램을 사용할 때에는 local GCS가 됩니다. 
								 즉 자신의 IP를 TELEM으로 지정하고 해당 data를  MAV proxy를 통해 여러 GCS에 전달해줍니다.<br><br>
								  *MAV proxy는 이후 자세히 설명될 예정입니다.<br><br>
							- ardupilot configuration reload<br>
							이전 단계에서 무언가를 변경 한 경우, systemd가 제대로 작동하도록 configuration을 reload해야합니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pi@navio: ~ $ sudo systemctl daemon-reload
								</span><br><br>
							- ardupilot 시작하기	<br>
							이제 ArduPilot을 시작할 수 있습니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pi@navio: ~ $ sudo systemctl start arducopter
								</span><br><br>
								다음을 통해 실행을 중지할 수 있습니다<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pi@navio: ~ $ sudo systemctl stop arducopter
								</span><br><br>
							- 부팅시 ardupilot 자동실행<br>
							부팅시 ArduPilot을 자동으로 시작하려면 다음을 활성화해야합니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pi@navio: ~ $ sudo systemctl enable arducopter
								</span><br><br>
							자동 시작을 비활성화하려면 다음을 수행하십시오.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pi@navio: ~ $ sudo systemctl disable arducopter
								</span><br><br>
							ArduPilot이 이미 활성화되어 있는지 확인할 수 있습니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pi@navio: ~ $ systemctl is-enabled arducopter
								</span><br><br>
								  
								 <br>
							<span style="font-size:20px">[ 6. GCS의 연결]</span> <br><br>
							
							GCS의 종류 <br> <br>
							<span style=" font-weight:bold">
							Mission Planner
							</span><br>
							Windows 전용 GCS이며, 가장 널리 쓰입니다.<br><br>
							
							<span style=" font-weight:bold">
							QGroundControl 
							</span><br>
							Mavlink 기반 비행 스택 (Ardupilot와 같은)을 위한 crossplatform GCS입니다.<br><br>
							
							<span style=" font-weight:bold">
							APM Planner 
							</span><br>
							APM Planner는 ArduPilot의 GCS소프트웨어입니다. <br>
							
							<a href="https://docs.emlid.com/navio2/common/ardupilot/configuring-raspberry-pi/">ardupilot.com</a>
							에서 다운로드 할 수 있습니다.<br>
							APM Planner는 UDP 포트 14550에서 수신하므로 무인 항공기의 원격 측정을 자동으로 포착해야합니다.<br><br>

							<span style=" font-weight:bold">
							MAVProxy 
							</span><br><br>
							- MAVProxy란<br>
							MAVProxy는 Python으로 작성된 콘솔 기반의 GCS소프트웨어이며, 고급 사용자 및 개발자에게 적합합니다.<br><br>
							
							- MAVProxy 설치 및 실행  <br>
								
								<a href="http://ardupilot.github.io/MAVProxy/html/getting_started/download_and_installation.html">MAVProxy 설치 및 실행</a>
								하려면 다운로드 및 설치 지침을 사용하십시오.<br><br>
								[리눅스일때 MAVProxy 설치법]<br>
								첫째, 몇 가지 사전 필수 패키지를 설치해야합니다.<br>
								데비안 기반의 시스템에서, 아래 사항을 입력한다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											sudo apt-get install python-dev python-opencv python-wxgtk3.0 python-pip python-matplotlib python-pygame python-lxml
								</span><br><br>
								그런 다음 Pypi를 통해 MAVProxy를 다운로드하고 설치하십시오. 선결제도 자동으로 다운로드됩니다. 설치가 오류를 생성하는 경우에는 경우에 따라 sudo가 필요할 수 있습니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											pip  install  MAVProxy
								</span><br><br>
								사용자 및 시스템 설정에 따라 약간의 추가 구성이 필요할 수 있습니다. 아직 설정하지 않은 경우 MAVProxy가 시스템 경로에 있어야합니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											echo  "export PATH = $ PATH : $ HOME / .local / bin"  >>  ~ /. bashrc
								</span><br><br>
								직렬 장치에 대한 액세스를 허용하려면 사용자 권한을 변경해야 할 수도 있습니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
											sudo adduser &lt;username&gt; dialout
								</span><br><br>
								위의 두 가지 변경 사항을 적용하려면 시스템을 로그 아웃하고 다시 로그인해야합니다.
								<br><br>
								[리눅스일때 MAVProxy 실행법]<br>
								 MAVProxy는 연결할 USB 포트 또는 네트워크 주소의 주소 만 필요합니다.<br>
								직렬 포트, TCP 포트 또는 UDP 포트가 될 수있는 --master 포트를 지정하십시오. 또한 --out 옵션을 사용하여 data passthrough를 수행 할 수 있습니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
										mavproxy . py  - master = / dev / ttyUSB0
								</span><br><br>
								
								일반적으로 MAVProxy는 올바른 baud rate를 자동 감지합니다. 필요한 경우 전송 속도를 수동으로 지정할 수 있습니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
										mavproxy.py --master=/dev/ttyUSB0 --baudrate=57600
								</span><br><br>
								
								다음을 통해 MAVProxy를 실행합니다.<br>
								<span style="background-color: #F7F7F7; height: 25px; width: 100%;">
										pi@navio: ~ $ mavproxy.py --master 192.168.1.2:14550 --console
								</span><br>
								(여기서 192.168.1.2는 RPi가 아니라 GCS의 IP 주소입니다.)
								
								<br><br><br>
							
							<span style="font-size:20px">[ 7. Mission planner를 이용한 Drone configuration ]</span> <br><br>
							
							
							Mission planner를 이용하여 드론 configuration할때, 
							참고링크에서는 transmitter를 이용하지만 Garfish프로젝트에서는 웹페이지상에 제어페이지에서 가능하도록 설계되어 있습니다.
							그러므로 캘리브레이션이나 간단한 테스트 및 실제 주행테스트 등을 할때에도 해당 제어페이지를 이용할 수 있습니다.<br><br>
							
							<span style=" font-weight:bold">
							[리시버 바인딩 작업]
							</span><br><br>
							<a href="https://hackaday.io/project/16352/instructions">유튜브 영상</a>을 참고하여 수신기 바인딩(receiver binding) 작업을 수행합니다.<br><br>
							
							<span style=" font-weight:bold">
							[캘리브레이션(calibration)]
							</span><br><br>
							RC와 TX가 바운드되면 자동 조종 장치를 캘리브레이션 할 차례입니다.<br>
							1. USB 포트에서 라즈베리 파이를 켭니다.<br>
							2. ardupilot이 자동으로 시작되는지 확인하십시오. NAVIO2 상단의 LED가 깜박이기 시작해야합니다.<br>
							3. 노트북에 원격 측정을 전송하는지 확인하십시오. 아직 수행하지 않았다면 이전 단계를 참조하십시오.<br>							
							4. Mission Planner(또는 바람직한 GCS) 실행합니다.<br>							
							5. 송신기가 작동하고 X8R LED가 녹색인지 확인하십시오. (참고 : RX와 TX 간의 연결이 서로 너무 가까우면 연결이 잘못 될 수 있습니다).<br>							
							6. 초기 설정 -> 필수 하드웨어 -> 
							<a href="http://ardupilot.org/copter/docs/common-radio-control-calibration.html">캘리브레이션</a>
							 으로 이동하십시오.<br>
							 <img src="<%=application.getContextPath()%>/resources/image/manual/calibration.png"><br><br>
							 
							 <span style=" font-weight:bold">
							[가속도계 및 나침반 교정의 교정]
							</span><br><br>
							다음 설정을 구성하는 것이 필요합니다. (Initial Setup-> Mandatory)<br>
							1. 프레임 유형 : 우리의 경우 'X'형 드론<br>
							2. Accel calibration.<br>
							가속도계를 성공적으로 구성하려면 매끄러운 표면이 필요합니다. 측정 수준을 사용하는 것이 좋습니다. "Calibrate Accel"버튼을 누른 후 지침을 따르십시오.<br>
							(주의사항 : Accel calibration은 APMPlanner가 작동하지 않을 수 있습니다. 이 GCS로 헬리콥터를 조정할 수 없었습니다.)<br>
							3. 나침반 설정 <br>
							<img src="<%=application.getContextPath()%>/resources/image/manual/calibration_1.png"><br><br>
							필수 하드웨어의 "Compass"로 이동하고 "General Compass Settings"에서 Primary Compass의 Compass2를 선택하십시오. 그런 다음 Compass1에서 "이 Compass 사용"의 선택을 해제하고 Compass2에 대해이 매개 변수를 선택하십시오. 이를 캘리브레이션하려면 "Onboard Mag Calibration"에서 "Start"를 누르십시오. 그런 다음 NAVIO2로 다음 영상에서처럼 헬기를 움직입니다.  
								<br><br>
							[ 나침반 설정 참고 영상 : <a href="https://youtu.be/DmsueBS0J3E">나침반 설정</a> ]
								<br><br>
							캘리브레이션 후 Raspberry Pi를 재부팅하십시오. Ardupilot이 Mission Planner에 다시 연결될 때까지 재부팅하는 동안 Compass창에 있어야합니다.
							비행 데이터 창에서 캘리브레이션이 성공적으로 이루어지면 경고 메시지가 표시되지 않습니다.
							<br><br><br>
					
							Drone configuration의 참고 링크 : 
							<a href="https://hackaday.io/project/16352/instructions">
							 <span style=" font-weight:bold">
							Mission planner 이용 참고 페이지
							</span>
							</a><br><br>
						</p>
					</div> 
					</li>
			</ul>
			<!-- cd-faq-group -->

			<ul id="payments" class="cd-faq-group">
				<li class="cd-faq-title"><h2>Account</h2></li>
				<li><a class="cd-faq-trigger" href="#0">How do I change my
						password?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Perferendis earum autem consectetur labore eius tenetur esse, in
							temporibus sequi cum voluptatem vitae repellat nostrum odio
							perspiciatis dolores recusandae necessitatibus, unde, deserunt
							voluptas possimus veniam magni soluta deleniti! Architecto,
							quidem, totam. Fugit minus odit unde ea cupiditate ab aperiam sed
							dolore facere nihil laboriosam dolorum repellat deleniti aliquam
							fugiat laudantium delectus sint iure odio, necessitatibus rem
							quisquam! Ipsum praesentium quam nisi sint, impedit sapiente
							facilis laudantium mollitia quae fugiat similique. Dolor maiores
							aliquid incidunt commodi doloremque rem! Quaerat, debitis
							voluptatem vero qui enim, sunt reiciendis tempore inventore
							maxime quasi fugiat accusamus beatae modi voluptates iste officia
							esse soluta tempora labore quisquam fuga, cum. Sint nemo iste
							nulla accusamus quam qui quos, vero, minus id. Eius mollitia
							consequatur fugit nam consequuntur nesciunt illo id quis
							reprehenderit obcaecati voluptates corrupti, minus! Possimus,
							perspiciatis!</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How do I delete my
						account?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Illo tempore soluta, minus magnam non blanditiis dolore, in nam
							voluptas nobis minima deserunt deleniti id animi amet, suscipit
							consequuntur corporis nihil laborum facere temporibus. Qui
							inventore, doloribus facilis, provident soluta voluptas excepturi
							perspiciatis fugiat odit vero! Optio assumenda animi at!
							Assumenda doloremque nemo est sequi eaque, ipsum id, labore rem
							nisi, amet similique vel autem dolore totam facilis deserunt.
							Mollitia non ut libero unde accusamus praesentium sint maiores,
							illo, nemo aliquid?</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How do I change my
						account settings?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">I forgot my
						password. How do I reset it?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Ipsum at aspernatur iure facere ab a corporis mollitia molestiae
							quod omnis minima, est labore quidem nobis accusantium ad totam
							sunt doloremque laudantium impedit similique iste quasi cum!
							Libero fugit at praesentium vero. Maiores non consequuntur rerum,
							nemo a qui repellat quibusdam architecto voluptatem? Sequi,
							possimus, cupiditate autem soluta ipsa rerum officiis cum libero
							delectus explicabo facilis, odit ullam aperiam reprehenderit!
							Vero ad non harum veritatis tempore beatae possimus, ex odio quo.</p>
					</div> <!-- cd-faq-content --></li>
			</ul>
			<!-- cd-faq-group -->

			<ul id="payments" class="cd-faq-group">
				<li class="cd-faq-title"><h2>Payments</h2></li>
				<li><a class="cd-faq-trigger" href="#0">Can I have an
						invoice for my subscription?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Impedit quidem delectus rerum eligendi mollitia, repudiandae quae
							beatae. Et repellat quam atque corrupti iusto architecto impedit
							explicabo repudiandae qui similique aut iure ipsum quis inventore
							nulla error aliquid alias quia dolorem dolore, odio excepturi
							veniam odit veritatis. Quo iure magnam, et cum. Laudantium, eaque
							non? Tempore nihil corporis cumque dolor ipsum accusamus sapiente
							aliquid quis ea assumenda deserunt praesentium voluptatibus,
							accusantium a mollitia necessitatibus nostrum voluptatem numquam
							modi ab, sint rem.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">Why did my credit
						card or PayPal payment fail?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Tenetur accusantium dolorem vel, ad, nostrum natus eos, nemo
							placeat quos nisi architecto rem dolorum amet consectetur
							molestiae reprehenderit cum harum commodi beatae necessitatibus.
							Mollitia a nostrum enim earum minima doloribus illum autem,
							provident vero et, aspernatur quae sunt illo dolorem. Corporis
							blanditiis, unde, neque, adipisci debitis quas ullam accusantium
							repudiandae eum nostrum quis! Velit esse harum qui, modi ratione
							debitis alias laboriosam minus eaque, quod, itaque labore illo
							totam aut quia fuga nemo. Perferendis ipsa laborum atque
							assumenda tempore aspernatur a eos harum non id commodi excepturi
							quaerat ullam, explicabo, incidunt ipsam, accusantium quo magni
							ut! Ratione, magnam. Delectus nesciunt impedit praesentium sed,
							aliquam architecto dolores quae, distinctio consectetur obcaecati
							esse, consequuntur vel sit quis blanditiis possimus dolorum.
							Eaque architecto doloremque aliquid quae cumque, vitae
							perferendis enim, iure fugiat, soluta aut!</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">Why does my bank
						statement show multiple charges for one upgrade?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>
			</ul>
			<!-- cd-faq-group -->

			<ul id="privacy" class="cd-faq-group">
				<li class="cd-faq-title"><h2>Privacy</h2></li>
				<li><a class="cd-faq-trigger" href="#0">Can I specify my
						own private key?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Impedit quidem delectus rerum eligendi mollitia, repudiandae quae
							beatae. Et repellat quam atque corrupti iusto architecto impedit
							explicabo repudiandae qui similique aut iure ipsum quis inventore
							nulla error aliquid alias quia dolorem dolore, odio excepturi
							veniam odit veritatis. Quo iure magnam, et cum. Laudantium, eaque
							non? Tempore nihil corporis cumque dolor ipsum accusamus sapiente
							aliquid quis ea assumenda deserunt praesentium voluptatibus,
							accusantium a mollitia necessitatibus nostrum voluptatem numquam
							modi ab, sint rem.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">My files are
						missing! How do I get them back?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How can I access my
						account data?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Possimus magni vero deserunt enim et quia in aliquam, rem tempore
							voluptas illo nisi veritatis quas quod placeat ipsa! Error qui
							harum accusamus incidunt at libero ipsum, suscipit dolorum esse
							explicabo in eius voluptates quidem voluptatem inventore amet
							eaque deserunt veniam dignissimos excepturi? Dolore, quo amet
							nostrum autem nemo. Sit nam assumenda, corporis ea sunt
							distinctio nostrum doloribus alias, beatae nesciunt dolore saepe
							consequuntur minima eveniet porro dolor officiis maiores ab
							obcaecati officia enim aliquam. Itaque fuga molestiae hic
							accusantium atque corporis quia id sequi enim vero? Hic aperiam
							sint facilis aliquam quia, accusamus tenetur earum totam enim
							est, error. Iusto, reiciendis necessitatibus molestias.
							Voluptatibus eos explicabo repellat nesciunt nam vero minima.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How can I control
						if other search engines can link to my profile?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>
			</ul>
			<!-- cd-faq-group -->

			<ul id="delivery" class="cd-faq-group">
				<li class="cd-faq-title"><h2>Delivery</h2></li>
				<li><a class="cd-faq-trigger" href="#0">What should I do if
						my order hasn't been delivered yet?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Impedit quidem delectus rerum eligendi mollitia, repudiandae quae
							beatae. Et repellat quam atque corrupti iusto architecto impedit
							explicabo repudiandae qui similique aut iure ipsum quis inventore
							nulla error aliquid alias quia dolorem dolore, odio excepturi
							veniam odit veritatis. Quo iure magnam, et cum. Laudantium, eaque
							non? Tempore nihil corporis cumque dolor ipsum accusamus sapiente
							aliquid quis ea assumenda deserunt praesentium voluptatibus,
							accusantium a mollitia necessitatibus nostrum voluptatem numquam
							modi ab, sint rem.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How can I find your
						international delivery information?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">Who takes care of
						shipping?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How do returns or
						refunds work?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Impedit quidem delectus rerum eligendi mollitia, repudiandae quae
							beatae. Et repellat quam atque corrupti iusto architecto impedit
							explicabo repudiandae qui similique aut iure ipsum quis inventore
							nulla error aliquid alias quia dolorem dolore, odio excepturi
							veniam odit veritatis. Quo iure magnam, et cum. Laudantium, eaque
							non? Tempore nihil corporis cumque dolor ipsum accusamus sapiente
							aliquid quis ea assumenda deserunt praesentium voluptatibus,
							accusantium a mollitia necessitatibus nostrum voluptatem numquam
							modi ab, sint rem.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How do I use
						shipping profiles?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How does your UK
						Next Day Delivery service work?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How does your Next
						Day Delivery service work?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">When will my order
						arrive?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">When will my order
						ship?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>
			</ul>
			<!-- cd-faq-group -->
		</div>
		<!-- cd-faq-items -->
		<a href="#0" class="cd-close-panel">Close</a>
	</section>
	<!-- cd-faq -->
	<script
		src="<%=application.getContextPath()%>/resources/js/jquery-2.1.1_manual.js"></script>
	<script
		src="<%=application.getContextPath()%>/resources/js/jquery.mobile.custom.min_manual.js"></script>
	<script
		src="<%=application.getContextPath()%>/resources/js/main_manual.js"></script>
	<!-- Resource jQuery -->
	
	
	
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

