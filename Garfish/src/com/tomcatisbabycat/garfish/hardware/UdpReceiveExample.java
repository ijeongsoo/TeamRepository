/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tomcatisbabycat.garfish.hardware;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UdpReceiveExample extends Thread {
	public static void main(String[] args) throws SocketException {

		DatagramSocket datagramSocket = new DatagramSocket(28800);

		Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.println("[수신시작]");
				try {
					while (true) {
						DatagramPacket packet = new DatagramPacket(new byte[40], 40);

						datagramSocket.receive(packet);
						byte[] arrByte = packet.getData();
						// String data = new String(packet.getData(), 0,
						// packet.getLength());
						System.out.println(Arrays.toString(arrByte));
						// System.out.println("[받은내용:"+packet.getSocketAddress()+"]"+data);

					}
				} catch (IOException e) {
					System.out.println("[수신종료]");
				}
			}
		};

		thread.start();

	}
}