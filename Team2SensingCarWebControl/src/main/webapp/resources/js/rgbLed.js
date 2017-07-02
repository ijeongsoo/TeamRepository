

function rgbLed(sip){
	var redc = $('#redSlide').val();
	var greenc = $('#greenSlide').val();
	var bluec = $('#blueSlide').val();

	 redc = redc.toString(16);
	 if(redc.length==1){
		 redc='0'+redc;
	 }
	 greenc = greenc.toString(16);
	 if(greenc.length==1){
		 greenc='0'+greenc;
	 }
	 bluec = bluec.toString(16);
	 if(bluec.length==1){
		 bluec='0'+bluec;
	 }
	 var bgColor = '#'+redc+greenc+bluec;
	 
	
	var json = {
			"command" : "change",
			"sip": sip,
			"red" : $('#redSlide').val(),
			"green" : $('#greenSlide').val(),
			"blue" : $('#blueSlide').val()
		};
	
	$.ajax({
		url : "http://" + location.host + "/Team2SensingCarWebControl/rgbLed",
		data : json,
		method : "post",
		success : function(data) {
			if (data.result == "success") {
				$("#redH").html(data.red);
				$("#greenH").html(data.green);
				$("#blueH").html(data.blue);
			}
		}
	});
};