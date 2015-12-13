package fr.enseirb.t3.it340.servlets.atelier;

import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationCreerAtelier implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {

		// Configuration de la page
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Créer un atelier");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("labo", (request.session().attribute("labo") != null));
		attributes.put("enseignant", (request.session().attribute("enseignant") != null));

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return modelAndView;

		return new ModelAndView(attributes, "editer-atelier.ftl");
	}
}
