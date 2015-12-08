package fr.enseirb.t3.it340.servlets.ateliers;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.modeles.Atelier;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class VisualisationAtelier  implements TemplateViewRoute {

	public ModelAndView handle(Request request, Response response) throws Exception {
		String idAtelierString = request.params("idAtelier");
		int idAtelier = Integer.parseInt(idAtelierString); // TODO catch exception

		Map<String, Object> attributes = new HashMap<String, Object>();

		Atelier atelier = BddAtelier.getAtelierById(idAtelier);

		if (atelier != null) {
			attributes.put("atelier", atelier);
			return new ModelAndView(attributes, "atelier.ftl");
		} else {
			response.status(403);
		}

		return null;
	}
}
