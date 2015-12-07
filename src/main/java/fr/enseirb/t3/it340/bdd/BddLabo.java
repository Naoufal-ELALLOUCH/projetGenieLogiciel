package fr.enseirb.t3.it340.bdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
