var sensingcarInfoChart2;

$(function() {
	sensingcarInfoChart2 = new Highcharts.Chart({
		chart : {
			renderTo : "sensingcarInfoChartContainer2",
			
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			type : 'pie'
		},

		title : {
			text : "등록 장비 종류",
	        style: {
	        	color:'#ffffff',
	        	fontSize:20
	        }
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : true,
					distance : -70,
					format : '{point.y}개',
					style : {
						fontWeight : 'light',
						color : 'black',
						textOutline : '0px',
						fontSize : '20px'
					}
				},
				showInLegend : true
			}

		},
		series : [ {
			name : '비율',
			colorByPoint : true,
			data : [ {
				name : '드론',
				y : 0
			}, {
				name : '자동차',
				y : 0
			}, {
				name : '비행기',
				y : 0
			} ]
		} ]
	});
});

$(function() {
	drawDeviceKind();
	setInterval("drawDeviceKind()", 2000)
});

function drawDeviceKind() {
	
	var series = sensingcarInfoChart2.series[0];
	series.data[0].update(droneNum);
	series.data[1].update(roverNum);
	series.data[2].update(planeNum);

}

