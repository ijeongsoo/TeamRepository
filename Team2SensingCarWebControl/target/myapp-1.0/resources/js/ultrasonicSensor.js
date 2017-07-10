function getUltasonicSensor(ip) {	
	$("#ultraH").html($('#ultraSlide').val());
	var json = {
		"command" : "change",
		"angle" : String(180-Number($('#ultraSlide').val())),
		"sip" : ip
	};

	$.ajax({
		url : "http://" + location.host
				+ "/Team2SensingCarWebControl/ultrasonicSensor",
		data : json,
		method : "post",
		success : function(data) {
				
		}
	});
}


