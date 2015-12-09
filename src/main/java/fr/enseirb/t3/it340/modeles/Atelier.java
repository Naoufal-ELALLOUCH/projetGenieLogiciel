package fr.enseirb.t3.it340.modeles;

import java.sql.Date;
import java.util.*;

public class Atelier {
	
	private final int idAtelier;
	private final int idLabo;
	private String titre = "";
	private String themes = "";
	private String zone = "";
	private String adresse = "";
	private String orateurs = "";
	private String partenaires = "";
	private String cible = "";
	private String remarques = "";
	private Map<Integer, Creneau> creneaux = new HashMap<Integer, Creneau>();
	private String statut = "";
	
	//private enum Statut {PROPOSE , VALIDE , CLOTURE} ;

	public Atelier(int idAtelier, int idLabo, String titre){
		this.idAtelier = idAtelier;
		this.idLabo = idLabo;
		this.titre = titre;
	}
	
	public Atelier(int idAtelier, int idLabo, String titre, String themes, String zone, String adresse, String orateurs, String partenaires, String cible, String remarques, Map<Integer, Creneau> creneaux, String statut) {
		this.idAtelier = idAtelier;
		this.idLabo = idLabo;
		this.titre = titre;
		this.themes = themes;
		this.zone = zone;
		this.adresse = adresse;
		this.orateurs = orateurs;
		this.partenaires = partenaires;
		this.cible = cible;
		this.remarques = remarques;
		this.creneaux = creneaux;
		this.statut = statut;
	}

	public void ajoutCreneau(Creneau creneau) {
		creneaux.put(creneau.getIdCreneau(), creneau);
	}

	public void modifierCreneau(int idCreneau, Creneau creneau) {
		Creneau c = creneaux.get(idCreneau);
		if (idCreneau == creneau.getIdCreneau() || c == null)
			creneaux.put(idCreneau, creneau);
	}

	public void supprimerCreneau(int idCreneau) {
		creneaux.remove(idCreneau);
	}
	
	
	public int getIdAtelier() {
		return idAtelier;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getThemes() {
		return themes;
	}
	public void setThemes(String themes) {
		this.themes = themes;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getOrateurs() {
		return orateurs;
	}
	public void setOrateurs(String orateurs) {
		this.orateurs = orateurs;
	}
	public String getPartenaires() {
		return partenaires;
	}
	public void setPartenaires(String partenaires) {
		this.partenaires = partenaires;
	}
	public String getCible() {
		return cible;
	}
	public void setCibles(String cible) {
		this.cible = cible;
	}
	public String getRemarques() {
		return remarques;
	}
	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public Map<Integer, Creneau> getCreneaux() {
		return creneaux;
	}
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getStatut() {
		return statut;
	}

	public int getIdLabo() {
		return idLabo;
	}
	
}
