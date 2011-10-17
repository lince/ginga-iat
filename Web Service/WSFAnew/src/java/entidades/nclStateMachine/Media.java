package entidades.nclStateMachine;

import java.sql.Time;
import java.util.ArrayList;

public class Media {

	private int idMedia;
	private String id;
	private String status;
	private Time time;
	private ArrayList<Property> properties;
	private ArrayList<Interface> interfaces;

	public Media() {
		properties = new ArrayList<Property>();
		interfaces = new ArrayList<Interface>();
	}

	/**
	 * @return the idMedia
	 */
	public int getIdMedia() {
		return idMedia;
	}

	/**
	 * @param idMedia
	 *            the idMedia to set
	 */
	public void setIdMedia(int idMedia) {
		this.idMedia = idMedia;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setInterfaces(ArrayList<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public ArrayList<Interface> getInterfaces() {
		return interfaces;
	}

}
