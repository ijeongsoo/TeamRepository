<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Team2's SensingCar</title>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/skel.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/style-xlarge.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/circle-img.css"
	rel="stylesheet" type="text/css" />

<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/skel.min.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/skel-layers.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/init.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts-more.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/modules/solid-gauge.js"></script>

<script src="<%=application.getContextPath()%>/resources/js/rgbView.js"></script>




<style>
@media screen and (min-width: 480px) {
	#title {
		height: 10px;
	}
}
</style>

<script>



/* $(function(){
	window.moveTo(0, 0);
	window.resizeTo(screen.availWidth, screen.availHeight);
	if($(window).width()>980){
		$( ".leftcon " ).css("height", ($(window).width()/10));
		 
	}
	
	$( window ).resize(function() {
		if($(window).width()>980){
		  $( ".leftcon" ).css("height", ($(window).width()/10));
		}else{
			$( ".leftcon" ).css("height", '200px');
		}
	});

}); */
</script>

</head>

<body>

	<!-- Header -->
	<header id="header">
		<h1 class="0u(medium)">
			<a href="<%=application.getContextPath()%>">Team2's SensingCar</a> <span>|
				"${sensingcar.sregistor}"님이 등록하신 "${sensingcar.sname}":
				${sensingcar.sip }</span>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href="generic.html">RealTime Sensor</a></li>
				<li><a href="<%=application.getContextPath()%>">Home</a></li>
				<li><a type="button" class="button special" href="logout">Log
						Out</a></li>
			</ul>
		</nav>
	</header>

	<!-- Main -->
	<section id="main" class="wrapper" style="padding: 0px">
		<div class="row">
			<div class="3u 12u(medium) ">
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-thermistor"
						style="height:11em"></div>

					<div class="6u$ 6u(medium) leftcon" id="container-photoresistor"
						style="height:11em"></div>
				</div>
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-gas"
						style="height:11em"></div>
					<div class="6u$ 6u$(medium) leftcon" id="container-ultrasonic"
						style="height:11em"></div>
				</div>
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-rgb"
						style="height:11em"></div>
					<div class="6u$ 6u$(medium) leftcon" id="container-tracking"
						style="height:11em"></div>
				</div>
			</div>
			<div class="6u 12u(medium)"
				style="padding-left: 0; height: 0; padding-bottom: 31%;">
				<div class="thumbnail">
					<div class="centered">
						<img src="${cameraUrl}" class="portrait" />
					</div>
				</div>
			</div>
			<div class="3u$ 12u(medium)" style="height: 0; padding-bottom: 31%;">
			</div>


		</div>
	</section>

	<!-- One -->
	<section id="one" class="wrapper style2 special">

		aaaaaaaaaaaaaaaaaaa</section>


	<section id="one" class="wrapper style2 special" style="color: black">
		<div class="container">
			<header class="major">
				<h2>등록된 Sensingcar 요약정보</h2>
				<p>현재 등록된 SensigCar에 대한 요약 정보 입니다.</p>
			</header>

		</div>
	</section>



	<!-- Two -->
	<section id="two" class="wrapper style1 special">
		<div class="container">
			<header class="major">
				<h2>Developer</h2>
				<p>Team2's SensingCar를 만든 인재들을 소개합니다.</p>
			</header>
			<section class="profiles" style="text-align: center">
				<div style="text-align: center;">
					<section class="3u 12u(medium) 12u$(xsmall) profile"
						style="display: inline-block">
						<img height="100" src="resources/images/jsPhoto.jpg" alt="" />
						<h4>이정수</h4>
						<p>Phon. 010-9895-5986</p>
					</section>
					<section class="3u 12u$(medium) 12u$(xsmall) profile"
						style="display: inline-block;">
						<img height="100" src="resources/images/hkPhoto.jpg" alt="" />
						<h4>강현규</h4>
						<p>Phon. 010-9895-5986</p>
					</section>
					<section class="3u 12u(medium) 12u$(xsmall) profile"
						style="display: inline-block;">
						<img height="100" src="resources/images/jhPhoto.jpeg" alt="" />
						<h4>조재훈</h4>
						<p>Phon. 010-9895-5986</p>
					</section>

				</div>
			</section>
			<footer>
				<p>해당 프로젝트는 한국 소프트웨어 산업협회 IoT 전문가 개발 양성과정 프로젝트로 위 개발자들에 의해 개발
					되었습니다. 사이트 혹은 기능 관련된 문의는 위의 연락처로 문의 해주시길 바라며 개발자의 상세 이력을 확인하시려면 아래
					버튼을 클릭해 주세요.</p>
				<ul class="actions">
					<li><a href="#" class="button big">개발자 상세 이력확인</a></li>
				</ul>
			</footer>
		</div>
	</section>



	<!-- Footer -->
	<footer id="footer">
		<div class="container">
			<section class="links">
				<div class="row">
					<section class="3u 6u(medium) 12u$(small)">
						<h3>한국 소프트웨어 산업협회</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Nesciunt itaque, alias possimus</a></li>
							<li><a href="#">Optio rerum beatae autem</a></li>
							<li><a href="#">Nostrum nemo dolorum facilis</a></li>
							<li><a href="#">Quo fugit dolor totam</a></li>
						</ul>
					</section>
					<section class="3u 6u$(medium) 12u$(small)">
						<h3>웹 개발 참고 사이트</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Reiciendis dicta laboriosam enim</a></li>
							<li><a href="#">Corporis, non aut rerum</a></li>
							<li><a href="#">Laboriosam nulla voluptas, harum</a></li>
							<li><a href="#">Facere eligendi, inventore dolor</a></li>
						</ul>
					</section>
					<section class="3u 6u(medium) 12u$(small)">
						<h3>SensingCar 개발 참고 사이트</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Distinctio, inventore quidem nesciunt</a></li>
							<li><a href="#">Explicabo inventore itaque autem</a></li>
							<li><a href="#">Aperiam harum, sint quibusdam</a></li>
							<li><a href="#">Labore excepturi assumenda</a></li>
						</ul>
					</section>
					<section class="3u$ 6u$(medium) 12u$(small)">
						<h3>네트워크 통신관련 참고 사이트</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Recusandae, culpa necessita nam</a></li>
							<li><a href="#">Cupiditate, debitis adipisci blandi</a></li>
							<li><a href="#">Tempore nam, enim quia</a></li>
							<li><a href="#">Explicabo molestiae dolor labore</a></li>
						</ul>
					</section>
				</div>
			</section>
			<div class="row">
				<div class="8u 12u$(medium)">
					<ul class="copyright">
						<li>&copy; Team2. All rights reserved.</li>
						<li>Design: <a href="http://templated.co">TEMPLATED</a></li>
						<li>Images: <a href="http://unsplash.com">Unsplash</a></li>
					</ul>
				</div>
				<div class="4u$ 12u$(medium)">
					<ul class="icons">
						<li><a class="icon rounded fa-facebook"><span
								class="label">Facebook</span></a></li>
						<li><a class="icon rounded fa-twitter"><span
								class="label">Twitter</span></a></li>
						<li><a class="icon rounded fa-google-plus"><span
								class="label">Google+</span></a></li>
						<li><a class="icon rounded fa-linkedin"><span
								class="label">LinkedIn</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
	<script src="<%=application.getContextPath()%>/resources/js/gauge.js"></script>



</body>
</html>






