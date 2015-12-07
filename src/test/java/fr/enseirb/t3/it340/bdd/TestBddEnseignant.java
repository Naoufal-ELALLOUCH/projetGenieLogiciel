package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestBddEnseignant {
	@Test
	public void testAjout() throws SQLException, IOException, ClassNotFoundException {
		
		Connection connection = BddConnecteur.getConnection();
	
		// Insertion
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddEnseignant.ajout(1, "nom", "prenom");
	
		// Vérification
		String sql = "SELECT nom, prenom FROM Enseignant WHERE idUtilisateur=1";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
	
		String nom = "";
		String prenom = "";
		int count = 0;
	
		while(rs.next()) {
			nom = rs.getString("nom");
			prenom = rs.getString("prenom");
			count++;
		}
	
		assertEquals(count, 1);
		assertEquals(nom, "nom");
		assertEquals(prenom, "prenom");

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}
}
