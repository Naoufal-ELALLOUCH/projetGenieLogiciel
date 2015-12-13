package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationCreerCreneau implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {

		// Configuration de la page
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Ajouter un créneau");
		attributes.put("connected", (request.session().attribute("email") != null));

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return modelAndView;

		Atelier atelier = null;

		// On vérifie si l'atelier appartient au labo
		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			Atelier atelierTmp = BddAtelier.getAtelierById(idAtelier);
			int idLabo = (Integer) request.session().attribute("labo");
			if (atelierTmp.getIdLabo() == idLabo) {
				atelier = atelierTmp;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier != null) {
			Map<Integer, Creneau> creneauxMap = atelier.getCreneaux();
			List<Creneau> creneaux = new ArrayList<Creneau>(creneauxMap.values());
			attributes.put("creneaux", creneaux);
		}

		return new ModelAndView(attributes, "editer-creneau.ftl");
	}
}
