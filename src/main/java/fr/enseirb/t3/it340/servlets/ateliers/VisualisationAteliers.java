package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.modeles.Atelier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationAteliers implements TemplateViewRoute {

	public ModelAndView handle(Request req, Response res) throws Exception {
		Map<String, Object> attributes = new HashMap<String, Object>();

		// TODO récupérer la liste des ateliers
		List<Atelier> ateliers = new ArrayList<Atelier>();

		ateliers.add(new Atelier(1, 1, "toto"));
		ateliers.add(new Atelier(2, 1, "tata"));

		attributes.put("title", "Ateliers");
		attributes.put("ateliers", ateliers);

		return new ModelAndView(attributes, "ateliers.ftl");
	}

}
