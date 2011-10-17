package entidades.head;

import java.util.ArrayList;

public class STBDevice {

	private int idSTB_Device;
	private String profile;
	private String type;
	private String serialNumber;
	private ArrayList<Device> devices;
	private ArrayList<Resource> resources;

	public STBDevice() {
		devices = new ArrayList<Device>();
		resources = new ArrayList<Resource>();
	}

	/**
	 * @return the idSTB_Device
	 */
	public int getIdSTB_Device() {
		return idSTB_Device;
	}

	/**
	 * @param idSTB_Device
	 *            the idSTB_Device to set
	 */
	public void setIdSTB_Device(int idSTB_Device) {
		this.idSTB_Device = idSTB_Device;
	}

	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the devices
	 */
	public ArrayList<Device> getDevices() {
		return devices;
	}

	/**
	 * @param devices
	 *            the devices to set
	 */
	public void setDevices(ArrayList<Device> devices) {
		this.devices = devices;
	}

	/**
	 * @return the resources
	 */
	public ArrayList<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
