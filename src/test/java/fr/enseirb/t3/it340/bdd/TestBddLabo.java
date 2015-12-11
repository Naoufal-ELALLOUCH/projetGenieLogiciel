package fr.enseirb.t3.it340.bdd;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.*;


public class TestBddLabo {

	@Test
	public void testAjout() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		//BddAtelier.ajoutAtelier();

		// Insertion
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");

		// Vérification
		String sql = "SELECT * FROM Labo WHERE idUtilisateur=1";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		String nom = "";
		int count = 0;

		while(rs.next()) {
			nom = rs.getString("nom");
			count++;
		}

		assertEquals(count, 1);
		assertEquals(nom, "Labri");

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}

	@Test
	public void testIsLabo() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		// Insertion
		BddUtilisateur.ajout("labri@labri.fr", "labri");

		// N'est pas encore labo
		assertFalse(BddLabo.isLabo(1));

		// Est labo
		BddLabo.ajout(1, "Labri");

		assertTrue(BddLabo.isLabo(1));

		connection.close();
	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Utilisateur");
		statement.execute("DROP TABLE Labo");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}
}
