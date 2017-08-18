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
<script
	src="<%=application.getContextPath()%>/resources/js/chartTheme.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/sensingcarInfoChart3.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/sensingcarInfoChart.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/sensingcarInfoChart2.js"></script>



<script type="text/javascript">
	function fileChange(event) {
		
		if ($("#sattach")[0].files.length != 0) {
			var originalfilename = $("#sattach")[0].files[0].name;
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
	$('.modal').on('hidden.bs.modal', function(e) {
		$(this).find('form')[0].reset()
		});
	$(document)
			.ready(
					function() {
						$("#sip").keyup(function() {
							$("#canUse").removeClass("alert-danger");
							$("#canUse").removeClass("alert-warning");
							$("#canUse").removeClass("alert-info");
							$("#canUse").text('중복 및 통신 확인해주세요');

						});

						$("#checkButton")
								.click(
										function() {
											$("#canUse").removeClass(
													"alert-info");
											$("#canUse").removeClass(
													"alert-danger");
											$("#canUse").removeClass(
													"alert-warning");
											$('#canUse')
													.html(
															"<img width='20px' src='resources/images/loading.gif'/>");

											if ($("#sip").val() == "") {
												$("#canUse").addClass(
														"alert-danger");
												$("#canUse")
														.text('IP를 입력해주세요.');
											} else {
												$
														.ajax({
															'url' : "check_ip",
															'data' : {
																'sip' : $(
																		"#sip")
																		.val(),
																'command' : "status"
															},
															'type' : "POST",
															'beforSend' : function() {
																$("#canUse")
																		.html(
																				"<img width='20px' src='resources/images/loading.gif'/>")
															},
															'success' : function(
																	data) {
																if (data.result == 1) {
																	$("#canUse")
																			.removeClass(
																					"alert-danger");
																	$("#canUse")
																			.removeClass(
																					"alert-warning");
																	$("#canUse")
																			.addClass(
																					"alert-info");
																	$("#canUse")
																			.text(
																					'사용가능합니다.');

																} else if (data.result == 0) {
																	$("#canUse")
																			.removeClass(
																					"alert-info");
																	$("#canUse")
																			.removeClass(
																					"alert-warning");
																	$("#canUse")
																			.addClass(
																					"alert-danger");
																	$("#canUse")
																			.text(
																					'이미 등록된 SensingCar입니다.');
																} else if (data.result == -1) {
																	$("#canUse")
																			.removeClass(
																					"alert-info");
																	$("#canUse")
																			.removeClass(
																					"alert-warning");
																	$("#canUse")
																			.addClass(
																					"alert-danger");
																	$("#canUse")
																			.text(
																					'통신이 되지 않습니다.');
																} else {
																	$("#canUse")
																			.removeClass(
																					"alert-info");
																	$("#canUse")
																			.removeClass(
																					"alert-danger");
																	$("#canUse")
																			.addClass(
																					"alert-warning");
																	$("#canUse")
																			.text(
																					'아이피를 입력해주세요.');
																}

															}
														});
											}

										});
					});
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
<script>

	
	function controlAlert() {
		alert('접속이 불가능한 상태입니다.');
	}

	function controlYet() {
		alert('통신상태를 확인중입니다.');
	}

	<c:forEach var="s" items="${list}">
		setInterval("checkComunication('${s.sip}','${s.sregistor}${s.sno}')", 2000)
	</c:forEach>

	function checkComunication(ip, dest) {
		var json = {
			"command" : "status",
			"sip" : ip
		};
		var dest = "#" + dest;

		$.ajax({
					url : "http://" + location.host
							+ "/Team2SensingCarWebControl/check_comunication",
					data : json,
					method : "post",
					success : function(data) {
						if (data.result == 1) {
							$(dest)
									.html(
											"접속가능 <img width='15px' src='resources/images/green.png'/>");
							$(dest).parent().parent().attr('href',
									'control?sip=' + ip);
							$(dest).parent().parent().attr('onclick', '');

						} else {
							$(dest)
									.html(
											"접속불가능 <img width='15px' src='resources/images/red.png'/>");
							$(dest).parent().parent().attr('href',
									'javascript:void(0);');
							$(dest).parent().parent().attr('onclick',
									'controlAlert();');

						}
					}
				});
	}
</script>





<script type="text/javascript">

<c:forEach var="i" begin="1" end="1" step="1">
	setInterval("drawUserAvailable('${login_info.mid}')", 2000)
</c:forEach>

function drawUserAvailable(mid) {

	var userAvailablecount=0;
	var userNoAvailablecount=0;
	<c:forEach var="s" items="${list}">
	var dest = '#${s.sregistor}${s.sno}';
	if(($('#${s.sregistor}${s.sno}').html())=='접속가능 <img width="15px" src="resources/images/green.png">' && $('#${s.sregistor}${s.sno}').siblings('h4').html()==(mid+" ")){
		userAvailablecount++;
	}else if(($('#${s.sregistor}${s.sno}').html())=='접속불가능 <img width="15px" src="resources/images/red.png">' && $('#${s.sregistor}${s.sno}').siblings('h4').html()==(mid+" ")){
		userNoAvailablecount++;
	}
	</c:forEach>
	
	console.log(sensingcarInfoChart2);
	var userTotal=userAvailablecount+userNoAvailablecount;
	   
	sensingcarInfoChart2.xAxis[0].setTitle({
        text: mid
    });
	sensingcarInfoChart2.series[0].data[0].update({'y':userTotal});
	sensingcarInfoChart2.series[1].data[0].update({'y':userNoAvailablecount});
	sensingcarInfoChart2.series[2].data[0].update({'y': userAvailablecount});
}
</script>





</head>

<body class="landing">

	<!-- Header -->
	<header id="header">
		<h1>
			<a href="<%=application.getContextPath()%>/">Team2's SensingCar</a>
		</h1>
		<nav id="nav">
			<ul>
				<li><a type="button" class="button special" href="logout">LogOut</a></li>
			</ul>
		</nav>

	</header>

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-md">

			<!-- Modal content-->
			<div id="myModalContent" class="modal-content">

				<div class="modal-body">
					<form id="modalForm" method="post" action="regist"
						class="form-signin" onsubmit="return check()"
						enctype="multipart/form-data">

						<h2 class="form-signin-heading">SensingCar 등록</h2>
						<div class="row">
							<input type="hidden" id="sregistor" name="sregistor"
								value="${login_info.mname}">
							<div class="col-sm-2">
								<label style="font-size: 23px">IP:</label>
							</div>
							<div class="col-sm-6">
								<input type="text" id="sip" name="sip" class="form-control"
									placeholder="IP" required style="background-color: #e8e8e8">
								<label class="help-block" id="canUse" style="margin-top: 5px">확인해주세요.</label>
							</div>

							<div class="2u$ 12u$(small)">
								<a id="checkButton" class="btn btn-primary"
									style="font-size: 17px">등록 및 통신확인</a>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label style="font-size: 23px">이름:</label>
							</div>
							<div class="col-sm-10">
								<input type="text" id="sname" name="sname" class="form-control"
									placeholder="이름" required style="background-color: #e8e8e8">
							</div>
						</div>

						<br>
						<div>
							<label style="font-size: 23px" for="inputPassword">대표사진</label>
						</div>

						<div class="col-sm-12" style="text-align: center">
							<div>
								<img id="imgFileView" /> 
								<label for="sattach" id="sattachBtn" class="btn btn-default">등록</label> 
								<input type="file" id="sattach" style="visibility: hidden" onchange="fileChange(event)" name="sattach"/>
								<label class="help-block" id="imgcheck" name="imgcheck">사진을 반드시 첨부해 주세요.</label>
							</div>
						</div>

						<br>
						<div style="margin-top: 110px">
							<label style="font-size: 23px" for="inputPassword">설명</label>
						</div>

						<div class="12u$">
							<textarea name="sdescription" id="sdescription" placeholder="설명"
								rows="6" style="background-color: #e8e8e8" required></textarea>
						</div>
						<br>

						<button class="btn btn-lg btn-primary btn-block" type="submit">등록하기</button>
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
		<h2>${login_info.mid}님환영합니다.</h2>
		<p>본 웹사이트는 한국소프트웨어산업협회 IoT전문가 과정 프로젝트입니다.</p>

	</section>

	<!-- One -->
	<section id="one" class="wrapper style1 special">
		<div class="container">
			<header class="major">
				<h2>등록된 SensingCar</h2>
				<p>현재 등록된 SensigCar는 다음과 같으며 추가할 수 있습니다.</p>
			</header>
			<div class="row 150%">
				<c:forEach var="s" items="${list}">
					<div class="4u 12u$(medium)">
						<a href='javascript:void(0);' onclick='controlYet();'>
							<section class="box">

								<img
									src="<%=application.getContextPath()%>/file?ssavedfilename=${s.ssavedfilename}&sfilecontent=${s.sfilecontent}"
									class="photo3" />
								<h3>${s.sip}</h3>

								<h3>${s.sname}</h3>
								<h4 class="checkMid">${s.sregistor} </h4>
								<p class="available" id="${s.sregistor}${s.sno}"><img width="15px" src="resources/images/loading.gif" /></p>
							</section>
						</a>
					</div>
				</c:forEach>


				<div class="4u 12u$(medium)">
					<a data-toggle="modal" data-target="#myModal">
						<section class="box">
							<img class="icon big rounded " src="resources/images/plus.png">
							</image>
							<h3>자신만의 새로운</h3>
							<h3>SensingCar를 추가하세요.</h3>
							<p>Click This Button</p>
						</section>
					</a>
				</div>
			</div>
		</div>
	</section>


	<section id="one" class="wrapper style2 special" style="color: black">
		<div class="container">
			<header class="major">
				<h2>등록된 Sensingcar 요약정보</h2>
				<p>현재 등록된 SensigCar에 대한 요약 정보 입니다.</p>
			</header>
			<div class="row 150%">
				<div class="6u 12u$(medium)">
					<section class="major">
						<div class="container" id="sensingcarInfoChartContainer1"
							style="min-width: 310px; height: 400px; max-width: 400px; margin: 0 auto"></div>
					</section>
				</div>

				<div class="6u 12u$(medium)">
					<section class="major">
						<div class="container" id="sensingcarInfoChartContainer2"
							style="min-width: 310px; height: 400px; max-width: 500px; margin: 0 auto"></div>
					</section>
				</div>


			</div>
			<hr>
			
			<div class="row 150%">
			<hr>
				<div class="12u$ 12u$(medium)">
				
					<section class="major">
						<div class="container" id="sensingcarInfoChartContainer3"
							style="min-width: 310px; height: 400px; max-width: 1200px; margin: 0 auto"></div>
						<p class="available" id="result">
						<div style="text-align: left">
							<span style="font-size: 20pt; font-weight: bold; width: 1000px">
								Network Quality : </span> <img
								id="testPTagChange" width="15px"
								src="resources/images/loading.gif" /> <span
								style="font-size: 20pt; font-weight: bold; width: 1000px"
								id="testSTagChange">Ready</span>
						</div>
						</p>
					</section>
				</div>

			</div>
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



</body>
</html>






