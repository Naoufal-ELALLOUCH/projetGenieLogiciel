package fr.enseirb.t3.it340.servlets.creneau;

import fr.enseirb.t3.it340.bdd.BddAtelier;
import fr.enseirb.t3.it340.bdd.BddCreneau;
import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import spark.Request;
import spark.Response;
import spark.Route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditerCreneau implements Route {

	public String handle(Request request, Response response) throws Exception {

		Authentification.checkLabo(request, response);

		Atelier atelier = null;
		Creneau creneau = null;
		int idAtelier = 0;
		int idCreneau = 0;

		// On vérifie si l'atelier et le créneau appartiennent au labo
		try {
			idAtelier = Integer.parseInt(request.params("idAtelier"));
			Atelier atelierTmp = BddAtelier.getAtelierById(idAtelier);
			int idLabo = request.session().attribute("labo");
			idCreneau = Integer.parseInt(request.params("idCreneau"));
			if (atelierTmp.getIdLabo() == idLabo) {
				atelier = atelierTmp;
				creneau = atelier.getCreneaux().get(idCreneau);
				if (creneau == null)
					throw new Exception();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			response.redirect("/laboratoire/ateliers");
		}

		if (atelier != null && idAtelier > 0 && idCreneau > 0 && creneau != null) {
			String jour = request.queryParams("jour");
			String heure = request.queryParams("heure");
			SimpleDateFormat jourFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");

			try {
				jourFormat.parse(jour);
				heureFormat.parse(heure);
			} catch (ParseException e) {
				Date date = new Date();
				jour = jourFormat.format(date);
				heure = heureFormat.format(date);
			}

			int capacite = 0;

			try {
				capacite = Integer.parseInt(request.queryParams("capacite"));
			} catch (Exception e) {
				response.redirect("/laboratoire/ateliers");
			}

			BddCreneau.editCreneau(idCreneau, jour, heure, capacite);
			response.redirect("/atelier/" + idAtelier + "/creneaux");
		}

		return "";
	}
}
