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
			backgroundColor: {
                linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
                stops: [
                    [0, 'rgb(255, 255, 255)'],
                    [1, 'rgb(169, 204, 40)']
                ]
			},
			type:"spline",
		},
		
		colors: ['green'],
		title: {
			text: "< 거리감지 센서 현황 >"
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
			name: "현재 거리감지 현황",
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
