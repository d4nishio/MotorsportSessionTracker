package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import database.DBConnection;
import model.Timing;



public class TimingController {

	public List<Timing> viewTiming(int circuitID,int type,int setupID) throws ClassNotFoundException, SQLException
	{
		 List<Timing> timings = new ArrayList<>();
		 String tempTime="0:00:000";
		 int count = 0;
		 String sign = "";
		 Duration gapCalc;
		 String sql;
		 PreparedStatement preparedStatement;
		 
		Connection db = DBConnection.doConnection();
		
		if(setupID == 0)
		{
			if(type == 0)
			{
				sql = "SELECT * FROM timing WHERE circuitID = ? ORDER BY lapNumber ASC";
				
			}
			else
			{
				sql = "SELECT * FROM timing WHERE circuitID = ? ORDER BY time ASC";
			}
			preparedStatement = db.prepareStatement(sql);
			preparedStatement.setInt(1, circuitID);
		}
		else
		{
			if(type == 0)
			{
				sql = "SELECT * FROM timing WHERE circuitID = ? AND setupID = ? ORDER BY lapNumber ASC";
				
			}
			else
			{
				sql = "SELECT * FROM timing WHERE circuitID = ? AND setupID = ? ORDER BY time ASC";
			}
			preparedStatement = db.prepareStatement(sql);
			preparedStatement.setInt(1, circuitID);
			preparedStatement.setInt(2, setupID);
		}
		
		
		ResultSet resultSet = preparedStatement.executeQuery();
	
		
		
		while (resultSet.next()) 
		{
			
			
            int timingID = resultSet.getInt("timingID");
            int lapNumber = resultSet.getInt("lapNumber");
            String time = resultSet.getString("time");
            double averageSpeed = resultSet.getDouble("speed");
            setupID = resultSet.getInt("setupID");
     
            Duration time1 = parseTimeString(tempTime);
            Duration time2 = parseTimeString(time);
            
            
            if (time2.compareTo(time1)>0)
            {
                gapCalc = time2.minus(time1);
                sign = "+";
                
            }
            else
            {
            	gapCalc = time1.minus(time2);
            	sign = "-";
            }
            
            String gap = convertDurationToSecondsMillisString(gapCalc);
          
          gap = sign + gap;

            tempTime = time;
            
            if(count==0)
            	gap = "+0:000";
            
            Timing timing = new Timing();
            timing.setTimingID(timingID);
            timing.setLapNumber(lapNumber);
            timing.setTime(time);
            timing.setAverageSpeed(averageSpeed);
            timing.setSetupID(setupID);
            timing.setGap(gap);
            
            timings.add(timing);
            count++;
        }
		
		db.close();
		
		return timings;
	}
	
	private static Duration parseTimeString(String timeStr) {
        String[] parts = timeStr.split(":");
        long minutes = Long.parseLong(parts[0]);
        long seconds = Long.parseLong(parts[1]);
        long milliseconds = Long.parseLong(parts[2]);
        
        return Duration.ofMinutes(minutes).plus(Duration.ofSeconds(seconds, milliseconds * 1_000_000));
    }

	private static String convertDurationToSecondsMillisString(Duration duration) {
        // Get seconds and milliseconds from the duration
        long seconds = duration.getSeconds();
        long milliseconds = duration.toMillis() % 1000;
        
 
        // Format the result as seconds:milliseconds
        return String.format("%d.%03d", seconds, milliseconds);
    }
	
	public void insertTiming(Timing timing,int circuitID) throws ClassNotFoundException, SQLException
	{
		
		Connection db = DBConnection.doConnection();
		
		String sql = "INSERT INTO timing (lapNumber,time,speed,setupID,circuitID) VALUES (?,?,?,?,?)";
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		preparedStatement.setInt(1, timing.getLapNumber());
		preparedStatement.setString(2, timing.getTime());
		preparedStatement.setDouble(3, timing.getAverageSpeed());
		preparedStatement.setInt(4, timing.getSetupID());
		preparedStatement.setInt(5, circuitID);
	
		preparedStatement.executeUpdate();

		db.close();
		
	}
	
	
	public void updateTiming(Timing timing) throws ClassNotFoundException, SQLException
	{
		
		Connection db = DBConnection.doConnection();
		
		String sql = "UPDATE timing SET lapNumber = ?, time = ?,speed = ?,setupID = ? WHERE timingID = ?";
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		preparedStatement.setInt(1, timing.getLapNumber());
		preparedStatement.setString(2, timing.getTime());
		preparedStatement.setDouble(3, timing.getAverageSpeed());
		preparedStatement.setInt(4, timing.getSetupID());
		preparedStatement.setInt(5, timing.getTimingID());
		
		preparedStatement.executeUpdate();

		db.close();
		
	}
	
	public void deleteTiming(Timing timing) throws ClassNotFoundException, SQLException
	{
		
		Connection db = DBConnection.doConnection();
		
		String sql = "DELETE from timing WHERE timingID = ?";
		PreparedStatement preparedStatement = db.prepareStatement(sql);
		preparedStatement.setInt(1, timing.getTimingID());
		
		preparedStatement.executeUpdate();

		db.close();
		
	}
	
	
	
}