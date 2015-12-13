package fr.enseirb.t3.it340.servlets.atelier;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddCreneau;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class SupprimerAtelier implements Route {
	public String handle(Request request, Response response) throws Exception {

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return null;

		// On vérifie si l'atelier appartient bien au labo
		Atelier atelier = null;

		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			atelier = BddAtelier.getAtelierById(idAtelier);
		} catch (NumberFormatException e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier == null) {
			response.redirect("/laboratoire/ateliers");
			return "";
		} else {

			// On vérifie que l'atelier appartient bien au labo
			int idLaboFromAtelier = atelier.getIdLabo();
			int idLabo = request.session().attribute("labo");

			if (idLabo != idLaboFromAtelier) {
				return "";
			} else {
				// TODO
			}
		}

		return "";
	}
}
