var gassensorInfoChart;

$(function() {
	
	 Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });
	 
	gassensorInfoChart = new Highcharts.Chart({
		chart: {
			renderTo:"gassensorInfoChartContainer",
			backgroundColor: {
                linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
                stops: [
                    [0, 'rgb(255, 255, 255)'],
                    [1, 'rgb(204, 118, 33)']
                ]
            },
			type:"spline",
			
		},
		colors: ['brown'],
		title: {
			text: "< 가스 센서 현황 >"
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
				text: "가스농도 (ppm)",
				style: {
					fontSize: 18,
				},
				margin : 25
			}
		},
		series: [{
			name: "현재가스농도",
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

