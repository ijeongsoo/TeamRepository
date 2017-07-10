var frontTireAngleView;
$(function() {
	frontTireAngleView = new Highcharts.chart('container-frontTireAngle', {

	    chart: {
	        type: 'gauge',
	        plotBorderWidth: 0,
	        
	        plotBackgroundImage: null,
	        height: null
	    },

	    title: {
	        text: '방향 각도'
	    },

	    pane: [{
	        startAngle: -45,
	        endAngle: 45,
	        background: null,
	        center: ['50%', '175%'],
	        size: '300%'
	    }],

	    tooltip: {
	        enabled: false
	    },
	    credits : {
			enabled : false
		},

	    yAxis: [{
	        min: 0,
	        max: 180,
	        minorTickPosition: 'outside',
	        tickPosition: 'outside',
	        labels: {
	            rotation: 'auto',
	            distance: 20
	        },
	        plotBands: [{
	            from: 170,
	            to: 180,
	            color: '#C02316',
	            innerRadius: '100%',
	            outerRadius: '105%'
	        },{
	            from: 0,
	            to: 10,
	            color: '#C02316',
	            innerRadius: '100%',
	            outerRadius: '105%'
	        }],
	        pane: 0,
	        title: {
	            text: '<span style="font-size:8px">좌우</span>',
	            y: -20
	        }
	    }],

	    plotOptions: {
	        gauge: {
	            dataLabels: {
	                enabled: false
	            },
	            dial: {
	                radius: '100%'
	            }
	        }
	    },


	    series: [{
	        name: 'Channel A',
	        data: [10],
	        yAxis: 0
	    }]

	});
});
