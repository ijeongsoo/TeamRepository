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

<style>
/* Disable intrinsic user agent touch behaviors (such as panning or zooming) */
canvas {
	touch-action: none;
}
</style>


<script type='text/javascript'>
$(document).ready(function() {

	$('#mycanvas').bind('touchstart', function(e) {

		$("#msg").html("터치가 시작되었어요.");

		e.preventDefault();	//	이벤트취소

	});

	$('#mycanvas').bind('touchmove', function(e) {

		//	jQuery 이벤트 객체를 자바스크립트 표준 이벤트 객체로 바꾸기

		//	이유는 jQuery 에서 자바 스크립트

		var event = e.originalEvent;

		$('#msg').html('touch 이벤트 중입니다.'); 

		//	div에 터치한 좌표값 넣기

		$('#msg').append('<div>' + event.touches[0].pageX + ',' +

				event.touches[0].pageY + '</div>');

		event.preventDefault();

	});

	$('#mycanvas').bind('touchend', function(e) {

		$("#msg").append("<div>터치이벤트가 종료되었어요</div>"); 

	});
	
	$('#mycanvas2').bind('touchstart', function(e) {

		$("#msg2").html("터치가 시작되었어요.");

		e.preventDefault();	//	이벤트취소

	});

	$('#mycanvas2').bind('touchmove', function(e) {

		//	jQuery 이벤트 객체를 자바스크립트 표준 이벤트 객체로 바꾸기

		//	이유는 jQuery 에서 자바 스크립트

		var event = e.originalEvent;

		$('#msg2').html('touch 이벤트 중입니다.'); 

		//	div에 터치한 좌표값 넣기

		$('#msg2').append('<div>' + event.touches[0].pageX + ',' +

				event.touches[0].pageY + '</div>');

		event.preventDefault();

	});

	$('#mycanvas2').bind('touchend', function(e) {

		$("#msg2").append("<div>터치이벤트가 종료되었어요</div>"); 

	});

});



  </script>
<head>
<body >
	<canvas id="mycanvas" style="background-color: #c8c8c8;" width="100"
		height="100">
      Canvas element not supported.
    </canvas>
    <div id="msg"></div>
	<canvas id="mycanvas2" style="background-color: #c8c8c8;" width="100"
		height="100">
      Canvas element not supported.
    </canvas>
    <div id="msg2"></div>
</body>



<!-- Plugin JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js">
	
</script>

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
</head>

</html>