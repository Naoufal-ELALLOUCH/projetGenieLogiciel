package fr.enseirb.t3.it340.modeles;

public class Enregistrement {
	public int getNbInscrits() {
		return nbInscrits;
	}

	public Atelier getAtelier() {
		return atelier;
	}

	public String getDate() {
		return date;
	}

	private final int nbInscrits;
	private final Atelier atelier;
	private final String date;

	public Enregistrement(int nbInscrits, Atelier atelier, String date) {
		this.nbInscrits = nbInscrits;
		this.atelier = atelier;
		this.date = date;
	}
}
