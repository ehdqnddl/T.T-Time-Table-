package DataAccess;

import java.sql.*;

public class Main {
	public static void main(String args[]) {
		try {
			// Connect
			Connection conn = DBConnector.getConnection();
			Statement st    = conn.createStatement();
			ResultSet rs    = st.executeQuery("SELECT * FROM 2016spr"); // SQL����   
			
			// ���� ���
			while(rs.next()){
            	System.out.println("�������: " + rs.getString("�������") + " �̼�����: "+rs.getString("�̼�����"));
            }
			
			// �ʵ� �� Ȯ��
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfFields = rsMetaData.getColumnCount();
			System.out.println("Fields Count: " + numberOfFields);
			
			
			// �� �� �ұ�
			
			
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
