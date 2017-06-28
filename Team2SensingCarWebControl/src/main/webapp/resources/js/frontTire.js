function frontTire(command, angle) {

	var json = {
		"command" : command,
		"angle" : angle
	};

	$.ajax({
		url : "http://" + location.host
				+ "/SensingCarRemoteControl/frontTire",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#frontTireStatus").html(
						data.angle + "도") ;
			}
		}
	});
}
