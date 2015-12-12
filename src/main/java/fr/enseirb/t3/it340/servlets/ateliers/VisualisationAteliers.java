package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
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

		Map<Integer, Atelier> ateliersMap = BddAtelier.getAteliers();
		List<Atelier> ateliers = new ArrayList<Atelier>(ateliersMap.values());

		VisualisationAteliersGenerator.getModelAndView(req, ateliers, attributes);

		return new ModelAndView(attributes, "ateliers.ftl");
	}

}
