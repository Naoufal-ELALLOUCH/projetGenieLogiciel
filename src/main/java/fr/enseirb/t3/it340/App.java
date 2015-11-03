package fr.enseirb.t3.it340;
import static spark.Spark.*;

import fr.enseirb.t3.it340.servlets.VisualisationAteliers;

public class App {

	public static void main(String[] args) {
		get("/ateliers", new VisualisationAteliers());
	}

}
