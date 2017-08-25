

function mode(dmacaddr){
	var modeValue = $('#modeSlide').val()
	var json = {
			"mode" : String(modeValue),
			"dmacaddr": dmacaddr
		};
	
	$.ajax({
		url : "http://" + location.host + "/Team2DroneWebControl/mode",
		data : json,
		method : "post",
		success : function(data) {
				$('#ch5').html(modeValue);
		}
	});
	
};