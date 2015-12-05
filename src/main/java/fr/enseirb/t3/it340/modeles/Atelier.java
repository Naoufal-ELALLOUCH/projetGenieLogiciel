package fr.enseirb.t3.it340.modeles;

import java.sql.Date;
import java.util.*;

public class Atelier {
	
	private final int idAtelier;
	private String titre;
	private Set<String> themes = new HashSet<String>();
	private String zone;
	private Set<String> orateurs = new HashSet<String>();
	private Set<String> partenaires = new HashSet<String>();
	private Set<String> cibles = new HashSet<String>();
	private String remarques ;
	private Map<Integer, Creneau> creneaux = new HashMap<Integer, Creneau>();
	private enum Status {PROPOSE , VALIDE , CLOTURE} ;

	public Atelier(int idAtelier, String titre) {
		this.idAtelier = idAtelier;
		this.titre = titre;
	}

	/*public Atelier(String titre, Set<String> themes, String zone, Set<String> orateurs,
			Set<String> partenaires, Set<String> cibles, String remarques, Set<Creneau> creneaux) {
		this.titre = titre;
		this.themes = themes;
		this.zone = zone;
		this.orateurs = orateurs;
		this.partenaires = partenaires;
		this.cibles = cibles;
		this.remarques = remarques;
		this.creneaux = creneaux;
	}*/

	public void ajoutCreneau(Creneau creneau) {
		creneaux.put(creneau.getIdCreneau(), creneau);
	}
	
	/*void ajoutCreneau(Date date, int capacite) {
		int id = creneaux.size() + 1;
		Creneau creneau = new Creneau(id, date , capacite);
		creneaux.add(creneau);
	}*/

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

	public Map<Integer, Creneau> getCreneaux() {
		return creneaux;
	}
	
	
}
