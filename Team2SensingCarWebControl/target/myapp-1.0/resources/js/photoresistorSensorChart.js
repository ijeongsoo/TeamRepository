var photoresistorSensorChart;
$(function() {
	photoresistorSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"photoresistorSensorChartContainer",
			type:"spline",
			events: {
				load: requestPhotoresistorSensorData
			}
		},
		colors: ['green'],
		title: {
			text: "PhotoresistorSensor(조도센서)"
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
				text: "조도",
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
			name: "조도",
			data: [],
			allowPointSelect: true
		}]
	});
});

function requestPhotoresistorSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteControl/websocket/photoresistorSensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = photoresistorSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.photoresistor], true, shift);
	};
}
