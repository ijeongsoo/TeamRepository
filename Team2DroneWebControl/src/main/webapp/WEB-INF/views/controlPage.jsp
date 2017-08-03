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


<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>



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

							}
						});

					}
				});
		$("#myCanvas").mouseup(function(event) {
			isDragging = false;
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
		$("#myCanvas2").mouseup(function(event) {
			isDragging = false;
		});
	});
</script>

<script type="text/javascript">
	$(function() {
		var isDragging = false;
		var x = 100;
		var y = 100;
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

		$("#myCanvas3").mousedown(
				function(event) {
					isDragging = true;
					var x = event.offsetX;
					var y = event.offsetY;
					var cnvs = document.getElementById('myCanvas3');
					var ctx = document.getElementById('myCanvas3').getContext(
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
				});

		$("#myCanvas3").mousemove(
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
		$("#myCanvas3").mouseup(function(event) {
			isDragging = false;
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
						"upDown" : String(120-parseInt((3/5) * y) + 30),
						"leftRight" : String(parseInt((3/5) * x) + 30)
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

		$("#myCanvas4").mousemove(
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
						ctx.arc(x, y, 10, 0, (Math.PI / 180) * 360, false);
						//ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
						//그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

						ctx.fillStyle = "rgb(255, 0, 0)"; //채울 색상
						ctx.fill(); //채우기
						ctx.stroke(); //테두리
						
						var json = {
							"upDown" : String(120-parseInt((3/5) * y) + 30),
							"leftRight" : String(parseInt((3/5) * x) + 30)
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
		$("#myCanvas4").mouseup(function(event) {
			isDragging = false;
		});
	});
	
	function arm(){
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
</script>

</head>

<body>

	<div style="text-align: center">
		<button onclick="arm()" class="btn" id="arm">ARM</button>
		<h3>throttle and yaw</h3>
		<canvas id="myCanvas" height="200" width="200"
			style="background: gray;"></canvas>
		<h3>pitch and roll</h3>
		<canvas id="myCanvas2" height="200" width="200"
			style="background: gray;"></canvas>

		<h3>fly mode</h3>
		<canvas id="myCanvas3" height="200" width="200"
			style="background: gray;"></canvas>

		<h3>camera</h3>
		<canvas id="myCanvas4" height="200" width="200"
			style="background: gray;"></canvas>
	</div>


</body>
</html>






