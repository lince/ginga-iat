package entidades.head;

import java.util.ArrayList;

public class Device {

	private int idDevice;
	private String serialNumber;
	private String type;
	private String profile;
	private ArrayList<Resource> resources;

	public Device() {
		setResources(new ArrayList<Resource>());
	}

	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public int getIdDevice() {
		return idDevice;
	}

}
