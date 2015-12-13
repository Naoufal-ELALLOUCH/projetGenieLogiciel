package fr.enseirb.t3.it340.servlets.creneau;

import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationInscrireCreneau implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {
		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkEnseignant(request, response);
		if (modelAndView != null)
			return modelAndView;

		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Inscription au créneau");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("labo", (request.session().attribute("labo") != null));
		attributes.put("enseignant", (request.session().attribute("enseignant") != null));

		Integer idEnseignant = request.session().attribute("enseignant");

		if (idEnseignant == null)
			response.redirect("/enseignant");

		return new ModelAndView(attributes, "inscription-creneau.ftl");
	}
}
