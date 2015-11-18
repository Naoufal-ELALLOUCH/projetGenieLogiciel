package fr.enseirb.t3.it340;
import static spark.Spark.*;

import fr.enseirb.t3.it340.bdd.BddConnecteur;
import fr.enseirb.t3.it340.servlets.VisualisationAteliers;
import fr.enseirb.t3.it340.servlets.authentification.Authentification;

import java.io.IOException;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		post("/authentification", new Authentification());
		get("/ateliers", new VisualisationAteliers());

	}

}