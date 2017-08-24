/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.device;

import com.tomcatisbabycat.garfish.mavlink.Parser;
import com.tomcatisbabycat.garfish.mavlink.messages.MAVLinkMessage;
import com.tomcatisbabycat.garfish.mavlink.messages.MAVLinkPacket;
import java.net.InetAddress;

/**
 *
 * @author kang
 */
public class UdpThread implements Runnable{
	private static final UdpThread UT = new UdpThread();
	
	private UdpThread(){
		
	}
	private DeviceInfo dInfo = DeviceInfo.getInstance();
	
	private UdpReceiver udpReceiver = new UdpReceiver(dInfo.getDefaultPort());
	private Parser parser = new Parser();
	private MAVLinkMessage msg;
	
	

	@Override
	public void run() {
		try {
			udpReceiver.getUdpStream();
			System.out.printf("Listening on UDP :%s:%d%n", InetAddress.getLocalHost().getHostAddress(), udpReceiver.getLocalPort());
			
			while(true){
				byte[] data = udpReceiver.readDataBlock();
				
				for(int i = 0 ; i < udpReceiver.getLength() ; i++){
					MAVLinkPacket mavPacket = parser.mavlink_parse_char(data[i] & 0x00ff);
					if(mavPacket != null){
						//changeFlightMode();
						msg = mavPacket.unpack();
						if(msg.msgid == 178){
							tokenizer();
						}
//						System.out.println(msg.msgid + " " + msg.toString());
						if(msg.msgid == 74){
							tokenizer();
						}
					}
				}
			}
		} catch (Exception e){
		}
		System.out.println("Closing Scoket");
		udpReceiver.socketClose();
	}
	
	public static UdpThread getInstance(){
		return UT;
	}
	
	public MAVLinkMessage getMsg(){
		return msg;
	}
	
	public void tokenizer(){
		String msgString = msg.toString();
		
		String[] token = msgString.split(" |:");
		
		if(msg.msgid == 74){
			dInfo.setAirSpeed(token[3]);
			dInfo.setGroundSpeed(token[5]);
			dInfo.setAltitude(token[7]);
			dInfo.setHeading(token[11]);
//			System.out.println(token[3]);
//			System.out.println(token[5]);
			System.out.println(token[7]);
//			System.out.println(token[11]);
		}
		
		if(msg.msgid == 178){
			dInfo.setRollSensor(token[3]);
			dInfo.setPitchSensor(token[5]);
			dInfo.setYawSensor(token[7]);
			dInfo.setLatitude(token[11]);
			dInfo.setLongitude(token[13]);
//			System.out.println(token[3]);
//			System.out.println(token[5]);
//			System.out.println(token[7]);
//			System.out.println(token[11]);
//			System.out.println(token[13]);
		}
	}
}
