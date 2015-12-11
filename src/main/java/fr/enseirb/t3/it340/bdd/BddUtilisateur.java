package fr.enseirb.t3.it340.bdd;

import fr.enseirb.t3.it340.modeles.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BddUtilisateur {

	private static final Logger log = LoggerFactory.getLogger(BddUtilisateur.class);

	public static void ajout(String email, String motDePasse) {
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "INSERT INTO Utilisateur(email, motDePasse) VALUES(?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, motDePasse);
			statement.executeUpdate();

			log.info("Nouvel utilisateur : {}", email);

			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible d'insérer un utilisateur dans la base de données : {}", e);
		}
	}
	
	public static boolean authentification(String email, String motDePasse) {
		boolean authentification = false;

		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "SELECT email, motDePasse FROM Utilisateur WHERE email=? AND motDePasse=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, motDePasse);
			ResultSet resultat = statement.executeQuery();

			//on place le curseur sur le dernier tuple
			resultat.last();
			//on récupère le numéro de la ligne
			int nombreLignes = resultat.getRow();

			if (nombreLignes == 1) {
				authentification = true;
			}

			resultat.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible d'authentifier un utilisateur à partir de la base de données : {}", e);
		}

		return authentification;
	}

	public static Utilisateur getUtilisateurByEmail(String email) {
		
		Utilisateur utilisateur = null;
		
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "SELECT idUtilisateur, email, motDePasse FROM Utilisateur WHERE email=? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet resultat = statement.executeQuery();

			if (!BddConnecteur.checkAccuracy(resultat, 1))
				return null;

			int idUtilisateur  = resultat.getInt("idUtilisateur");
			String motDePasse = resultat.getString("motDePasse");

			resultat.close();
			statement.close();
			connection.close();

			utilisateur = new Utilisateur(idUtilisateur, email, motDePasse);
		} catch (Exception e) {
			log.error("Impossible de récupérer un utilisateur à partir de son email : {}", e);
		}
		return utilisateur;
	}

}
