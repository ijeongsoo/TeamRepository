var thermistorSensorChart;
$(function() {
	thermistorSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"thermistorSensorChartContainer",
			type:"spline",
			events: {
				load: requestThermistorSensorData
			}
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

function requestThermistorSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteControl/websocket/thermistorSensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = thermistorSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.temperature], true, shift);
	};
}
