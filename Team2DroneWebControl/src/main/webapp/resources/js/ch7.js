

function ch7(dmacaddr){
	var ch7Value = $('#ch7Slide').val()
	var json = {
			"ch7" : String(ch7Value),
			"dmacaddr": dmacaddr
		};
	
	$.ajax({
		url : "http://" + location.host + "/Team2DroneWebControl/ch7",
		data : json,
		method : "post",
		success : function(data) {
				$('#ch7').html(ch7Value);
		}
	});
	
};