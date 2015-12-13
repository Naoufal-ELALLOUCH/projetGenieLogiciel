package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.t3.it340.modeles.Creneau;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TestBddCreneau {

	@Test
	public void testAjoutCreneau() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		// Insertion
		int idAtelier = 1;
		String jour = "2015-12-23";
		String heure = "13:00";
		int capacite = 20;
		BddCreneau.ajoutCreneau(1, jour, heure, capacite);

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

	@Test
	public void getCreneauxByIdAtelier() throws SQLException, IOException, ClassNotFoundException { 

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
		
		Map<Integer, Creneau> creneaux = new HashMap<Integer, Creneau>();
		
		// Test : récupération d'un objet null
		creneaux = BddCreneau.getCreneauxByIdAtelier(1);
		assertEquals(creneaux.size(), 0);
			
		// Insertion
		int idAtelier = 1;
		String jour = "2015-12-23";
		String heure = "21:00";
		int capacite = 20;
		BddCreneau.ajoutCreneau(idAtelier, jour, heure, capacite);
		
		// Test : récupération de creneaux 
		creneaux = BddCreneau.getCreneauxByIdAtelier(idAtelier);
		assertEquals(creneaux.size(), 1);
			
	}
	
	@Test
	public void testEditCreneau() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		// Insertion
		int idAtelier = 1;
		String jour = "2015-12-23";
		String heure = "13:00";
		int oldCapacite = 20;
		int newCapacite = 40;
		BddCreneau.ajoutCreneau(1, jour, heure, oldCapacite);

		// Edition
		BddCreneau.editCreneau(1, jour, heure, newCapacite);

		// Vérification
		String sql = "SELECT * FROM Creneau WHERE idAtelier='"+ idAtelier +"'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;
		int capaciteRecup = 0;

		while(rs.next()) {
			capaciteRecup = rs.getInt("capacite");
			count++;
		}

		assertEquals(count, 1);
		assertEquals(capaciteRecup, newCapacite);

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}

	// test récuperation creneaux par idAtelier
	
	
	@Test
	public void testSupprCreneau() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		// Insertion
		int idAtelier = 1;
		String jour = "2015-12-23";
		String heure = "13:00";
		int oldCapacite = 20;
		int newCapacite = 40;
		BddCreneau.ajoutCreneau(1, jour, heure, oldCapacite);

		// Suppression
		BddCreneau.supprCreneau(1);

		// Vérification
		String sql = "SELECT * FROM Creneau WHERE idAtelier='"+ idAtelier +"'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;

		while(rs.next()) {
			count++;
		}

		assertEquals(count, 0);

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}
	
	@Test
	public void testSupprCreneauxByIdAtelier() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		// Insertion
		int idAtelier = 1;
		String jour = "2015-12-23";
		String heure = "13:00";
		int oldCapacite = 20;
		int newCapacite = 40;
		BddCreneau.ajoutCreneau(1, jour, heure, oldCapacite);

		// Suppression
		BddCreneau.supprCreneauxByIdAtelier(1);

		// Vérification
		String sql = "SELECT * FROM Creneau WHERE idAtelier='"+ idAtelier +"'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;

		while(rs.next()) {
			count++;
		}

		assertEquals(count, 0);

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
		statement.execute("DROP TABLE Atelier");
		statement.execute("DROP TABLE Labo");
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}

}
