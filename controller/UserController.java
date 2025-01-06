package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import database.DBConnection;
import model.User;

public class UserController {

	public boolean doLogin(User user) throws ClassNotFoundException, SQLException
	{
		boolean valid = false;
		Connection db = DBConnection.doConnection();
		
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			valid = true;
		
		db.close();
		
		return valid;
	}
	
}
