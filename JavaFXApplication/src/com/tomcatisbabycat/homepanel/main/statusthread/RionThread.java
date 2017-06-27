/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

import com.tomcatisbabycat.homepanel.resources.icons.IconSelector;
import com.tomcatisbabycat.homepanel.resources.images.ImageResourceFinder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ijeongsu
 */
public class RionThread extends Thread{
	private ImageView imgRion;
	

	public RionThread(ThreadGroup threadGroup, String threadName,ImageView imgRion) {
		super(threadGroup, threadName);
		this.imgRion = imgRion;
		
	}

	

	

	
	@Override
	public void run() {
		int i=1;
		
		while(true){
			
			imgRion.setImage(new Image(ImageResourceFinder.class.getResource("rion-"+i+".png").toString()));
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				break;
			}
			i++;
			if(i==16){
				i=1;
			}
		}
			
		
		System.out.println("라이언 스레드 종료");
		System.gc();
	}
	
}
