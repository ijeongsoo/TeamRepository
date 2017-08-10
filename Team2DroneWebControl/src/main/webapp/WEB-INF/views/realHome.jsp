<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Astral by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="resources/css/main.css" />
<noscript>
	<link rel="stylesheet" href="resources/css/noscript.css" />
</noscript>
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->


<!-- controlPanel ( throttle / pitch / roll / yaw ) -->
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
							$("#throttleValue").html(
									String(3000 - ((5 * y) + 1000)));
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
								$("#throttleValue").html(
										String(3000 - ((5 * y) + 1000)));
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

		$("#myCanvas3").mousedown(function(event) {
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
						"upDown" : String(120 - parseInt((3 / 5) * y) + 30),
						"leftRight" : String(parseInt((3 / 5) * x) + 30)
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
									"upDown" : String(120 - parseInt((3 / 5)
											* y) + 30),
									"leftRight" : String(parseInt((3 / 5) * x) + 30)
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
					"leftRight" : String(parseInt((3 / 5) * x) + 30)
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
<body>
	<!-- Wrapper-->
	<div id="wrapper" style="position: relative; bottom: 100px">

		<!-- Nav -->
		<nav id="nav">
			<a href="#me" class="icon fa-home active"><span>Home</span></a> <a
				href="#ControlPanel" class="icon fa-folder"><span>Control</span></a>
			<a href="#sensor" class="icon fa-folder"><span>Sensor</span></a> <a
				href="#join" class="icon fa-envelope"><span>Join</span></a> <a
				href="#" class="icon fa-twitter"><span>Twitter</span></a>
		</nav>

		<!-- Main -->
		<div id="main">

			<!-- Me -->
			<article id="me" class="panel">
				<header>

					<h1>Drone's world</h1>
					<p style="font-size: 20px">
						It is homepage that display drone data and control it. <br />
						Let's start!
					</p>


					<!-- 회원가입 모달 -->
					<div style="position: relative; top: 320px; right: 10px">
						<nav id="nav-1">
							<ul>
								<li><button type="button" class="button special"
										data-toggle="modal" data-target="#myModal-1">Join</button></li>
							</ul>
						</nav>
					</div>
					<div id="myModal-1" class="modal fade" role="dialog">
						<div style="width: 600px;" class="modal-dialog modal-center">

							<!-- join Modal content-->
							<div class="modal-content">

								<section style="margin: 30px 10px 20px 50px">

									<h1>Join Fomat</h1>

									<div>
										<form action="<%=application.getContextPath()%>/join"
											method="post" onsubmit="return check()"
											enctype="multipart/form-data">
											<div>
												<div style="text-align: left">
													<input class="input" name="joinMid" id="joinMid"
														type="text" placeholder="ID" required> <label
														id="canUse" style="margin-top: 5px">중복확인해주세요</label>
												</div>
												<div>
													<a id="checkButton" class="btn btn-primary"
														style="font-size: 15px">중복확인</a>
												</div>

												<br />

												<div style="text-align: left">
													<input class="input" id="joinMpassword"
														name="joinMpassword" type="password" placeholder="비밀번호"
														required>
												</div>

												</br>

												<div style="text-align: left">
													<input class="input" id="mPasswordCheck"
														name="mPasswordCheck" type="password"
														placeholder="비밀번호 확인" required> <label
														style="margin-top: 5px" id="pwcheck" name="pwcheck">비밀번호를
														한번 더 입력해주세요.</label>
												</div>

												<div style="text-align: left">
													<input class="input" id="mname" name="mname" type="text"
														placeholder="이름" required>
												</div>
												</br>

												<div style="text-align: left">
													<input class="input" id="memail" name="memail" type="email"
														placeholder="E-mail" required>
												</div>
												</br>

												<div style="text-align: left">
													<img id="imgFileView" /> <input type="file" id="sattach"
														style="visibility: hidden" onchange="fileChange(event)"
														name="sattach" />
												</div>
												</br>

												<div>
													<ul>
														<li><input class="btn btn-primary" class="input"
															id="join" name="join" value="회원가입" type="submit" /></li>
														<br />
														<li><input class="btn btn-primary" class="input"
															type="reset" value="Reset" /></li>
													</ul>
												</div>
											</div>
										</form>
									</div>
								</section>

							</div>
						</div>
					</div>




					<!-- 로그인 모달 -->
					<div style="position: relative; top: 250px; left: 150px">
						<nav id="nav-2">
							<ul>
								<li><button type="button" class="button special"
										data-toggle="modal" data-target="#myModal-2">Log-in</button></li>
							</ul>
						</nav>
					</div>

					<div id="myModal-2" class="modal fade" role="dialog">
						<div class="modal-dialog modal-sm">

							<!-- login Modal content-->
							<div class="modal-content">

								<div class="modal-body">
									<form method="post"
										action="<%=application.getContextPath()%>/login"
										class="form-signin">
										<h2 class="form-signin-heading">Log-in</h2>
										<label for="inputEmail" class="sr-only">ID</label> <input
											type="text" id="mid" name="mid" class="form-control"
											placeholder="ID" required style="background-color: #e8e8e8">
										<label for="inputPassword" class="sr-only">PW</label> <input
											type="password" id="mpassword" name="mpassword"
											class="form-control" placeholder="Password" required
											style="background-color: #e8e8e8; margin-top: 20px">
										<br>
										<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>


					<div style="position: relative; top: 300px">
						<nav id="nav">
							<ul>
								<li><button type="button" class="button special"
										data-toggle="modal" data-target="#myModal">Log-in</button></li>
							</ul>
						</nav>
					</div>






				</header>
				<a href="#ControlPanel" class="jumplink pic"> <span
					class="arrow icon fa-chevron-right"><span>See my work</span></span>
					<img src="resources/images/me.jpg" alt="" />
				</a>
			</article>


			<!-- controlPanel ( throttle / pitch / roll / yaw ) -->
			<article id="ControlPanel" class="panel">
				<header>
					<h2>Drone Control</h2>
				</header>
				<p>
				<h4>&lt;Throttle&gt;</h4>
				Throttle controls the vertical up and down motion of the drone.
				Positive throttle will make the drone fly higher and negative
				throttle will make the drone fly lower.
				<h4>&lt;Yaw&gt;</h4>
				Yaw is the left and right rotation of the drone. Positive yaw will
				make the drone turn to the right and negative yaw will make the
				drone turn the left.
				<h4>&lt;Pitch&gt;</h4>
				Pitch is the forward and backward tilt of the drone. positive pitch
				will make the drone tilt and move forward and negative pitch will
				make the drone tilt and move backwards.
				<h4>&lt;roll&gt;</h4>
				Roll is the side to side tilt of the drone. Positive roll will make
				the drone tilt to the right and negative roll will make the drone
				tilt to the left.
				</p>
				<section>
					<div class="row">
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

							<div style="border: 1px solid gold; float: left; width: 33%;">
								<h3>throttle and yaw</h3>
								<canvas id="myCanvas" height="200" width="200"
									style="background: gray;"></canvas>
							</div>

							<div style="border: 1px solid gold; float: left; width: 33%;">
								<h3>pitch and roll</h3>
								<canvas id="myCanvas2" height="200" width="200"
									style="background: gray;"></canvas>
							</div>

							<div style="border: 1px solid gold; float: left; width: 33%;">
								<h3>fly mode</h3>
								<canvas id="myCanvas3" height="200" width="200"
									style="background: gray;"></canvas>
							</div>

							<div style="border: 1px solid gold; float: left; width: 33%;">
								<h3>camera</h3>
								<canvas id="myCanvas4" height="200" width="200"
									style="background: gray;"></canvas>
							</div>
							<img style="height: 240; width: 320" id="cameraView" />
						</div>
						<!--  
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic01.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic02.jpg" alt=""></a>
										</div>
										<div class="4u$ 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic03.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic04.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic05.jpg" alt=""></a>
										</div>
										<div class="4u$ 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic06.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic07.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic08.jpg" alt=""></a>
										</div>
										<div class="4u$ 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic09.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic10.jpg" alt=""></a>
										</div>
										<div class="4u 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic11.jpg" alt=""></a>
										</div>
										<div class="4u$ 12u$(mobile)">
											<a href="#" class="image fit"><img src="resources/images/pic12.jpg" alt=""></a>
										</div>
										-->
					</div>
				</section>
			</article>
			<!-- Work -->
			<article id="sensor" class="panel">
				<header>
					<h2>Sensor</h2>
				</header>

			</article>





			<!-- Join page -->
			<article id="join" class="panel">
				<header>
					<h2>Join</h2>
				</header>
				<form action="#" method="post">
					<div>
						<div class="row">
							<div class="6u 12u$(mobile)">
								<input type="text" name="name" placeholder="Name" />
							</div>
							<div class="6u$ 12u$(mobile)">
								<input type="text" name="email" placeholder="Email" />
							</div>
							<div class="12u$">
								<input type="text" name="subject" placeholder="Subject" />
							</div>
							<div class="12u$">
								<textarea name="message" placeholder="Message" rows="8"></textarea>
							</div>
							<div class="12u$">
								<input type="submit" value="Send Message" />
							</div>
						</div>
					</div>
				</form>
			</article>

		</div>

		<!-- Footer -->
		<div id="footer">
			<ul class="copyright">
				<li>&copy; Untitled.</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>

	</div>

	<!-- Scripts -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/skel.min.js"></script>
	<script src="resources/js/skel-viewport.min.js"></script>
	<script src="resources/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="resources/js/main.js"></script>
</body>
</html>