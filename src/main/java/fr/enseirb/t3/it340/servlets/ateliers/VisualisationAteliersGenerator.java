package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.modeles.Atelier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationAteliersGenerator {

	public static ModelAndView getModelAndView(Request req, List<Atelier> ateliers, Map<String, Object> attributes) throws Exception {

		attributes.put("title", "Ateliers");
		attributes.put("ateliers", ateliers);
		attributes.put("connected", (req.session().attribute("email") != null));
		attributes.put("labo", (req.session().attribute("labo") != null));
		attributes.put("enseignant", (req.session().attribute("enseignant") != null));

		return new ModelAndView(attributes, "ateliers.ftl");
	}

}
