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

	private final Logger log = LoggerFactory.getLogger(BddUtilisateur.class);

	private Connection getConnexion() throws SQLException, IOException, ClassNotFoundException {
		return BddConnecteur.getInstance().getConnexion();
	}

	public void ajout(Connection connection, String email, String motDePasse) throws SQLException {

		String sql = "INSERT INTO Utilisateur(email, motDePasse) VALUES(?,?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, motDePasse);
		statement.executeUpdate();

		statement.close();

	}

	public void ajout(String email, String password) {
		try {
			Connection connection = getConnexion();
			ajout(connection, email, password);
		} catch (Exception e) {
			log.error("Impossible d'insérer un utilisateur dans la base de données : {}", e);
		}
	}
	
	public boolean authentification(Connection connection, String email, String motDePasse) throws SQLException {

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
			return true;
			
		}
		
		statement.close();
		return false;

	}
//	public Utilisateur getUtilisateurByEmail(String email) {
//
//	}

}
