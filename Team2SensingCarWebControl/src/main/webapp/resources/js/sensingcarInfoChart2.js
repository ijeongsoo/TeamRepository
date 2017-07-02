var sensingcarInfoChart2;


$(function() {
	
	sensingcarInfoChart2 = new Highcharts.Chart({
		    chart: {
		    	renderTo : "sensingcarInfoChartContainer2",
		        type: 'column'
		    },
		    title: {
		        text: '현 계정의 장비현황'
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
		            text: '센싱카 수 (개)',
	                style: {
						fontSize: 18,
	    				   },
	        		margin : 25
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



