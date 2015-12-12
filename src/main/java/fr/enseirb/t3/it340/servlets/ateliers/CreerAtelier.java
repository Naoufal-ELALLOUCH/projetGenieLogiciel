package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreerAtelier implements Route {

	public Void handle(Request request, Response response) throws Exception {

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return null;

		int idLabo = request.session().attribute("labo");

		String titre = request.queryParams("titre");
		String themes = request.queryParams("themes");
		String zone = request.queryParams("zone");
		String adresse = request.queryParams("adresse");
		String orateurs = request.queryParams("orateurs");
		String partenaires = request.queryParams("partenaires");
		String cible = request.queryParams("cible");
		String remarques = request.queryParams("remarques");

		BddAtelier.ajoutAtelier(idLabo, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques);

		response.redirect("/laboratoire/ateliers");

		return null;
	}
}
