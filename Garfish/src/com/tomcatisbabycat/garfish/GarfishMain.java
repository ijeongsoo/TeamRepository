/**
 *
 * @author 2Team(Lee, Kang, Cho)
 */
package com.tomcatisbabycat.garfish;

import com.tomcatisbabycat.garfish.device.DBConnection;
import com.tomcatisbabycat.garfish.device.DeviceInfo;
import com.tomcatisbabycat.garfish.device.ProgramStart;
import com.tomcatisbabycat.garfish.device.SendCommands;
import com.tomcatisbabycat.garfish.device.UdpThread;
import com.tomcatisbabycat.garfish.hardware.camera.CamPublisher;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarfishMain {
	private static final Logger LOG = LoggerFactory.getLogger(GarfishMain.class);
	
	public static void main(String[] args) throws Exception {

		DeviceInfo deviceInfo = DeviceInfo.getInstance();
		ProgramStart program = ProgramStart.getInstance();
		DBConnection db = new DBConnection();
		UdpThread udp = UdpThread.getInstance();
		SendCommands comm = new SendCommands();
		
		CamPublisher camPublisher;


		LOG.info(deviceInfo.getMacAddress());
		
		System.out.println("MAC 주소 확인중...");
		
		if(db.macAddressChecked(deviceInfo.getMacAddress())){
			System.out.println("등록된 드론입니다...");
			System.out.println("프로그램을 시작합니다.");
			deviceInfo.makeTopic();
			deviceInfo.setLocalUsedIP();
			camPublisher = new CamPublisher("http://127.0.0.1:50001/?action=stream", "tcp://106.253.56.122:1883", deviceInfo.getMtCamera(), true);
			new Thread(udp).start();
			
//			comm.chageMode();
			//comm.sendLand();
			
			program.startMqtt();
			comm.chageMode();
			comm.sendLand();
			
		}else{
			System.out.println("등록되지 않은 드론입니다. 로그인후 등록해주세요");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("ID : ");
			String id = sc.nextLine();
			char[] pcArray = System.console().readPassword("%s", "PW : ");
			String pw = String.valueOf(pcArray);
			
			if(db.login(id, pw).equals("true")){
				System.out.println("로그인에 성공하였습니다");
				System.out.println("장비를 등록합니다.");
				System.out.print("장비의 이름을 입력해주세요(only English) : ");
				String name = sc.nextLine();
				System.out.println("장비의 타입을 입력해주세요");
				System.out.print("1. copter 2. plane 3. rover : ");
				int type = sc.nextInt();
				
				db.enrollDrone(id, name, deviceInfo.getMacAddress(), type);
				
				System.out.println("드론이 등록되었습니다.");
				System.out.println("프로그램을 시작합니다.");
				deviceInfo.makeTopic();
				deviceInfo.setLocalUsedIP();
				camPublisher = new CamPublisher("http://127.0.0.1:50001/?action=stream", "tcp://106.253.56.122:1883", deviceInfo.getMtCamera(), true);
				new Thread(udp).start();
				program.startMqtt();
			}else{
				System.out.println("로그인에 실패하였습니다");
			}
		}
	}
}
