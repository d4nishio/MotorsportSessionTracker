package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import database.DBConnection;
import model.Setup;
import model.Timing;

public class SetupController {

	
	public List<Setup> viewSetup() throws ClassNotFoundException, SQLException
	{
	    List<Setup> setups = new ArrayList<>();
	        
		Connection db = DBConnection.doConnection();
		
		String sql = "SELECT * FROM setup";
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
            int setupID = resultSet.getInt("setupID");
            int frontWing = resultSet.getInt("frontWing");
            int rearWing = resultSet.getInt("rearWing");
            int gearRatio = resultSet.getInt("gearRatio");
            int suspension = resultSet.getInt("suspension");

            Setup setup = new Setup();
            setup.setSetupID(setupID);
            setup.setFrontWing(frontWing);
            setup.setRearWing(rearWing);
            setup.setGearRatio(gearRatio);
            setup.setSuspension(suspension);
            
            setups.add(setup);
        }
		
		db.close();
		
		return setups;
	}
	
	 public void insertSetup(Setup setup)  throws ClassNotFoundException, SQLException
	    {
	        String sql = "INSERT INTO setup (frontWing,rearWing, gearRatio,suspension) VALUES (?, ?, ?, ?)";
	       
	        Connection db = DBConnection.doConnection();
	        PreparedStatement preparedStatement = db.prepareStatement(sql);
	        
	        preparedStatement.setInt(1, setup.getFrontWing());
	        preparedStatement.setInt(2, setup.getRearWing());
	        preparedStatement.setInt(3, setup.getGearRatio());
	        preparedStatement.setInt(4, setup.getSuspension());
	        preparedStatement.executeUpdate();
	        
	        db.close();
	    }
	 
	 
	 public void deleteSetup(Setup setup) throws ClassNotFoundException, SQLException
		{
			
			Connection db = DBConnection.doConnection();
			
			String sql = "DELETE from setup WHERE setupID = ?";
			PreparedStatement preparedStatement = db.prepareStatement(sql);
			preparedStatement.setInt(1, setup.getSetupID());
			
			preparedStatement.executeUpdate();

			db.close();
			
		}
}
