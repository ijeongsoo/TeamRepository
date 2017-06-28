<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>WebApplication</title>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
		<script src="<%=application.getContextPath()%>/resources/highcharts/code/themes/gray.js"></script>  
		<style>@media screen and (min-width: 480px) { #title { height: 10px; } }</style>
		
		
		
		<script src="<%=application.getContextPath()%>/resources/js/camera.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rgbLed.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/laserEmitter.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicSensor.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/thermistorSensorChart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicSensorChart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/frontTire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/backTire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/trackingSensorChart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/photoresistorSensorChart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/gasSensorChart.js"></script>
		
	</head>

	<body style="background-color: black;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-8">
					<div id="title" style="height:50px; margin:20px 0px; color: white; text-align: center; font-size: 30px; font-style: italic; font-weight: bold;">
						IoT Smart Sensing Car Control Panel
					</div>
				</div>
				<div class="col-lg-4">
					<div style="height:50px; margin-top: 40px; text-align: center; color: white; font-size: 20px; font-style: italic; font-weight: bold;">
						Powered By <span style="color: orange;">CoAP</span> Java Application
					</div>
				</div>                
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="row">
						<div class="col-md-5">
							<div style="height:210px;">
								<img class="img-responsive"  src="<%=application.getContextPath()%>/resources/image/smartcar2.jpg" style="width:100%;height:100%;"/>
							</div>
						</div>
						<div class="col-md-3">
							<div  style="height:210px;">
								<img class="img-responsive" src="${cameraUrl}" style="width:100%; height:100%;"/>
							</div>
						</div>
						<div class="col-md-4">
							<div style="background: linear-gradient(#737373, black); color: white; height:210px; padding: 5px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">Camera 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="cameraStatus">leftRight=${leftRight};upDown=${upDown}</span></div>
								<br/>
								<div style="text-align: center;">
									좌우<br/>
									<button id="btnLeftRight180" type="button" class="btn btn-danger" onclick="camera('change','180', '${upDown}')">&lt;&lt;</button> 
									<button id="btnLeftRight135" type="button" class="btn btn-warning" onclick="camera('change','135', '${upDown}')">&lt;</button> 
									<button id="btnLeftRight90"type="button" class="btn btn-default" onclick="camera('change','90', '${upDown}')">정면</button>                                    
									<button id="btnLeftRight45"type="button" class="btn btn-info" onclick="camera('change','45', '${upDown}')">&gt;</button>
									<button id="btnLeftRight0"type="button" class="btn btn-primary" onclick="camera('change','0', '${upDown}')">&gt;&gt;</button>                                    
									<br/><br/>상하<br/>
									<button id="btnUpDown25"type="button" class="btn btn-default" onclick="camera('change','${leftRight}', '25')">정면</button>
									<button id="btnUpDown45"type="button" class="btn btn-info" onclick="camera('change','${leftRight}', '45')">45도</button>
									<button id="btnUpDown75"type="button" class="btn btn-primary" onclick="camera('change','${leftRight}', '75')">75도</button>
								</div>
							</div>
						</div>       
					</div>

					<div class="row">
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">RGBLed 장치 제어</div>
								<div style="text-align: center">현재 상태: <div id="rgbLedStatus" style="background-color:rgb(${red},${green},${blue}); width:15px;height:15px;display: inline-block"></div></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="rgbLed('change', '255', '0', '0')">Red</button>
									<button type="button" class="btn btn-success" onclick="rgbLed('change', '0', '255', '0')">Green</button>
									<button type="button" class="btn btn-primary" onclick="rgbLed('change', '0', '0', '255')">Blue</button>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">LaserEmitter 장치 제어</div>
								<div style="text-align: center;">현재 상태: <span id="laserEmitterStatus">${laserEmitterStatus }</span></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-warning" onclick="laserEmitter('change', 'on')">ON</button>
									<button type="button" class="btn btn-info" onclick="laserEmitter('change', 'off')">OFF</button>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">Buzzer 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="buzzerStatus">${buzzerStatus }</span></div>
								<br/>  
								<div style="text-align: center;">
									<button type="button" class="btn btn-warning" onclick="buzzer('change', 'on')">ON</button>
									<button type="button" class="btn btn-info" onclick="buzzer('change', 'off')">OFF</button>
								</div>                                
							</div>
						</div>      
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">UltrasonicSensor 제어</div>
								<div style="text-align: center">현재 상태: <span id="ultrasonicSensorStatus">각도:${ultrasonicSensorAngle};거리:${distance }</span></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="ultrasonicSensor('change', '180')">&lt;&lt;</button>                                    
									<button type="button" class="btn btn-default" onclick="ultrasonicSensor('change', '90')">정면</button>
									<button type="button" class="btn btn-primary" onclick="ultrasonicSensor('change', '0')">&gt;&gt;</button>                                    
								</div>
							</div>
						</div>                            
					</div>

					<div class="row">
						<div class="col-lg-4">
							<div style="background: linear-gradient(#737373, black); color: white; height:180px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">LCD 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="lcdStatus"><br/>line0:${lcdLine0} <br/>line1:${lcdLine1 }</span></div>
								<br/>
								<div style="padding-left: 20px;">
									<table style="width:100%">
										<tr>
											<td style="width:70%">
												Line0: <input id="lcdline0" type="text" maxlength="16" placeholder="line0을 입력하세요." style="color: black; width:120px;"/><br/>
												Line1: <input id="lcdline1" type="text" maxlength="16" placeholder="line1을 입력하세요." style="color: black; width:120px;"/>
											</td>
											<td style="width:30%">
												<button type="button" onclick="lcd('change')" class="btn btn-primary">보내기</button>
											</td>
										</tr>
									</table>
								</div>
							</div> 
						</div>
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">FrontTire 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="frontTireStatus">${frontTireAngle}도 </span></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="frontTire('change', '60')"><<</button>
									<button type="button" class="btn btn-default" onclick="frontTire('change', '90')">정면</button>
									<button type="button" class="btn btn-primary" onclick="frontTire('change', '120')">>></button>
								</div>
							</div> 
						</div>
						<div class="col-lg-5">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">BackTire 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="backTireStatus">방향=${backTireDirection},속력=${backTireSpeed}</span></div>
								<br/>
								<div style="text-align: center;">
									<button id="btnDirectionForward" type="button" class="btn btn-warning" onclick="backTire('change', 'forward', '${backTireSpeed}')" style="margin-bottom: 5px;">전진</button>
									<button id="btnDirectionBackward" type="button" class="btn btn-info" onclick="backTire('change', 'backward','${backTireSpeed}')" style="margin-bottom: 5px;">후진</button>
									<br/>
									<button id="btnSpeed0" onclick="backTire('change','${backTireDirection}', '0')" style="color: black;">0</button>
									<button id="btnSpeed900" onclick="backTire('change','${backTireDirection}', '950')" style="color: black;">1</button>
									<button id="btnSpeed1250" onclick="backTire('change','${backTireDirection}', '1250')" style="color: black;">2</button>
									<button id="btnSpeed1600" onclick="backTire('change','${backTireDirection}', '1600')" style="color: black;">3</button>
									<button id="btnSpeed1950" onclick="backTire('change','${backTireDirection}', '1950')" style="color: black;">4</button>
									<button id="btnSpeed2300" onclick="backTire('change','${backTireDirection}', '2300')" style="color: black;">5</button>
									<button id="btnSpeed2650" onclick="backTire('change','${backTireDirection}', '2650')" style="color: black;">6</button>
									<button id="btnSpeed3000" onclick="backTire('change','${backTireDirection}', '3000')" style="color: black;">7</button>
									<button id="btnSpeed3350" onclick="backTire('change','${backTireDirection}', '3350')" style="color: black;">8</button>
									<button id="btnSpeed3700" onclick="backTire('change','${backTireDirection}', '3700')" style="color: black;">9</button>
									<button id="btnSpeed4095" onclick="backTire('change','${backTireDirection}', '4095')" style="color: black;">10</button>
								</div>                                      
							</div> 
						</div>
					</div>          
					<div class="row">
						<div class="col-lg-6"> 
							<div id="thermistorSensorChartContainer" style="height:230px; margin-top: 20px; border:1px solid white;"></div>
						</div>
						<div class="col-lg-6"> 
							<div id="trackingSensorChartContainer" style="height:230px; margin-top: 20px; border:1px solid white;"></div>
						</div> 
					</div>
				</div>
				<div class="col-lg-4">
					<div>
						<div class="row">
							<div class="col-lg-12">
								<div id="ultrasonicSensorChartContainer" style="height:270px; border:1px solid white;"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div id="photoresistorSensorChartContainer" style="height:270px; margin-top: 15px; border:1px solid white;"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div id="gasSensorChartContainer" style="height:270px; margin-top: 15px; border:1px solid white;"></div>
							</div>
						</div>                   
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
