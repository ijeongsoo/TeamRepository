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

								if (pwReg.test($("#joinMpassword").val()) && $("#joinMpassword").val().length<=20) {
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
								if (pwReg.test($("#joinMpassword").val()) && $("#joinMpassword").val().length<=20) {
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

	$(document).ready(function() {
		$("#joinMid").keyup(function() {
			$("#canUse").removeClass("alert-danger");
			$("#canUse").removeClass("alert-warning");
			$("#canUse").removeClass("alert-info");
			$("#canUse").text('중복확인해주세요');

		});

		$("#checkButton").click(function() {
			var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
			if (idReg.test($("#joinMid").val()) && $("#joinMid").val().length<=20) {
				$.ajax({
					'url' : "check_id",
					'data' : {
						'mid' : $("#joinMid").val()
					},
					'type' : "POST",
					'success' : function(data) {
						if (data.result == 1) {
							alert('사용 가능한 아이디 입니다.');
							$("#canUse").removeClass("alert-danger");
							$("#canUse").removeClass("alert-warning");
							$("#canUse").addClass("alert-info");
							$("#canUse").text('중복확인 되었습니다.');

						} else if (data.result == 0) {
							alert('사용 불가능한 아이디 입니다. ');
							$("#canUse").removeClass("alert-info");
							$("#canUse").removeClass("alert-warning");
							$("#canUse").addClass("alert-danger");
							$("#canUse").text('다시 입력해주세요');
						} else {
							alert('아이디를 입력해 주세요 ');
							$("#canUse").removeClass("alert-info");
							$("#canUse").removeClass("alert-danger");
							$("#canUse").addClass("alert-warning");
							$("#canUse").text('아이디를 입력해주세요.');
						}

					}
				});
			} else {
				$("#canUse").addClass("alert-danger");
				$("#canUse").text('ID는 영어, 숫자 조합 6~20자리이어야 합니다.');
				
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
</head>

<body>

	<!-- Header -->
	<header id="header">
		<h1>
			<a href="<%=application.getContextPath()%>/">Team2's SensingCar</a>
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
					<form method="post" class="form-signin" action="<%=application.getContextPath()%>/login">
						<h2 class="form-signin-heading">로그인</h2>
						<label for="inputEmail" class="sr-only">ID</label> <input
							type="text" id="mid" name="mid" class="form-control"
							placeholder="ID" required style="background-color: #e8e8e8">
						<label for="inputPassword" class="sr-only">PW</label> <input
							type="password" id="mpassword" name="mpassword"
							class="form-control" placeholder="Password" required
							style="background-color: #e8e8e8; margin-top: 20px"> <br>
						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
					</form>

				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>

	</div>

	<section id="one" class="wrapper style1 special">
		<div class="container">
			<header class="major">
				<h2>회원가입</h2>
				<p>Team2's SensingCar에 가입 해서 아래와 같은 기능을 누려보세요!</p>
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
	<!-- One -->


	<!-- Two -->


	<!-- <!-- Three -->
	<section id="three" class="wrapper style3 special">
		<header class="major">
			<h2>가입양식</h2>
		</header>
		<div class="container 50%">
			<form id="formjoin" action="<%=application.getContextPath()%>/join"method="post" onsubmit="return check()"
				enctype="multipart/form-data">
				<div class="row uniform">
					<label style="font-size: 23px" class="2u 12u$(small)"
						for="inputEmail">ID</label>
					<div class="8u 12u$(small)" style="text-align: left">
						<input name="joinMid" id="joinMid" type="text" placeholder="ID"
							required> <label id="canUse" style="margin-top: 5px">중복확인해주세요</label>
					</div>
					<div class="2u$ 12u$(small)">
						<a id="checkButton" class="btn btn-primary"
							style="font-size: 23px">중복확인</a>
					</div>

					<label style="font-size: 23px" class="2u 12u$(small)"
						for="inputPassword">PW</label>
					<div class="10u$ 12u$(small)" style="text-align: left">
						<input id="joinMpassword" name="joinMpassword" type="password"
							placeholder="비밀번호" required>

					</div>


					<label style="font-size: 23px" class="2u 12u$(small)"
						for="inputPasswordCheck"></label>
					<div class="10u$ 12u$(small)" style="text-align: left">
						<input id="mPasswordCheck" name="mPasswordCheck" type="password"
							placeholder="비밀번호 확인" required><label
							style="margin-top: 5px" id="pwcheck" name="pwcheck" class="">비밀번호를
							한번 더 입력해주세요.</label>
					</div>


					<label style="font-size: 23px" class="2u 12u$(small)"
						for="inputAddress">주소</label>
					<div class="10u$ 12u$(small)" style="text-align: left">
						<input id="maddress" name="maddress" type="text" placeholder="주소"
							required>
					</div>


					<label style="font-size: 23px" class="2u 12u$(small)"
						for="inputName">이름</label>
					<div class="10u$ 12u$(small)" style="text-align: left">
						<input id="mname" name="mname" type="text" placeholder="이름"
							required>
					</div>


					<label style="font-size: 23px" class="2u 12u$(small)"
						for="inputNumber">HP</label>
					<div class="10u$ 12u$(small)" style="text-align: left">
						<input id="mtel" name="mtel" type="text" placeholder="휴대폰번호"
							required><label>- 없이 적어주세요.</label>
					</div>

					<label style="font-size: 23px" class="2u 12u$(small)" for="email">Email</label>
					<div class="10u$ 12u$(small)" style="text-align: left">
						<input id="memail" name="memail" type="email" placeholder="E-mail"
							required>
					</div>



					<hr />
					<div class="12u$">
						<ul class="actions">

							<li><input id="join" name="join" value="회원가입"
								class="special big" type="submit" /></li>
							<li><input type="reset" class="special big" value="Reset" /></li>
						</ul>
					</div>
				</div>
			</form>
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






