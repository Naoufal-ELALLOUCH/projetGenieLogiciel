package fr.enseirb.t3.it340.bdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.enseirb.t3.it340.modeles.Laboratoire;
import fr.enseirb.t3.it340.modeles.Utilisateur;

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

			log.info("Nouveau labo : {}", nom);

			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible d'insérer un laboratoire dans la base de données : {}", e);
		}
	}

	public static Laboratoire getLaboByIdUtilisateur(int idUtilisateur) {
		Laboratoire labo = null;
		Utilisateur utilisateur = null;
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql =    "SELECT Utilisateur.email AS email, " +
							"Utilisateur.motDePasse AS motDePasse, " +
							"Labo.idLabo AS idLabo, " +
							"Labo.nom AS nom " +
							"FROM Utilisateur, Labo " +
							"WHERE Utilisateur.idUtilisateur = Labo.idUtilisateur " +
							"AND Utilisateur.idUtilisateur = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idUtilisateur);
			ResultSet resultat = statement.executeQuery();

			if (!BddConnecteur.checkAccuracy(resultat, 1))
				return null;

			int idLabo = resultat.getInt("idLabo");
			String nom = resultat.getString("nom");
			String email = resultat.getString("email");
			String motDePasse = resultat.getString("motDePasse");

			labo = new Laboratoire(idUtilisateur, email, motDePasse, idLabo, nom);

			resultat.close();
			statement.close();
			connection.close();


		} catch (Exception e) {
			log.error("Impossible de récupérer un labo à partir de son id : {}", e);
		}
		return labo;
	}

	public static Laboratoire getLaboByIdLabo(int idLabo){
		Laboratoire labo = null;
		Utilisateur utilisateur = null;
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "SELECT * FROM Labo WHERE idLabo = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idLabo);
			ResultSet resultat = statement.executeQuery();

			if (!BddConnecteur.checkAccuracy(resultat, 1))
				return null;

			int idUtilisateur = resultat.getInt("idUtilisateur");
			String nom = resultat.getString("nom");
			
			//  récupération de l'utilisateur à partir de IdUtilisateur
			utilisateur = BddUtilisateur.getUtilisateurByIdUtilisateur(idUtilisateur);
			String email = utilisateur.getEmail();
			String mpd = utilisateur.getMotDePasse();
			labo = new Laboratoire(idUtilisateur, email, mpd, idLabo, nom);
		
			resultat.close();
			statement.close();
			connection.close();
			
			
		} catch (Exception e) {
			log.error("Impossible de récupérer un labo à partir de son id : {}", e);
		}
		return labo;
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
