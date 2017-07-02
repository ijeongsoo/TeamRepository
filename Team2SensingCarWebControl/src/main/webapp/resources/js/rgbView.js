var rgbView;
$(function() {
	rgbView = new Highcharts.chart('container-rgb', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: 0,
	        plotShadow: false, 
	        height: null
	    },
	    title: {
	        text: 'RGB',
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
	            startAngle: -120,
	            endAngle: 120,
	            center: ['50%', '50%']
	        }
	    },
	    series: [{
	        type: 'pie',
	        name: 'Browser share',
	        innerSize: '70%',
	        data: [
	            
	            {
	                name: 'RED',
	                color: '#DF5353',
	                y: 0.2
	            },
	            {
	                name: 'GREEN',
	                color: '#90ee7e',
	                y: 0.2
	            },
	            {
	                name: 'BLUE',
	                color: '#7cb5ec',
	                y: 0.2
	            }
	        ]
	    }]
	},function (chart) { // on complete

        chart.renderer.circle('50%', '49%', '15%').attr({
            fill: '#FF0000',
            stroke: 'white',
            'stroke-width': 0
        }).add();

    });
});
