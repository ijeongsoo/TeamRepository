var photoresistorInfoChart;
$(function() {
	
	 Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });
	 
	 photoresistorInfoChart = new Highcharts.Chart({
		chart: {
			renderTo:"photoresistorInfoChartContainer",
			 backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
	                stops: [
	                    [0, 'rgb(255, 255, 255)'],
	                    [1, 'rgb(255, 255, 89)']
	                ]
	            },
			type:"spline"
		},
		 
		colors: ['rgb(216,216,28)'],
		title: {
			text: "< 조도 센서 현황 >"
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
				text: "조명도 (lux)",
				style: {
					fontSize: 18,
				},
				margin : 25
			}
		},
		series: [{
			name: "현재조명도",
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
