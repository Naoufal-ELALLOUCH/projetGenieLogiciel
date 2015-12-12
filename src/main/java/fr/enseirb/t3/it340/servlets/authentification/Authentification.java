package fr.enseirb.t3.it340.servlets.authentification;

import fr.enseirb.t3.it340.bdd.BddEnseignant;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.modeles.Utilisateur;
import fr.enseirb.t3.it340.servlets.VisualisationAccueil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class Authentification implements Route {

	private final Logger log = LoggerFactory.getLogger(Authentification.class);

	private static void erreurIdentifiants(Request request, Response response) {
		request.session().attribute("erreur", true);
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

	public static ModelAndView checkLabo(Request request, Response response) throws Exception {
		boolean access;
		boolean access1 = true;
		boolean access2 = true;

		// On regarde si l'utilisateur a accès
		access1 &= Authentification.checkLoggedIn(request, response);
		access2 &= (request.session().attribute("labo") != null);
		access = access1 & access2;

		if (!access) {
			request.session().attribute("erreur", true);
			if (access1)
				response.redirect("/");
			return new VisualisationAccueil().handle(request, response);
		}

		return null;
	}

	public Object handle(Request request, Response response) throws Exception {

		if (request.session().isNew()) {
			request.session(true);
		}

		if (request.session().attribute("email") == null) {

			String email = request.queryParams("email");
			String motDePasse = request.queryParams("motDePasse");

			boolean identifiantsOk = new BddUtilisateur().authentification(email, motDePasse);

			if (identifiantsOk) {

				Utilisateur utilisateur = BddUtilisateur.getUtilisateurByEmail(email);
				int idUtilisateur = utilisateur.getIdUtilisateur();

				// Permet de savoir dans une session si l'utilisateur est un laboratoire ou un enseignant
				if (BddLabo.isLabo(idUtilisateur)) {
					Laboratoire labo = BddLabo.getLaboByIdUtilisateur(idUtilisateur);
					request.session().attribute("labo", labo.getIdLaboratoire());
					log.info("L'utilisateur {} est un laboratoire", idUtilisateur);
				} else if (BddEnseignant.isEnseignant(idUtilisateur)) {
					request.session().attribute("enseignant", "true");
					log.info("L'utilisateur {} est un enseignant", idUtilisateur);
				}

				request.session().attribute("email", email);
				log.info("{} s'est connecté avec succès", request.session().attribute("email"));

				// TODO la bonne redirection
				response.redirect("/laboratoire/ateliers");


			} else {
				log.warn("{} a essayé de se connecter avec un mauvais mot de passe", email);
				erreurIdentifiants(request, response);
			}
		} else {
			// TODO la bonne rediction
			response.redirect("/laboratoire/ateliers");
		}


		return null;
	}
}