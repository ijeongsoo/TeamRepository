function backTire(command, direction, speed) {
	
	var json = {
		"command" : command,
		"direction" : direction,
		"speed" : speed
	};
	$.ajax({
		url : "http://" + location.host + "/SensingCarRemoteControl/backTire",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#backTireStatus").html(
						"방향=" + data.direction + ";속력="
								+ data.speed);
				$("#btnSpeed0").attr("onclick","backTire('change','"+data.direction+"','0')");
				$("#btnSpeed900").attr("onclick","backTire('change','"+data.direction+"','950')");
				$("#btnSpeed1250").attr("onclick","backTire('change','"+data.direction+"','1250')");
				$("#btnSpeed1600").attr("onclick","backTire('change','"+data.direction+"','1600')");
				$("#btnSpeed1950").attr("onclick","backTire('change','"+data.direction+"','1950')");
				$("#btnSpeed2300").attr("onclick","backTire('change','"+data.direction+"','2300')");
				$("#btnSpeed2650").attr("onclick","backTire('change','"+data.direction+"','2650')");
				$("#btnSpeed3000").attr("onclick","backTire('change','"+data.direction+"','3000')");
				$("#btnSpeed3350").attr("onclick","backTire('change','"+data.direction+"','3350')");
				$("#btnSpeed3700").attr("onclick","backTire('change','"+data.direction+"','3700')");
				$("#btnSpeed4095").attr("onclick","backTire('change','"+data.direction+"','4095')");
				$("#btnDirectionForward").attr("onclick","backTire('change','forward','"+data.speed+"')");
				$("#btnDirectionBackward").attr("onclick","backTire('change','backward','"+data.speed+"')");
			}
		}
	});
}