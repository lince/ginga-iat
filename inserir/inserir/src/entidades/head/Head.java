package entidades.head;

import java.util.ArrayList;

public class Head {

	private int idHead;
	private Location location;
	private STBDevice sTBDevice;
	private Usuario usuario;

	/**
	 * @return the idHead
	 */
	public int getIdHead() {
		return idHead;
	}

	/**
	 * @param idHead
	 *            the idHead to set
	 */
	public void setIdHead(int idHead) {
		this.idHead = idHead;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the sTBDevice
	 */
	public STBDevice getsTBDevice() {
		return sTBDevice;
	}

	/**
	 * @param sTBDevice
	 *            the sTBDevice to set
	 */
	public void setsTBDevice(STBDevice sTBDevice) {
		this.sTBDevice = sTBDevice;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
