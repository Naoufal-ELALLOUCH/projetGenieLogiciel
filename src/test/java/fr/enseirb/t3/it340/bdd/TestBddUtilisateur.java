package fr.enseirb.t3.it340.bdd;

import fr.enseirb.t3.it340.modeles.Utilisateur;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.*;

public class TestBddUtilisateur {

	private final String email = "charlie@heloise.com";
	private final String mdp = "mdp";

	@Test
	public void testAjout() throws IOException, SQLException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		// Insertion
		String email =  this.email;
		String motDePasseOriginal = this.mdp;
		BddUtilisateur.ajout(email, motDePasseOriginal);

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
		rs.close();
		statement.close();
		connection.close();
	}

	@Test
	public void testAuthentification() throws IOException, SQLException, ClassNotFoundException {

		// Insertion
		String email = this.email;
		String motDePasseOriginal = this.mdp;
		BddUtilisateur.ajout(email, motDePasseOriginal);

		// Vérification
		boolean res1 = BddUtilisateur.authentification(email, motDePasseOriginal);
		assertTrue(res1);
		
		boolean res2 = BddUtilisateur.authentification(email, "mdperreur");
		assertFalse(res2);
		
		boolean res3 = BddUtilisateur.authentification("mauvais email", motDePasseOriginal);
		assertFalse(res3);
	}

	@Test
	public void testRecuperation() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		Utilisateur utilisateur;

		// Test : récupération d'un objet null
		utilisateur = BddUtilisateur.getUtilisateurByEmail(this.email);
		assertNull(utilisateur);

		// Insertion
		BddUtilisateur.ajout(this.email, this.mdp);

		// Test : récupération d'un utilisateur
		utilisateur = BddUtilisateur.getUtilisateurByEmail(this.email);
		assertNotNull(utilisateur);
		assertEquals(utilisateur.getEmail(), this.email);
		assertEquals(utilisateur.getMotDePasse(), this.mdp);
	}
	
	@Test
	public void testGetUtilisateurByIdUtilisateur() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();

		Utilisateur utilisateur;

		// Test : récupération d'un objet null
		utilisateur = BddUtilisateur.getUtilisateurByIdUtilisateur(1);
		assertNull(utilisateur);

		// Insertion
		BddUtilisateur.ajout(this.email, this.mdp);

		// Test : récupération d'un utilisateur à partir de IdUtilisateur
		utilisateur = BddUtilisateur.getUtilisateurByIdUtilisateur(1);
		assertNotNull(utilisateur);
		assertEquals(utilisateur.getEmail(), this.email);
		assertEquals(utilisateur.getMotDePasse(), this.mdp);
	}

	@After
	public void dispose() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = BddConnecteur.getConnection();
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
		connection.close();
		BddConnecteur.dispose();
	}

}
