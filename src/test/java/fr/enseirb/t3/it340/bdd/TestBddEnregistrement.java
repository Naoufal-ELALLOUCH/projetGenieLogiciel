package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Test;

public class TestBddEnregistrement {
	@Test
	public void testEnregistrement() throws IOException, SQLException, ClassNotFoundException {
		
		Connection connection = BddConnecteur.getConnection();

		int idAtelier = 1;
		String jour = "2015-05-12";
		String heure = "13:00";
		int capacite = 30;
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
		BddCreneau.ajoutCreneau(1, jour, heure, capacite);

		BddUtilisateur.ajout("enseignant@labri.fr", "labri");
		BddEnseignant.ajout(2, "nom", "prenom");

		//Insertion d'un nombre correct
		BddEnregistrement.enregistrement(1, 1, 20);
		
		// Vérification
		String sql = "SELECT nbInscrits FROM Enregistrement WHERE idEnregistrement=1";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		int count = 0;
		int nbInscrits = 0;
		while(rs.next()) {
			nbInscrits = rs.getInt("nbInscrits");
			count++;
		}
		
		assertEquals(count, 1);
		assertEquals(nbInscrits, 20 );
		
		//Insertion d'un nombre égal à la capcité
		BddEnregistrement.enregistrement(1, 1, 10);

		// Vérification
		sql = "SELECT nbInscrits FROM Enregistrement WHERE idEnregistrement=1";
		statement = connection.createStatement();
		rs = statement.executeQuery(sql);

		count = 0;
		nbInscrits = 0;
		while(rs.next()) {
			nbInscrits = rs.getInt("nbInscrits");
			count++;
		}

		assertEquals(count, 1);
		assertEquals(nbInscrits, 30 );

		//Insertion d'un nombre supérieur à la capcité
		BddEnregistrement.enregistrement(1, 1, 10);

		// Vérification
		sql = "SELECT nbInscrits FROM Enregistrement WHERE idEnregistrement=1";
		statement = connection.createStatement();
		rs = statement.executeQuery(sql);

		count = 0;
		nbInscrits = 0;
		while(rs.next()) {
			nbInscrits = rs.getInt("nbInscrits");
			count++;
		}

		assertEquals(count, 1);
		assertEquals(nbInscrits, 30 );

		// Fermeture
		rs.close();
		statement.close();
		connection.close();
	}

	@Test
	public void testGetListeIdEnseignantByIdCreneau() throws IOException, SQLException, ClassNotFoundException {
		
		Connection connection = BddConnecteur.getConnection();

		List<Integer> idEnseignant =  new ArrayList<Integer>() ;

		
		String jour = "2015-12-05";
		String heure = "13:00";
		int capacite = 30;
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
		BddCreneau.ajoutCreneau(1, jour, heure, capacite);

		BddUtilisateur.ajout("enseignant1@labri.fr", "labri");
		BddEnseignant.ajout(2, "nom1", "prenom1");
		
		BddUtilisateur.ajout("enseignant2@labri.fr", "labri");
		BddEnseignant.ajout(3, "nom2", "prenom2");
		

		BddEnregistrement.enregistrement(1, 1, 20);
		BddEnregistrement.enregistrement(2, 1, 5);

		// Récupération de la liste des enseignants pour un créneau
		idEnseignant = BddEnregistrement.getListeIdEnseignantByIdCreneau(1);
		
		// Vérification
		ListIterator<Integer> it = idEnseignant.listIterator() ;
		Integer count =1;
		 while(it.hasNext()) {
			   assertEquals(it.next(), count);
			   count ++;
			}
	}
	
	@Test
	public void testSupprEnregistrementByIdCreneau() throws IOException, SQLException, ClassNotFoundException {

		Connection connection = BddConnecteur.getConnection();
		
		String jour = "2015-12-05";
		String heure = "13:00";
		String heure1 = "14:00";
		int capacite = 30;
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","");
		BddCreneau.ajoutCreneau(1, jour, heure, capacite);
		BddCreneau.ajoutCreneau(1, jour, heure1, capacite);

		BddEnseignant.ajout(1, "nom1", "prenom1");
		
		// Insertion
		BddEnregistrement.enregistrement(1, 1, 20);
		BddEnregistrement.enregistrement(1, 2, 10);
		
		// Suppression
		BddEnregistrement.supprEnregistrementByIdCreneau(1);
		
		// Vérification
		String sql = "SELECT idEnseignant FROM Enregistrement WHERE idCreneau = 1 ";
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
		statement.execute("DROP TABLE Enseignant");
		statement.execute("DROP TABLE Enregistrement");
		statement.execute("DROP TABLE Atelier");
		statement.execute("DROP TABLE Utilisateur");
		statement.execute("DROP TABLE Labo");

		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}
}
