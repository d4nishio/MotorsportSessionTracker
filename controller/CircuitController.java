package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import database.DBConnection;
import model.Circuit;

public class CircuitController {
	
	public List<Circuit> viewCircuit() throws ClassNotFoundException, SQLException
	{
	    List<Circuit> circuits = new ArrayList<>();
	        
		Connection db = DBConnection.doConnection();
		
		String sql = "SELECT * FROM circuit";
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
            int circuitID = resultSet.getInt("circuitID");
            String name = resultSet.getString("name");

            Circuit circuit = new Circuit();
            circuit.setCircuitID(circuitID);
            circuit.setName(name);
            
            circuits.add(circuit);
        }
		
		db.close();
		
		return circuits;
	}
	
	public String selectCircuit(int circuitID) throws ClassNotFoundException, SQLException
	{
		String  name = "";
		
		Connection db = DBConnection.doConnection();
		
		
		String sql = "SELECT * FROM circuit where circuitID = ?";
		
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		preparedStatement.setInt(1, circuitID);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
            
           name = resultSet.getString("name");
        }
		
		db.close();
		return name;
		
	}
	
	

}
