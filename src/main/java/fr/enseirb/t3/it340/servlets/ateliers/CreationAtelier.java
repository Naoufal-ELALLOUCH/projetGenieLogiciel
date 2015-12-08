package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreationAtelier implements Route {

	public Void handle(Request request, Response response) throws Exception {

		String idLaboString = request.session().attribute("idLabo");
		int idLabo = Integer.parseInt(idLaboString);

		String titre = request.queryParams("titre");
		String themes = request.queryParams("themes");
		String zone = request.queryParams("zone");
		String adresse = request.queryParams("adresse");
		String orateurs = request.queryParams("orateurs");
		String partenaires = request.queryParams("partenaires");
		String cible = request.queryParams("cible");
		String remarques = request.queryParams("remarques");

		BddAtelier.ajoutAtelier(idLabo, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques);

		response.redirect("/ateliers/" + idLaboString);

		return null;
	}
}
