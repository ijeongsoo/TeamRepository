<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


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

<link rel="stylesheet"
	href="<%=application.getContextPath()%>/resources/css/list_view_style.css">

<link
	href="<%=application.getContextPath()%>/resources/css/circle-img.css"
	rel="stylesheet" type="text/css" />
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
	src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.min.js"></script>



<script>
	var client = new Paho.MQTT.Client("106.253.56.122", Number(61614),"clientID"+parseInt(Math.random() * 100, 10));
	var message2json;
	var communication;
	setInterval("listCheckAndClear()", 3000);
	client.onConnectionLost = onConnectionLost;
	client.onMessageArrived=onMessageArrived;
	client.connect({onSuccess:onConnect});
	
	function onConnect() {
		//client.subscribe("/B8:27:EB:54:10:0B/communicationCheck");
			client.subscribe("/${dmacaddr}/communicationCheck");
	}
	function onConnectionLost(responseObject) {
		if(responseObject.errorCode !=0){
			console.log(responseObject.errorCode);
		}
	}
	function onMessageArrived(message) {
		message2json= eval("("+message.payloadString+")");
		communication=message2json.comm;
	}
	
	function listCheckAndClear(){
		if('${dmacaddr}'==communication){
			
		}else{
			
		}
		communication="";
	}
</script>
<script
	src="<%=application.getContextPath()%>/resources/js/modernizr.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/modernizr_3.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/list_view_modernizr.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/chartTheme.js"></script>



<!-- Modernizr -->

<script>
	function communityRequest() {
		location.href = "community";
	}

	function logoutRequest() {
		location.href = "logout";
	}
	function listRequest() {
		location.href = "<%=application.getContextPath()%>/";
	}

	
	
</script>
<script type="text/javascript">
	$(function() {
		var isDragging = false;
		var x = 100;
		var y = 200;
		var cnvs = document.getElementById('myCanvas');
		var ctx = document.getElementById('myCanvas').getContext("2d");

		// 픽셀 정리
		ctx.clearRect(0, 0, cnvs.width, cnvs.height);
		// 컨텍스트 리셋
		ctx.beginPath();

		//원 그리기
		ctx.beginPath();
		ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
		//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
		//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

		ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
		ctx.fill(); //채우기
		ctx.stroke(); //테두리

		$("#myCanvas").bind('touchstart',
				function(event) {
					isDragging = true;
					var x = event.offsetX;
					var y = event.offsetY;
					var cnvs = document.getElementById('myCanvas');
					var ctx = document.getElementById('myCanvas').getContext(
							"2d");

					// 픽셀 정리
					ctx.clearRect(0, 0, cnvs.width, cnvs.height);
					// 컨텍스트 리셋
					ctx.beginPath();

					//원 그리기
					ctx.beginPath();
					ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리

					var json = {
						"throttle" : String(3000 - ((5 * y) + 1000)),
						"yaw" : String((5 * x) + 1000)
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/throttleAndYaw",
						data : json,
						method : "post",
						success : function(data) {
							$("#throttleValue").html(
									String(3000 - ((5 * y) + 1000)));
						}
					});
				});

		$("#myCanvas").bind('touchmove',
				function(event) {
					if (isDragging) {
						var x = event.offsetX;
						var y = event.offsetY;
						var cnvs = document.getElementById('myCanvas');
						var ctx = document.getElementById('myCanvas')
								.getContext("2d");

						// 픽셀 정리
						ctx.clearRect(0, 0, cnvs.width, cnvs.height);
						// 컨텍스트 리셋
						ctx.beginPath();

						//원 그리기
						ctx.beginPath();
						ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"throttle" : String(3000 - ((5 * y) + 1000)),
							"yaw" : String((5 * x) + 1000)

						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/throttleAndYaw",
							data : json,
							method : "post",
							success : function(data) {
								$("#throttleValue").html(
										String(3000 - ((5 * y) + 1000)));
							}
						});

					}
				});
		$("#myCanvas").bind('touchend',
				function(event) {
					isDragging = false;
					var x = 100;
					var y = event.offsetY;
					var cnvs = document.getElementById('myCanvas');
					var ctx = document.getElementById('myCanvas').getContext(
							"2d");

					// 픽셀 정리
					ctx.clearRect(0, 0, cnvs.width, cnvs.height);
					// 컨텍스트 리셋
					ctx.beginPath();

					//원 그리기
					ctx.beginPath();
					ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리
					var json = {
						"throttle" : String(3000 - ((5 * y) + 1000)),
						"yaw" : 100

					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/throttleAndYaw",
						data : json,
						method : "post",
						success : function(data) {
							$("#throttleValue").html(
									String(3000 - ((5 * y) + 1000)));
						}
					});
				});

		$("#myCanvas").mouseleave(
				function(event) {
					if (isDragging) {
						isDragging = false;
						var x = 100;
						var y = event.offsetY;
						var cnvs = document.getElementById('myCanvas');
						var ctx = document.getElementById('myCanvas')
								.getContext("2d");

						// 픽셀 정리
						ctx.clearRect(0, 0, cnvs.width, cnvs.height);
						// 컨텍스트 리셋
						ctx.beginPath();

						//원 그리기
						ctx.beginPath();
						ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"throttle" : String(3000 - ((5 * y) + 1000)),
							"yaw" : 100

						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/throttleAndYaw",
							data : json,
							method : "post",
							success : function(data) {
								$("#throttleValue").html(
										String(3000 - ((5 * y) + 1000)));
							}
						});
					}
				});
	});
</script>


<script type="text/javascript">
	$(function() {
		var isDragging = false;
		var x = 100;
		var y = 100;
		var cnvs = document.getElementById('myCanvas2');
		var ctx = document.getElementById('myCanvas2').getContext("2d");

		// 픽셀 정리
		ctx.clearRect(0, 0, cnvs.width, cnvs.height);
		// 컨텍스트 리셋
		ctx.beginPath();

		//원 그리기
		ctx.beginPath();
		ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
		//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
		//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

		ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
		ctx.fill(); //채우기
		ctx.stroke(); //테두리

		$("#myCanvas2").ontouchstart(
				function(event) {
					isDragging = true;
					var x = event.offsetX;
					var y = event.offsetY;
					var cnvs = document.getElementById('myCanvas2');
					var ctx = document.getElementById('myCanvas2').getContext(
							"2d");

					// 픽셀 정리
					ctx.clearRect(0, 0, cnvs.width, cnvs.height);
					// 컨텍스트 리셋
					ctx.beginPath();

					//원 그리기
					ctx.beginPath();
					ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리

					var json = {
						"pitch" : String((5 * y) + 1000),
						"roll" : String((5 * x) + 1000)
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/pitchAndRoll",
						data : json,
						method : "post",
						success : function(data) {

						}
					});
				});

		$("#myCanvas2").touchmove(
				function(event) {
					if (isDragging) {
						var x = event.offsetX;
						var y = event.offsetY;
						var cnvs = document.getElementById('myCanvas2');
						var ctx = document.getElementById('myCanvas2')
								.getContext("2d");

						// 픽셀 정리
						ctx.clearRect(0, 0, cnvs.width, cnvs.height);
						// 컨텍스트 리셋
						ctx.beginPath();

						//원 그리기
						ctx.beginPath();
						ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"pitch" : String((5 * y) + 1000),
							"roll" : String((5 * x) + 1000)
						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/pitchAndRoll",
							data : json,
							method : "post",
							success : function(data) {

							}
						});

					}
				});
		$("#myCanvas2").touchend(
				function(event) {
					isDragging = false;
					var x = 100;
					var y = 100;
					var cnvs = document.getElementById('myCanvas2');
					var ctx = document.getElementById('myCanvas2').getContext(
							"2d");

					// 픽셀 정리
					ctx.clearRect(0, 0, cnvs.width, cnvs.height);
					// 컨텍스트 리셋
					ctx.beginPath();

					//원 그리기
					ctx.beginPath();
					ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리
					var json = {
						"pitch" : "1500",
						"roll" : "1500"
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/pitchAndRoll",
						data : json,
						method : "post",
						success : function(data) {

						}
					});
				});

		$("#myCanvas2").mouseleave(
				function(event) {
					if (isDragging) {
						isDragging = false;
						var x = 100;
						var y = 100;
						var cnvs = document.getElementById('myCanvas2');
						var ctx = document.getElementById('myCanvas2')
								.getContext("2d");

						// 픽셀 정리
						ctx.clearRect(0, 0, cnvs.width, cnvs.height);
						// 컨텍스트 리셋
						ctx.beginPath();

						//원 그리기
						ctx.beginPath();
						ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"pitch" : "1500",
							"roll" : "1500"
						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/pitchAndRoll",
							data : json,
							method : "post",
							success : function(data) {

							}
						});
					}
				});
	});
</script>

<script type="text/javascript">
	$(function() {
		var isDragging = false;
		var x = 100;
		var y = 200;
		var cnvs = document.getElementById('myCanvas3');
		var ctx = document.getElementById('myCanvas3').getContext("2d");

		// 픽셀 정리
		ctx.clearRect(0, 0, cnvs.width, cnvs.height);
		// 컨텍스트 리셋
		ctx.beginPath();

		//원 그리기
		ctx.beginPath();
		ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
		//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
		//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

		ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
		ctx.fill(); //채우기
		ctx.stroke(); //테두리

		$("#myCanvas3").ontouchstart(function(event) {
			isDragging = true;
			var x = event.offsetX;
			var y = event.offsetY;
			var cnvs = document.getElementById('myCanvas3');
			var ctx = document.getElementById('myCanvas3').getContext("2d");

			// 픽셀 정리
			ctx.clearRect(0, 0, cnvs.width, cnvs.height);
			// 컨텍스트 리셋
			ctx.beginPath();

			//원 그리기
			ctx.beginPath();
			ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
			//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
			//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

			ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
			ctx.fill(); //채우기
			ctx.stroke(); //테두리

			var json = {
				"mode" : String(3000 - ((5 * y) + 1000)),
			};

			$.ajax({
				url : "http://" + location.host + "/Team2DroneWebControl/mode",
				data : json,
				method : "post",
				success : function(data) {

				}
			});
		});

		$("#myCanvas3").touchmove(
				function(event) {
					if (isDragging) {
						var x = event.offsetX;
						var y = event.offsetY;
						var cnvs = document.getElementById('myCanvas3');
						var ctx = document.getElementById('myCanvas3')
								.getContext("2d");

						// 픽셀 정리
						ctx.clearRect(0, 0, cnvs.width, cnvs.height);
						// 컨텍스트 리셋
						ctx.beginPath();

						//원 그리기
						ctx.beginPath();
						ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"mode" : String(3000 - ((5 * y) + 1000)),
						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/mode",
							data : json,
							method : "post",
							success : function(data) {

							}
						});

					}
				});
		$("#myCanvas3").touchend(function(event) {
			isDragging = false;
		});
		
		$("#myCanvas3").mouseleave(function(event) {
			if(isDragging){
				isDragging = false;
				var x = event.offsetX;
				var y = event.offsetY;
				var cnvs = document.getElementById('myCanvas3');
				var ctx = document.getElementById('myCanvas3')
						.getContext("2d");

				// 픽셀 정리
				ctx.clearRect(0, 0, cnvs.width, cnvs.height);
				// 컨텍스트 리셋
				ctx.beginPath();

				//원 그리기
				ctx.beginPath();
				ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
				//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
				//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

				ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
				ctx.fill(); //채우기
				ctx.stroke(); //테두리
				var json = {
					"mode" : String(3000 - ((5 * y) + 1000)),
				};

				$.ajax({
					url : "http://" + location.host
							+ "/Team2DroneWebControl/mode",
					data : json,
					method : "post",
					success : function(data) {

					}
				});
			}
			
		});
	});
</script>


<script type="text/javascript">
	$(function() {
		var isDragging = false;
		var x = 100;
		var y = 100;
		var cnvs = document.getElementById('myCanvas4');
		var ctx = document.getElementById('myCanvas4').getContext("2d");

		// 픽셀 정리
		ctx.clearRect(0, 0, cnvs.width, cnvs.height);
		// 컨텍스트 리셋
		ctx.beginPath();

		//원 그리기
		ctx.beginPath();
		ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
		//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
		//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

		ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
		ctx.fill(); //채우기
		ctx.stroke(); //테두리

		$("#myCanvas4").ontouchstart(
				function(event) {
					isDragging = true;
					var x = event.offsetX;
					var y = event.offsetY;
					var cnvs = document.getElementById('myCanvas4');
					var ctx = document.getElementById('myCanvas4').getContext(
							"2d");

					// 픽셀 정리
					ctx.clearRect(0, 0, cnvs.width, cnvs.height);
					// 컨텍스트 리셋
					ctx.beginPath();

					//원 그리기
					ctx.beginPath();
					ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리

					var json = {
						"upDown" : String(120 - parseInt((3 / 5) * y) + 30),
						"leftRight" : String(parseInt((3 / 5) * x) + 30),
						"dmacaddr":'${dmacaddr}'
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/camera",
						data : json,
						method : "post",
						success : function(data) {

						}
					});
				});

		$("#myCanvas4")
				.touchmove(
						function(event) {
							if (isDragging) {
								var x = event.offsetX;
								var y = event.offsetY;
								var cnvs = document.getElementById('myCanvas4');
								var ctx = document.getElementById('myCanvas4')
										.getContext("2d");

								// 픽셀 정리
								ctx.clearRect(0, 0, cnvs.width, cnvs.height);
								// 컨텍스트 리셋
								ctx.beginPath();

								//원 그리기
								ctx.beginPath();
								ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360,
										false);
								//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
								//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

								ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
								ctx.fill(); //채우기
								ctx.stroke(); //테두리

								var json = {
									"upDown" : String(120 - parseInt((3 / 5)
											* y) + 30),
									"leftRight" : String(parseInt((3 / 5) * x) + 30),
									"dmacaddr":'${dmacaddr}'
								};

								$.ajax({
									url : "http://" + location.host
											+ "/Team2DroneWebControl/camera",
									data : json,
									method : "post",
									success : function(data) {

									}
								});

							}
						});
		$("#myCanvas4").touchend(function(event) {
			isDragging = false;
		});
		
		$("#myCanvas4").mouseleave(function(event) {
			if(isDragging){
				isDragging = false;
				var x = event.offsetX;
				var y = event.offsetY;
				var cnvs = document.getElementById('myCanvas4');
				var ctx = document.getElementById('myCanvas4')
						.getContext("2d");

				// 픽셀 정리
				ctx.clearRect(0, 0, cnvs.width, cnvs.height);
				// 컨텍스트 리셋
				ctx.beginPath();

				//원 그리기
				ctx.beginPath();
				ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360,
						false);
				//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
				//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

				ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
				ctx.fill(); //채우기
				ctx.stroke(); //테두리

				var json = {
					"upDown" : String(120 - parseInt((3 / 5)
							* y) + 30),
					"leftRight" : String(parseInt((3 / 5) * x) + 30),
					"dmacaddr":'${dmacaddr}'
				};

				$.ajax({
					url : "http://" + location.host
							+ "/Team2DroneWebControl/camera",
					data : json,
					method : "post",
					success : function(data) {

					}
				});
			}
			
		});
	});

	function arm() {
		var json = {
			"throttle" : 1000,
			"yaw" : 2000
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});
	}

	function throttle100() {
		var x;

		var json = {
			"throttle" : x
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});
	}

	function max() {
		var x;

		var json = {
			"throttle" : "2000",
			"yaw" : "2000"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"pitch" : "2000",
			"roll" : "2000"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/pitchAndRoll",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"mode" : "2000",
		};

		$.ajax({
			url : "http://" + location.host + "/Team2DroneWebControl/mode",
			data : json,
			method : "post",
			success : function(data) {

			}
		});
	}

	function min() {
		var x;

		var json = {
			"throttle" : "1000",
			"yaw" : "1000"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"pitch" : "1000",
			"roll" : "1000"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/pitchAndRoll",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"mode" : "1000",
		};

		$.ajax({
			url : "http://" + location.host + "/Team2DroneWebControl/mode",
			data : json,
			method : "post",
			success : function(data) {

			}
		});
	}

	function center() {
		var x;

		var json = {
			"throttle" : "1000",
			"yaw" : "1500"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"pitch" : "1500",
			"roll" : "1500"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/pitchAndRoll",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"mode" : "1500",
		};

		$.ajax({
			url : "http://" + location.host + "/Team2DroneWebControl/mode",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

	}

	function throttleMax() {
		var x;

		var json = {
			"throttle" : "2000",
			"yaw" : "1500"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"pitch" : "1500",
			"roll" : "1500"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/pitchAndRoll",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"mode" : "1500",
		};

		$.ajax({
			url : "http://" + location.host + "/Team2DroneWebControl/mode",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

	}

	function throttleMin() {
		var x;

		var json = {
			"throttle" : "1000",
			"yaw" : "1500"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"pitch" : "1500",
			"roll" : "1500"
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/pitchAndRoll",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

		var json = {
			"mode" : "1500",
		};

		$.ajax({
			url : "http://" + location.host + "/Team2DroneWebControl/mode",
			data : json,
			method : "post",
			success : function(data) {

			}
		});

	}
</script>
<script type="text/javascript">
	$(function() {
		var ws = new WebSocket("ws://" + location.host
				+ "/Team2DroneWebControl/websocket/camera");
		// 함수를 바로 대입해도 괜찮음.메시지가 도착했을 때 시행
		ws.onmessage = function(event) {
			$("#cameraView").attr("src", "data:image/jpg;base64," + event.data);
		};
	});
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
					<li class="page-scroll"><a href="comunity"
						onclick="logoutRequest()">로그아웃</a></li>
					<li class="page-scroll"><a href="comunity"
						onclick="listRequest()">목록</a></li>
					<li class="page-scroll"><a
						style="background-color: rgba(0, 0, 0, 0)" class="cd-signup"
						href="#0">정보수정</a></li>
					<li class="page-scroll"><a href="comunity"
						onclick="communityRequest()">Community</a></li>

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
				<li>정보수정</li>
			</ul>


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


			<a href="#0" class="cd-close-form">Close</a>
		</div>
		<!-- cd-user-modal-container -->
	</div>
	<!-- cd-user-modal -->




	<header class="cd-slider-wrapper" style="height: 100vh">
		<ul class="cd-slider">
			<li class="visible">
				<div style="text-align: center;">
							<button onclick="arm()" style="color: white" class="btn" id="arm">ARM</button>
							<button onclick="throttle100()" style="color: white" class="btn"
								id="throttle100">Throttle</button>

							<button onclick="max()" style="color: white" class="btn" id="max">max</button>
							<button onclick="min()" style="color: white" class="btn" id="min">min</button>
							<button onclick="center()" style="color: white" class="btn"
								id="center">center</button>

							<button onclick="throttleMax()" style="color: white" class="btn"
								id="throttleMax">throttleMax</button>
							<button onclick="throttleMin()" style="color: white" class="btn"
								id="throttleMin">throttleMIn</button>

							<h1 id="throttleValue">Throttle : ${throttle }</h1>
							<h1 >Yaw : ${yaw }</h1>
							<h1 >Pitch : ${pitch }</h1>
							<h1 >Roll : ${roll }</h1>
							<h1 >Mode : ${mode }</h1>

							<br />

							<div style="border: 1px solid gold; float: left; width: 50%;">
								<h3>throttle and yaw</h3>
								<canvas id="myCanvas" height="200" width="200"
									style="background: gray;"></canvas>
							</div>

							<div style="border: 1px solid gold; float: left; width: 50%;">
								<h3>pitch and roll</h3>
								<canvas id="myCanvas2" height="200" width="200"
									style="background: gray;"></canvas>
							</div>

							<div style="border: 1px solid gold; float: left; width: 50%;">
								<h3>fly mode</h3>
								<canvas id="myCanvas3" height="200" width="200"
									style="background: gray;"></canvas>
							</div>

							<div style="border: 1px solid gold; float: left; width: 50%;">
								<h3>camera</h3>
								<canvas id="myCanvas4" height="200" width="200"
									style="background: gray;"></canvas>
							</div>
							<img style="height: 240; width: 320" id="cameraView" />
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




	

	

	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div
		class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
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
	<script
		src="<%=application.getContextPath()%>/resources/js/list_view.js"></script>

	<!-- Resource jQuery -->

</body>

</html>