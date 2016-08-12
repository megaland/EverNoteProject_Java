package com.usnschool.evernoteproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Main {


Main (){
	DBConnector dbconnector = new DBConnector();
}

	
}




class DBConnector {
	Connection con ;
	public DBConnector() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "psh";
			String password = "1234";
			
			con = DriverManager.getConnection(url, user, password);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertFile(){
		String sql = "insert into filetable values (filetable_seq.nextval, ?)";
		byte[] buffer = new byte[5];
		buffer[0] = 4;
		buffer[1] = 5;
		buffer[2] = 6;
		buffer[3] = 7;
		buffer[4] = 8;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setBytes(1, buffer);
			psmt.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(psmt !=null){
				try {
					psmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public void showFile(){
		String sql = "select * from filetable";
		byte[] buffer = new byte[5];
		ResultSet rs = null;
		try {
			
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			
			rs.next();
		
			buffer = rs.getBytes(2);
			for (int i = 0; i < buffer.length; i++) {
				System.out.println(buffer[i]);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
