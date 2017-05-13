/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.samplestatus.Month;
import com.tomcatisbabycat.homepanel.samplestatus.SampleConsume;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.util.List;

/**
 *
 * @author ijeongsu
 */
public class statusThread extends Thread{
	
	SampleStatus ss= SampleStatus.getInstance();
	List<Month> list = SampleConsume.getInstance().getList();
	@Override
	public void run() {
		double temp;
		int moist;
		int dust;
		
		double elecUsed;
		double waterUsed;
		double gasUsed;
		
		boolean exist=false;
		
			Month month2 = new Month("2");
			month2.setElecTotalUsed(300);
			month2.setGasTotalUsed(40);
			month2.setWaterTotalUsed(20);
			Month month3 = new Month("3");
			month3.setElecTotalUsed(350);
			month3.setGasTotalUsed(30);
			month3.setWaterTotalUsed(30);
			Month month4 = new Month("4");
			month4.setElecTotalUsed(200);
			month4.setGasTotalUsed(20);
			month4.setWaterTotalUsed(34);
			list.add(month2);
			list.add(month3);
			list.add(month4);
		
		while(true){
			System.gc();
			
			Month thisMonth = new Month();
			for(int i=0; i<list.size(); i++){
				exist=list.get(i).equals(thisMonth);
				if(exist==true){
					break;
				}
			}
			if(exist==false){
				list.add(thisMonth);
			}
			
			if(list.size()>4){
				list.remove(0);
			}			
			
			
			
			temp=Math.round(((Math.random()*(35-8+1))+8)*10d)/10d;
			moist=(int)(Math.random()*(90-30+1))+30;
			dust=(int)(Math.random()*(200-50+1))+50;
			ss.setTemperature(temp);
			ss.setDust(dust);
			ss.setMoisture(moist);
			
			elecUsed=Math.round(((Math.random()*(3-0+1))+0)/1000*10000d)/10000d;
			gasUsed=Math.round(((Math.random()*(3-0+1))+0)/10000*10000d)/10000d;
			waterUsed=Math.round(((Math.random()*(3-0+1))+0)/10000*10000d)/10000d;
			ss.setElecUsed(elecUsed);
			ss.setGasUsed(gasUsed);
			ss.setWaterUsed(waterUsed);
			list.get(3).setElecTotalUsed(elecUsed);
			list.get(3).setWaterTotalUsed(gasUsed);
			list.get(3).setGasTotalUsed(waterUsed);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
		}
		
	}
	
	
}
