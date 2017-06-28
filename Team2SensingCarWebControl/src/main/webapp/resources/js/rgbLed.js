function rgbLed(command, red, green, blue) {
	var json = {
		"command" : command,
		"red" : red,
		"green" : green,
		"blue" : blue
	};
	
	$.ajax({
		url : "http://" + location.host + "/SensingCarRemoteControl/rgbLed",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#rgbLedStatus").css("background-color", "rgb("+data.red+","+data.green+","+data.blue+")");
			}
		}
	});
}