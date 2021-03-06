package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Test;

import fr.enseirb.t3.it340.modeles.Enseignant;
import fr.enseirb.t3.it340.modeles.Utilisateur;

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

	@Test
	public void testGetEnseignantByIdEnseignant() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		Enseignant enseignant;
		String nom = "nom";
		String prenom = "prenom";
		BddUtilisateur.ajout("labri@labri.fr", "labri");

		// Test : récupération d'un objet null
		enseignant = BddEnseignant.getEnseignantByIdEnseignant(1);
		assertNull(enseignant);

		// Insertion
		BddEnseignant.ajout(1,nom, prenom);

		// Test : récupération d'un utilisateur à partir de IdUtilisateur
		enseignant = BddEnseignant.getEnseignantByIdEnseignant(1);
		assertNotNull(enseignant);
		assertEquals(enseignant.getNom(), nom);
		assertEquals(enseignant.getPrenom(), prenom);
	}
	
	@Test
	public void testGetEnseignantByIdUtilisateur() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		Enseignant enseignant;
		String nom = "nom";
		String prenom = "prenom";
		BddUtilisateur.ajout("labri@labri.fr", "labri");

		// Test : récupération d'un objet null
		enseignant = BddEnseignant.getEnseignantByIdUtilisateur(1);
		assertNull(enseignant);

		// Insertion
		BddEnseignant.ajout(1,nom, prenom);

		// Test : récupération d'un utilisateur à partir de IdUtilisateur
		enseignant = BddEnseignant.getEnseignantByIdUtilisateur(1);
		assertNotNull(enseignant);
		assertEquals(enseignant.getIdEnseignant(), 1);
		assertEquals(enseignant.getNom(), nom);
		assertEquals(enseignant.getPrenom(), prenom);
	}
	@Test
	public void testIsEnseignant() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		// Insertion
		BddUtilisateur.ajout("falleri@falleri.fr", "falleri");

		// N'est pas encore enseignant
		assertFalse(BddEnseignant.isEnseignant(1));

		// Est enseignant
		BddEnseignant.ajout(1, "Falleri", "Jean-Rémy");

		assertTrue(BddEnseignant.isEnseignant(1));

		connection.close();
	}
	
	

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Utilisateur");
		statement.execute("DROP TABLE Enseignant");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}
}
