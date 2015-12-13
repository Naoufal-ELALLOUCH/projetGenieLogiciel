package fr.enseirb.t3.it340;
import static spark.Spark.*;

import fr.enseirb.t3.it340.servlets.VisualisationAccueil;
import fr.enseirb.t3.it340.servlets.ateliers.*;
import fr.enseirb.t3.it340.servlets.authentification.*;
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

		// Authentification
		get("/inscription", new VisualisationInscription(), engine);
		post("/inscription", new Inscription());

		get("/authentification", new VisualisationAuthentification(), engine);
		post("/authentification", new Authentification());

		get("/deconnexion", new Deconnexion());

		get("/ateliers", new VisualisationAteliers(), engine);
		get("/ateliers/:idLabo", new VisualisationAteliersLabo(), engine);
		get("/atelier/:idAtelier", new VisualisationAtelier(), engine);

		get("/laboratoire/ateliers", new VisualisationAteliersMonLabo(), engine);

		get("/laboratoire/atelier/creer", new VisualisationCreerAtelier(), engine);
		post("/laboratoire/atelier/creer", new CreerAtelier());

		get("/laboratoire/atelier/:idAtelier/modifier", new VisualisationEditerAtelier(), engine);
		post("/laboratoire/atelier/:idAtelier/modifier", new EditerAtelier());

		get("atelier/:idAtelier/creneaux", new VisualisationCreneaux(), engine);
		post("atelier/:idAtelier/creneaux", new CreerCreneau());

		get("/atelier/:idAtelier/creneaux/:idCreneau", new VisualisationEditerCreneau(), engine);
		post("/atelier/:idAtelier/creneaux/:idCreneau", new EditerCreneau());

	}

}