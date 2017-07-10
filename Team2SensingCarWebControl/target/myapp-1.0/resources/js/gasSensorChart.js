var gasSensorChart;
$(function() {
	gasSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"gasSensorChartContainer",
			type:"spline",
			events: {
				load: requestGasSensorData
			}
		},
		colors: ['yellow'],
		title: {
			text: "GasSensor(가스센서)"
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
				text: "가스",
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
			name: "가스",
			data: [],
			allowPointSelect: true
		}]
	});
});

function requestGasSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteControl/websocket/gasSensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = gasSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.gas], true, shift);
	};
}
