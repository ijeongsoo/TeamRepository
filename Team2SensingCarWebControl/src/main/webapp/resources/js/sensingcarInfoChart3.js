var sensingcarInfoChart3;
$(function() {
	sensingcarInfoChart3 = new Highcharts.Chart({
		chart: {
			renderTo:"sensingcarInfoChartContainer3",
			type:"spline"
			
		},
		colors: ['cyan'],
		title: {
			text: "ThermistorSensor(온도센서)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			minPadding: 0.2,
			maxPadding: 0.2,
			title: {
				text: "온도",
				margin: 30
			}
		},
		plotOptions: {
	        series: {
	            marker: {
	                enabled: true
	            }
	        }
	    },
		series: [{
			name: "온도",
			data: [],
			allowPointSelect: true
		}]
	});
});


$(function() {
	//webServerNetworkStatusCheckStart();
	setInterval("webServerNetworkStatusCheck()", 2000)
});



function webServerNetworkStatusCheck() {
//	StartTime을 제는 부분
	
	var json={"startTime":(new Date().getTime()).toString()};
	//통신 및 endTime 
	$.ajax({
		url : "http://" + location.host
				+ "/Team2SensingCarWebControl/check_webserver_comunication",
		data : json,
		async:true,
		method : "post",
		timeout:10000,
		error: function (jqXHR, textStatus, errorThrown) {
			
	    },
		
		success : function(data) {
			var startTime=data.startTime;
			var endTime= new Date().getTime();
			var result=endTime-startTime;
			var series = sensingcarInfoChart3.series[0];
			var shift = series.data.length > 10;
			
			series.addPoint([new Date().getTime(), result], true, shift);
		}
		
	});
	
}



