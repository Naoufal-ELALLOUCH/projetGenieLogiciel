package fr.enseirb.t3.it340.servlets.authentification;

import fr.enseirb.t3.it340.bdd.BddConnecteur;
import fr.enseirb.t3.it340.bdd.BddEnseignant;
import fr.enseirb.t3.it340.bdd.BddLabo;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestAuthentification {

	Authentification authentification = new Authentification();
	Request request = Mockito.mock(Request.class);
	Response response = Mockito.mock(Response.class);
	Session session = Mockito.mock(Session.class);
	String email = "charlie@heloise.com";
	String motDePasse = "mdp";
	Map<String, String> attributes = new HashMap<String, String>();

	@Before
	public void before() {

		Mockito.when(request.session()).thenReturn(session);

		Mockito.doAnswer(new Answer() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				attributes.put(arguments[0].toString(), arguments[1].toString());
				return null;
			}
		}).when(session).attribute(Mockito.anyString(), Mockito.any());

		Mockito.doAnswer(new Answer() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				attributes.put("redirection", arguments[0].toString());
				return null;
			}
		}).when(response).redirect(Mockito.anyString());
	}

	@Test
	public void testHandleGoodLoginsEnseignant() throws Exception {

		BddUtilisateur.ajout(email, motDePasse);
		BddEnseignant.ajout(1, "Falleri", "Jean-Rémy");

		Mockito.when(request.queryParams("email")).thenReturn(this.email);
		Mockito.when(request.queryParams("motDePasse")).thenReturn(motDePasse);
		Mockito.when(session.isNew()).thenReturn(true);

		authentification.handle(request, response);

		assertEquals(attributes.get("email"), email);
		assertNotNull(attributes.get("enseignant"));
		assertNull(attributes.get("labo"));
	}

	@Test
	public void testHandleGoodLoginsLabo() throws Exception {

		BddUtilisateur.ajout(email, motDePasse);
		BddLabo.ajout(1, "INRIA");

		Mockito.when(request.queryParams("email")).thenReturn(this.email);
		Mockito.when(request.queryParams("motDePasse")).thenReturn(motDePasse);
		Mockito.when(session.isNew()).thenReturn(true);

		authentification.handle(request, response);

		assertEquals(attributes.get("email"), email);
		assertNotNull(attributes.get("labo"));
		assertNull(attributes.get("enseignant"));
	}

	@Test
	public void testHandleWrongLogins() throws Exception {

		BddUtilisateur.ajout(email, motDePasse);

		Mockito.when(request.queryParams("email")).thenReturn(this.email);
		Mockito.when(request.queryParams("motDePasse")).thenReturn("1234");
		Mockito.when(request.session()).thenReturn(session);
		Mockito.when(session.isNew()).thenReturn(true);

		authentification.handle(request, response);

		assertNull(attributes.get("email"));
	}

	@Test
	public void testCheckLabo1() throws Exception {
		Mockito.when(session.attribute(Mockito.anyString())).thenReturn(null);
		Authentification.checkLabo(request, response);
		assertEquals(attributes.get("redirection"), "/authentification");
	}

	@Test
	public void testCheckLabo2() throws Exception {
		Mockito.when(session.attribute("email")).thenReturn("charlie@heloise.fr");
		Authentification.checkLabo(request, response);
		assertEquals(attributes.get("redirection"), "/");
	}

	@Test
	public void testCheckLabo3() throws Exception {

		Mockito.when(session.attribute("email")).thenReturn("charlie@heloise.fr");
		Mockito.when(session.attribute("labo")).thenReturn(1);
		Authentification.checkLabo(request, response);
		assertEquals(attributes.get("redirection"), null);

	}

	@Test
	public void testCheckEnseignant1() throws Exception {
		Mockito.when(session.attribute(Mockito.anyString())).thenReturn(null);
		Authentification.checkEnseignant(request, response);
		assertEquals(attributes.get("redirection"), "/authentification");
	}

	@Test
	public void testCheckEnseignant2() throws Exception {
		Mockito.when(session.attribute("email")).thenReturn("charlie@heloise.fr");
		Authentification.checkEnseignant(request, response);
		assertEquals(attributes.get("redirection"), "/");
	}

	@Test
	public void testCheckEnseignant3() throws Exception {

		Mockito.when(session.attribute("email")).thenReturn("charlie@heloise.fr");
		Mockito.when(session.attribute("enseignant")).thenReturn(1);
		Authentification.checkEnseignant(request, response);
		assertEquals(attributes.get("redirection"), null);

	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		attributes.clear();

		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Labo");
		statement.execute("DROP TABLE Enseignant");
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}
}
