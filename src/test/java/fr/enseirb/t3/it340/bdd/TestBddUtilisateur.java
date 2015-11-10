package fr.enseirb.t3.it340.bdd;

import org.h2.*;
import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

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
	public void testInsert() throws IOException, SQLException {
		BddUtilisateur bddUtilisateur = new BddUtilisateur();
		Connection connection = getConnection();

		// Insertion
		bddUtilisateur.insert(connection, "charlie@heloise.com", "falleri");

		// Vérification
		String sql = "SELECT COUNT(*) FROM Utilisateur WHERE email=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "charlie@heloise.com");

		ResultSet rs = statement.executeQuery();
		System.out.println(rs);
		statement.close();
	}

}
