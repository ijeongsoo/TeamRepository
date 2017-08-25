

function ch6(dmacaddr){
	var ch6Value = $('#ch6Slide').val()
	var json = {
			"ch6" : String(ch6Value),
			"dmacaddr": dmacaddr
		};
	
	$.ajax({
		url : "http://" + location.host + "/Team2DroneWebControl/ch6",
		data : json,
		method : "post",
		success : function(data) {
				$('#ch6').html(ch6Value);
		}
	});
	
};