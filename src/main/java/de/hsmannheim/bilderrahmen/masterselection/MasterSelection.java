package de.hsmannheim.bilderrahmen.masterselection;

/**
 * DTO for synchronizing master requests. The object contains the deviceID of the new master.
 */
public class MasterSelection {

	private String deviceId;

	public MasterSelection build(String deviceId){
		this.deviceId = deviceId;
		return this;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
