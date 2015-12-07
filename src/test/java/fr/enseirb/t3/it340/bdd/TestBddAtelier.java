package fr.enseirb.t3.it340.bdd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Utilisateur;

public class TestBddAtelier {

	private final String email = "charlie@heloise.com";
	private final String mdp = "mdp";

	@Test
	public void testAjout() throws IOException, SQLException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		
		BddUtilisateur.ajout("labri@labri.fr", "labri");
		BddLabo.ajout(1, "Labri");
		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","",null);
		
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
		//atelier = BddAtelier.getAtelierById(1);
		//assertNull(atelier);
	
		// Insertion

		BddAtelier.ajoutAtelier(1, "A la poursuite d'ennemis invisibles", "Sciences de la vie ", "Campus Carreire (Hôpital Pellegrin)", "Labo MFP", "","", "","",null);
	
		// Test : récupération d'un atelier
		atelier = BddAtelier.getAtelierById(1);
		assertNotNull(atelier);
		
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
