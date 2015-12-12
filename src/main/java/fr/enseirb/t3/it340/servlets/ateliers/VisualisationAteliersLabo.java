package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.modeles.Atelier;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationAteliersLabo implements TemplateViewRoute {
	public ModelAndView handle(Request request, Response response) throws Exception {
		Map<String, Object> attributes = new HashMap<String, Object>();
		int idLabo = 0;
		try {
			idLabo = Integer.parseInt(request.params("idLabo"));
		} catch (NumberFormatException e) {}

		Map<Integer, Atelier> ateliersMap = BddAtelier.getAteliersByIdLabo(idLabo);
		List<Atelier> ateliers = new ArrayList<Atelier>(ateliersMap.values());

		VisualisationAteliersGenerator.getModelAndView(request, ateliers, attributes);

		return new ModelAndView(attributes, "ateliers.ftl");
	}
}
