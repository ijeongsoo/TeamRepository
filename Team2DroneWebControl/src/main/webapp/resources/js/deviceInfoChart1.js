var sensingcarInfoChart;

$(function() {
	sensingcarInfoChart = new Highcharts.Chart({
		chart : {
			renderTo : "sensingcarInfoChartContainer1",
			
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			type : 'pie'
		},

		title : {
			text : "등록 장비 통신 정보",
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
				name : '이용가능',
				y : 0
			}, {
				name : '이용불가',
				y : 0
			} ]
		} ]
	});
});

$(function() {
	drawAvailable();
	setInterval("drawAvailable()", 2000)
});

function drawAvailable() {
	var availableStr;
	var availableArray;
	var availableNo = 0;
	var noAvailableNo = 0;
	availableStr = $('.available').text();
	availableArray=availableStr.split(" ");
	for(i=0; i<availableArray.length-1;i++){
		if(availableArray[i]=='접속가능'){
			availableNo++;
		}else if(availableArray[i]=='접속불가능'){
			noAvailableNo++;
		}
	}
	
	var series = sensingcarInfoChart.series[0];
	series.data[0].update(availableNo);
	series.data[1].update(noAvailableNo);

}
