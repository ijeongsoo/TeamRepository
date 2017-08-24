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

<link href="<%=application.getContextPath()%>/resources/css/custom.css"
	rel="stylesheet" type="text/css" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.min.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/mode.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ch6.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ch7.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ch8.js"
	type="text/javascript"></script>

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

			client.subscribe("/${drone.dmacaddr}/communicationCheck");
			client.subscribe("/${drone.dmacaddr}/camera");
	}
	function onConnectionLost(responseObject) {
		if(responseObject.errorCode !=0){
			console.log(responseObject.errorCode);
		}
	}
	function onMessageArrived(message) {

		if(message.payloadString.length<50){
			message2json=eval("("+message.payloadString+")");
			communication=message2json.comm;
		}else{
			$("#cameraView").attr("src", "data:image/jpg;base64," + message.payloadString);
		}
		
		
	}
	
	function listCheckAndClear(){
		if('${drone.dmacaddr}'==communication){
			$('.available').html("<img width='25px' src='resources/image/green.png'/>");
		}else{
			$('.available').html("<img width='25px' src='resources/image/red.png'/>");
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

<script type="text/javascript">

function communityRequest() {
	location.href = "community";
}

function logoutRequest() {
	location.href = "logout";
}

function listRequest() {
	location.href = "<%=application.getContextPath()%>/";
	}

	$(function() {
		
		mode('${drone.dmacaddr}');
		ch6('${drone.dmacaddr}');
		ch7('${drone.dmacaddr}');
		ch8('${drone.dmacaddr}');
		
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
		ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
		//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
		//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

		ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
		ctx.fill(); //채우기
		ctx.stroke(); //테두리

		var json = {
			"throttle" : String(3000 - ((5 * y) + 1000)),
			"yaw" : String((5 * x) + 1000),
			"dmacaddr" : '${drone.dmacaddr}'
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2DroneWebControl/throttleAndYaw",
			data : json,
			method : "post",
			success : function(data) {
				$("#ch3").html(String(3000 - ((5 * y) + 1000)));
				$("#ch4").html(String((5 * x) + 1000));
			}
		});
		
				
			

		$("#myCanvas").mousedown(
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
					ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리

					var json = {
						"throttle" : String(3000 - ((5 * y) + 1000)),
						"yaw" : String((5 * x) + 1000),
						"dmacaddr" : '${drone.dmacaddr}'
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/throttleAndYaw",
						data : json,
						method : "post",
						success : function(data) {
							$("#ch3").html(
									String(3000 - ((5 * y) + 1000)));
							$("#ch4").html(String((5 * x) + 1000));
						}
					});
				});

		$("#myCanvas").mousemove(
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
						ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"throttle" : String(3000 - ((5 * y) + 1000)),
							"yaw" : String((5 * x) + 1000),
							"dmacaddr" : '${drone.dmacaddr}'

						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/throttleAndYaw",
							data : json,
							method : "post",
							success : function(data) {
								$("#ch3").html(
										String(3000 - ((5 * y) + 1000)));
								$("#ch4").html(String((5 * x) + 1000));
							}
						});

					}
				});
		$("#myCanvas").mouseup(
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
					ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리
					var json = {
						"throttle" : String(3000 - ((5 * y) + 1000)),
						"yaw" : String((5 * x) + 1000),
						"dmacaddr" : '${drone.dmacaddr}'

					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/throttleAndYaw",
						data : json,
						method : "post",
						success : function(data) {
							$("#ch3").html(
									String(3000 - ((5 * y) + 1000)));
							$("#ch4").html(String((5 * x) + 1000));
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
						ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"throttle" : String(3000 - ((5 * y) + 1000)),
							"yaw" : String((5 * x) + 1000),
							"dmacaddr":'${drone.dmacaddr}'

						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/throttleAndYaw",
							data : json,
							method : "post",
							success : function(data) {
								$("#ch3").html(
										String(3000 - ((5 * y) + 1000)));
								$("#ch4").html(String((5 * x) + 1000));
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
		ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
		//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
		//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

		ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
		ctx.fill(); //채우기
		ctx.stroke(); //테두리
		
		var json = {
				"pitch" : String((5 * y) + 1000),
				"roll" : String((5 * x) + 1000),
				"dmacaddr" : '${drone.dmacaddr}'
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2DroneWebControl/pitchAndRoll",
				data : json,
				method : "post",
				success : function(data) {
					$("#ch2").html(String((5 * y) + 1000));
					$("#ch1").html(String((5 * x) + 1000));
				}
			});

		$("#myCanvas2").mousedown(
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
					ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리

					var json = {
						"pitch" : String((5 * y) + 1000),
						"roll" : String((5 * x) + 1000),
						"dmacaddr" : '${drone.dmacaddr}'
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/pitchAndRoll",
						data : json,
						method : "post",
						success : function(data) {
							$("#ch2").html(
									String((5 * y) + 1000));
							$("#ch1").html(String((5 * x) + 1000));


						}
					});
				});

		$("#myCanvas2").mousemove(
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
						ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
							"pitch" : String((5 * y) + 1000),
							"roll" : String((5 * x) + 1000),
							"dmacaddr" : '${drone.dmacaddr}'
						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/pitchAndRoll",
							data : json,
							method : "post",
							success : function(data) {
								$("#ch2").html(
										String((5 * y) + 1000));
								$("#ch1").html(String((5 * x) + 1000));


							}
						});

					}
				});
		$("#myCanvas2").mouseup(
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
					ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
					//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
					//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

					ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
					ctx.fill(); //채우기
					ctx.stroke(); //테두리
					var json = {
							"pitch" : String((5 * y) + 1000),
							"roll" : String((5 * x) + 1000),
							"dmacaddr" : '${drone.dmacaddr}'
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/pitchAndRoll",
						data : json,
						method : "post",
						success : function(data) {
							$("#ch2").html(
									String((5 * y) + 1000));
							$("#ch1").html(String((5 * x) + 1000));
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
						ctx.arc(x, y, 15, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						var json = {
								"pitch" : String((5 * y) + 1000),
								"roll" : String((5 * x) + 1000),
								"dmacaddr" : '${drone.dmacaddr}'
						};

						$.ajax({
							url : "http://" + location.host
									+ "/Team2DroneWebControl/pitchAndRoll",
							data : json,
							method : "post",
							success : function(data) {
								$("#ch2").html(
										String((5 * y) + 1000));
								$("#ch1").html(String((5 * x) + 1000));
							}
						});
					}
				});
	});
</script>




<script type="text/javascript">
	$(function() {
		var isDragging = false;
		var x = 50;
		var y = 50;
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
		
		var json = {
				"upDown" : String(120 - parseInt((3 / 2.5) * y) + 30),
				"leftRight" : String(parseInt((3 / 2.5) * x) + 30),
				"dmacaddr" : '${drone.dmacaddr}'
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2DroneWebControl/camera",
				data : json,
				method : "post",
				success : function(data) {
					$("#pwm1").html(
							String(120 - parseInt((3 / 2.5) * y) + 30));
					$("#pwm2").html( String(parseInt((3 / 2.5) * x) + 30));
				}
			});

		$("#myCanvas4").mousedown(
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
						"upDown" : String(120 - parseInt((3 / 2.5) * y) + 30),
						"leftRight" : String(parseInt((3 / 2.5) * x) + 30),
						"dmacaddr" : '${drone.dmacaddr}'
					};

					$.ajax({
						url : "http://" + location.host
								+ "/Team2DroneWebControl/camera",
						data : json,
						method : "post",
						success : function(data) {
							$("#pwm1").html(
									String(120 - parseInt((3 / 2.5) * y) + 30));
							$("#pwm2").html( String(parseInt((3 / 2.5) * x) + 30));
						}
					});
				});

		$("#myCanvas4")
				.mousemove(
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
									"upDown" : String(120 - parseInt((3 / 2.5)
											* y) + 30),
									"leftRight" : String(parseInt((3 / 2.5) * x) + 30),
									"dmacaddr" : '${drone.dmacaddr}'
								};

								$.ajax({
									url : "http://" + location.host
											+ "/Team2DroneWebControl/camera",
									data : json,
									method : "post",
									success : function(data) {
										$("#pwm1").html(
												String(120 - parseInt((3 / 2.5) * y) + 30));
										$("#pwm2").html( String(parseInt((3 / 2.5) * x) + 30));
									}
								});

							}
						});
		$("#myCanvas4").mouseup(function(event) {
			isDragging = false;
		});

		$("#myCanvas4")
				.mouseleave(
						function(event) {
							if (isDragging) {
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
									"upDown" : String(120 - parseInt((3 / 2.5)
											* y) + 30),
									"leftRight" : String(parseInt((3 / 2.5) * x) + 30),
									"dmacaddr" : '${drone.dmacaddr}'
								};

								$.ajax({
									url : "http://" + location.host
											+ "/Team2DroneWebControl/camera",
									data : json,
									method : "post",
									success : function(data) {
										$("#pwm1").html(
												String(120 - parseInt((3 / 2.5) * y) + 30));
										$("#pwm2").html( String(parseInt((3 / 2.5) * x) + 30));
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
					
					<li class="page-scroll"><a href="comunity"
						onclick="communityRequest()">Community</a></li>

				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	



	<header class="cd-slider-wrapper" style="height: auto">
		<ul class="cd-slider">
			<li class="visible">
				<div style="text-align: center;">
					<%--  <button onclick="arm()" style="color: white" class="btn" id="arm">ARM</button>
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

					<h1> id="throttleValue">Throttle : ${throttle }</h1>
					<h1>Yaw : ${yaw }</h1>
					<h1>Pitch : ${pitch }</h1>
					<h1>Roll : ${roll }</h1>
					<h1>Mode : ${mode }</h1>  --%>

					<br />
					<div style="margin-bottom: 30px">
						<div style="height: 100px"></div>
						<div
							style="display: inline-block; vertical-align: middle; width: 122px">
							<h3>장비정보</h3>
							<img class='photo3' style="height: 100px; width: 100px"
								src="<%=application.getContextPath()%>/file?msavedfilename=${drone.dsavedfilename}&mfiletype=${drone.dfilecontent}">
							${drone.dname }<br>${drone.dmacaddr }<br>
							<br>
							<h3 style='margin-bottom: 20px'; >연결상태</h3>
							<p class='available'>
								<img width='15px' src='resources/image/loading.gif' />
							</p>

						</div>
						<div style="display: inline-block; vertical-align: middle;">
							<h3>camera</h3>
							<img
								style="display: inline-block; padding: 0; margin-right: 50px; margin-left: 50px; height: 300px; width: 420px"
								id="cameraView" />
						</div>
						<div style="vertical-align: middle; display: inline-block;">
							<h3>camera</h3>
							<div
								style="background-color: grey; border-radius: 150px; border: 1px solid grey; display: inline-block; padding: 22px">
								<div>
									<canvas id="myCanvas4" height="100" width="100"
										style="background: white;"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div style="display: inline-table;">
							<h3>throttle and yaw</h3>
							<div
								style="background-color: grey; border-radius: 150px; border: 1px solid grey; display: inline-table; padding: 45px">
								<div style="display: inline-table;">
									<canvas id="myCanvas" height="200" width="200"
										style="background: white;"></canvas>
								</div>
							</div>
						</div>

						<div style="display: inline-table; margin-left: -50px">
							<h3 style="margin-left:; margin-top:">mode</h3>
							<div style="display: inline-table;">

								<div style="margin-left:">
									<input oninput="mode('${drone.dmacaddr }')"
										class="vertical" id="modeSlide" data-fix-max-value="2000"
										data-orientation="horizontal"
										style="display: inline-block; width: 250px; margin-top: 150px"
										value="1000" type="range" min="1000" max="2000"
										step="1">
								</div>
							</div>
						</div>
						<div style="display: inline-table; vertical-align: bottom;">
							<h3 style="display: inline-table; margin-top: 12px">c1:</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch1">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c2:</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch2">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c3:</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch3">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c4:</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch4">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c5:</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch5">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c6:</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch6">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c7 :</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch7">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">c8 :</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="ch8">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">p1 :</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="pwm1">
							</h3>
							<br>
							<h3 style="display: inline-table; margin-top: 12px">p2 :</h3>
							<h3 style="display: inline-table; margin-top: 12px" id="pwm2">
							</h3>
							<br>
						</div>
						<div style="display: inline-table; margin-right: -50px">
							<h3 style="margin-left:; margin-top:">ch6</h3>
							<div style="display: inline-table;">
								<div style="margin-left:">
									<input oninput="ch6('${drone.dmacaddr }')"
										class="vertical" id="ch6Slide" data-fix-max-value="2000"
										data-orientation="horizontal"
										style="display: inline-block; width: 250px; margin-top: 150px"
										value="1500" type="range" min="1000" max="2000"
										step="1">
								</div>
							</div>
						</div>

						<div style="display: inline-table;">
							<h3>pitch and roll</h3>
							<div
								style="background-color: grey; border-radius: 150px; border: 1px solid grey; display: inline-table; padding: 45px">
								<div style="display: inline-table;">
									<canvas id="myCanvas2" height="200" width="200"
										style="background: white;"></canvas>
								</div>
							</div>
						</div>

					</div>

					<div>
						<div style="display: inline-table; margin-top: 30px">
							<h3 style="margin-left:; margin-top:">ch7</h3>
							<div style="display: inline-table;">
								<input oninput="ch7('${drone.dmacaddr }')" class="blueSlide"
									id="ch7Slide" data-fix-max-value="2000"
									data-orientation="horizontal"
									style="display: inline-table; width: 250px;" value="1500"
									type="range" min="1000" max="2000" step="1">
							</div>
						</div>
						
						<div style="display: inline-table; vertical-align: bottom ; width: 500px;"><p>${drone.member_mid }</p></div>
						
						<div style="display: inline-table; margin-top: 30px">
							<h3 style="margin-left:; margin-top:">ch8</h3>
							<div style="display: inline-table;">
								<input oninput="ch8('${drone.dmacaddr }')" class="greenSlide"
									id="ch8Slide" data-fix-max-value="2000"
									data-orientation="horizontal"
									style="display: inline-table; width: 250px;  margin-bottom: 50px"
									value="1500" type="range" min="1000" max="2000" step="1">
							</div>
						</div>
					</div>



				</div>

			</li>




			<!-- .cd-slider-navigation -->

		</ul>
		<!-- .cd-svg-cover -->
	</header>
	<!-- .cd-slider-wrapper -->
	
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