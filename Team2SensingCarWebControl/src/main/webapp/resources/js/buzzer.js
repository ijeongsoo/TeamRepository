
function buzzer(sip){
	
	if($('#buzzerToggle')[0].checked){
		var json={"command":"change", "status":"on", "sip":sip};
    	$.ajax({
    		url : "http://" + location.host + "/Team2SensingCarWebControl/buzzer",
    		data : json,
    		method : "post",
    		success : function(data) {
    			
    		}
    	});

	}else{
		var json={"command":"change", "status":"off", "sip":sip};
    	$.ajax({
    		url : "http://" + location.host + "/Team2SensingCarWebControl/buzzer",
    		data : json,
    		method : "post",
    		success : function(data) {
    			
    		}
    	});
	}
	
};

