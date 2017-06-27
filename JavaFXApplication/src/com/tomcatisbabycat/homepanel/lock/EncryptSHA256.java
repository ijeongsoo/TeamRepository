/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import java.security.MessageDigest;

/**
 *
 * @author kang
 */
public class EncryptSHA256 {
	public static String encryptSHA256(String inputPassword){
		try {
			MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
			msgDigest.update(inputPassword.getBytes());
			byte[] byteData = msgDigest.digest();
			
			StringBuffer sb = new StringBuffer();
			for(int i=0 ; i<byteData.length ; i++){
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			StringBuffer hexString = new StringBuffer();
			for(int i=0 ; i<byteData.length ; i++){
				String hex = Integer.toHexString(0xff & byteData[i]);
				if(hex.length() == 1){
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException();
		}
	}
}
