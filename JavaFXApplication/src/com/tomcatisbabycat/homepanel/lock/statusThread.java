/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;

/**
 *
 * @author ijeongsu
 */
public class statusThread extends Thread{

	SampleStatus ss= SampleStatus.getInstance();
	@Override
	public void run() {
		double temp;
		int moist;
		int dust;
		
		while(true){
			temp=Math.round(((Math.random()*(35-8+1))+8)*10d)/10d;
			moist=(int)(Math.random()*(90-30+1))+30;
			dust=(int)(Math.random()*(200-50+1))+50;
			ss.setTemperature(temp);
			ss.setDust(dust);
			ss.setMoisture(moist);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
			}
		}
		
	}
	
	
}
