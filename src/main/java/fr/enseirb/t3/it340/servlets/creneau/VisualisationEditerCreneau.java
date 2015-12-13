package fr.enseirb.t3.it340.servlets.creneau;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationEditerCreneau implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {

		// Configuration de la page
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Éditer un créneau");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("labo", (request.session().attribute("labo") != null));
		attributes.put("enseignant", (request.session().attribute("enseignant") != null));

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return modelAndView;

		// On vérifie si l'atelier et le créneau appartiennent bien au labo
		Atelier atelier = null;
		Creneau creneau = null;

		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			Atelier atelierTmp = BddAtelier.getAtelierById(idAtelier);
			int idLabo = (Integer) request.session().attribute("labo");
			if (atelierTmp.getIdLabo() == idLabo) {
				atelier = atelierTmp;
				int idCreneau = Integer.parseInt(request.params("idCreneau"));
				creneau = atelier.getCreneaux().get(idCreneau);
			}
		} catch (Exception e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier == null || creneau == null)
			response.redirect("/laboratoire/ateliers");
		else {
			attributes.put("creneau", creneau);
			attributes.put("subtitle", "Éditer un créneau");
			attributes.put("modificationAutorisee", true);
		}

		return new ModelAndView(attributes, "editer-creneau.ftl");
	}
}
