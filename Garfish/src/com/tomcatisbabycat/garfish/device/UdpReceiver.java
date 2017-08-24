/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tomcatisbabycat.garfish.device;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpReceiver{
	DeviceInfo dInfo = DeviceInfo.getInstance();
	
	private int localPort = 14550;
	private byte [] receiveData = new byte[1024];
	private DatagramSocket socket;
	private int length;
	private int hostPort;
	private InetAddress hostAdd = null;
	
	public UdpReceiver(int port){
		localPort = port;
	}
	
	
	public byte[] readDataBlock() throws IOException {
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		socket.receive(receivePacket);
		length = receivePacket.getLength();
		hostAdd = receivePacket.getAddress();
		hostPort = receivePacket.getPort();
		return receivePacket.getData();
	}
	
	public void getUdpStream() throws SocketException{
		socket = new DatagramSocket(localPort);
		socket.setBroadcast(true);
		socket.setReuseAddress(true);
		
		settingStream();
	}

	public int getLength(){
		return length;
	}
	
	public int getLocalPort(){
		return localPort;
	}
	
	public void socketClose(){
		socket.close();
	}
	
	public DatagramSocket getSocket(){
		return socket;
	}
	
	public void settingStream(){
		dInfo.setSocket(this.socket);
	}
	
//	public static void main(String[] args) throws SocketException {
//
//		DatagramSocket datagramSocket = new DatagramSocket(20000);
//		
//		Parser mavParser = new Parser();
//
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				System.out.println("[수신시작]");
//				try {
//					while (true) {
//						DatagramPacket packet = new DatagramPacket(new byte[40], 40);
//
//						datagramSocket.receive(packet);
//						byte[] arrByte = packet.getData();
//						
//						MAVLinkPacket mavPacket = mavParser.mavlink_parse_char(packet.getData());
//						
//						// String data = new String(packet.getData(), 0,
//						// packet.getLength());
//						System.out.println(Arrays.toString(arrByte));
//						// System.out.println("[받은내용:"+packet.getSocketAddress()+"]"+data);
//
//					}
//				} catch (IOException e) {
//					System.out.println("[수신종료]");
//				}
//			}
//		};
//
//		thread.start();
//
//	}
}