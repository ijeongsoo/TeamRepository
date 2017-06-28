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
<script>
	$(document).ready(function(){
			if (${result} == 0) {
				alert('로그인에 실패하였습니다.');
			}
	});
		
</script>
</head>

<body class="landing">

	<!-- Header -->
	<header id="header">
		<h1>
			<a href="<%=application.getContextPath()%>">Team2's SensingCar</a>
		</h1>
		<nav id="nav">
			<ul>
				<li><button type="button" class="button special"
						data-toggle="modal" data-target="#myModal">Sign up</button></li>
			</ul>
		</nav>

	</header>

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-body">
					<form method="post" action="login" class="form-signin">
						<h2 class="form-signin-heading">로그인</h2>
						<label for="inputEmail" class="sr-only">ID</label> <input
							type="text" id="mid" name="mid" class="form-control"
							placeholder="ID" required  style="background-color:#e8e8e8"> <label
							for="inputPassword" class="sr-only">PW</label> <input
							type="password" id="mpassword" name="mpassword" class="form-control"
							placeholder="Password" required style="background-color:#e8e8e8 ; margin-top: 20px">
						<br>
						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
					</form>

				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>

	</div>
	


	<!-- Banner -->
	<section id="banner">
		<h2>센싱카 컨트롤 페이지에 오신걸 환영합니다.</h2>
		<p>본 웹사이트는 한국소프트웨어산업협회 IoT전문가 과정 프로젝트입니다.</p>
		<ul class="actions">
			<li><a href="join" class="button big">회원가입</a></li>
		</ul>
	</section>

	<!-- One -->
	<section id="one" class="wrapper style1 special">
		<div class="container">
			<header class="major">
				<h2>Team2's SensingCar</h2>
				<p>Team2's SensingCar는 아래와 같은 다양한 기능을 제공합니다.</p>
			</header>
			<div class="row 150%">
				<div class="4u 12u$(medium)">
					<section class="box">
						<i class="icon big rounded color6 fa-rocket"></i>
						<h3>다양한 SensigCar 등록</h3>
						<p>Team2's SensingCar는 다양한 장비의 등록이 가능합니다. 하나의 장비가 아닌 원하는 장비를
							마음껏 선택하고 제어할수 있습니다.</p>
					</section>
				</div>
				<div class="4u 12u$(medium)">
					<section class="box">
						<i class="icon big rounded color9 fa-desktop"></i>
						<h3>Sensor 정보 모니터링</h3>
						<p>Team2's SensingCar는 SensingCar의 다양한 센서 정보를 시각화 하여 제공합니다.
							한눈에 모든 상황을 감시하고 확인 할 수 있습니다.</p>
					</section>
				</div>
				<div class="4u$ 12u$(medium)">
					<section class="box">
						<i class="icon big rounded color1 fa-cloud"></i>
						<h3>SensingCar 조작 및 제어</h3>
						<p>Team2's SensingCar는 SensingCar를 웹브라우저를 통해 조작및 제어가 가능하게 합니다.
							다양한 기능을 지금 동작해보세요.</p>
					</section>
				</div>
			</div>
		</div>
	</section>

	<!-- Two -->
	<section id="two" class="wrapper style2 special">
		<div class="container">
			<header class="major">
				<h2>Developer</h2>
				<p>Team2's SensingCar를 만든 인재들을 소개합니다.</p>
			</header>
			<section class="profiles" style="text-align: center">
				<div style="text-align: center;">
					<section class="3u 12u(medium) 12u$(xsmall) profile"
						style="display: inline-block; margin-left: 150px">
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



</body>
</html>






