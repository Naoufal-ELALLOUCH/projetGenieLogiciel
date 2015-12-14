package fr.enseirb.t3.it340.servlets;

import fr.enseirb.t3.it340.App;
import fr.enseirb.t3.it340.bdd.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static org.junit.Assert.*;
import static spark.Spark.stop;

public class TestApp {

	@Before
	public void before() {
		BddUtilisateur.ajout("labri@labri.com", "labri");
		BddUtilisateur.ajout("charlie@heloise.com", "mdp");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "Titre", "themes", "zone", "adresse", "orateurs", "partenaires", "cible", "remarques");
		BddCreneau.ajoutCreneau(1, "2014-12-12", "12:34", 20);
		BddEnseignant.ajout(1, "Heloise", "Charlie");
	}

	@Test
	public void testGetProperties() throws IOException {
		Integer port = App.getPort();
		assertNotNull(port);
	}

	@Test
	public void testMainGetVisiteur() throws SQLException, IOException, ClassNotFoundException {
		String format = "http://0.0.0.0:" + App.getPort() + "%s";
		HttpGet request;
		HttpResponse response;
		Set<String> urls200 = new HashSet<String>();
		Set<String> urls302 = new HashSet<String>();

		// URLs où on est autorisé
		urls200.add("/");
		urls200.add("/authentification");
		urls200.add("/inscription");
		urls200.add("/ateliers");
		urls200.add("/ateliers/1");
		urls200.add("/atelier/1");
		urls200.add("/atelier/1/creneaux");

		// URLs où on doit être redirigé
		urls302.add("/deconnexion");
		urls302.add("/laboratoire/ateliers");
		urls302.add("/laboratoire/atelier/creer");
		urls302.add("/laboratoire/atelier/1/modifier");
		urls302.add("/laboratoire/atelier/1/supprimer");
		urls302.add("/atelier/1/creneaux/1");
		urls302.add("/atelier/1/creneaux/1/supprimer");
		urls302.add("/atelier/1/creneaux/1/inscrire");
		urls302.add("/enseignant");

		App.main(null);
		CloseableHttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();

		// Test 200 OK
		for (String uri : urls200) {
			String url = String.format(format, uri);
			request = new HttpGet(url);
			response = client.execute(request);
			assertEquals(response.getStatusLine().getStatusCode(), 200);
			request.abort();
		}

		// Test 302 Redirection
		for (String uri : urls302) {
			String url = String.format(format, uri);
			request = new HttpGet(url);
			response = client.execute(request);
			assertEquals(response.getStatusLine().getStatusCode(), 302);
			request.abort();
		}

		client.close();

	}

	@Test
	public void testMainGetLabo() throws SQLException, IOException, ClassNotFoundException {
		String format = "http://0.0.0.0:" + App.getPort() + "%s";
		HttpGet request;
		HttpResponse response;
		Set<String> urls200 = new HashSet<String>();
		Set<String> urls302 = new HashSet<String>();

		App.main(null);
		CloseableHttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();

		// Authentification en tant que labo
		HttpPost post = new HttpPost(String.format(format, "/authentification"));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "labri@labri.com"));
		params.add(new BasicNameValuePair("motDePasse", "labri"));
		post.setEntity(new UrlEncodedFormEntity(params));
		response = client.execute(post);

		// URLs où on est autorisé
		urls200.add("/");
		urls200.add("/inscription");
		urls200.add("/ateliers");
		urls200.add("/ateliers/1");
		urls200.add("/atelier/1");
		urls200.add("/atelier/1/creneaux");
		urls200.add("/laboratoire/atelier/1/modifier");
		urls200.add("/atelier/1/creneaux/1");
		urls200.add("/laboratoire/ateliers");
		urls200.add("/laboratoire/atelier/creer");

		// URLs où on doit être redirigé
		urls302.add("/atelier/1/creneaux/1/supprimer"); // TODO unitariser ce test
		urls302.add("/laboratoire/atelier/1/supprimer"); // TODO unitariser ce test
		urls302.add("/atelier/1/creneaux/1/inscrire");
		urls302.add("/authentification");
		urls302.add("/deconnexion");
		urls302.add("/enseignant");

		// Test 200 OK
		for (String uri : urls200) {
			String url = String.format(format, uri);
			request = new HttpGet(url);
			response = client.execute(request);
			assertEquals(response.getStatusLine().getStatusCode(), 200);
			request.abort();
		}

		// Test 302 Redirection
		for (String uri : urls302) {
			String url = String.format(format, uri);
			request = new HttpGet(url);
			response = client.execute(request);
			assertEquals(response.getStatusLine().getStatusCode(), 302);
			request.abort();
		}

		client.close();

	}

	@Test
	public void testMainGetEnseignant() throws SQLException, IOException, ClassNotFoundException {
		String format = "http://0.0.0.0:" + App.getPort() + "%s";
		HttpGet request;
		HttpResponse response;
		Set<String> urls200 = new HashSet<String>();
		Set<String> urls302 = new HashSet<String>();

		App.main(null);
		CloseableHttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();

		// Authentification en tant qu'enseignant
		HttpPost post = new HttpPost(String.format(format, "/authentification"));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "charlie@heloise.com"));
		params.add(new BasicNameValuePair("motDePasse", "mdp"));
		post.setEntity(new UrlEncodedFormEntity(params));
		response = client.execute(post);

		// URLs où on est autorisé
		urls200.add("/");
		urls200.add("/inscription");
		urls200.add("/ateliers");
		urls200.add("/ateliers/1");
		urls200.add("/atelier/1");
		urls200.add("/atelier/1/creneaux");

		// URLs où on doit être redirigé
		urls302.add("/enseignant"); // TODO unitariser ce test
		urls302.add("/deconnexion");
		urls302.add("/authentification");
		urls302.add("/atelier/1/creneaux/1/inscrire"); // TODO unitariser ce test
		urls302.add("/laboratoire/ateliers");
		urls302.add("/laboratoire/atelier/creer");
		urls302.add("/laboratoire/atelier/1/modifier");
		urls302.add("/laboratoire/atelier/1/supprimer");
		urls302.add("/atelier/1/creneaux/1");
		urls302.add("/atelier/1/creneaux/1/supprimer");

		// Test 200 OK
		for (String uri : urls200) {
			String url = String.format(format, uri);
			request = new HttpGet(url);
			response = client.execute(request);
			assertEquals(response.getStatusLine().getStatusCode(), 200);
			request.abort();
		}

		// Test 302 Redirection
		for (String uri : urls302) {
			String url = String.format(format, uri);
			request = new HttpGet(url);
			response = client.execute(request);
			assertEquals(response.getStatusLine().getStatusCode(), 302);
			request.abort();
		}

		client.close();

	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		stop();
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Enregistrement");
		statement.execute("DROP TABLE Creneau");
		statement.execute("DROP TABLE Atelier");
		statement.execute("DROP TABLE Enseignant");
		statement.execute("DROP TABLE Labo");
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}
}
