package fr.enseirb.t3.it340.bdd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import fr.enseirb.t3.it340.modeles.Atelier;
import fr.enseirb.t3.it340.modeles.Creneau;
import fr.enseirb.t3.it340.modeles.Enregistrement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.enseirb.t3.it340.modeles.Utilisateur;

public class BddEnregistrement {
	private static final Logger log = LoggerFactory.getLogger(BddEnregistrement.class);

	public static void enregistrement(int idEnseignant, int idCreneau, int nbAInscrire) {
		try {
			Connection connection = BddConnecteur.getConnection();

			String sql1 = "SELECT capacite FROM Creneau WHERE idCreneau = ? ";
			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setInt(1, idCreneau);

			String sql2 = "SELECT nbInscrits FROM Enregistrement WHERE idCreneau = ? AND idEnseignant = ? ";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(1, idCreneau);
			statement2.setInt(2, idEnseignant);

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

			rs1.close();
			rs2.close();
			statement1.close();
			statement2.close();

			if (count == 0) {
				String sql = "INSERT INTO Enregistrement(idEnseignant, idCreneau,nbInscrits) VALUES(?,?,?)";

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idEnseignant);
				statement.setInt(2, idCreneau);
				statement.setInt(3, nbAInscrire);

				if(nbInscrits + nbAInscrire <= capacite)
					statement.executeUpdate();

				statement.close();
				connection.close();
			}

			if (count != 0){
				String sql = "UPDATE Enregistrement SET nbInscrits = ? WHERE idEnseignant = ? AND idCreneau = ?";

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, nbAInscrire+nbInscrits);
				statement.setInt(2, idEnseignant);
				statement.setInt(3, idCreneau);

				if(nbInscrits + nbAInscrire <= capacite)
					statement.executeUpdate();

				statement.close();
			}
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de faire l'enregistrement : {}", e);
		}
	}

	public static List<Enregistrement> getEnregistrementsByIdEnseignant(int idEnseignant) {
		List<Enregistrement> infos = new ArrayList<Enregistrement>();

		Connection connection = null;
		try {
			connection = BddConnecteur.getConnection();

			String sql = "SELECT * FROM Enregistrement WHERE idEnseignant = ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEnseignant);

			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Map<String, String> info = new HashMap<String, String>();
				int nbInscrits = rs.getInt("nbInscrits");
				int idCreneau = rs.getInt("idCreneau");

				Atelier atelier = BddCreneau.getAtelierByIdCreneau(idCreneau);
				Creneau creneau = atelier.getCreneaux().get(idCreneau);
				Date date = creneau.getDate();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm");

				String dateFormat = info.put("date", format.format(date));

				infos.add(new Enregistrement(nbInscrits, atelier, dateFormat));
			}
		} catch (Exception e) {
			log.error("Impossible d'avoir la liste des créneaux en fonction de l'enseignant {}", e);
		}

		return infos;
	}

	public static List<Integer> getListeIdEnseignantByIdCreneau(int idCreneau) {

		List<Integer> idEnseignant =  new ArrayList<Integer>() ;

		try {
			Connection connection = BddConnecteur.getConnection();
			// Récupération de la liste des enseignants pour un créneau
			String sql = "SELECT Enseignant.idEnseignant FROM Enseignant, Enregistrement WHERE Enregistrement.idCreneau=? AND Enregistrement.idEnseignant = Enseignant.idEnseignant ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idCreneau);
			ResultSet resultat = statement.executeQuery();
			while(resultat.next()) {
				idEnseignant.add(resultat.getInt("idEnseignant"));
			}

			resultat.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			log.error("Impossible de récupérer la liste des idEnseignant : {}", e);
		}
		return idEnseignant;
	}
}
