var trackingSensorChart;
$(function() {
	trackingSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"trackingSensorChartContainer",
			type:"spline",
			events: {
				load: requestTrackingSensorData
			}
		},
		colors: ['white'],
		title: {
			text: "TrackingSensor(트래킹센서)"
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
				text: "색상",
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
			name: "색상",
			data: [],
			allowPointSelect: true
		}]
	});
});

function requestTrackingSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteControl/websocket/trackingSensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = trackingSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.color], true, shift);
	};
}
