package fr.enseirb.t3.it340;
import static spark.Spark.*;

import fr.enseirb.t3.it340.servlets.VisualisationAccueil;
import fr.enseirb.t3.it340.servlets.atelier.*;
import fr.enseirb.t3.it340.servlets.ateliers.*;
import fr.enseirb.t3.it340.servlets.authentification.*;
import fr.enseirb.t3.it340.servlets.creneau.CreerCreneau;
import fr.enseirb.t3.it340.servlets.creneau.EditerCreneau;
import fr.enseirb.t3.it340.servlets.creneau.SupprimerCreneau;
import fr.enseirb.t3.it340.servlets.creneau.VisualisationEditerCreneau;
import fr.enseirb.t3.it340.servlets.creneaux.VisualisationCreneaux;
import fr.enseirb.t3.it340.servlets.creneaux.VisualisationEnseignant;
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

		// Inscription
		get("/inscription", new VisualisationInscription(), engine);
		post("/inscription", new Inscription());

		// Authentification
		get("/authentification", new VisualisationAuthentification(), engine);
		post("/authentification", new Authentification());

		// Déconnexion
		get("/deconnexion", new Deconnexion());

		// Ateliers
		get("/ateliers", new VisualisationAteliers(), engine);
		get("/ateliers/:idLabo", new VisualisationAteliersLabo(), engine);
		get("/laboratoire/ateliers", new VisualisationAteliersMonLabo(), engine);

		// Atelier
		get("/atelier/:idAtelier", new VisualisationAtelier(), engine);

		// Atelier - création
		get("/laboratoire/atelier/creer", new VisualisationCreerAtelier(), engine);
		post("/laboratoire/atelier/creer", new CreerAtelier());

		// Atelier - modification
		get("/laboratoire/atelier/:idAtelier/modifier", new VisualisationEditerAtelier(), engine);
		post("/laboratoire/atelier/:idAtelier/modifier", new EditerAtelier());

		// Atelier - suppression
		get("/laboratoire/atelier/:idAtelier/supprimer", new SupprimerAtelier());

		// Créneau - ajout
		get("atelier/:idAtelier/creneaux", new VisualisationCreneaux(), engine);
		post("atelier/:idAtelier/creneaux", new CreerCreneau());

		// Créneau - modification
		get("/atelier/:idAtelier/creneaux/:idCreneau", new VisualisationEditerCreneau(), engine);
		post("/atelier/:idAtelier/creneaux/:idCreneau", new EditerCreneau());

		// Créneau - suppression
		get("/atelier/:idAtelier/creneaux/:idCreneau/supprimer", new SupprimerCreneau());

		// TODO Créneau - inscription
		// get("/atelier/:idAtelier/creneaux/:idCreneau/inscription", new VisualisationInscrireCreneau(), engine);
		// post("/atelier/:idAtelier/creneaux/:idCreneau/inscription", new InscrireCreneau());

		// Enregistrements
		get("/enseignant", new VisualisationEnseignant(), engine);

	}

}