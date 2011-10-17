package entidades.head;

public class SocialNetwork {

	private int idSocialNetwork;
	private SocialNetworkName name;
	private String identification;

	public void setName(SocialNetworkName name) {
		this.name = name;
	}

	public SocialNetworkName getName() {
		return name;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdSocialNetwork(int idSocialNetwork) {
		this.idSocialNetwork = idSocialNetwork;
	}

	public int getIdSocialNetwork() {
		return idSocialNetwork;
	}

}
