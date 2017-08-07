<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>Home</title>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		 $(function() {
			var ws = new WebSocket("ws://" + location.host + "/Team2DroneWebControl/websocket/camera");
			// 함수를 바로 대입해도 괜찮음.메시지가 도착했을 때 시행
			ws.onmessage = function(event) {
				$("#cameraView").attr("src", "data:image/jpg;base64,"+event.data);
			};
		}); 
		</script>
	</head>
	<body>
		<h4>WebApplication Home</h4>
		<hr/>
		<a href="control">control</a>
		<a href="realHome">realHome</a>
		
		<img style="height: 240; width: 320" id="cameraView"  />
	</body>
</html>






