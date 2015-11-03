package fr.enseirb.t3.it340.servlets;

import spark.Request;
import spark.Response;
import spark.Route;

public class VisualisationAteliers implements Route {

	public String handle(Request arg0, Response arg1) throws Exception {
		return "helloWorld";
	}

}
