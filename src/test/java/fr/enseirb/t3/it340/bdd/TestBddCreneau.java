package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class TestBddCreneau {

	@Test
	public void testAjoutCreneau() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		//BddAtelier.ajoutAtelier();

		// Insertion
		int idAtelier = 1;
		String jour = "12/05/2015";
		String heure = "13:00";
		int capacite = 20;
		BddCreneau.ajoutCreneau(idAtelier, jour, heure, capacite);

		// Vérification
		String sql = "SELECT * FROM Creneau WHERE idAtelier='"+ idAtelier +"'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;
		Date jourRecup;
		Time heureRecup;
		int capaciteRecup = 0;

		while(rs.next()) {
			jourRecup = rs.getDate("jour");
			heureRecup = rs.getTime("heure");
			capaciteRecup = rs.getInt("capacite");
			System.out.println(heureRecup.toString());
			count++;
		}


		assertEquals(count, 1);
		//assertEquals(jourRecup, jour);
		// assertEquals(heureRecup, heure);
		assertEquals(capaciteRecup, capacite);

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Creneau");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}

}
