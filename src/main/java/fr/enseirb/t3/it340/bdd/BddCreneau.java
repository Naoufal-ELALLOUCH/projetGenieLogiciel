package fr.enseirb.t3.it340.bdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BddCreneau {

	private static final Logger log = LoggerFactory.getLogger(BddCreneau.class);

	// Ajout d'un creneau
	public static void ajoutCreneau(int idAtelier, String jour, String heure, int capacite) {

		String sql = "INSERT INTO Creneau(idAtelier, jour, heure, capacite) VALUES(?,?,?,?)";

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			// String to sqlDate
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date parsed = format.parse(jour);
			java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

			// String to sqlTime
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			java.sql.Time timeValue = new java.sql.Time(formatter.parse(heure).getTime());

			statement.setInt(1, idAtelier);
			statement.setDate(2, sqlDate);
			statement.setTime(3, timeValue);
			statement.setInt(4, capacite);

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'ajouter le creneau", e);
		} 
		
	}

	// Editer un creneau
	public static void editCreneau(int idCreneau, String jour ,String heure ,int capacite) {

		String sql = "UPDATE Creneau SET (jour=? , heure=? , capacite=?) WHERE idCreneau=?";
		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, jour);
			statement.setString(2, heure);
			statement.setInt(3, capacite);
			statement.setInt(4, idCreneau);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'éditer le creneau", e);
		} 
		
	}
	
	// Supprimer un creneau
	public static void supprCreneau(int idCreneau){

	String sql = "DELETE FROM Creneau WHERE idCreneau=?";

		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, idCreneau);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de supprimer cet creneau ", e);
		}		
		
	}

	
}
