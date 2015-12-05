package fr.enseirb.t3.it340.modeles;

import java.sql.Date;
import java.util.*;

public class Atelier {
	
	private final int idAtelier;
	private final int idLabo;
	private String titre;
	private String themes;
	private String zone;
	private String orateurs;
	private String partenaires;
	private String cibles;
	private String remarques;
	private Map<Integer, Creneau> creneaux = new HashMap<Integer, Creneau>();
	private enum Status {PROPOSE , VALIDE , CLOTURE} ;

	public Atelier(int idAtelier, int idLabo, String titre) {
		this.idAtelier = idAtelier;
		this.idLabo = idLabo;
		this.titre = titre;
	}

	public void ajoutCreneau(Creneau creneau) {
		creneaux.put(creneau.getIdCreneau(), creneau);
	}

	public void modifierCreneau(int idCreneau, Creneau creneau) {
		Creneau c = creneaux.get(idCreneau);
		if (idCreneau == creneau.getIdCreneau() || c == null)
			creneaux.put(idCreneau, creneau);
	}
	
	/*void modifierCreneau(int idCreneau , Date date , int capacite){
		Iterator<Creneau> iterator = creneaux.iterator();
		
		while(iterator.hasNext()){
			
			Creneau creneau = iterator.next();
			
			if(creneau.getIdCreneau() == idCreneau){
			creneau.setCapacite(capacite);
			creneau.setDate(date);
			break;
			}
		}
		
	}*/

	public void supprimerCreneau(int idCreneau) {
		creneaux.remove(idCreneau);
	}
	/*void supprimerCreneau(int idCreneau ){
		Iterator<Creneau> iterator = creneaux.iterator();
		
		while(iterator.hasNext()){
			
			Creneau creneau = iterator.next();
			
			if(creneau.getIdCreneau() == idCreneau){
			iterator.remove();
			break;
			}
		}
		
	}*/
	
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
	public String getCibles() {
		return cibles;
	}
	public void setCibles(String cibles) {
		this.cibles = cibles;
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
	
	
}
