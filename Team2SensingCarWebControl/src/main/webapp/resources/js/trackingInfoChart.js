var trackingInfoChart;
$(function() {
	
	 Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });
	 
	 trackingInfoChart = new Highcharts.Chart({
		chart: {
			renderTo:"trackingInfoChartContainer",
			backgroundColor: {
                linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
                stops: [
                    [0, 'rgb(255, 255, 255)'],
                    [1, 'rgb(134, 197, 250)']
                ]
			},
			type:"spline"
		},
		colors: ['rgb(21,46,113)'],
		title: {
			text: "< 트래킹 센서 현황 >"
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
				text: "black(0) & white(1)",
				style: {
					fontSize: 18,
				},
				margin : 25
			}
		},
		series: [{
			name: "현재 트래킹 현황",
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
//
//
//$(function() {
//	trackingSensor()
//	setInterval("trackingSensor('${sensingcar.sip}')", 1000)
//});
//
//
//
//function trackingSensor(ip) {
//	var json = {
//		"command" : "status",
//		"sip" : ip
//	};
//
//	$.ajax({
//		url : "http://" + location.host
//				+ "/Team2SensingCarWebControl/trackingSensor",
//		data : json,
//		method : "post",
//		success : function(data) {
//			var series = trackingView.series[0];
//			if(data.tracking==0){
//				series.data[0].update({'y':0});
//				 series.data[1].update({'y':1});
//				 
//				 
//			}else if(data.tracking==1){
//				series.data[0].update({'y':1});
//				 series.data[1].update({'y':0});
//				
//			}
//	
//			
//		}
//	});
//}
//
//
//
//
