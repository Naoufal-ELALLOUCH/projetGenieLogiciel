package fr.enseirb.t3.it340.bdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BddCreneau {

	private static final Logger log = LoggerFactory.getLogger(BddCreneau.class);

	public static java.sql.Date stringToDate(String stringDate) throws ParseException {
		// String to sqlDate
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date parsed = format.parse(stringDate);
		return new java.sql.Date(parsed.getTime());
	}

	public static java.sql.Time stringToTime(String stringTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return new java.sql.Time(formatter.parse(stringTime).getTime());
	}

	// Ajout d'un creneau
	public static void ajoutCreneau(int idAtelier, String jour, String heure, int capacite) {

		String sql = "INSERT INTO Creneau(idAtelier, jour, heure, capacite) VALUES(?,?,?,?)";

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, idAtelier);
			statement.setDate(2, stringToDate(jour));
			statement.setTime(3, stringToTime(heure));
			statement.setInt(4, capacite);

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'ajouter le creneau", e);
		} 
		
	}

	// Editer un creneau
	public static void editCreneau(int idCreneau, String jour, String heure, int capacite) {

		String sql = "UPDATE Creneau SET jour=?, heure=?, capacite=? WHERE idCreneau=?";
		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, stringToDate(jour));
			statement.setTime(2, stringToTime(heure));
			statement.setInt(3, capacite);
			statement.setInt(4, idCreneau);

			statement.executeUpdate();
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

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de supprimer cet creneau ", e);
		}		
		
	}

	
}
