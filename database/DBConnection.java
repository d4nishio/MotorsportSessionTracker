package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn=null;
	public static Connection doConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myoop","root","");
		return conn;
	}
	

	public static void main(String[] args) {
		try {
			System.out.println(DBConnection.doConnection());
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}

}