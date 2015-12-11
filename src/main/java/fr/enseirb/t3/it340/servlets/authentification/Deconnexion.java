package fr.enseirb.t3.it340.servlets.authentification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public class Deconnexion implements Route {

	private final Logger log = LoggerFactory.getLogger(Deconnexion.class);

	public Void handle(Request request, Response response) throws Exception {

		String email = request.session().attribute("email");

		if (email != null) {
			for (String attribute : request.session().attributes()) {
				request.session().removeAttribute(attribute);
			}

			log.info("{} s'est déconnecté avec succès", email);
		}

		response.redirect("/");
		return null;
	}
}
