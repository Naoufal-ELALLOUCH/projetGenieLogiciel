package fr.enseirb.t3.it340.modeles;

import java.util.Date;

public class Creneau {
	int idCreneau;
	int capacite;
	Date date;
	
	public Creneau(Date date , int capacite){
		this.date = date;
		this.capacite = capacite;
	}
	
	public Creneau (int idCreneau , Date date, int capacite) {
		this.idCreneau = idCreneau;
		this.date= date; 
		this.capacite=capacite;	
	}
	public int getIdCreneau() {
		return idCreneau;
	}

	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
