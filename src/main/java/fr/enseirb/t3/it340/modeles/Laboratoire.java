package fr.enseirb.t3.it340.modeles;


public class Laboratoire extends Utilisateur {

	private int idLaboratoire;
	
	public Laboratoire(int idLaboratoire , Utilisateur utilisateur){
		super(utilisateur.getIdUtilisateur() , utilisateur.getEmail() , utilisateur.getPassword());
		this.idLaboratoire = idLaboratoire;
	}
	
	
	public int getIdLaboratoire() {
		return idLaboratoire;
	}
	public void setIdLaboratoire(int idLaboratoire) {
		this.idLaboratoire = idLaboratoire;
	}

}
