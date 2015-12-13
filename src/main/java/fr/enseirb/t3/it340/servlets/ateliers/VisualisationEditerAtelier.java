package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationEditerAtelier implements TemplateViewRoute {
	public ModelAndView handle(Request request, Response response) throws Exception {

		// Configuration de la page
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("title", "Éditer un atelier");
		attributes.put("connected", (request.session().attribute("email") != null));
		attributes.put("labo", (request.session().attribute("labo") != null));
		attributes.put("enseignant", (request.session().attribute("enseignant") != null));

		// On regarde si l'utilisateur a accès
		ModelAndView modelAndView = Authentification.checkLabo(request, response);
		if (modelAndView != null)
			return modelAndView;

		Atelier atelier = null;
		List<Creneau> creneaux = null;

		try {
			int idAtelier = Integer.parseInt(request.params("idAtelier"));
			atelier = BddAtelier.getAtelierById(idAtelier);
			creneaux = new ArrayList<Creneau>(atelier.getCreneaux().values());

			Integer idLabo = (Integer) request.session().attribute("labo");
			if (idLabo != null) {
				if (!idLabo.equals(atelier.getIdLabo())) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			response.redirect("/laboratoire/ateliers");
		}


		if (atelier == null && creneaux == null)
			response.redirect("/laboratoire/ateliers");
		else {
			attributes.put("atelier", atelier);
			attributes.put("idAtelier", atelier.getIdAtelier());
			attributes.put("creneaux", creneaux);
		}

		return new ModelAndView(attributes, "editer-atelier.ftl");
	}
}
