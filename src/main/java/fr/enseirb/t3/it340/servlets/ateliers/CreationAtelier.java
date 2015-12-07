package fr.enseirb.t3.it340.servlets.ateliers;

import spark.Request;
import spark.Response;
import spark.Route;

public class CreationAtelier implements Route {

	public Void handle(Request request, Response response) throws Exception {


		// TODO récupérer l'id et rediriger vers la page de l'atelier
		response.redirect("/toto");

		return null;
	}
}
