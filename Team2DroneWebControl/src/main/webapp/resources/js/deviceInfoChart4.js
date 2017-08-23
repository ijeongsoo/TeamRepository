var sensingcarInfoChart4;


$(function() {
	
	sensingcarInfoChart4 = new Highcharts.Chart({
		    chart: {
		    	renderTo : "sensingcarInfoChartContainer4",
		    	backgroundColor: {
	                linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
	                stops: [
	                    [0, 'rgb(24, 188, 156)'],
	                    [1, 'rgb(24, 188, 156)']
	                ]
				},
		        type: 'column'
		    },
		    title: {
		        text: '등록 장비 종류',
		        style: {
		        	color:'#ffffff',
		        	fontSize:20
		        }
		    },
		   
		    xAxis: {
	            title: {
	                text: "",
	                style: {
						fontSize: 18,
	    				   },
	        		margin : 25	
		                },
		       categories: [''],
		        crosshair: true
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: '장비 수 (개)',
			        style: {
			        	color:'#ffffff',
			        	fontSize:13
			        },
	        		margin : 25
		        }
		    
		    
		    },
		    tooltip: {
		        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		            '<td style="padding:0"><b>{point.y:1f} </b></td></tr>',
		        footerFormat: '</table>',
		        shared: true,
		        useHTML: true
		    },
		    plotOptions: {
		        column: {
		            pointPadding: 0.2,
		            borderWidth: 1
		        }
		    },
		    series: [{
		        name: '전체',
		        data: [0]
		
		    }, {
		        name: '드론',
		        data: [0]
		
		    }, {
		        name: '자동차',
		        data: [0]
		
		    }, {
		        name: '비행기',
		        data: [0]
		
		    }]
		});

});

$(function() {
	drawDeviceKindBar();
	setInterval("drawDeviceKindBar()", 2000)
});

function drawDeviceKindBar() {
	
	sensingcarInfoChart4.series[0].data[0].update({'y':totalDeviceNum});
	sensingcarInfoChart4.series[1].data[0].update({'y':droneNum});
	sensingcarInfoChart4.series[2].data[0].update({'y': roverNum});
	sensingcarInfoChart4.series[3].data[0].update({'y': planeNum});

}

