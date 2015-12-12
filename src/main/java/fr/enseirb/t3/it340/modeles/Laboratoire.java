package fr.enseirb.t3.it340.modeles;

import java.util.HashMap;
import java.util.Map;

public class Laboratoire extends Utilisateur {

	final private int idLaboratoire;
	private String nom;
	private Map<Integer, Atelier> ateliers = new HashMap<Integer, Atelier>();

	public Laboratoire(int idUtilisateur, String email, String password, int idLaboratoire, String nom, Map<Integer, Atelier> ateliers) {
		super(idUtilisateur, email, password);
		this.idLaboratoire = idLaboratoire;
		this.nom = nom;

		if (ateliers != null)
			this.ateliers.putAll(ateliers);
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

	public Map<Integer, Atelier> getAteliers() {
		return ateliers;
	}

	public void addAtelier(Atelier atelier) {
		ateliers.put(atelier.getIdAtelier(), atelier);
	}

	public void removeAtelier(int idAtelier) {
		ateliers.remove(idAtelier);
	}
}
