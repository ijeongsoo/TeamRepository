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
		<ul class="cd-faq-categories">
			<li><a class="selected" href="#basics">드론 소개</a></li>
			<li><a href="#mobile">하드웨어 설정</a></li>
			<li><a href="#account">소프트웨어 설정</a></li>
			<li><a href="#payments">웹페이지 안내</a></li>
			<li><a href="#privacy">기타</a></li>
			<li><a href="#delivery"></a></li>
		</ul>
		<!-- cd-faq-categories -->

		<div class="cd-faq-items">
			<ul id="basics" class="cd-faq-group">
				<li class="cd-faq-title"><h2>드론 소개</h2></li>
				<li><a class="cd-faq-trigger" href="#0">드론 개요</a>
					<div class="cd-faq-content">
						<p>
							[ 쿼드 콥터 ]
							<br>
							<br>
							   쿼드콥터란 영어 접두사 quad(4개)를 의미하며 모터가 4개로 돌아가는 드론을 말합니다.
							  따라서 앞에 붙는 어원에 따라[Tri(3개), quad(4개), hexa(6개)]날개의 개수를
							  알 수 있습니다. 그 다음 콥터 뒤에 나오는 +,x,H등은 드론의 모양을 설명해주는 것이므로
							  날개의 배치 모양을 통해 +인지 x인지 기호를 써서 알려줍니다.
							
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
							<img src="<%=application.getContextPath()%>/resources/image/throttlePitchYawRoll.png">
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

			<ul id="mobile" class="cd-faq-group">
				<li class="cd-faq-title"><h2>하드웨어 설정</h2></li>
				<li><a class="cd-faq-trigger" href="#0">구성 부품</a>
					<div class="cd-faq-content">
						<p>
							<h4> FPV 250 Kit 구성 </h4><br>
							 프레임틀 / 변속기(ESC) / 브러시리스모터(brushless motor) / 드론배터리(리포배터리) / 프로펠러 / 배터리 전압분배 케이블
							<img src="<%=application.getContextPath()%>/resources/image/component_1.png">
							<img src="<%=application.getContextPath()%>/resources/image/component_2.png">
							<img src="<%=application.getContextPath()%>/resources/image/component_3.png">
						</p>
					</div> <!-- cd-faq-content --></li>
					
					<li><a class="cd-faq-trigger" href="#0">주요 부품 제원</a>
					<div class="cd-faq-content">
						<p>
							<h4> 브러쉬리스 모터(brushless motor) - BLDC </h4><br><br>
							
							1. 장점 <br>
							- 브러쉬가 없어서 수명이 길다.<br>
							- 브로쉬로 인한 마찰이 없어 발열이 적다.<br>
							- 효율이 높아 크기가 작다.<br>
							- 부하로 인한 회전수 변동이 적다.<br><br>
							2. 단점 <br>
							- 가격이 비싸다.<br>
							- 변속기 셋팅이 비교적 어렵다.<br><br>
							3. 모터 동작 원리<br>
							<img src="<%=application.getContextPath()%>/resources/image/brushless.gif">       
							<img src="<%=application.getContextPath()%>/resources/image/brushless.jpg"><br>
							DC모터와 달리 도선측이 고정되어 있고, 영구자석측이 회전하는 구조입니다. 자력선이 공기중에는 통과하기 어려우나 금속은
							통과하기 쉽기때문에 회전력을 더욱 강하게 할 수 있습니다. 위 이미지처럼 바깥 축이 회전하고 가운데 축은 전자회로로 전극을 
							빠르게 바꿔주며 회전력이 발생합니다.
							<br><br><br><br>
							
							
							<h4> 전자변속기(ESC) </h4><br><br>
							 <img src="<%=application.getContextPath()%>/resources/image/esc_1.jpg"><br><br>
							1. 전자변속기 종류<br><br>
							- OPTO type : Optoisolator의 약자로 전자 신호를 전기로 연결하지 않고 전달할 수 있는 전자 회로입니다.
							입력 전자 신호는 일련의 빛으로 변환된다. 이 빛을 반대편에 있는 광 검출기가 감지를 합니다. 일종의 모르스 부호를 통신하는 것으로 볼 수 있습니다.
							따라서 신호가 전기가 아닌 빛으로 전송된다.<br><br>
							- BEC/UBEC type : 배터리 제거회로(Battery Eliminator Circuit/Universal Battery Eliminator Circuit)로써
							 멀티로더 배터리(일반적으로 11.1V)의 고전압전기를 저전압(5V)로 변환하는 것으로써, 비행컨트롤러나 서보와 같은 저전압 장치를 구동시키는데 사용됩니다.
							 만약 비행컨트롤러를 배터리와 직접 연결하면 비행컨트롤러가 타버리게 됩니다. 따라서 배터리와 비행컨트롤러 사이에 BEC/UBEC를 두어 적절한 수준으로 전압을
							  떨어뜨려야 합니다.<br><br>
							2. 전자변속기(BEC/UBEC)의 원리<br><br>
							<img src="<%=application.getContextPath()%>/resources/image/ESC_1.png"><br><br>
							전자변속기는 모터와 배터리, 수신모듈 사이에 전선으로 연결하여 사용되는데, 모터(BLDC)쪽으로는 3선으로 연결하며, 배터리로는 전원을 받고 수신모듈쪽에서는 3선(VCC, GND, DATA)이 공급됩니다.
							수신모듈쪽으로 공급되는 3선(VCC, GND, DATA)은 전원은 5V이며 DATA는 PWM 2ms 주기의 아날로그 신호(수신기에서 전자변속기로의 신호)입니다.<br>
							즉, 배터리에서 전력을 공급받고 수신기의 명령을 받아 BLDC모터의 속도 및 방향을 제어하는 것입니다.<br><br>
							전자변속기는 무선수신모듈에서 제공되는 PWM신호를 가지고 모터를 제어할 수 있고, 전류제어로 변환하여 서보모터나 BLDC를 제어합니다.
							수신기에서 변속기쪽으로 유입되는 선이 있는데, 여기로 PWM출력이 나오며 또한 변속기에 붙은 배터리의 전원이 변속기를 지나 수신모듈에 공급됩니다.
							<br><br><br><br>
							
							
							<h4> 배터리 </h4><br><br>
							1. 배터리 종류<br><br>
							니켈수소, 리튬철, 리튬폴리머 등이 있으며, 드론이나 멀티콥터에 가장 적합한 배터리는 단연 리튬폴리머(LiPo)배터리입니다. 왜냐하면 배터리 자체 무게 대비 전압 및 방전율이 월등하기 때문입니다.
							<br><br>
							2. 배터리 제원 보는 방법<br><br>
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
							[주의사항]<br><br>
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
							
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">드론조립 및 조립시 주의사항</a>
					<div class="cd-faq-content">
						<p>
							<h4> 드론 조립 </h4> <br><br>
							1. 드론 프레임에 모터를 고정시킵니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_1.png"><br><br>
							2. 모터를 모두 틀에 연결시킵니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_2.png"><br><br>
							3. 프로펠러를 모터에 연결 시켜 줍니다.<br>
							[주의: 후에 캘리브레이션 설정시 제거해야 하므로 굳이 지금 달 필요는 없습니다. 또한 드론의 앞 뒤 분간을 위해 위와같이 앞뒤로
							나눠 프로펠러를 색깔로 달아주면 좋습니다. 프로펠러를 거꾸로 달지 않도록 주의하세요! 거꾸로 달면 날개를 뒤집어서 돌리는 것과 같이 바람이
							밑이 아닌 위로 향합니다.]<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_3.png"><br><br>
							4. 모터에 베어링을 조립합니다.<br>
							[주의: 프로펠러의 기울기가 2쌍씩 다르게 생겼습니다. 이는 모터의 회전방향이 CW 2개, CCW 2개 이므로 이에 따라 바람을 밑으로 보내기 
							위하여 기울기가 다릅니다. 어떻게 끼는지 모르겠다 싶으면 후에 모터를 돌려난 후 끼면 됩니다. ]<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_4.png"><br><br>
							5. ESC를 모터에 연결시켜 줍니다. 모터의 3개의 케이블선에 임의로 연결해도 상관없습니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_5.png"><br><br>
							6. ESC는 모터의 기능선, +선, -선을 자기가 알아서 처리해줍니다. 따라서 아무렇게나 연결해도 CW, CWW방향으로 돌아가게 해줍니다.
							또한 모터의 방향을 바꿔주고 싶을 땐 임의의 2선을 교차해서 꼽아주면 방향이 거꾸로 돌아갑니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_6.png"><br><br>
							7. 프레임틀에 거치대를 연결해줍니다. 후에 비행 시 거치대가 잘 떨어지므로 본드나 접착제로 고정시켜줍니다.<br>
							<img src="<%=application.getContextPath()%>/resources/image/assembly_7.png"><br>	
						</p>
					</div> <!-- cd-faq-content --></li>

				<li><a class="cd-faq-trigger" href="#0">How do I link to a file or folder?</a>
					<div class="cd-faq-content">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Blanditiis provident officiis, reprehenderit numquam. Praesentium
							veritatis eos tenetur magni debitis inventore fugit, magnam,
							reiciendis, saepe obcaecati ex vero quaerat distinctio velit.</p>
					</div> <!-- cd-faq-content --></li>
			</ul>
			<!-- cd-faq-group -->

			<ul id="account" class="cd-faq-group">
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

