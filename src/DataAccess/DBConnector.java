package DataAccess;

import java.sql.*;

public class DBConnector {
	public static Connection getConnection() {
		String driver   = "net.ucanaccess.jdbc.UcanaccessDriver";
		String url      = "jdbc:ucanaccess://C:/GraduateIsMine/allcourse.accdb"; //allcourse.mdb is the database
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
