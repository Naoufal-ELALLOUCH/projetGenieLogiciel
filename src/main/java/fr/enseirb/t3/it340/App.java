package fr.enseirb.t3.it340;
import static spark.Spark.*;

import fr.enseirb.t3.it340.servlets.VisualisationAccueil;
import fr.enseirb.t3.it340.servlets.ateliers.CreationAtelier;
import fr.enseirb.t3.it340.servlets.ateliers.VisualisationAtelier;
import fr.enseirb.t3.it340.servlets.ateliers.VisualisationAteliers;
import fr.enseirb.t3.it340.servlets.ateliers.VisualisationCreerAtelier;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

		// Configuration du moteur de template
		FreeMarkerEngine engine = new FreeMarkerEngine();
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(App.class, "/templates/");
		engine.setConfiguration(cfg);

		// Gestion des urls
		get("/", new VisualisationAccueil(), engine);

		get("/ateliers", new VisualisationAteliers(), engine);
		get("/atelier/:idAtelier", new VisualisationAtelier(), engine);
		get("/laboratoire/atelier/creer", new VisualisationCreerAtelier(), engine);

		post("/laboratoire/atelier/creer", new CreationAtelier());
		post("/authentification", new Authentification());

	}

}