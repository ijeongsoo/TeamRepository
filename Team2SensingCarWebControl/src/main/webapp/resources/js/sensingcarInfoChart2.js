var sensingcarInfoChart2;

$(function() {
	sensingcarInfoChart2 = new Highcharts.chart( {
	    chart: {
	    	renderTo : "sensingcarInfoChartContainer2",
	        type: 'column'
	    },
	    title: {
	        text: 'Monthly Average Rainfall'
	    },
	    
	    xAxis: {
	        categories: [
	            'Jan',
	            'Feb',
	            'Mar',
	            'Apr',
	            'May',
	            'Jun',
	            'Jul'
	        ],
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: ''
	        }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    series: [{
	        name: 'Tokyo',
	        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6]

	    }, {
	        name: 'New York',
	        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0]

	    }, {
	        name: 'London',
	        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0,]

	    }, {
	        name: 'Berlin',
	        data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4]

	    }]
	});
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
			
		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		window.location.replace("http://10.10.10.57:8080/Team2SensingCarWebControl/");
	    },
		
		success : function(data) {
			var startTime=data.startTime;
			var endTime= new Date().getTime();
			var result = endTime-startTime;
			var series = sensingcarInfoChart3.series[0];
			var shift = series.data.length > 10;
			
			series.addPoint([new Date().getTime(), result], true, shift);
			
			if (result <= 5) {
				 $("#testPTagChange").attr("src","resources/images/green.png");
				 $("#testSTagChange").html("Good");
				
			}else if(result <= 8){
				 $("#testPTagChange").attr("src","resources/images/orange.png");
				 $("#testSTagChange").html("Normal");
					
			}else{
				$("#testPTagChange").attr("src","resources/images/red.png");
				$("#testSTagChange").html("Bad");
			}
			
		}
		
	});
	
}


