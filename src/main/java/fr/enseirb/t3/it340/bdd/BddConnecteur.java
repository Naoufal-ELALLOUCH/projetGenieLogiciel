package fr.enseirb.t3.it340.bdd;

import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BddConnecteur {

	private final Logger log = LoggerFactory.getLogger(BddConnecteur.class);

	private static BddConnecteur bddConnecteur = null;
	private static JdbcConnectionPool pool;

	private String propertiesFile = "bdd.properties";

	public BddConnecteur() throws ClassNotFoundException, SQLException, IOException {

		Properties properties = getProperties();

		String url = properties.getProperty("url");
		String login = properties.getProperty("login");
		String password = properties.getProperty("password");

		// Ajout du driver H2
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);

		// Génération d'une "connection pool"
		pool = JdbcConnectionPool.create("jdbc:h2:" + url, login, password);
		log.info("Création d'un groupe de connexions à la base de données réussie");
	}

	private Properties getProperties() throws IOException {
		// Récupération des paramètres de la base de données
		InputStream is = getClass().getClassLoader().getResourceAsStream(propertiesFile);
		Properties properties = new Properties();

		if (is == null) {
			throw new FileNotFoundException();
		} else {
			properties.load(is);
		}

		return properties;
	}

	public Connection getConnexion() {
		Connection connexion = null;
		try {
			connexion = pool.getConnection();
		} catch (SQLException e) {
			log.error("Impossible d'obtenir une connexion depuis le groupe de connexions");
		}

		return connexion;
	}

	public static BddConnecteur getInstance() throws SQLException, IOException, ClassNotFoundException {
		if (bddConnecteur == null) {
			bddConnecteur = new BddConnecteur();
		}
		return bddConnecteur;
	}
}