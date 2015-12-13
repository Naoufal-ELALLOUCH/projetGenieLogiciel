package fr.enseirb.t3.it340.servlets.creneaux;

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

public class VisualisationCreneaux implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {

		// Configuration de la page
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Créneaux");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("labo", (request.session().attribute("labo") != null));
		attributes.put("enseignant", (request.session().attribute("enseignant") != null));

		Atelier atelier = null;
		boolean modificationAutorisee = false;

		// On vérifie si l'atelier appartient au labo
		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			atelier = BddAtelier.getAtelierById(idAtelier);
			Integer idLabo = (Integer) request.session().attribute("labo");
			if (idLabo != null) {
				if (idLabo.equals(atelier.getIdLabo())) {
					modificationAutorisee = true;
				}
			}
		} catch (Exception e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier != null) {
			Map<Integer, Creneau> creneauxMap = atelier.getCreneaux();
			List<Creneau> creneaux = new ArrayList<Creneau>(creneauxMap.values());
			attributes.put("creneaux", creneaux);
			attributes.put("modificationAutorisee", modificationAutorisee);
			attributes.put("subtitle", "Ajouter un créneau");
			attributes.put("idAtelier", atelier.getIdAtelier());
		}

		return new ModelAndView(attributes, "editer-creneau.ftl");
	}
}
