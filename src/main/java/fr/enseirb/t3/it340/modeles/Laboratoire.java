package fr.enseirb.t3.it340.modeles;


public class Laboratoire extends Utilisateur {

	final private int idLaboratoire;
	
	public Laboratoire(int idUtilisateur, int idLaboratoire, String email, String password) {
		super(idUtilisateur, email, password);
		this.idLaboratoire = idLaboratoire;
	}
	
	public int getIdLaboratoire() {
		return idLaboratoire;
	}

}
