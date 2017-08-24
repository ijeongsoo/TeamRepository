

function ch8(dmacaddr){
	var ch8Value = $('#ch8Slide').val()
	var json = {
			"ch8" : String(ch8Value),
			"dmacaddr": dmacaddr
		};
	
	$.ajax({
		url : "http://" + location.host + "/Team2DroneWebControl/ch8",
		data : json,
		method : "post",
		success : function(data) {
				$('#ch8').html(ch8Value);
		}
	});
	
};