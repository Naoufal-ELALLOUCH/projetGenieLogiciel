package fr.enseirb.t3.it340.modeles;

public class Utilisateur {
	
	final private int idUtilisateur;
	private String email;
	private String motDePasse;
	
	public Utilisateur(int idUtilisateur, String email, String motDePasse) {
		this.idUtilisateur = idUtilisateur;
		this.email = email;
		this.motDePasse = motDePasse;
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
		return motDePasse;
	}

	public void setPassword(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
