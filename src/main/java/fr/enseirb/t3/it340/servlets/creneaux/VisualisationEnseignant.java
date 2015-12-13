package fr.enseirb.t3.it340.servlets.creneaux;

import fr.enseirb.t3.it340.bdd.BddEnregistrement;
import fr.enseirb.t3.it340.modeles.Enregistrement;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationEnseignant implements TemplateViewRoute {
	public ModelAndView handle(Request request, Response response) throws Exception {

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkEnseignant(request, response);
		if (modelAndView != null)
			return modelAndView;

		Integer idEnseignant = request.session().attribute("enseignant");
		if (idEnseignant == null)
			response.redirect("/");

		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Mes enregistrements");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("labo", (request.session().attribute("labo") != null));
		attributes.put("enseignant", (request.session().attribute("enseignant") != null));

		List<Enregistrement> enregistrements = BddEnregistrement.getEnregistrementsByIdEnseignant(idEnseignant);

		attributes.put("enregistrements", enregistrements);
		return new ModelAndView(attributes, "enregistrements.ftl");
	}
}
