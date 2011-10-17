package entidades.head;

import java.util.ArrayList;

public class Resource {

	private int idResource;
	private String type;
	private ArrayList<Information> informations;

	public Resource() {
		informations = new ArrayList<Information>();
	}

	/**
	 * @return the idResource
	 */
	public int getIdResource() {
		return idResource;
	}

	/**
	 * @param idResource
	 *            the idResource to set
	 */
	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}

	/**
	 * @return the informations
	 */
	public ArrayList<Information> getInformations() {
		return informations;
	}

	/**
	 * @param informations
	 *            the informations to set
	 */
	public void setInformations(ArrayList<Information> informations) {
		this.informations = informations;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
