function camera(command, leftRight, upDown) {
	
	var json = {
		"command" : command,
		"leftRight" : leftRight,
		"upDown" : upDown
	};
	$.ajax({
		url : "http://" + location.host + "/SensingCarRemoteControl/camera",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#cameraStatus").html(
						"leftRight=" + data.leftRight + ";upDown="
								+ data.upDown);
				$("#btnLeftRight180").attr("onclick","camera('change','180','"+data.upDown+"')");
				$("#btnLeftRight135").attr("onclick","camera('change','135','"+data.upDown+"')");
				$("#btnLeftRight90").attr("onclick","camera('change','90','"+data.upDown+"')");
				$("#btnLeftRight45").attr("onclick","camera('change','45','"+data.upDown+"')");
				$("#btnLeftRight0").attr("onclick","camera('change','0','"+data.upDown+"')");
				$("#btnUpDown25").attr("onclick","camera('change','"+data.leftRight+"','25')");
				$("#btnUpDown45").attr("onclick","camera('change','"+data.leftRight+"','45')");
				$("#btnUpDown75").attr("onclick","camera('change','"+data.leftRight+"','75')");
			}
		}
	});
}