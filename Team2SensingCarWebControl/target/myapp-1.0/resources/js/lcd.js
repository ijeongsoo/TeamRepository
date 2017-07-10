function lcd(sip) {
	var line0 = $("#line0").val();
	var line1 = $("#line1").val();
	var json = {
		"command" : "change",
		"line0" : line0,
		"line1" : line1,
		"sip": sip
	};

	$.ajax({
		url : "http://" + location.host
				+ "/Team2SensingCarWebControl/lcd",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				
			}
		}
	});
}
