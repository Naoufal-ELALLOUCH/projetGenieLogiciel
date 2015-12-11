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

public class Authentification implements Route {

	private final Logger log = LoggerFactory.getLogger(Authentification.class);

	private static void erreurIdentifiants(Request request, Response response) {
		request.session().attribute("erreur", "Identifiants invalides");
		response.redirect("/authentification");
	}

	public static boolean checkLoggedIn(Request request, Response response) {
		String email = request.session().attribute("email");
		if (email == null) {
			erreurIdentifiants(request, response);
			return false;
		}
		return true;
	}

	public Object handle(Request request, Response response) throws Exception {

		if (request.session().isNew()) {
			request.session(true);
		}

		String email = request.queryParams("email");
		String motDePasse = request.queryParams("motDePasse");

		boolean identifiantsOk = new BddUtilisateur().authentification(email, motDePasse);

		if (identifiantsOk) {

			Utilisateur utilisateur = BddUtilisateur.getUtilisateurByEmail(email);
			int idUtilisateur = utilisateur.getIdUtilisateur();

			// Permet de savoir dans une session si l'utilisateur est un laboratoire ou un enseignant
			if (BddLabo.isLabo(idUtilisateur)) {
				request.session().attribute("labo", "true");
				log.info("L'utilisateur {} est un laboratoire", idUtilisateur);
			} else if (BddEnseignant.isEnseignant(idUtilisateur)) {
				request.session().attribute("enseignant", "true");
				log.info("L'utilisateur {} est un enseignant", idUtilisateur);
			}

			request.session().attribute("email", email);
			log.info("{} s'est connecté avec succès", request.session().attribute("email"));

			// TODO la bonne redirection


		} else {
			log.warn("{} a essayé de se connecter avec un mauvais mot de passe", email);
			erreurIdentifiants(request, response);
		}


		return null;
	}
}