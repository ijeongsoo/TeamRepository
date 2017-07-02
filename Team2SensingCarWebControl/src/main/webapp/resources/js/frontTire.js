function frontTire(ip) {
	var json = {
		"command" : change,
		"angle" : $('#frontTireAngle').val(),
		"sip":ip
	};

	$.ajax({
		url : "http://" + location.host
				+ "/SensingCarRemoteControl/frontTire",
		data : json,
		method : "post",
		success : function(data) {
			$('#frontTireAngle').val(data.angle);
			frontTireAngleView.series[0].points[0].update(Number(data.angle));

		}
	});
}
