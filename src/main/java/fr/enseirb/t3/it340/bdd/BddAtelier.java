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
	
	public void ceerAtelier(int idLabo , String titre , String themes , String zone ,String orateurs , String partenaires , String cibles , String remarques ){

		String creerReq = "INSERT INTO Atelier(idAtelier , idLabo , titre , themes , zone , orateurs , partenaires , cibles , remarques) VALUES(?,?,?,?,?,?,?)";

		
		try {
			Connection connection = getConnexion();
			PreparedStatement statement = connection.prepareStatement(creerReq);
			int NouveauIdAtelier = getNouveauTdAtelier();
					
			statement.setInt(1 , NouveauIdAtelier);
			statement.setInt(2 , idLabo);
			statement.setString(3 , titre);
			statement.setString(4 , themes);
			statement.setString(5 , zone);
			statement.setString(6 , orateurs);
			statement.setString(7 , partenaires);
			statement.setString(8 , cibles);
			statement.setString(9 , remarques);
			statement.executeQuery();
			 
				
		} catch (Exception e) {
			log.error("Connexion Impossible à la base de données", e);
		} 
		
	}

	
}
