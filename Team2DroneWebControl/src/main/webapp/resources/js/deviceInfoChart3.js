var sensingcarInfoChart3;


$(function() {
	
	sensingcarInfoChart3 = new Highcharts.Chart({
		    chart: {
		    	renderTo : "sensingcarInfoChartContainer3",
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
		        text: '등록 장비 통신 현황',
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
		        name: '접속불가',
		        data: [0]
		
		    }, {
		        name: '접속가능',
		        data: [0]
		
		    }]
		});

});

setInterval("drawUserAvailable()", 2000)

function drawUserAvailable() {

var userAvailablecount=0;
var userNoAvailablecount=0;
var a=$("p[class='available']");
var existList=new Array();
for(var i=0; i<a.length; i++){
	existList.push(a[i].id);
}
for(var i=0; i<existList.length;i++){
	if(($("p[id='"+existList[i]+"']").html())=='접속가능 <img width="15px" src="resources/image/green.png">' ){
		userAvailablecount++;
	}else if(($("p[id='"+existList[i]+"']").html())=='접속불가능 <img width="15px" src="resources/image/red.png">' ){
		userNoAvailablecount++;
	}
}

var userTotal=userAvailablecount+userNoAvailablecount;
   

sensingcarInfoChart3.series[0].data[0].update({'y':userTotal});
sensingcarInfoChart3.series[1].data[0].update({'y':userNoAvailablecount});
sensingcarInfoChart3.series[2].data[0].update({'y': userAvailablecount});
}



