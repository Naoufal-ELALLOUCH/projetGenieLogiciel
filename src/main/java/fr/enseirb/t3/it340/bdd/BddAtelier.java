package fr.enseirb.t3.it340.bdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

public class BddAtelier {
private final org.slf4j.Logger log = LoggerFactory.getLogger(BddUtilisateur.class);

	
	private Connection getConnexion() throws SQLException, IOException, ClassNotFoundException {
		return BddConnecteur.getInstance().getConnexion();
	}
	
	public int getNouveauTdAtelier(){
		
		try {
			String idAtelierReq = "SELECT * FROM Atelier ";

			Connection connection = getConnexion();
			
			PreparedStatement statement = connection.prepareStatement(idAtelierReq);
			
			ResultSet resultat = statement.executeQuery();
			
			return resultat.getFetchSize()+1;
		} catch (Exception e) {
			log.error("Connexion Impossible à la base de données | Impossible d'obtenir idAtelier", e);
			return 0;
		} 
}	
}
