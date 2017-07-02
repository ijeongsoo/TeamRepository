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
<link href="<%=application.getContextPath()%>/resources/css/toggle.css"
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
<script
	src="<%=application.getContextPath()%>/resources/js/trackingView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/speedView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/cameraAngleView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ultraAngleView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/frontTireAngleView.js"></script>
	<script
	src="<%=application.getContextPath()%>/resources/js/laserEmitter.js"></script>
	<script
	src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>




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
	 $(function(){
		 
		var red;
		var green;
		var blue;
		 rgbView.series[0].data[0].update({'y':${red}});
		 rgbView.series[0].data[1].update({'y':${green}});
		 rgbView.series[0].data[2].update({'y':${blue}});
		
		  red = ${red};
		 green = ${green};
		  blue = ${blue};
		 red = red.toString(16);
		 if(red.length==1){
			 red='0'+red;
		 }
		 green = green.toString(16);
		 if(green.length==1){
			 green='0'+green;
		 }
		 blue = blue.toString(16);
		 if(blue.length==1){
			 blue='0'+blue;
		 }
		 var bgColor = '#'+red+green+blue;
		 rgbView.renderer.circle('50%', '49%', '15%').attr({
	            fill: bgColor,
	            stroke: 'gray',
	            'stroke-width': 1
	        }).add();
		 
		 var series = chartThermistor.series[0];
			series.data[0].update({'y':${temperature}});
			 series = chartPhotoresistor.series[0];
			series.data[0].update({'y':${photoresistor}});
			
			
			 series = chartGas.series[0];
			series.data[0].update({'y':${gas}});
			 series = chartUltrasonic.series[0];
			series.data[0].update({'y':${distance}});
			
			series = trackingView.series[0];
			if(${tracking}==0){
				series.data[0].update({'y':0});
				 series.data[1].update({'y':1});
				 
				 
			}else if(${tracking}==1){
				series.data[0].update({'y':1});
				 series.data[1].update({'y':0});
				
			}
			
			speedView.series[0].points[0].update(${backTireSpeed});
			ultraAngleView.series[0].points[0].update(${ultrasonicSensorAngle});
			frontTireAngleView.series[0].points[0].update(${frontTireAngle});
			cameraAngleView.series[0].points[0].update(${leftRight});
			cameraAngleView.series[1].points[0].update(${upDown});
	 });

	setInterval("thermistorSensor('${sensingcar.sip}')", 1000);
	 setInterval("photoresistorSensor('${sensingcar.sip}')", 1000);
	 setInterval("gasSensor('${sensingcar.sip}')", 1000);
	 setInterval("ultrasonicSensor('${sensingcar.sip}')", 1000);
	 setInterval("trackingSensor('${sensingcar.sip}')", 1000);
	function thermistorSensor(ip) {
		var json = {
			"command" : "status",
			"sip" : ip
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2SensingCarWebControl/thermistorSensor",
			data : json,
			method : "post",
			success : function(data) {
				var series = chartThermistor.series[0];
				series.data[0].update({'y':data.temperature});
			}
		});
	}
	 
	 function photoresistorSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/photoresistorSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = chartPhotoresistor.series[0];
					series.data[0].update({'y':data.photoresistor});
				}
			});
		}
	 
	 function gasSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/gasSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = chartGas.series[0];
					series.data[0].update({'y':data.gas});
				}
			});
		}
	 
	 function ultrasonicSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/ultrasonicSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = chartUltrasonic.series[0];
					series.data[0].update({'y':data.distance});
				}
			});
		}
	 
	 function trackingSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/trackingSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = trackingView.series[0];
					if(data.tracking==0){
						series.data[0].update({'y':0});
						 series.data[1].update({'y':1});
						 
						 
					}else if(data.tracking==1){
						series.data[0].update({'y':1});
						 series.data[1].update({'y':0});
						
					}
			
					
				}
			});
		}
	 
	 
	
	 

		
		
	 
	/* KeyEvent */
	function keyEvent(){
		console.log("키눌림");
	};
	 
	 
</script>


</head>

<body onkeydown="keyEvent()">

	<!-- Header -->
	<header id="header">
		<h1 class="0u(medium)">
			<a href="<%=application.getContextPath()%>">Team2's SensingCar</a> <span>|
				"${sensingcar.sregistor}"님이 등록하신 "${sensingcar.sname}":
				${sensingcar.sip }</span>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href="sensor?sip=${sensingcar.sip }">RealTime Sensor</a></li>
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
						style="height: 10em"></div>

					<div class="6u$ 6u(medium) leftcon" id="container-photoresistor"
						style="height: 10em"></div>
				</div>
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-gas"
						style="height: 10em"></div>
					<div class="6u$ 6u$(medium) leftcon" id="container-ultrasonic"
						style="height: 10em"></div>
				</div>
				<hr>

				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-rgb"
						style="height: 10em"></div>
					<div class="6u$ 6u$(medium) leftcon" id="container-tracking"
						style="height: 10em"></div>
				</div>
			</div>

			<div class="6u 12u$(medium)"
				style="padding-left: 0; padding-right: 0; height: auto">
				<div class="thumbnail">
					<div class="centered">
						<img src="${cameraUrl}" class="portrait" />
					</div>
				</div>
			</div>

			<div class="3u$ 12u$(medium)"
				style="padding-left: 0; padding-right: 0;">
				<div class="row">
					<div class="12u$ 12u$(medium) leftcon" id="container-speed"
						style="height: 14em"></div>

				</div>

				<div class="row">
					<div class="12u 12u$(medium) leftcon" id="container-cameraAngle"
						style="height: 8em"></div>

				</div>
				<hr>
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-ultraAngle"
						style="height: 8em"></div>
					<div class="6u 6u$(medium) leftcon" id="container-frontTireAngle"
						style="height: 8em"></div>
				</div>
			</div>


		</div>
	</section>

	<!-- One -->
	<section id="one" class="wrapper style2 special">

		<div class="row">

			<div class="3u 12u(medium) ">
				<div style="display: inline-block; vertical-align: middle;">
					<div style="display: inline-block; width :200px;">
						<h3 style="display: inline-block; margin-right: 20px">부저</h3>
						<label class="switch"> <input type="checkbox" id="buzzerToggle" onchange="buzzer('${sensingcar.sip }')"/>
							<div class="slider round"></div>
						</label>
					</div>

					<div style="display: inline-block;width :200px">
					<h3 style="display: inline-block; margin-right: 20px">레이저</h3>
					<label class="switch"> <input type="checkbox" id="laserToggle" onchange="laserEmitter('${sensingcar.sip }')"/>
						<div class="slider round"></div>
					</label>
					</div>
					
					
					

				</div>
			</div>

			<div class="6u 12u$(medium)"></div>

			<div class="3u$ 12u$(medium)"
				style="padding-left: 0; padding-right: 0;"></div>
		</div>

	</section>


	<section id="one" class="wrapper style2 special" style="color: black">
		
	</section>



	<!-- Two -->
	<section id="two" class="wrapper style1 special">
		
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






