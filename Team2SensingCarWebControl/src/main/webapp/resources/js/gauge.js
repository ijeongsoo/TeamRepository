

	
	var gaugeOptions = {

		chart : {
			height: null,
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
											max : 200,
											title : {
												text : 'Speed'
											}
										},

										credits : {
											enabled : false
										},

										series : [ {
											name : 'Speed',
											data : [ 80 ],
											dataLabels : {
												format : '<div style="text-align:center"><span style="font-size:25px;color:'
														+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
														+ '">{y}</span><br/>'
														+ '<span style="font-size:12px;color:silver">km/h</span></div>'
											},
											tooltip : {
												valueSuffix : ' km/h'
											}
										} ]

									}));

	// The RPM gauge
	var chartPhotoresistor = Highcharts
			.chart('container-photoresistor',
					Highcharts.merge(gaugeOptions,
									{
										yAxis : {
											min : 0,
											max : 5,
											title : {
												text : 'RPM'
											}
										},
										credits : {
											enabled : false
										},
										series : [ {
											name : 'RPM',
											data : [ 1 ],
											dataLabels : {
												format : '<div style="text-align:center"><span style="font-size:25px;color:'
														+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
														+ '">{y:.1f}</span><br/>'
														+ '<span style="font-size:12px;color:silver">* 1000 / min</span></div>'
											},
											tooltip : {
												valueSuffix : ' revolutions/min'
											}
										} ]

									}));
	
	var chartGas = Highcharts
	.chart('container-gas',
			Highcharts.merge(gaugeOptions,
							{
								yAxis : {
									min : 0,
									max : 5,
									title : {
										text : 'RPM'
									}
								},
								credits : {
									enabled : false
								},
								series : [ {
									name : 'RPM',
									data : [ 1 ],
									dataLabels : {
										format : '<div style="text-align:center"><span style="font-size:25px;color:'
												+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
												+ '">{y:.1f}</span><br/>'
												+ '<span style="font-size:12px;color:silver">* 1000 / min</span></div>'
									},
									tooltip : {
										valueSuffix : ' revolutions/min'
									}
								} ]

							}));

	var chartTracking = Highcharts
	.chart('container-ultrasonic',
			Highcharts.merge(gaugeOptions,
							{
								yAxis : {
									min : 0,
									max : 5,
									title : {
										text : 'RPM'
									}
								},
								credits : {
									enabled : false
								},
								series : [ {
									name : 'RPM',
									data : [ 1 ],
									dataLabels : {
										format : '<div style="text-align:center"><span style="font-size:25px;color:'
												+ ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black')
												+ '">{y:.1f}</span><br/>'
												+ '<span style="font-size:12px;color:silver">* 1000 / min</span></div>'
									},
									tooltip : {
										valueSuffix : ' revolutions/min'
									}
								} ]

							}));


	

