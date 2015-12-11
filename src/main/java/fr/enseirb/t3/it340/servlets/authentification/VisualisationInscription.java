package fr.enseirb.t3.it340.servlets.authentification;

import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationInscription implements TemplateViewRoute {
	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("title", "Inscription");

		return new ModelAndView(attributes, "inscription.ftl");
	}
}
