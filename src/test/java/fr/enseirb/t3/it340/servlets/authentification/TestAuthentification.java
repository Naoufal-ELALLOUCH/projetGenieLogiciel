package fr.enseirb.t3.it340.servlets.authentification;

import fr.enseirb.t3.it340.bdd.BddConnecteur;
import fr.enseirb.t3.it340.bdd.BddUtilisateur;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

import static org.junit.Assert.*;

public class TestAuthentification {

	Authentification authentification = new Authentification();
	Request request = Mockito.mock(Request.class);
	Response response = Mockito.mock(Response.class);
	Session session = Mockito.mock(Session.class);
	String email = "charlie@heloise.com";
	String motDePasse = "mdp";
	String result;

	@Before
	public void before() {
		this.result = "";

		Mockito.when(request.session()).thenReturn(session);

		Mockito.doAnswer(new Answer() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				setResult((String) args[1]);
				return null;
			}
		}).when(session).attribute("email", email);

		Mockito.doAnswer(new Answer() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				setResult("redirection");
				return null;
			}
		}).when(response).redirect("/laboratoire/ateliers");
	}

	@Test
	public void testHandleGoodLogins() throws Exception {

		BddUtilisateur.ajout(email, motDePasse);

		Mockito.when(request.queryParams("email")).thenReturn(this.email);
		Mockito.when(request.queryParams("motDePasse")).thenReturn(motDePasse);
		Mockito.when(session.isNew()).thenReturn(true);

		authentification.handle(request, response);

		assertEquals(result, email);
	}

	@Test
	public void testHandleWrongLogins() throws Exception {

		BddUtilisateur.ajout(email, motDePasse);

		Mockito.when(request.queryParams("email")).thenReturn(this.email);
		Mockito.when(request.queryParams("motDePasse")).thenReturn("1234");
		Mockito.when(request.session()).thenReturn(session);
		Mockito.when(session.isNew()).thenReturn(true);

		authentification.handle(request, response);

		assertNotEquals(result, email);
	}

	@Test
	public void testHandleRedirectIfAlreadyLogged() throws Exception {

		BddUtilisateur.ajout(email, motDePasse);

		Mockito.when(request.queryParams("email")).thenReturn(this.email);
		Mockito.when(request.queryParams("motDePasse")).thenReturn(motDePasse);
		Mockito.when(request.session()).thenReturn(session);
		Mockito.when(session.isNew()).thenReturn(false);

		authentification.handle(request, response);

		assertEquals(result, "redirection");
	}

	private void setResult(String result) {
		this.result = result;
	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}
}
