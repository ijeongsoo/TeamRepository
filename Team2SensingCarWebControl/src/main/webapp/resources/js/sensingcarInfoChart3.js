var sensingcarInfoChart3;
$(function() {
	
	 Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });
	 
	sensingcarInfoChart3 = new Highcharts.Chart({
		chart: {
			renderTo:"sensingcarInfoChartContainer3",
			type:"spline"
		},
		colors: ['cyan'],
		title: {
			text: "WebServer Network Status(네트워크 상태)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000,
			title: {
				text: "Time Interval : 2sec",
				style: {
					fontSize: 18,
				},
				margin : 25
			}
		},
		yAxis: {
			minPadding: 0.2,
			maxPadding: 0.2,
			title: {
				text: "네트워크 통신 시간 (millisec)",
				style: {
					fontSize: 18,
				},
				margin : 25
			}
		},
		series: [{
			name: "네트워크 상태",
			data: [],
			allowPointSelect: true
		}],
		plotOptions: {
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    },
		
	});
});





$(function webServerCheckRepeat() {
	setInterval("webServerNetworkStatusCheck()", 2000)
});



function webServerNetworkStatusCheck() {
	//	StartTime을 제는 부분
	
	var	startTime = (new Date().getTime()).toString();
	var json={
			"startTime" : startTime
			};
	
	//통신 및 endTime 
	$.ajax({
		url : "http://" + location.host + "/Team2SensingCarWebControl/check_webserver_comunication",
		data : json,
		async: true,
		method : "post",
		timeout: 10000,
		error: function (request, Status, error) {
	
	    },
		
		success : function(data) {
			var startTime=data.startTime;
			var endTime= new Date().getTime();
			var result = endTime-startTime;
			var series = sensingcarInfoChart3.series[0];
			var shift = series.data.length > 10;
			
			series.addPoint([new Date().getTime(), result], true, shift);
			
			if (result <= 500) {
				 $("#testPTagChange").attr("src","resources/images/green.png");
				 $("#testSTagChange").html("Good");
				
			}else if(result <= 1000){
				 $("#testPTagChange").attr("src","resources/images/orange.png");
				 $("#testSTagChange").html("Normal");
					
			}else{
				$("#testPTagChange").attr("src","resources/images/red.png");
				$("#testSTagChange").html("Bad");
			}
			
		}
		
	});
	
}

