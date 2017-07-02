

function laserEmitter(sip){
	
	if($('#laserToggle')[0].checked){
		var json={"command":"change", "status":"on", "sip":sip};
    	$.ajax({
    		url : "http://" + location.host + "/Team2SensingCarWebControl/laserEmitter",
    		data : json,
    		method : "post",
    		success : function(data) {
    			
    		}
    	});

	}else{
		var json={"command":"change", "status":"off", "sip":sip};
    	$.ajax({
    		url : "http://" + location.host + "/Team2SensingCarWebControl/laserEmitter",
    		data : json,
    		method : "post",
    		success : function(data) {
    			
    		}
    	});
	}
	
};