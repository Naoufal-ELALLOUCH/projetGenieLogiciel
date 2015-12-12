package fr.enseirb.t3.it340.modeles;

public class Enseignant {
	final private int idEnseignant;
	private int idUtilisateur;
	private String nom;
	private String prenom;
	
	public Enseignant(int idEnseignant, int idUtilisateur, String nom,
			String prenom) {
		super();
		this.idEnseignant = idEnseignant;
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getIdEnseignant() {
		return idEnseignant;
	}
	
}
