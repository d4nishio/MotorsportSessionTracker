package model;

public class Timing {

	private int timingID;
	private int lapNumber;
	private String time;
	private String gap;
	private double averageSpeed;
	private int setupID;
	private int circuitID;

	
	public Timing()
	{
		
	}
	
	public int getTimingID() {
		return timingID;
	}
	public void setTimingID(int timingID) {
		this.timingID = timingID;
	}
	public int getLapNumber() {
		return lapNumber;
	}
	public void setLapNumber(int lapNumber) {
		this.lapNumber = lapNumber;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGap() {
		return gap;
	}
	public void setGap(String gap) {
		this.gap = gap;
	}
	public double getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public int getSetupID() {
		return setupID;
	}
	public void setSetupID(int setupID) {
		this.setupID = setupID;
	}

	public int getCircuitID() {
		return circuitID;
	}

	public void setCircuitID(int circuitID) {
		this.circuitID = circuitID;
	}
	
	
	
	
}
