package fr.enseirb.t3.it340.modeles;


public class Laboratoire extends Utilisateur {

	final private int idLaboratoire;
	String nom;
	public Laboratoire(int idUtilisateur, int idLaboratoire, String email, String password) {
		super(idUtilisateur, email, password);
		this.idLaboratoire = idLaboratoire;
	}
	
	public int getIdLaboratoire() {
		return idLaboratoire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
