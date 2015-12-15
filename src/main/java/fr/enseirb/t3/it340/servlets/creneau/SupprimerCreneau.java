package fr.enseirb.t3.it340.servlets.creneau;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddCreneau;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class SupprimerCreneau implements Route {
	public String handle(Request request, Response response) throws Exception {

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return null;

		Atelier atelier = null;
		int idCreneau = 0;

		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			idCreneau = Integer.parseInt(request.params("idCreneau"));
			atelier = BddAtelier.getAtelierById(idAtelier);
		} catch (NumberFormatException e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier == null || idCreneau == 0) {
			response.redirect("/laboratoire/ateliers");
			return "";
		} else {

			// On vérifie que l'atelier appartient bien au labo
			int idLaboFromAtelier = atelier.getIdLabo();
			int idLabo = request.session().attribute("labo");

			// On vérifie que le créneau appartient bien à l'atelier
			Creneau creneau = atelier.getCreneaux().get(idCreneau);

			if (idLabo != idLaboFromAtelier || creneau == null) {
				return "";
			} else {
				BddCreneau.supprCreneau(idCreneau);
				response.redirect("/atelier/" + atelier.getIdAtelier() + "/creneaux");
			}
		}

		return "";
	}
}
