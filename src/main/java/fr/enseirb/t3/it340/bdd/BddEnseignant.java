package fr.enseirb.t3.it340.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BddEnseignant {
	private static final Logger log = LoggerFactory.getLogger(BddLabo.class);

	public static void ajout(int idUtilisateur, String nom, String prenom) {
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "INSERT INTO Enseignant(idUtilisateur, nom, prenom) VALUES(?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUtilisateur);
			statement.setString(2, nom);
			statement.setString(3, prenom);
			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible d'insérer un enseignant dans la base de données : {}", e);
		}
	}

	public static boolean isEnseignant(int idUtilisateur) {
		Connection connection = null;
		ResultSet rs = null;
		boolean isEnseignant = true;

		try {
			connection = BddConnecteur.getConnection();
			String sql = "SELECT * FROM Enseignant WHERE idUtilisateur = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUtilisateur);
			rs = statement.executeQuery();

			isEnseignant = BddConnecteur.checkAccuracy(rs, 1);

			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible de savoir si l'utilisateur est un enseignant : {}", e);
		}

		return isEnseignant;
	}
}
