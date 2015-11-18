package fr.enseirb.t3.it340.bdd;

import org.h2.*;
import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class TestBddUtilisateur {

	public static Connection getConnection() throws IOException, SQLException {
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("bddTest.properties"));

		String url = properties.getProperty("url");
		String login = properties.getProperty("login");
		String password = properties.getProperty("password");

		// Ajout du driver H2
		DriverManager.registerDriver(new Driver());

		// Génération d'une "connection pool"
		JdbcConnectionPool pool = JdbcConnectionPool.create("jdbc:h2:" + url, login, password);

		// Exécution du script SQL d'initialisation
		Connection connection = pool.getConnection();
		Statement stat = connection.createStatement();

		stat.execute("runscript from 'init.sql'");

		return connection;
	}

	@Test
	public void testAjout() throws IOException, SQLException {
		BddUtilisateur bddUtilisateur = new BddUtilisateur();
		Connection connection = getConnection();

		// Insertion
		String email =  "charlie@heloise.com";
		String motDePasseOriginal = "mdp";
		bddUtilisateur.ajout(connection, email, motDePasseOriginal);

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
		statement.execute("DROP TABLE Utilisateur");
		statement.close();
	}

}
