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

	public static void enregistrement(int idEnseignant, int idCreneau, int nbAInscrire ) {
		try {
		Connection connection = BddConnecteur.getConnection();

		String sql1 = "SELECT capacite FROM Creneau WHERE idCreneau = ? ";
		PreparedStatement statement1 = connection.prepareStatement(sql1);
		statement1.setInt(1, idCreneau);
		
		String sql2 = "SELECT nbInscrits FROM Enregistrement WHERE idCreneau = ? AND idEnseignant = ? ";
		PreparedStatement statement2 = connection.prepareStatement(sql2);
		statement2.setInt(1, idCreneau);
		statement2.setInt(2, idEnseignant);


		//statement.executeQuery();
		ResultSet rs1 = statement1.executeQuery();
		ResultSet rs2 = statement2.executeQuery();

		int capacite = 0;
		int nbInscrits = 0;
		int count = 0;
		while(rs1.next()) {
			capacite = rs1.getInt("capacite");
		}
		while(rs2.next()) {
			nbInscrits = rs2.getInt("nbInscrits");
			count++;
		}

		statement1.close();
		statement2.close();
		connection.close();
			try {
				connection = BddConnecteur.getConnection();

				if (count==0){
					String sql = "INSERT INTO Enregistrement(idEnseignant, idCreneau,nbInscrits) VALUES(?,?,?)";
	
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, idEnseignant);
					statement.setInt(2, idCreneau);
					statement.setInt(3, nbAInscrire);
					
					if(nbInscrits+nbAInscrire<=capacite)
						statement.executeUpdate();

					statement.close();
					connection.close();
				}
				if (count!=0){
					String sql = "UPDATE Enregistrement SET nbInscrits = ? WHERE idEnseignant = ? AND idCreneau = ?";
	
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, nbAInscrire+nbInscrits);
					statement.setInt(2, idEnseignant);
					statement.setInt(3, idCreneau);
				
					if(nbInscrits+nbAInscrire<=capacite)
						statement.executeUpdate();
					
					statement.close();
					connection.close();
				}

			} catch (Exception e) {
				log.error("Impossible d'insérer l'enregistrement d'un enseignant à un créneau : {}", e);
			}
		} catch (Exception e) {
			log.error("Impossible de récupérer le nombre de place restante : {}", e);
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
