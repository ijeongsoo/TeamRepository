/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

/**
 *
 * @author kang
 */
public class SHA256 {
	private String passWord = "";

	public SHA256(){
		encrypt("1111");
	}
	public void encrypt(String inputPassword){
		passWord = EncryptSHA256.encryptSHA256(inputPassword);
	}

	public String getPassWord() {
		return passWord;
	}	
}
