var ultrasonicsensorInfoChart;
$(function() {
	
	 Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });
	 
	 ultrasonicsensorInfoChart = new Highcharts.Chart({
		chart: {
			renderTo:"ultrasonicsensorInfoChartContainer",
			type:"spline",
		},
		
		colors: ['cyan'],
		title: {
			text: "거리감지 센서 현황"
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
				text: "거리(cm)",
				style: {
					fontSize: 18,
				},
				margin : 25
			}
		},
		series: [{
			name: "현제 거리감지 현황",
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
