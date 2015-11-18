package fr.enseirb.t3.it340.servlets.authentification;

import fr.enseirb.t3.it340.bdd.BddConnecteur;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.sql.SQLException;

public class Authentification implements Route {

	private final Logger log = LoggerFactory.getLogger(Authentification.class);

	public boolean checkAuthentication(String email, String motDePasse) {
		return new BddUtilisateur().authentification(email, motDePasse);
	}

	public Object handle(Request request, Response response) throws Exception {

		if (request.session().isNew()) {
			String email = request.queryParams("email");
			String motDePasse = request.queryParams("motDePasse");

			if (checkAuthentication(email, motDePasse)) {
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