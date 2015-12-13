package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.servlets.ateliers.VisualisationAteliersGenerator;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationAteliersMonLabo implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return modelAndView;

		Map<String, Object> attributes = new HashMap<String, Object>();

		Laboratoire laboratoire = BddLabo.getLaboByIdLabo((Integer) request.session().attribute("labo"));
		Map<Integer, Atelier> ateliersMap = laboratoire.getAteliers();
		List<Atelier> ateliers = new ArrayList<Atelier>(ateliersMap.values());

		VisualisationAteliersGenerator.getModelAndView(request, ateliers, attributes);

		return new ModelAndView(attributes, "ateliers.ftl");
	}
}
