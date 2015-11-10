package fr.enseirb.t3.it340.bdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BddUtilisateur {

	private final Logger log = LoggerFactory.getLogger(BddUtilisateur.class);

	private Connection getConnexion() throws SQLException, IOException, ClassNotFoundException {
		return BddConnecteur.getInstance().getConnexion();
	}

	public void insert(Connection connection, String email, String motDePasse) throws SQLException {

		String sql = "INSERT INTO Utilisateur(email, motDePasse) VALUES(?,?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, motDePasse);
		statement.executeUpdate();

		statement.close();

	}

	public void insert(String email, String password) {
		try {
			Connection connection = getConnexion();
			insert(connection, email, password);
		} catch (Exception e) {
			log.error("Impossible d'insérer un utilisateur dans la base de données : {}", e);
		}
	}

}
