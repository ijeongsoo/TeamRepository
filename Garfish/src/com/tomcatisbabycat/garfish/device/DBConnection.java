/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.device;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kang
 */
public class DBConnection {
	private static final Logger LOG = LoggerFactory.getLogger(DBConnection.class);
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@106.253.56.122:1521:orcl";
	String user = "iot1_drone";
	String password = "iot1_drone";
	
	public boolean macAddressChecked(String mac){
		boolean isDrone = false;
		
		try{
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			LOG.info("연결 성공");
			
			String sql = "select * from drone where dmacaddr=?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, mac);
			
			ResultSet rs = preparedStatement.executeQuery();
			
//			while(rs.next()){
//				int a = 0;
//				System.out.println(a);
//				a++;
//			}
			// false - 등록된 드론 없음, true - 등록된 드론 존재
			if(rs.next()){
				isDrone = true;
			}
			
			
			rs.close();
			preparedStatement.close();
			
			LOG.info("조회 성공");
			
		}catch(ClassNotFoundException e){
			LOG.info("로드 오류");
		}catch(SQLException e){
			LOG.info("연결 오류");
		}
		finally{
			try {
				connection.close();
				LOG.info("연결 끊김");
			} catch (SQLException ex) {}

		}
		return isDrone;
	}
	
	public String login(String id, String pw){
		String login = "false";
		
		try{
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			LOG.info("연결 성공");
			
			String sql = "select mpassword from member where mid=?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				if(rs.getString("mpassword").equals(pw)){
					login = "true";
				}
			}
			
			
			rs.close();
			preparedStatement.close();
			
			LOG.info("조회 성공");
			
		}catch(ClassNotFoundException e){
			LOG.info("로드 오류");
		}catch(SQLException e){
			LOG.info("연결 오류");
		}
		finally{
			try {
				connection.close();
				LOG.info("연결 끊김");
			} catch (SQLException ex) {}

		}
		return login;
	}

	public void enrollDrone(String id, String name, String macAddress, int type) {
		try{
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			LOG.info("연결 성공");
			
			String sql = "insert into drone ";
			sql += "( dname, member_mid, dmacaddr, dtype ) ";
			sql += "values ";
			sql += "(?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, macAddress);
			preparedStatement.setInt(4, type);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			LOG.info("조회 성공");
			
		}catch(ClassNotFoundException e){
			LOG.info("로드 오류");
		}catch(SQLException e){
			LOG.info("연결 오류");
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
				LOG.info("연결 끊김");
			} catch (SQLException ex) {}

		}
	}
}
