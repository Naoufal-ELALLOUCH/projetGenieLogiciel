package fr.enseirb.t3.it340.servlets.authentification;

import fr.enseirb.t3.it340.bdd.BddEnseignant;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import fr.enseirb.t3.it340.modeles.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class Inscription implements Route {

	private final Logger log = LoggerFactory.getLogger(Inscription.class);

	public Void handle(Request request, Response response) throws Exception {

		String nom = request.queryParams("nom");
		String prenom = request.queryParams("prenom");
		String email = request.queryParams("email");
		String motDePasse = request.queryParams("motDePasse");
		String type = request.queryParams("type");

		BddUtilisateur.ajout(email, motDePasse);
		Utilisateur utilisateur = BddUtilisateur.getUtilisateurByEmail(email);
		int idUtilisateur = utilisateur.getIdUtilisateur();

		if (type.equals("labo"))
			BddLabo.ajout(idUtilisateur, nom);
		else
			BddEnseignant.ajout(idUtilisateur, nom, prenom);

		return null;
	}
}
