package model;

public class Setup {

	private int setupID;
	private int frontWing;
	private int rearWing;
	private int gearRatio;
	private int suspension;
	
	public Setup()
	{}

	public int getSetupID() {
		return setupID;
	}

	public void setSetupID(int setupID) {
		this.setupID = setupID;
	}

	public int getFrontWing() {
		return frontWing;
	}

	public void setFrontWing(int frontWing) {
		this.frontWing = frontWing;
	}

	public int getRearWing() {
		return rearWing;
	}

	public void setRearWing(int rearWing) {
		this.rearWing = rearWing;
	}

	public int getGearRatio() {
		return gearRatio;
	}

	public void setGearRatio(int gearRatio) {
		this.gearRatio = gearRatio;
	}

	public int getSuspension() {
		return suspension;
	}

	public void setSuspension(int suspension) {
		this.suspension = suspension;
	}
	
	
}
