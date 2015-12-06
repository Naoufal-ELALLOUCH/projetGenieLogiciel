package fr.enseirb.t3.it340.servlets.ateliers;

import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationCreerAtelier implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("title", "Création d'un atelier");

		return new ModelAndView(attributes, "creer-atelier.ftl");
	}
}
