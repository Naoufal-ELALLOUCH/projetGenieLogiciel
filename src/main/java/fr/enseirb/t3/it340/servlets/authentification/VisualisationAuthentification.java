package fr.enseirb.t3.it340.servlets.authentification;

import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationAuthentification implements TemplateViewRoute {
	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("title", "Authentification");

		if (request.session().attribute("erreur") != null) {
			attributes.put("erreur", "Identifiants invalides");
			request.session().removeAttribute("erreur");
		}

		return new ModelAndView(attributes, "authentification.ftl");
	}
}
