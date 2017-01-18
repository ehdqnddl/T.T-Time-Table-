package DataAccess;

import java.sql.*;

public class Main {
	public static void main(String args[]) {
		try {
			// Connect
			Connection conn = DBConnector.getConnection();
			Statement st    = conn.createStatement();
			ResultSet rs    = st.executeQuery("SELECT * FROM 2016spr"); // SQL문법   
			
			// 전부 출력
			while(rs.next()){
            	System.out.println("교과목명: " + rs.getString("교과목명") + " 이수구분: "+rs.getString("이수구분"));
            }
			
			// 필드 수 확인
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfFields = rsMetaData.getColumnCount();
			System.out.println("Fields Count: " + numberOfFields);
			
			
			// 뭘 더 할까
			
			
			// Disconnect
			rs.close();
			st.close();
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
