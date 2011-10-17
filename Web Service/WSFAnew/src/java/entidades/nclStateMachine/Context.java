package entidades.nclStateMachine;

import java.util.ArrayList;

public class Context {

	private int idContext;
	private String id;
	private ArrayList<Context> contexts;
	private ArrayList<Media> medias;

	public Context() {
		contexts = new ArrayList<Context>();
		medias = new ArrayList<Media>();
	}

	/**
	 * @return the idContext
	 */
	public int getIdContext() {
		return idContext;
	}

	/**
	 * @param idContext
	 *            the idContext to set
	 */
	public void setIdContext(int idContext) {
		this.idContext = idContext;
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

	public void setContexts(ArrayList<Context> contexts) {
		this.contexts = contexts;
	}

	public ArrayList<Context> getContexts() {
		return contexts;
	}

	public void setMedias(ArrayList<Media> medias) {
		this.medias = medias;
	}

	public ArrayList<Media> getMedias() {
		return medias;
	}

}
