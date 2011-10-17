package entidades.nclStateMachine;

import java.util.ArrayList;

public class nclStateMachine {
	private int idnclStateMachine;
	private ArrayList<Document> documents;

	public nclStateMachine() {
		setDocuments(new ArrayList<Document>());
	}

	/**
	 * @return the idnclStateMachine
	 */
	public int getIdnclStateMachine() {
		return idnclStateMachine;
	}

	/**
	 * @param idnclStateMachine
	 *            the idnclStateMachine to set
	 */
	public void setIdnclStateMachine(int idnclStateMachine) {
		this.idnclStateMachine = idnclStateMachine;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}
}
