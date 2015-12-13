package fr.enseirb.t3.it340.servlets.atelier;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

public class EditerAtelier implements Route {

	public String handle(Request request, Response response) throws Exception {

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return "";

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
			}
		}

		atelier.setTitre(request.queryParams("titre"));
		atelier.setThemes(request.queryParams("themes"));
		atelier.setZone(request.queryParams("zone"));
		atelier.setAdresse(request.queryParams("adresse"));
		atelier.setOrateurs(request.queryParams("orateurs"));
		atelier.setPartenaires(request.queryParams("partenaires"));
		atelier.setCibles(request.queryParams("cible"));
		atelier.setRemarques(request.queryParams("remarques"));

		BddAtelier.editAtelier(atelier);

		response.redirect("/laboratoire/ateliers");

		return "";
	}
}
