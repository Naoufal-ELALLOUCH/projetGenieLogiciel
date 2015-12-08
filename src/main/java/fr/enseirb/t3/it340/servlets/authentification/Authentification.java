package fr.enseirb.t3.it340.servlets.authentification;

import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class Authentification implements Route {

	private final Logger log = LoggerFactory.getLogger(Authentification.class);

	public static void checkAuthentication(Request request, Response response) {
		String email = request.session().attribute("email");
		if (email == null)
			response.redirect("/authentification");
	}

	public Object handle(Request request, Response response) throws Exception {

		if (request.session().isNew()) {
			String email = request.queryParams("email");
			String motDePasse = request.queryParams("motDePasse");

			boolean identifiantsOk = new BddUtilisateur().authentification(email, motDePasse);

			if (identifiantsOk) {
				request.session().attribute("email", email);
			} else {
				// TODO :  message d'erreur
			}

		} else {

			response.redirect("/laboratoire/ateliers");

		}

		return null;
	}
}