function ultrasonicSensor(command, angle) {

	var json = {
		"command" : command,
		"angle" : angle
	};

	$.ajax({
		url : "http://" + location.host
				+ "/SensingCarRemoteControl/ultrasonicSensor",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#ultrasonicSensorStatus").html(
						"각도:" + data.angle + ";거리:" + data.distance);
			}
		}
	});
}
