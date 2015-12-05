package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.modeles.Atelier;
import spark.Request;
import spark.Response;
import spark.Route;

public class VisualisationAtelier implements Route {

	public Object handle(Request request, Response response) throws Exception {
		String idAtelier = request.params("idAtelier");
		//Atelier atelier = new BddAtelier.getAtelier(idAtelier)
		// TODO
		return null;
	}
}
