
var gaugeOptions = {

	chart : {
		height : null,
		type : 'solidgauge'
	},

	title : null,

	pane : {
		center : [ '50%', '50%' ],
		size : '100%',
		startAngle : -120,
		endAngle : 120,
		background : {
			backgroundColor : (Highcharts.theme && Highcharts.theme.background2)
					|| '#EEE',
			innerRadius : '60%',
			outerRadius : '100%',
			shape : 'arc'
		}
	},

	tooltip : {
		enabled : false
	},

	// the value axis
	yAxis : {
		stops : [ [ 0.1, '#55BF3B' ], // green
		[ 0.5, '#DDDF0D' ], // yellow
		[ 0.9, '#DF5353' ] // red
		],
		lineWidth : 0,
		minorTickInterval : null,
		tickAmount : 2,
		title : {
			y : 80
		},
		labels : {
			y : 16
		}
	},

	plotOptions : {
		solidgauge : {
			dataLabels : {
				y : -30,
				borderWidth : 0,
				useHTML : true
			}
		}
	}
};

// The speed gauge
var chartThermistor = Highcharts
		.chart(
				'container-thermistor',
				Highcharts
						.merge(
								gaugeOptions,
								{
									yAxis : {
										min : 0,
										max : 50,
										
									},
									title : {
										text : '온도',
										align : 'center',
										verticalAlign : 'middle',
										y : 60
									},

									credits : {
										enabled : false
									},

									series : [ {
										name : '온도',
										data : [0],
										dataLabels : {
											format : '<div style="text-align:center"><span style="font-size:25px;color:'
													+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
													+ '">{y}</span><br/>'
													+ '<span style="font-size:12px;color:silver">°C</span></div>'
										},
										tooltip : {
											valueSuffix : ' °C'
										}
									} ]

								}));

// The RPM gauge
var chartPhotoresistor = Highcharts
		.chart(
				'container-photoresistor',
				Highcharts
						.merge(
								gaugeOptions,
								{
									yAxis : {
										min : 0,
										max : 255,
										
									},
									title : {
										text : '조도',
										align : 'center',
										verticalAlign : 'middle',
										y : 60
									},
									credits : {
										enabled : false
									},
									series : [ {
										name : '조도',
										data : [0],
										dataLabels : {
											format : '<div style="text-align:center"><span style="font-size:25px;color:'
													+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
													+ '">{y}</span><br/>'
													+ '<span style="font-size:12px;color:silver">밝기</span></div>'
										},
										tooltip : {
											valueSuffix : ' revolutions/min'
										}
									} ]

								}));

var chartGas = Highcharts
		.chart(
				'container-gas',
				Highcharts
						.merge(
								gaugeOptions,
								{
									yAxis : {
										min : 0,
										max : 255,
										
									},
									title : {
										text : '가스',
										align : 'center',
										verticalAlign : 'middle',
										y : 60
									},
									credits : {
										enabled : false
									},
									series : [ {
										name : 'RPM',
										data : [ 0 ],
										dataLabels : {
											format : '<div style="text-align:center"><span style="font-size:25px;color:'
													+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
													+ '">{y}</span><br/>'
													+ '<span style="font-size:12px;color:silver">가스량</span></div>'
										},
										tooltip : {
											valueSuffix : ' revolutions/min'
										}
									} ]

								}));

var chartUltrasonic = Highcharts
		.chart(
				'container-ultrasonic',
				Highcharts
						.merge(
								gaugeOptions,
								{
									yAxis : {
										min : 0,
										max : 400,
										
									},
									title : {
										text : '거리',
										align : 'center',
										verticalAlign : 'middle',
										y : 60
									},
									credits : {
										enabled : false
									},
									series : [ {
										name : 'RPM',
										data : [ 0 ],
										dataLabels : {
											format : '<div style="text-align:center"><span style="font-size:25px;color:'
													+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
													+ '">{y}</span><br/>'
													+ '<span style="font-size:12px;color:silver">cm</span></div>'
										},
										tooltip : {
											valueSuffix : ' cm'
										}
									} ]

								}));
