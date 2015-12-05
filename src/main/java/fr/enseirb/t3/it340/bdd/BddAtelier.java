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

public class BddAtelier {
	private static final Logger log = LoggerFactory.getLogger(BddUtilisateur.class);
	// Ajout d'un atelier
	public static void ajoutAtelier(int idLabo, String titre, String themes, String zone, String orateurs, String partenaires, String cibles, String remarques, Map<Integer, Creneau> creneaux) {

		String sql = "INSERT INTO Atelier(idLabo, titre, themes, zone, orateurs, partenaires, cibles, remarques) VALUES(?,?,?,?,?,?,?,?)";

		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, idLabo);
			statement.setString(2, titre);
			statement.setString(3, themes);
			statement.setString(4, zone);
			statement.setString(5, orateurs);
			statement.setString(6, partenaires);
			statement.setString(7, cibles);
			statement.setString(8, remarques);

			// TODO creneaux

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'ajouter un nouvel atelier {}", e);
		} 
		
	}
// Obtenir un atelier en fonction de son id
	public static Atelier getAtelierById(int idAtelier) {

		Atelier atelier = null;

		try {
			Connection connection = BddConnecteur.getConnection();
			String sql = "SELECT idLabo, titre, themes, zone, orateurs, partenaires, cibles, remarques FROM Atelier WHERE idAtelier = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAtelier);
			ResultSet resultat = statement.executeQuery();

			if (!BddConnecteur.checkAccuracy(resultat, 1))
				return null;

			int idLabo  = resultat.getInt("idLabo");
			String titre = resultat.getString("titre");
			String themes = resultat.getString("themes");
			String zone = resultat.getString("zone");
			String orateurs = resultat.getString("orateurs");
			String partenaires = resultat.getString("partenaires");
			String cibles = resultat.getString("cibles");
			String remarques = resultat.getString("remarques");

			atelier = new Atelier(idAtelier, idLabo, titre);
			atelier.setThemes(themes);
			atelier.setZone(zone);
			atelier.setOrateurs(orateurs);
			atelier.setPartenaires(partenaires);
			atelier.setCibles(cibles);

			// TODO creneaux

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de récupérer un utilisateur à partir de son email : {}", e);
		}

		return atelier;
	}
	
	// Modifier un atelier
	public void editAtelier(int idAtelier, String titre, String themes, String zone, String orateurs, String partenaires, String cibles, String remarques, String status){
		
	String editReq = "UPDATE Atelier SET (titre=? , themes=?, zone=?, orateurs=?, partenaires=?, cibles=?, remarques=?) WHERE idAtelier=?";

		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(editReq);

			statement.setString(1, titre);
			statement.setString(2, themes);
			statement.setString(3, zone);
			statement.setString(4, orateurs);
			statement.setString(5, partenaires);
			statement.setString(6, cibles);
			statement.setString(7, remarques);
			statement.setInt(8, idAtelier);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'�diter l'atelier ", e);
		} 

		
	}
	
	// Supprimer un atelier 
	public static void supprAtelier(int idAtelier){

	String supprReq = "DELETE FROM Atelier WHERE idAtelier=?";

		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(supprReq);

			statement.setInt(1, idAtelier);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de supprimer cet Atelier ", e);
		}		
		
	}
	
	public static void changeStatut(int idAtelier, String statut){

	String changeStatReq = "UPDATE Atelier Set statut=? WHERE idAtelier=?";
		
		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(changeStatReq);
			
			statement.setString(1, statut);
			statement.setInt(2, idAtelier);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de changer le statut de l'atelier ", e);
		}		
		
		
		
	}
	
}
