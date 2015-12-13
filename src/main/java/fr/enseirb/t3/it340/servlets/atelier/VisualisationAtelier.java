package fr.enseirb.t3.it340.servlets.atelier;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualisationAtelier  implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {
		String idAtelierString = request.params("idAtelier");
		int idAtelier = Integer.parseInt(idAtelierString); // TODO catch exception
		boolean modificationAutorisee = false;

		Map<String, Object> attributes = new HashMap<String, Object>();

		Atelier atelier = BddAtelier.getAtelierById(idAtelier);

		if (atelier != null) {
			attributes.put("title", atelier.getTitre());
			attributes.put("connected", (request.session().attribute("email") != null));
			attributes.put("labo", (request.session().attribute("labo") != null));
			attributes.put("enseignant", (request.session().attribute("enseignant") != null));
			attributes.put("atelier", atelier);

			List<Creneau> creneaux = new ArrayList<Creneau>(atelier.getCreneaux().values());

			// On vérifie si l'atelier appartient au labo
			try {
				Integer idLabo = (Integer) request.session().attribute("labo");
				if (idLabo != null) {
					if (idLabo.equals(atelier.getIdLabo())) {
						modificationAutorisee = true;
					}
				}
			} catch (Exception e) {}

			attributes.put("creneaux", creneaux);
			attributes.put("modificationAutorisee", modificationAutorisee);
			attributes.put("idAtelier", idAtelier);

			return new ModelAndView(attributes, "atelier.ftl");
		} else {
			response.status(403);
		}

		return null;
	}
}
