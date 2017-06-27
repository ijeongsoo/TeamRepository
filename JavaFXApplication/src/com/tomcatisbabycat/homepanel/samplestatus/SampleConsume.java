/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.samplestatus;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ijeongsu
 */
public class SampleConsume {
	  
	  private List<Month> list = new ArrayList<Month>();
	  
	  
	  private static SampleConsume sampleconsume=new SampleConsume();
	  
	  
	  private SampleConsume() {
	  }
	  
	  public static SampleConsume getInstance(){
			return sampleconsume;
	  }

	public List getList() {
		return list;
	}
	  
	  

	
	  
	  
}
