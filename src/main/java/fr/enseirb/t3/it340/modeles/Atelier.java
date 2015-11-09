package fr.enseirb.t3.it340.modeles;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Atelier {
	
	private int idAtelier;
	String titre;
	Set<String> themes;
	String zone;
	Set<String> orateurs;
	Set<String> partenaires;
	Set<String> cibles;
	String remarques; 
	private ArrayList<Creneau> myList = new ArrayList<Creneau>();
	
	public int getIdAtelier() {
		return idAtelier;
	}
	public void setIdAtelier(int idAtelier) {
		this.idAtelier = idAtelier;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Set<String> getThemes() {
		return themes;
	}
	public void setThemes(Set<String> themes) {
		this.themes = themes;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public Set<String> getOrateurs() {
		return orateurs;
	}
	public void setOrateurs(Set<String> orateurs) {
		this.orateurs = orateurs;
	}
	public Set<String> getPartenaires() {
		return partenaires;
	}
	public void setPartenaires(Set<String> partenaires) {
		this.partenaires = partenaires;
	}
	public Set<String> getCibles() {
		return cibles;
	}
	public void setCibles(Set<String> cibles) {
		this.cibles = cibles;
	}
	public String getRemarques() {
		return remarques;
	}
	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}	
	
	
}
