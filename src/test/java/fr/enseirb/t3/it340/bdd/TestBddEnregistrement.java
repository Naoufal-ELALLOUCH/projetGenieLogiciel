package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestBddEnregistrement {
	@Test
	public void testEnregistrement() throws IOException, SQLException, ClassNotFoundException {
		
		Connection connection = BddConnecteur.getConnection();

		int idAtelier = 1;
		String jour = "12/05/2015";
		String heure = "13:00";
		int oldCapacite = 20;
		int newCapacite = 40;
		BddCreneau.ajoutCreneau(1, jour, heure, oldCapacite);
		BddEnseignant.ajout(1, "nom", "prenom");

		BddEnregistrement.enregistrement(1, 1, 20);
		// Vérification
		String sql = "SELECT idLabo FROM Atelier WHERE idLabo=1";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;
		while(rs.next()) {
			count++;
		}

		assertEquals(count, 1);

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}
}
