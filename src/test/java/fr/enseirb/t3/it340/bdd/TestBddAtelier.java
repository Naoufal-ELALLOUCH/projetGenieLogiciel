package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.modeles.Utilisateur;

public class TestBddAtelier {

	private final String email = "charlie@heloise.com";
	private final String mdp = "mdp";

	@Test
	public void testAjout() throws IOException, SQLException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
		
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
	
	@Test
	public void testgetAtelierById() throws IOException, SQLException, ClassNotFoundException {

		Connection connection = BddConnecteur.getConnection();
	
		Atelier atelier;
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		// Test : récupération d'un objet null
		atelier = BddAtelier.getAtelierById(1);
		assertNull(atelier);
	
		// Insertion

		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
	
		// Test : récupération d'un atelier
		atelier = BddAtelier.getAtelierById(1);
		assertNotNull(atelier);
		
	}
	@Test
	public void testEditAtelier() throws IOException, SQLException, ClassNotFoundException {

		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");

		// Insertion
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		// Edition
		BddAtelier.editAtelier(1, "Nouveau titre", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
		// Vérification
		String sql = "SELECT titre FROM Atelier WHERE idAtelier=1";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;
		String titre = "";
		
		while(rs.next()) {
			titre = rs.getString("titre");
			count++;
		}

		assertEquals(count, 1);
		assertEquals(titre, "Nouveau titre");

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
//		
	}
	@Test
	public void testsupprAtelier() throws IOException, SQLException, ClassNotFoundException {

		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");

		// Insertion
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		// Suppression
		BddAtelier.supprAtelier(1);
		
		// Vérification
		String sql = "SELECT idAtelier FROM Atelier WHERE idAtelier=1";
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
//		
	}
	@Test
	public void testChangeStatutAtelier() throws IOException, SQLException, ClassNotFoundException {

		Connection connection = BddConnecteur.getConnection();

		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");

		// Insertion
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");

		//Changement mauvais statut 
		BddAtelier.changeStatut(1, "MAUVAIS");

		// Vérification		
		String sql1 = "SELECT statut FROM Atelier WHERE idAtelier=1";
		Statement statement1 = connection.createStatement();
		ResultSet rs1 = statement1.executeQuery(sql1);
		String statut1 = "";
		while(rs1.next()) {
			statut1 = rs1.getString("statut"); ;
		}
		assertEquals(statut1,"PROPOSE");
		
		//Changement bon statut
		BddAtelier.changeStatut(1, "VALIDE");

		// Vérification		
		String sql2 = "SELECT statut FROM Atelier WHERE idAtelier=1";
		Statement statement2 = connection.createStatement();
		ResultSet rs2 = statement2.executeQuery(sql2);
		String statut2 = "";
		while(rs2.next()) {
			statut2 = rs2.getString("statut"); ;
		}
		assertEquals(statut2,"VALIDE");	
	}

	@Test
	public void testGetAteliers() throws IOException, SQLException, ClassNotFoundException {

		Connection connection = BddConnecteur.getConnection();
		
		BddUtilisateur.ajout(email, mdp);
		BddUtilisateur.ajout("email2@aaa.com", "aaa");
		
		BddLabo.ajout(1,"Labo1");
		BddLabo.ajout(2, "Labo2");
		
		BddAtelier.ajoutAtelier(1, "", "", "", "", "", "", "", "");
		BddAtelier.ajoutAtelier(2, "", "", "", "", "", "", "", "");
	Map<Integer, Atelier> ateliers = BddAtelier.getAteliers();
	
	assertEquals(ateliers.size(),2);
	
	
	}

	
	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Atelier");
		statement.execute("DROP TABLE Utilisateur");
		statement.execute("DROP TABLE Labo");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}

}

