var trackingView;
$(function() {
	trackingView = new Highcharts.chart('container-tracking', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: 0,
	        plotShadow: false, 
	        height: null
	    },
	    title: {
	        text: 'tracking',
	        align: 'center',
	        verticalAlign: 'middle',
	        y: 60
	    },
	    credits : {
			enabled : false
		},
	    plotOptions: {
	        pie: {
	            dataLabels: {
	                enabled: false,
	                distance: -50,
	                style: {
	                    fontWeight: 'bold',
	                    color: 'white'
	                }
	            },
	            borderWidth:0,
	            size : '60%',
	            startAngle: -180,
	            endAngle: 180,
	            center: ['50%', '40%']
	        }
	    },
	    series: [{
	        type: 'pie',
	        name: 'now',
	        innerSize: '0%',
	        data: [
	            
	            {
	                name: 'WHITE',
	                color: '#E8E8E8',
	                y: 0
	            },
	            {
	                name: 'BLACK',
	                color: '#000000',
	                y: 0
	            }
	        ]
	    }]
	

    });
});
