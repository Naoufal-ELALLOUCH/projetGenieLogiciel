package fr.enseirb.t3.it340.bdd;

import org.h2.*;
import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class TestBddUtilisateur {

	@Test
	public void testAjout() throws IOException, SQLException, ClassNotFoundException {
		BddUtilisateur bddUtilisateur = new BddUtilisateur();
		Connection connection = BddConnecteur.getConnection();

		// Insertion
		String email =  "charlie@heloise.com";
		String motDePasseOriginal = "mdp";
		bddUtilisateur.ajout(email, motDePasseOriginal);

		// Vérification
		String sql = "SELECT email, motDePasse FROM Utilisateur WHERE email='"+ email +"'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;
		String motDePasse = "";
		while(rs.next()) {
			motDePasse = rs.getString("motDePasse");
			count++;
		}

		assertEquals(count, 1);
		assertEquals(motDePasse, motDePasseOriginal);

		// Fermeture
		statement.close();
	}

	@Test
	public void testAuthentification() throws IOException, SQLException, ClassNotFoundException {
		BddUtilisateur bddUtilisateur = new BddUtilisateur();

		// Insertion
		String email = "charlie@heloise.com";
		String motDePasseOriginal = "mdp";
		bddUtilisateur.ajout(email, motDePasseOriginal);

		// Vérification
		boolean res1 = bddUtilisateur.authentification(email, motDePasseOriginal);
		assertEquals(res1, true);
		
		boolean res2 = bddUtilisateur.authentification(email, "mdperreur");
		assertEquals(res2, false);
		
		boolean res3 = bddUtilisateur.authentification("mauvais email", motDePasseOriginal);
		assertEquals(res3, false);
	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
		BddConnecteur.dispose();
	}

}
