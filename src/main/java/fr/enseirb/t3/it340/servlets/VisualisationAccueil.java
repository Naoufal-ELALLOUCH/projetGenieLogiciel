package fr.enseirb.t3.it340.servlets;

import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationAccueil implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("title", "Accueil");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("name", "Chocolat");

		return new ModelAndView(attributes, "accueil.ftl");
	}
}
