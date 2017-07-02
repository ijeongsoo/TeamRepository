var rgbView;
$(function() {
	rgbView = new Highcharts.Chart({
		chart : {
			renderTo : "container-rgb",
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			type : 'pie'
		},

		title : {
			text : "RGB",
		        align: 'center',
		        verticalAlign: 'middle',
		        y: 60
		},credits : {
			enabled : false
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : false,
					distance : -70,
					format : '{point.y}개',
					style : {
						fontWeight : 'light',
						color : 'black',
						textOutline : '0px',
						fontSize : '20px'
					}
				},
				size : '80%',center: ['50%', '30%'],
				showInLegend : false
			}

		},
		series : [ {
			name : '비율',
			colorByPoint : true,innerSize: '50%',
			data : [ {
				name : 'RED',
				color:'#DF5353',
				y : 0
			}, {
				name : 'GREEN',
				color:'#90ee7e',
				y : 0
			},{
				name : 'BLUE',
				color:'#7cb5ec',
				y : 0
			}  ]
		} ]
	});
});
