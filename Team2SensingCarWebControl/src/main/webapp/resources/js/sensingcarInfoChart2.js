var sensingcarInfoChart2;


$(function() {
	
	sensingcarInfoChart2 = new Highcharts.Chart({
		    chart: {
		    	renderTo : "sensingcarInfoChartContainer2",
		        type: 'column'
		    },
		    title: {
		        text: '계정별 장비 접속 현황'
		    },
		   
		    xAxis: {
		            title: {
		                    text:""
		                },
		        categories: [
		            ''
		        ],
		        crosshair: true
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: '센싱카 수 (개)'
		        }
		    },
		    tooltip: {
		        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
		        footerFormat: '</table>',
		        shared: true,
		        useHTML: true
		    },
		    plotOptions: {
		        column: {
		            pointPadding: 0.2,
		            borderWidth: 0
		        }
		    },
		    series: [{
		        name: '전체',
		        data: [0]
		
		    }, {
		        name: '접속불가',
		        data: [0]
		
		    }, {
		        name: '접속가능',
		        data: [0]
		
		    }]
		});

});








