package fr.enseirb.t3.it340.modeles;

public class Utilisateur {
	
	final private int idUtilisateur;
	private String email;
	private String password;
	
	public Utilisateur(int idUtilisateur, String email, String password) {
		this.idUtilisateur = idUtilisateur;
		this.email = email;
		this.password = password;
	}
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
