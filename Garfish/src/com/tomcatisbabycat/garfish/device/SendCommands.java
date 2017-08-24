/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.device;

import com.tomcatisbabycat.garfish.mavlink.messages.ApmModes;
import com.tomcatisbabycat.garfish.mavlink.messages.MAVLinkPacket;
import com.tomcatisbabycat.garfish.mavlink.messages.ardupilotmega.msg_command_long;
import com.tomcatisbabycat.garfish.mavlink.messages.ardupilotmega.msg_set_mode;
import com.tomcatisbabycat.garfish.mavlink.messages.enums.MAV_CMD;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 *
 * @author kang
 */
public class SendCommands {
	DeviceInfo dInfo = DeviceInfo.getInstance();
	
	public void sendBuffer(byte[] buffer) {
		try {
			if (dInfo.getInet() != null) {
				DatagramPacket udpPacket = new DatagramPacket(buffer, buffer.length, dInfo.getInet(), dInfo.getDefaultPort());
				dInfo.getSocket().send(udpPacket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMavPacket(MAVLinkPacket packet) {
		sendBuffer(packet.encodePacket());
	}
	
	public void chageMode(){
		ApmModes mode = ApmModes.ROTOR_LAND;
		msg_set_mode msg = new msg_set_mode();
		msg.target_system = 3;
		msg.base_mode = 81;
		msg.custom_mode = mode.getNumber();
		
		
		sendMavPacket(msg.pack());
		
		
		System.out.println("changeMode : " + msg.toString());
	}
	
	public void sendLand(){
		msg_command_long msg = new msg_command_long();
		msg.target_system = 1;
		msg.target_component = 1;
		msg.command = MAV_CMD.MAV_CMD_NAV_LAND;
		
		System.out.println("sendLand" + dInfo.getAltitude());
		
		msg.param7 = Float.parseFloat(dInfo.getAltitude());
		
		sendMavPacket(msg.pack());
	}
}
