package fr.enseirb.t3.it340.servlets.creneau;

import fr.enseirb.t3.it340.bdd.BddEnregistrement;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class InscrireCreneau implements Route {


	public String handle(Request request, Response response) throws Exception {
		ModelAndView modelAndView = Authentification.checkEnseignant(request, response);
		if (modelAndView != null)
			return "";

		try {
			Integer idEnseignant = request.session().attribute("enseignant");
			Integer idCreneau = Integer.parseInt(request.params("idCreneau"));
			Integer nbInscrits = Integer.parseInt(request.queryParams("nbInscrits"));

			BddEnregistrement.enregistrement(idEnseignant, idCreneau, nbInscrits);
		} catch (Exception e) {}

		response.redirect("/enseignant");
		return "";
	}
}
