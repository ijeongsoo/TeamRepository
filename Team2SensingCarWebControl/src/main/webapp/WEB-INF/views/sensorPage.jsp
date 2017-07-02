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
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
	
<script
	src="<%=application.getContextPath()%>/resources/js/chartTheme.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/sensingcarInfoChart3.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/sensingcarInfoChart.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/sensingcarInfoChart2.js"></script>
		
<script
	src="<%=application.getContextPath()%>/resources/js/gassensorInfoChart.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/photoresistorInfoChart.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/thermistorInfoChart.js"></script>
	<script
	src="<%=application.getContextPath()%>/resources/js/ultrasonicsensorInfoChart.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/trackingInfoChart.js"></script>

<style>
@media screen and (min-width: 480px) {
	#title {
		height: 10px;
	}
}
</style>

<style type="text/css">
.box {
	background-color: #ffeedd;
}

.box:hover {
	border: 1px solid #F90;
	background-color: #ffccdds;
}

.box:active {
	border: 1px solid #A00;
}
</style>








</head>

<body class="landing">

	<!-- Header -->
	<header id="header">
		<h1>
			<a href="<%=application.getContextPath()%>">Team2's SensingCar</a>
		</h1>
		<nav id="nav">
			<ul>
				<li><a type="button" class="button special" href="logout">Log
						Out</a></li>
			</ul>
		</nav>

	</header>



	<!-- Banner -->
	<section id="banner">
		<h2>${login_info.mid}님환영합니다.</h2>
		<p>본 웹사이트는 한국소프트웨어산업협회 IoT전문가 과정 프로젝트입니다.</p>

	</section>


	<section id="one" class="wrapper style2 special" style="color: black">
		<div class="container">
			<header class="major">
				<h2>Sensingcar의 센서 현황</h2>
				
			</header>
			<div class="row 150%">
				<div class="6u 12u$(medium)">
					<section class="major">
					<p>가스센서의 현황입니다.</p>
			<div class="container" id="gassensorInfoChartContainer" style="min-width: 310px; height: 400px; max-width: 500px; margin: 0 auto"></div>
					</section>
				</div>
			
				<div class="6u 12u$(medium)">
					<section class="major">
					<p>조도센서의 현황입니다.</p>
						<div class="container" id="photoresistorInfoChartContainer"
							style="min-width: 310px; height: 400px; max-width: 500px; margin: 0 auto"></div>
					</section>
				</div>
				
				<div class="6u 12u$(medium)">
					<section class="major">
					<p>온도센서의 현황입니다.</p>
						<div class="container" id="thermistorInfoChartContainer"
							style="min-width: 310px; height: 400px; max-width: 500px; margin: 0 auto"></div>
					</section>
				</div>
				
				<div class="6u 12u$(medium)">
					<section class="major">
					<p>트래킹센서의 현황입니다.</p>
						<div class="container" id="trackingInfoChartContainer"
							style="min-width: 310px; height: 400px; max-width: 500px; margin: 0 auto"></div>
					</section>
				</div>
				
				<div class="6u 12u$(medium)">
					<section class="major">
					<p>거리감지센서의 현황입니다.</p>
						<div class="container" id="ultrasonicsensorInfoChartContainer"
							style="min-width: 310px; height: 400px; max-width: 500px; margin: 0 auto"></div>
					</section>
				</div>
				
			</div>
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



</body>
</html>






