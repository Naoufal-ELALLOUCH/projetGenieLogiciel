package fr.enseirb.t3.it340.bdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BddLabo {

	private static final Logger log = LoggerFactory.getLogger(BddLabo.class);

	public static void ajout(int idUtilisateur, String nom) {
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "INSERT INTO Labo(idUtilisateur, nom) VALUES(?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUtilisateur);
			statement.setString(2, nom);
			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible d'insérer un laboratoire dans la base de données : {}", e);
		}
	}

	public static boolean isLabo(int idUtilisateur) {
		Connection connection = null;
		ResultSet rs = null;
		boolean isLabo = true;

		try {
			connection = BddConnecteur.getConnection();
			String sql = "SELECT * FROM Labo WHERE idUtilisateur = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUtilisateur);
			rs = statement.executeQuery();

			isLabo = BddConnecteur.checkAccuracy(rs, 1);

			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible de savoir si l'utilisateur est un laboratoire : {}", e);
		}

		return isLabo;
	}
}
