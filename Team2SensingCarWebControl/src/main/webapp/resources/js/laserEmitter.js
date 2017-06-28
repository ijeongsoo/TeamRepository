function laserEmitter(command, status){
	var json={"command":command, "status":status};
	$.ajax({
		url : "http://" + location.host + "/SensingCarRemoteControl/laserEmitter",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#laserEmitterStatus").html(data.status);
			}
		}
	});
}