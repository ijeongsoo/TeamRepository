

function backTire(sip){
	
	if($('#directionToggle')[0].checked){
		var json = {
				"command" : "change",
				"direction" : "forward",
				"speed" : $('#speedSlide').val(),
				"sip":sip
			};
	}else{
		var json = {
				"command" : "change",
				"direction" : "backward",
				"speed" : $('#speedSlide').val(),
				"sip":sip
			};
	}
	
	
	$.ajax({
		url : "http://" + location.host + "/Team2SensingCarWebControl/backTire",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$('#speedH').html(data.speed);
				$('#speedSlide').val(data.speed);
				speedView.series[0].points[0].update(Number(data.speed));
			}
		}
	});
	
};