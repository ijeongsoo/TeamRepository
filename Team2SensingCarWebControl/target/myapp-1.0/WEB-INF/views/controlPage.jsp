<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Team2's SensingCar</title>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/skel.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/style-xlarge.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/circle-img.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/toggle.css"
	rel="stylesheet" type="text/css" />
<link href="<%=application.getContextPath()%>/resources/css/custom.css"
	rel="stylesheet" type="text/css" />

<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/skel.min.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/skel-layers.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/init.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/script.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/knob.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts-more.js"></script>

<script
	src="<%=application.getContextPath()%>/resources/highcharts/code/modules/solid-gauge.js"></script>

<script
	src="https://cdn.jsdelivr.net/jquery.roundslider/1.0/roundslider.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/jquery.roundslider/1.0/roundslider.min.css"
	rel="stylesheet" />


<script src="<%=application.getContextPath()%>/resources/js/rgbView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/trackingView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/speedView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/cameraAngleView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ultraAngleView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/frontTireAngleView.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/laserEmitter.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/rgbLed.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/backTire.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/ultrasonicSensor.js"></script>
<script
	src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>




<style>
@media screen and (min-width: 480px) {
	#title {
		height: 10px;
	}
}

#arc-slider {
	height: 150px !important;
	width: 100% !important;
	border: 0px dashed;
	overflow: hidden;
	padding: 15px;
}

#arc-slider .rs-container {
	margin-left: -350px; /* here 300 is the radius value */
	left: 50%;
}

#arc-slider .rs-tooltip {
	top: 60px;
}

#arc-slider .rs-tooltip-text {
	font-size: 25px;
}

#arc-slider .rs-border {
	border-width: 0px;
}

/* Appearance related changes */
.rs-control .rs-range-color {
	background-color: #54BBE0;
}

.rs-control .rs-path-color {
	background-color: #5f5f5f;
}

.rs-control .rs-handle {
	background-color: #51c5cf;
}
</style>

<script>



	/* $(function(){
	 window.moveTo(0, 0);
	 window.resizeTo(screen.availWidth, screen.availHeight);
	 if($(window).width()>980){
	 $( ".leftcon " ).css("height", ($(window).width()/10));
	
	 }
	
	 $( window ).resize(function() {
	 if($(window).width()>980){
	 $( ".leftcon" ).css("height", ($(window).width()/10));
	 }else{
	 $( ".leftcon" ).css("height", '200px');
	 }
	 });

	 }); */
	 $(function(){
		 
		 
		 $("#arc-slider").roundSlider({
			    sliderType: "min-range",
			    circleShape: "custom-quarter",
			    min:60,
			    max:120,
			    value:${frontTireAngle},
			    startAngle: 45,
			    editableTooltip: true,
			    radius: 350,
			    width: 6,
			    handleSize: "+30",
			    tooltipFormat: function (args) {
			        return args.value + " ";
			    },
			    'drag' :function (args) {
		        	 var json = {
		        			"command" : "change",
		        			"angle" : String(args.value),
		        			"sip":'${sensingcar.sip}'
		        		};

		        		$.ajax({
		        			url : "http://" + location.host
		        					+ "/Team2SensingCarWebControl/frontTire",
		        			data : json,
		        			method : "post",
		        			success : function(data) {
		        				$('#frontTireAngle').val(data.angle);
		        				frontTireAngleView.series[0].points[0].update(Number(data.angle));

		        			}
		        		}); 
		        }
			});	 
		 

		var red;
		var green;
		var blue;
		
		rgbView.series[0].data[0].update(${red});
		rgbView.series[0].data[1].update(${green});
		rgbView.series[0].data[2].update(${blue}); 
		 
		
		  red = ${red};
		 green = ${green};
		  blue = ${blue};
		 red = red.toString(16);
		 if(red.length==1){
			 red='0'+red;
		 }
		 green = green.toString(16);
		 if(green.length==1){
			 green='0'+green;
		 }
		 blue = blue.toString(16);
		 if(blue.length==1){
			 blue='0'+blue;
		 }
		 var bgColor = '#'+red+green+blue;
		 
		 
		 var series = chartThermistor.series[0];
			series.data[0].update({'y':${temperature}});
			 series = chartPhotoresistor.series[0];
			series.data[0].update({'y':${photoresistor}});
			
			
			 series = chartGas.series[0];
			series.data[0].update({'y':${gas}});
			 series = chartUltrasonic.series[0];
			series.data[0].update({'y':${distance}});
			
			series = trackingView.series[0];
			if(${tracking}==0){
				series.data[0].update({'y':0});
				 series.data[1].update({'y':1});
				 
				 
			}else if(${tracking}==1){
				series.data[0].update({'y':1});
				 series.data[1].update({'y':0});
				
			}
			
			speedView.series[0].points[0].update(${backTireSpeed});
			ultraAngleView.series[0].points[0].update(180-${ultrasonicSensorAngle});
			frontTireAngleView.series[0].points[0].update(${frontTireAngle});
			cameraAngleView.series[0].points[0].update(180-${leftRight});
			cameraAngleView.series[1].points[0].update(${upDown});
	 });

	 setInterval("thermistorSensor('${sensingcar.sip}')", 1000);
	 setInterval("photoresistorSensor('${sensingcar.sip}')", 1000);
	 setInterval("gasSensor('${sensingcar.sip}')", 1000);
	 setInterval("ultrasonicSensor('${sensingcar.sip}')", 1000);
	 setInterval("trackingSensor('${sensingcar.sip}')", 1000);
	 setInterval("buzzerStatus('${sensingcar.sip}')", 2000);
	 setInterval("laserStatus('${sensingcar.sip}')", 2000);
	 setInterval("ledStatus('${sensingcar.sip}')", 1000);
	 setInterval("backTireStatus('${sensingcar.sip}')", 2000);
	 setInterval("frontTireStatus('${sensingcar.sip}')", 2000);
	 setInterval("lcdStatus('${sensingcar.sip}')", 10000);
	function thermistorSensor(ip) {
		var json = {
			"command" : "status",
			"sip" : ip
		};

		$.ajax({
			url : "http://" + location.host
					+ "/Team2SensingCarWebControl/thermistorSensor",
			data : json,
			method : "post",
			success : function(data) {
				var series = chartThermistor.series[0];
				series.data[0].update({'y':data.temperature});
			}
		});
	}
	 
	 function photoresistorSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/photoresistorSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = chartPhotoresistor.series[0];
					series.data[0].update({'y':data.photoresistor});
				}
			});
		}
	 
	 function gasSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/gasSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = chartGas.series[0];
					series.data[0].update({'y':data.gas});
				}
			});
		}
	 
	 function ultrasonicSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/ultrasonicSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = chartUltrasonic.series[0];
					series.data[0].update({'y':data.distance});
					ultraAngleView.series[0].points[0].update(Number(180-data.angle));
					$("#ultraH").html(Number(180-data.angle));
					$("#ultraSlide").val(180-data.angle);
					
				}
			});
		}
	 
	 function trackingSensor(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/trackingSensor",
				data : json,
				method : "post",
				success : function(data) {
					var series = trackingView.series[0];
					if(data.tracking==0){
						series.data[0].update({'y':0});
						 series.data[1].update({'y':1});
						 
						 
					}else if(data.tracking==1){
						series.data[0].update({'y':1});
						 series.data[1].update({'y':0});
						
					}
			
					
				}
			});
		}
	 
	 function buzzerStatus(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/buzzer",
				data : json,
				method : "post",
				success : function(data) {
					if(data.status=='on'){
						$('#buzzerToggle').prop('checked', true);
						 
						 
					}else if(data.status=='off'){
						$('#buzzerToggle').prop('checked', false);
						
					}		
				}
			});
		}
	 
	 function laserStatus(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/laserEmitter",
				data : json,
				method : "post",
				success : function(data) {
					if(data.status=='on'){
						$('#laserToggle').prop('checked', true);
						 
						 
					}else if(data.status=='off'){
						$('#laserToggle').prop('checked', false);
						
					}		
				}
			});
		}
	 
	 function ledStatus(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/rgbLed",
				data : json,
				method : "post",
				success : function(data) {
					$("#redH").html(data.red);
					$("#greenH").html(data.green);
					$("#blueH").html(data.blue);
					rgbView.series[0].data[0].update(Number(data.red));
					rgbView.series[0].data[1].update(Number(data.green));
					rgbView.series[0].data[2].update(Number(data.blue));
					
					$('#redSlide').val(data.red);
					$('#greenSlide').val(data.green);
					$('#blueSlide').val(data.blue);
				}
			});
		}
	 
	 function backTireStatus(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/backTire",
				data : json,
				method : "post",
				success : function(data) {
					
					if(data.direction=="forward"){
						$("#directionToggle").prop('checked', true);
					}else{
						$("#directionToggle").prop('checked', false);
					}
					$("#speedH").html(data.speed);
					$('#speedSlide').val(data.speed);
					speedView.series[0].points[0].update(Number(data.speed));

				}
			});
		}
	 
	 function frontTireStatus(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/frontTire",
				data : json,
				method : "post",
				success : function(data) {
					$("#arc-slider").roundSlider("setValue", data.angle);
					frontTireAngleView.series[0].points[0].update(Number(data.angle));
				
				}
			});
		}
	 
	 function lcdStatus(ip) {
			var json = {
				"command" : "status",
				"sip" : ip
			};

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/lcd",
				data : json,
				method : "post",
				success : function(data) {
					$("#line0").val(data.line0);
					$("#line1").val(data.line1);
				
				}
			});
		}
	 
	 
	 
	 function speedUp(sip) {
		 	var speed = Number($("#speedSlide").val())+53;
		 	if($('#directionToggle')[0].checked){
				var json = {
						"command" : "change",
						"direction" : "forward",
						"speed" : String(speed),
						"sip":sip
					};
			}else{
				var json = {
						"command" : "change",
						"direction" : "backward",
						"speed" : String(speed),
						"sip":sip
					};
			}

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/backTire",
				data : json,
				method : "post",
				success : function(data) {
					$("#speedH").html(data.speed);
					$('#speedSlide').val(data.speed);
					speedView.series[0].points[0].update(Number(data.speed));

				}
			});
		}
	 
	 function zeroSpeed(sip) {
		 	var speed = 0;
		 	if($('#directionToggle')[0].checked){
				var json = {
						"command" : "change",
						"direction" : "forward",
						"speed" : String(speed),
						"sip":sip
					};
			}else{
				var json = {
						"command" : "change",
						"direction" : "backward",
						"speed" : String(speed),
						"sip":sip
					};
			}

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/backTire",
				data : json,
				method : "post",
				success : function(data) {
					$("#speedH").html(data.speed);
					$('#speedSlide').val(data.speed);
					speedView.series[0].points[0].update(Number(data.speed));

				}
			});
		}
	 function speedDown(sip) {
		 	var speed = Number($("#speedSlide").val())-53;
		 	if($('#directionToggle')[0].checked){
				var json = {
						"command" : "change",
						"direction" : "forward",
						"speed" : String(speed),
						"sip":sip
					};
			}else{
				var json = {
						"command" : "change",
						"direction" : "backward",
						"speed" : String(speed),
						"sip":sip
					};
			}

			$.ajax({
				url : "http://" + location.host
						+ "/Team2SensingCarWebControl/backTire",
				data : json,
				method : "post",
				success : function(data) {
					$("#speedH").html(data.speed);
					$('#speedSlide').val(data.speed);
					speedView.series[0].points[0].update(Number(data.speed));

				}
			});
		}
	 
	 function turnLeft(sip) {
		 	console.log("aa");
		 	 var angle = Number($("#arc-slider").roundSlider("getValue"))-5;	
				var json = {
						"command" : "change",
						"angle" : angle,
						"sip":sip
					};
			

				$.ajax({
        			url : "http://" + location.host
        					+ "/Team2SensingCarWebControl/frontTire",
        			data : json,
        			method : "post",
        			success : function(data) {
        				$("#arc-slider").roundSlider("setValue", data.angle);
        				frontTireAngleView.series[0].points[0].update(Number(data.angle));
        			}
        		});  
		}
	 
	 function turnRight(sip) {
		 	var angle = Number($("#arc-slider").roundSlider("getValue"))+5;
		 	
				var json = {
						"command" : "change",
						"angle" : angle,
						"sip":sip
					};
			

				$.ajax({
     			url : "http://" + location.host
     					+ "/Team2SensingCarWebControl/frontTire",
     			data : json,
     			method : "post",
     			success : function(data) {
     				$("#arc-slider").roundSlider("setValue", data.angle);
     				frontTireAngleView.series[0].points[0].update(Number(data.angle));

     			}
     		}); 
		}
	 
	
	 
	
		
	 var speedUpInterval=null;
	 var speedDownInterval=null;
	 var leftInterval=null;
	 var rightInterval=null;
	/* KeyEvent */
 	function keyEvent(event){
	 var keycode=event.keyCode;
		if(keycode==81){			
			if($('#buzzerToggle')[0].checked==false){
				$('#buzzerToggle').prop('checked', true);
				buzzer('${sensingcar.sip }');
			}else{
				$('#buzzerToggle').prop('checked', false);
				buzzer('${sensingcar.sip }');
			}
		}else if(keycode==87){			
			if($('#laserToggle')[0].checked==false){
				$('#laserToggle').prop('checked', true);
				laserEmitter('${sensingcar.sip }');
			}else{
				$('#laserToggle').prop('checked', false);
				laserEmitter('${sensingcar.sip }');
			}
		}else if(keycode==67){
			if(speedUpInterval==null){
				speedUpInterval=setInterval("speedUp('${sensingcar.sip}')", 10);
			}
		} else if(keycode==88){
			if(speedDownInterval==null){
				speedDownInterval=setInterval("speedDown('${sensingcar.sip}')", 10);
			}
		} else if(keycode==90){
			zeroSpeed('${sensingcar.sip}');
		}else if(keycode==80){
			$('#directionToggle').prop('checked', true);
			backTire('${sensingcar.sip }');	
		} else if(keycode==186){
			$('#directionToggle').prop('checked', false);
			backTire('${sensingcar.sip }');
		}else if(keycode == 76){
			if(leftInterval==null){
				leftInterval=setInterval("turnLeft('${sensingcar.sip}')", 100);	
			}
			
		}else if(keycode == 222){
			if(rightInterval==null){
				rightInterval=setInterval("turnRight('${sensingcar.sip}')", 100);
			}
		}else if(keycode == 13){
			lcd('${sensingcar.sip}');
		}
	}; 
	
	 
	function keyUpEvent(event){
		var keycode2=event.keyCode;
		console.log(keycode2);
		if(keycode2==67){
			clearInterval(speedUpInterval);
			speedUpInterval=null;
		}else if(keycode2==88){
			clearInterval(speedDownInterval);
			speedDownInterval=null;
		}else if(keycode2==76){
			clearInterval(leftInterval);
			leftInterval=null;
		}else if(keycode2==222){
			clearInterval(rightInterval);
			rightInterval=null;
		}
		
	}
	
	 
	$('#redSlider').spunkySlider();
	$('#greenSlider').spunkySlider();
	$('#blueSlider').spunkySlider();

	

	
		
		
	 
</script>

<script type="text/javascript">
      
      

$(function(){
	var isDragging = false;
	var x=Number(180-${leftRight})*(300/170);
 	var y=180-Number(${upDown})*(180/100);
		var cnvs=document.getElementById('myCanvas');
     var ctx = document.getElementById('myCanvas').getContext("2d");
     
  // 픽셀 정리
     ctx.clearRect(0, 0, cnvs.width, cnvs.height);
     // 컨텍스트 리셋
     ctx.beginPath();


     //원 그리기
     ctx.beginPath();
     ctx.arc(x, y, 10, 0,(Math.PI/180) *360,false);
     //ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
     //그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향

     ctx.fillStyle = "rgb(255, 0, 0)";  //채울 색상
     ctx.fill(); //채우기
     ctx.stroke(); //테두리
	
	
	 $("#myCanvas").mousedown(function(event) {
		    isDragging = true;
		    var x=event.offsetX;
         	var y=event.offsetY;
  			var cnvs=document.getElementById('myCanvas');
             var ctx = document.getElementById('myCanvas').getContext("2d");
             
          // 픽셀 정리
             ctx.clearRect(0, 0, cnvs.width, cnvs.height);
             // 컨텍스트 리셋
             ctx.beginPath();

  
             //원 그리기
             ctx.beginPath();
             ctx.arc(x, y, 10, 0,(Math.PI/180) *360,false);
             //ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
             //그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향
  
             ctx.fillStyle = "rgb(255, 0, 0)";  //채울 색상
             ctx.fill(); //채우기
             ctx.stroke(); //테두리
             
             var json = {
            			"command" : "change",
            			"leftRight" : String(180-parseInt(x*(170/300))),
            			"upDown":String(90-parseInt(y*(100/180))),
            			"sip" : '${sensingcar.sip}'
            		};

            		$.ajax({
            			url : "http://" + location.host
            					+ "/Team2SensingCarWebControl/camera",
            			data : json,
            			method : "post",
            			success : function(data) {
            				cameraAngleView.series[0].points[0].update(180-Number(data.leftright));
             				cameraAngleView.series[1].points[0].update(Number(data.updown));
            			}
            		});
	});
	 $("#myCanvas").mousemove(function(event) {
		 if(isDragging){
			 var x=event.offsetX;
         	var y=event.offsetY;
  			var cnvs=document.getElementById('myCanvas');
             var ctx = document.getElementById('myCanvas').getContext("2d");
             
          // 픽셀 정리
             ctx.clearRect(0, 0, cnvs.width, cnvs.height);
             // 컨텍스트 리셋
             ctx.beginPath();

  
             //원 그리기
             ctx.beginPath();
             ctx.arc(x, y, 10, 0,(Math.PI/180) *360,false);
             //ctx.arc(x,y, 반지름, 시작각도, 종료각도, 그리는 방향);
             //그리는 방향 : true 이면 시계 반대방향 / false 이면 시계 방향
  
             ctx.fillStyle = "rgb(255, 0, 0)";  //채울 색상
             ctx.fill(); //채우기
             ctx.stroke(); //테두리
             var json = {
         			"command" : "change",
         			"leftRight" : String(180-parseInt(x*(170/300))),
         			"upDown":String(90-parseInt(y*(100/180))),
         			"sip" : '${sensingcar.sip}'
         		};

         		$.ajax({
         			url : "http://" + location.host
         					+ "/Team2SensingCarWebControl/camera",
         			data : json,
         			method : "post",
         			success : function(data) {
         				cameraAngleView.series[0].points[0].update(180-Number(data.leftright));
         				cameraAngleView.series[1].points[0].update(Number(data.updown));
         			}
         		});
             
		 }
	});
	 $("#myCanvas").mouseup(function(event) {
		    isDragging = false;
	});
});

    </script>

</head>

<body onkeydown="keyEvent(event)" onkeyup="keyUpEvent(event)">

	<!-- Header -->
	<header id="header">
		<h1 class="0u(medium)">
			<a href="<%=application.getContextPath()%>/">Team2's SensingCar</a> <span>|
				"${sensingcar.sregistor}"님이 등록하신 "${sensingcar.sname}":
				${sensingcar.sip }</span>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href="<%=application.getContextPath()%>/remove?sno=${sensingcar.sno }">장비제거</a></li>
				<li><a href="<%=application.getContextPath()%>/sensor?sip=${sensingcar.sip }">RealTime Sensor</a></li>
				<li><a href="<%=application.getContextPath()%>/">Home</a></li>
				<li><a type="button" class="button special" href="logout">Log
						Out</a></li>
			</ul>
		</nav>
	</header>

	<!-- Main -->
	<section id="main" class="wrapper" style="padding: 0px">
		<div class="row">

			<div class="3u 12u(medium) ">
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-thermistor"
						style="height: 10em"></div>

					<div class="6u$ 6u(medium) leftcon" id="container-photoresistor"
						style="height: 10em"></div>
				</div>
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-gas"
						style="height: 10em"></div>
					<div class="6u$ 6u$(medium) leftcon" id="container-ultrasonic"
						style="height: 10em"></div>
				</div>
				<hr>

				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-rgb"
						style="height: 10em"></div>
					<div class="6u$ 6u$(medium) leftcon" id="container-tracking"
						style="height: 10em"></div>
				</div>
			</div>

			<div class="6u 12u$(medium)"
				style="padding-left: 0; padding-right: 0; height: auto">
				<div class="thumbnail">
					<div class="centered">
						<img src="${cameraUrl}" class="portrait" />
					</div>
				</div>
			</div>

			<div class="3u$ 12u$(medium)"
				style="padding-left: 0; padding-right: 0;">
				<div class="row">
					<div class="12u$ 12u$(medium) leftcon" id="container-speed"
						style="height: 14em"></div>

				</div>

				<div class="row">
					<div class="12u 12u$(medium) leftcon" id="container-cameraAngle"
						style="height: 8em"></div>

				</div>
				<hr>
				<div class="row">
					<div class="6u 6u(medium) leftcon" id="container-ultraAngle"
						style="height: 8em"></div>
					<div class="6u 6u$(medium) leftcon" id="container-frontTireAngle"
						style="height: 8em"></div>
				</div>
			</div>


		</div>
		<hr>
	</section>

	<!-- One -->
	<section id="one" class=" style2 special">

		<div class="row">
			<div class="2u 12u(medium) ">
				<div style="display: inline-block; vertical-align: middle;">
					<div style="display: inline-block; width: 200px;">
						<h3 style="display: inline-block; margin-right: 20px">부저</h3>
						<label class="switch"> <input type="checkbox"
							id="buzzerToggle" onchange="buzzer('${sensingcar.sip }')" />
							<div class="slider round"></div>
						</label>
					</div>

					<div style="display: inline-block; width: 200px">
						<h3 style="display: inline-block; margin-right: 20px">레이저</h3>
						<label class="switch"> <input type="checkbox"
							id="laserToggle" onchange="laserEmitter('${sensingcar.sip }')" />
							<div class="slider round"></div>
						</label>
					</div>
				</div>
				<div>
					<h3>RGB</h3>
					<div style="margin-left: 30px">
						<input oninput="rgbLed('${sensingcar.sip }')" class="redSlide"
							id="redSlide" data-fix-max-value="255"
							data-orientation="horizontal"
							style="display: inline-block; width: 250px;" value="${red}"
							type="range" min="0" max="255" step="1">
						<h4 id="redH">${red}</h4>
						<input oninput="rgbLed('${sensingcar.sip }')" class="greenSlide"
							id="greenSlide" data-fix-max-value="255"
							data-orientation="horizontal"
							style="display: inline-block; width: 250px;" value="${green}"
							type="range" min="0" max="255" step="1">
						<h4 id="greenH">${green}</h4>
						<input oninput="rgbLed('${sensingcar.sip }')" class="blueSlide"
							id="blueSlide" data-fix-max-value="255"
							data-orientation="horizontal"
							style="display: inline-block; width: 250px;" value="${blue}"
							type="range" min="0" max="255" step="1">
						<h4 id="blueH">${blue}</h4>

					</div>

				</div>
			</div>

			<div class="6u 12u$(medium)"
				style="padding-left: 0; padding-right: 0;">
				<div class="row">
					<div class="3u 3u(medium)">
						<div style="display: inline-block; width: 200px">
							<h3
								style="display: inline-block; padding-bottom: 0; margin-right: 20px">방향</h3>
							<label class="switchv"> <input type="checkbox"
								id="directionToggle" onchange="backTire('${sensingcar.sip }')" />
								<div class="sliderv roundv"></div>
							</label>
						</div>


						<div>
							<h3 style="margin-left: 30px; margin-top: 20px">속도</h3>
							<div style="margin-left: 30px">
								<input oninput="backTire('${sensingcar.sip }')" class="vertical"
									id="speedSlide" data-fix-max-value="255"
									data-orientation="horizontal"
									style="display: inline-block; width: 250px;"
									value="${backTireSpeed}" type="range" min="0" max="4095"
									step="1">
								<h4 id="speedH">${backTireSpeed}</h4>


							</div>
						</div>
					</div>


					<div class="9u 9u$(medium)">
						<h3>핸들</h3>
						<div id="arc-slider" oninput="frontTire('${sensingcar.sip }')"
							class="rslider"></div>
						<hr>
						<h3>거리센서</h3>
						<input onchange="getUltasonicSensor('${sensingcar.sip }')"
							class="blueSlide" id="ultraSlide" data-fix-max-value="180"
							data-orientation="horizontal"
							style="display: inline-block; width: 320px;" value="0"
							type="range" min="0" max="180" step="1">
						<h4 id="ultraH">0</h4>
					</div>

				</div>
			</div>

			<div class="4u 12u$(medium)"
				style="padding-left: 0; padding-right: 0;">
				<h3>카메라 각도</h3>
				<canvas id="myCanvas" height="180" style="background: gray;">
  				</canvas>
  				
				<h3>LCD메세지</h3>
				
				<input  type="text" id="line0" name="line0" class="form-control"
					placeholder="line0" required
					style="background-color: #e8e8e8; width: 300px; margin:0 auto" value="${lcdLine0 }"> <input value="${lcdLine1 }"
					type="text" id="line1" name="line1" class="form-control"
					placeholder="line1" required
					style="background-color: #e8e8e8;  width: 300px;margin:0 auto; margin-top: 20px;">


			</div>




		</div>

	</section>


	<section id="one" class="wrapper style2 special" style="color: black">

	</section>



	<!-- Two -->
	<section id="two" class="wrapper style1 special"></section>



	<!-- Footer -->
	<footer id="footer">
		<div class="container">
			<section class="links">
				<div class="row">
					<section class="3u 6u(medium) 12u$(small)">
						<h3>한국 소프트웨어 산업협회</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Nesciunt itaque, alias possimus</a></li>
							<li><a href="#">Optio rerum beatae autem</a></li>
							<li><a href="#">Nostrum nemo dolorum facilis</a></li>
							<li><a href="#">Quo fugit dolor totam</a></li>
						</ul>
					</section>
					<section class="3u 6u$(medium) 12u$(small)">
						<h3>웹 개발 참고 사이트</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Reiciendis dicta laboriosam enim</a></li>
							<li><a href="#">Corporis, non aut rerum</a></li>
							<li><a href="#">Laboriosam nulla voluptas, harum</a></li>
							<li><a href="#">Facere eligendi, inventore dolor</a></li>
						</ul>
					</section>
					<section class="3u 6u(medium) 12u$(small)">
						<h3>SensingCar 개발 참고 사이트</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Distinctio, inventore quidem nesciunt</a></li>
							<li><a href="#">Explicabo inventore itaque autem</a></li>
							<li><a href="#">Aperiam harum, sint quibusdam</a></li>
							<li><a href="#">Labore excepturi assumenda</a></li>
						</ul>
					</section>
					<section class="3u$ 6u$(medium) 12u$(small)">
						<h3>네트워크 통신관련 참고 사이트</h3>
						<ul class="unstyled">
							<li><a href="#">Lorem ipsum dolor sit</a></li>
							<li><a href="#">Recusandae, culpa necessita nam</a></li>
							<li><a href="#">Cupiditate, debitis adipisci blandi</a></li>
							<li><a href="#">Tempore nam, enim quia</a></li>
							<li><a href="#">Explicabo molestiae dolor labore</a></li>
						</ul>
					</section>
				</div>
			</section>
			<div class="row">
				<div class="8u 12u$(medium)">
					<ul class="copyright">
						<li>&copy; Team2. All rights reserved.</li>
						<li>Design: <a href="http://templated.co">TEMPLATED</a></li>
						<li>Images: <a href="http://unsplash.com">Unsplash</a></li>
					</ul>
				</div>
				<div class="4u$ 12u$(medium)">
					<ul class="icons">
						<li><a class="icon rounded fa-facebook"><span
								class="label">Facebook</span></a></li>
						<li><a class="icon rounded fa-twitter"><span
								class="label">Twitter</span></a></li>
						<li><a class="icon rounded fa-google-plus"><span
								class="label">Google+</span></a></li>
						<li><a class="icon rounded fa-linkedin"><span
								class="label">LinkedIn</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
	<script src="<%=application.getContextPath()%>/resources/js/gauge.js"></script>



</body>
</html>






