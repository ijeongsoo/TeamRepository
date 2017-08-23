/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.device;

import java.io.IOException;

/**
 *
 * @author kang
 */
public class ProgramStart {
	private static final ProgramStart PS = new ProgramStart();
	
	DeviceMqttClient deviceProgram;
	
	private ProgramStart(){}
	
	public static ProgramStart getInstance(){
		return PS;
	}
	
	public void startMqtt(){
		deviceProgram = new DeviceMqttClient();
		
		deviceProgram.subscribe();
		deviceProgram.communicationCheck();
		
		// JVM이 종료되면 마지막으로 Thread를 한번 실행시킨다.
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				deviceProgram.shutdown();
			}
		});
	}
	
}
