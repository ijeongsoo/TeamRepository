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
	        y: 40
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
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
	            startAngle: -180,
	            endAngle: 180,
	            center: ['50%', '50%']
	        }
	    },
	    series: [{
	        type: 'pie',
	        name: 'Browser share',
	        innerSize: '70%',
	        data: [
	            ['Firefox',   10.38],
	            ['IE',       56.33],
	            ['Chrome', 24.03],
	            {
	                name: 'Proprietary or Undetectable',
	                y: 0.2,
	                dataLabels: {
	                    enabled: false
	                }
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
