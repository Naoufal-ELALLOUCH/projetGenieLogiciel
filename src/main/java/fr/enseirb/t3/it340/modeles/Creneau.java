package fr.enseirb.t3.it340.modeles;

import java.util.Date;

public class Creneau {
	private final int idCreneau;
	private int capacite;
	private Date date;
	
	public Creneau (int idCreneau , Date date, int capacite) {
		this.idCreneau = idCreneau;
		this.date = date;
		this.capacite = capacite;
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
