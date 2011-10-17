package entidades.head;

import java.sql.Date;
import java.util.ArrayList;

public class Usuario {
	private int idUsuario;
	private char genre;
	private Date birth;
	private String identification;
	private ArrayList<SocialNetwork> socialNetworks;

	public Usuario() {
		socialNetworks = new ArrayList<SocialNetwork>();
	}

	public void setSocialNetworks(ArrayList<SocialNetwork> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}

	public ArrayList<SocialNetwork> getSocialNetworks() {
		return socialNetworks;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getIdentification() {
		return identification;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getBirth() {
		return birth;
	}

	public void setGenre(char genre) {
		this.genre = genre;
	}

	public char getGenre() {
		return genre;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
}
