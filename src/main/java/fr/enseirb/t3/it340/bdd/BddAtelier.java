package fr.enseirb.t3.it340.bdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BddAtelier {
	private static final Logger log = LoggerFactory.getLogger(BddUtilisateur.class);
	// Ajout d'un atelier
	public static void ajoutAtelier(int idLabo, String titre, String themes, String zone, String adresse, String orateurs, String partenaires, String cible, String remarques) {

		String sql = "INSERT INTO Atelier(idLabo, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques, statut) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, idLabo);
			statement.setString(2, titre);
			statement.setString(3, themes);
			statement.setString(4, zone);
			statement.setString(5, adresse);
			statement.setString(6, orateurs);
			statement.setString(7, partenaires);
			statement.setString(8, cible);
			statement.setString(9, remarques);
			statement.setString(10,"PROPOSE");

			statement.executeUpdate();
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
			String sql = "SELECT idLabo, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques, statut FROM Atelier WHERE idAtelier = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAtelier);
			ResultSet resultat = statement.executeQuery();

			if (!BddConnecteur.checkAccuracy(resultat, 1))
				return null;

			int idLabo  = resultat.getInt("idLabo");
			String titre = resultat.getString("titre");
			String themes = resultat.getString("themes");
			String zone = resultat.getString("zone");
			String adresse = resultat.getString("adresse");
			String orateurs = resultat.getString("orateurs");
			String partenaires = resultat.getString("partenaires");
			String cible = resultat.getString("cible");
			String remarques = resultat.getString("remarques");
			String statut = resultat.getString("statut");

			Map<Integer, Creneau> creneaux = BddCreneau.getCreneauxByIdAtelier(idAtelier);
			atelier = new Atelier(idAtelier, idLabo, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques, creneaux, statut);

			statement.executeQuery();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de récupérer un atelier à partir de son id : {}", e);
		}

		return atelier;
	}

	public static void editAtelier(Atelier atelier) {
		int idAtelier = atelier.getIdAtelier();
		String titre = atelier.getTitre();
		String themes = atelier.getThemes();
		String zone = atelier.getZone();
		String adresse = atelier.getAdresse();
		String orateurs = atelier.getOrateurs();
		String partenaires = atelier.getPartenaires();
		String cible = atelier.getCible();
		String remarques = atelier.getRemarques();
		editAtelier(idAtelier, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques);
	}

	// Modifier un atelier
	public static void editAtelier(int idAtelier, String titre, String themes, String zone, String adresse, String orateurs, String partenaires, String cible, String remarques){
		
		String editReq = "UPDATE Atelier SET titre=?, themes=?, zone=?, orateurs=?, adresse=?, partenaires=?, cible=?, remarques=? WHERE idAtelier=?";
		
		try {

			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(editReq);

			statement.setString(1, titre);
			statement.setString(2, themes);
			statement.setString(3, zone);
			statement.setString(4, orateurs);
			statement.setString(5, adresse);
			statement.setString(6, partenaires);
			statement.setString(7, cible);
			statement.setString(8, remarques);
			statement.setInt(9, idAtelier);

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'éditer l'atelier ", e);
		} 

		
	}
	
	// Supprimer un atelier 
	public static void supprAtelier(int idAtelier){

		String supprReq = "DELETE FROM Atelier WHERE idAtelier=?";

		BddCreneau.supprCreneauxByIdAtelier(idAtelier);

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(supprReq);

			statement.setInt(1, idAtelier);

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de supprimer cet Atelier ", e);
		}
		
	}

	public static void supprAtelierByIdLabo(int idLabo){

		String supprReq = "DELETE FROM Atelier WHERE idLabo=?";

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(supprReq);

			statement.setInt(1, idLabo);

			statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de supprimer cet Atelier par idLabo ", e);
		}
		
	}
	
	public static void changeStatut(int idAtelier, String statut) {
		
		String changeStatReq = "UPDATE Atelier Set statut=? WHERE idAtelier=?";

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(changeStatReq);

			statement.setString(1, statut);
			statement.setInt(2, idAtelier);
			
			if (statut.equals("PROPOSE") || statut.equals("VALIDE")  || statut.equals("CLOTURE"))
				statement.executeUpdate();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de changer le statut de l'atelier ", e);
		}
	}

	private static Map<Integer, Atelier> getAteliers(String sql) {
		Map<Integer, Atelier> ateliers = new HashMap<Integer, Atelier>();

		try {
			Connection connection = BddConnecteur.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while(result.next()) {

				int idAtelier = result.getInt(1);
				int idLabo = result.getInt(2);
				String titre = result.getString(3);
				String themes = result.getString(4);
				String zone = result.getString(5);
				String adresse = result.getString(6);
				String orateurs = result.getString(7);
				String partenaires = result.getString(8);
				String cible = result.getString(9);
				String remarques = result.getString(10);
				String statut = result.getString(11);
				// optimise considérablement les ressources en commentant
				// Map<Integer, Creneau> creneaux = BddCreneau.getCreneauxByIdAtelier(idAtelier);
				Atelier atelier = new Atelier(idAtelier, idLabo, titre, themes, zone, adresse, orateurs, partenaires, cible, remarques, null, statut);

				ateliers.put(atelier.getIdAtelier(), atelier);
			}

			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible d'avoir la liste de tous les ateliers ", e);
		}

		return ateliers;
	}
	

	public static Map<Integer, Atelier> getAteliers() {
		return getAteliers("SELECT * FROM Atelier");
	}
	
	public static Map<Integer, Atelier> getAteliersByIdLabo(int idLaboArg) {
		return getAteliers("SELECT * FROM Atelier WHERE idLabo=" + idLaboArg);
	}
	
}
