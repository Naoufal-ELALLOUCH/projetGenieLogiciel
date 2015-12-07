package fr.enseirb.t3.it340.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.enseirb.t3.it340.modeles.Utilisateur;

public class BddEnregistrement {
	private static final Logger log = LoggerFactory.getLogger(BddEnregistrement.class);

	public static void enregistrement(int idEnseignant, int idCreneau, int nbInscrits ) {
		
		//String sql = "SELECT email, motDePasse FROM Utilisateur WHERE email=? AND motDePasse=?";

		
		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "INSERT INTO Enregistrement(idEnseignant, idCreneau,nbInscrits) VALUES(?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEnseignant);
			statement.setInt(2, idCreneau);
			statement.setInt(3, nbInscrits);

			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (Exception e) {
			log.error("Impossible d'insérer l'enregistrement d'un enseignant à un créneau : {}", e);
		}
	}
	
//	public static List<Integer> getListeIdEnseignantByIdCreneau(String email) {
		
//		Utilisateur utilisateur = null;
//		
//		try {
//			Connection connection = BddConnecteur.getConnection();
//			String sql = "SELECT idUtilisateur, email, motDePasse FROM Utilisateur WHERE email=? ";
//
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, email);
//			ResultSet resultat = statement.executeQuery();
//
//			if (!BddConnecteur.checkAccuracy(resultat, 1))
//				return null;
//
//			int idUtilisateur  = resultat.getInt("idUtilisateur");
//			String motDePasse = resultat.getString("motDePasse");
//
//			resultat.close();
//			statement.close();
//			connection.close();
//
//			utilisateur = new Utilisateur(idUtilisateur, email, motDePasse);
//		} catch (Exception e) {
//			log.error("Impossible de récupérer un utilisateur à partir de son email : {}", e);
//		}
//		return utilisateur;
//	}
}
