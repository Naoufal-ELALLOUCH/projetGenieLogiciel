package fr.enseirb.t3.it340.modeles;

import java.util.Set;

public class Laboratoire extends Utilisateur {

	final private int idLaboratoire;
	String nom;
	private Set<Atelier> ateliers;
	
	
	public Laboratoire(int idUtilisateur, String email, String password, int idLaboratoire, String nom,
			Set<Atelier> ateliers) {
		super(idUtilisateur, email, password);
		this.idLaboratoire = idLaboratoire;
		this.nom = nom;
		this.ateliers = ateliers;
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
	public Set<Atelier> getAtelier(int idLaboratoire) {	
		return ateliers;
	}

	public void setAtelier(Set<Atelier> atelier) {
		this.ateliers = atelier;
	}
}
