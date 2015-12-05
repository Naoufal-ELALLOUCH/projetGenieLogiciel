package fr.enseirb.t3.it340.bdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BddCreneau {
	private static final Logger log = LoggerFactory.getLogger(BddUtilisateur.class);
	// Ajout d'un atelier
	public static void ajoutCreneau(int idAtelier,String jour ,String heure ,int capacite) {

		String ajoutCreneauReq = "INSERT INTO Creneau(idAtelier , jour , heure , capacite) VALUES(?,?,?,?)";

		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(ajoutCreneauReq);

			statement.setInt(1, idAtelier);
			statement.setString(2, jour);
			statement.setString(3, heure);
			statement.setInt(4, capacite);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'ajouter le creneau", e);
		} 
		
	}

	
}
