package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationEditerAtelier implements TemplateViewRoute {
	public ModelAndView handle(Request request, Response response) throws Exception {

		// Configuration de la page
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Éditer un atelier");
		attributes.put("connected", (request.session().attribute("email") != null));

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return modelAndView;

		Laboratoire laboratoire = BddLabo.getLaboByIdLabo((Integer) request.session().attribute("labo"));
		Map<Integer, Atelier> ateliers = laboratoire.getAteliers();

		// On vérifie si l'atelier appartient bien au labo
		Atelier atelier = null;

		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			atelier = ateliers.get(idAtelier);
		} catch (NumberFormatException e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier == null)
			response.redirect("/laboratoire/ateliers");
		else
			attributes.put("atelier", atelier);

		return new ModelAndView(attributes, "editer-atelier.ftl");
	}
}
