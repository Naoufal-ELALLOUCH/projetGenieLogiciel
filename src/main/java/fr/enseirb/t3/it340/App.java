package fr.enseirb.t3.it340;
import static spark.Spark.*;

import fr.enseirb.t3.it340.bdd.BddConnecteur;
import fr.enseirb.t3.it340.servlets.VisualisationAteliers;
import fr.enseirb.t3.it340.servlets.ateliers.VisualisationAtelier;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;

import java.io.IOException;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

		get("/ateliers", new VisualisationAteliers());

		get("/atelier/:idAtelier", new VisualisationAtelier());

		post("/authentification", new Authentification());

	}

}