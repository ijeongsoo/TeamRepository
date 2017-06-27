/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sampleEnvVariable;


/**
 *
 * @author ijeongsu
 */
public class EnvVariable {
	
	private int sleepSec;

	static private EnvVariable ev=new EnvVariable();
	
	private EnvVariable() {
	}
	
	static public EnvVariable getInstance(){
		return ev;
	}
	
}
