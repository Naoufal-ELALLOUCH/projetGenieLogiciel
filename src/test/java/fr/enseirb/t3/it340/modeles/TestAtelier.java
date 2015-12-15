package fr.enseirb.t3.it340.modeles;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAtelier {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestAtelier() {
	
		Creneau creneau1 = new Creneau(1, new Date(11112011), 50);
		Creneau creneau2 = new Creneau(2, new Date(11112011), 55);
		Creneau creneau3 = new Creneau(1, new Date(11112011), 60);
		Map<Integer, Creneau> creneaux = new HashMap<Integer, Creneau>();
		
		
		
		Atelier atelier = new Atelier(1, 1, "", "", "", "", "", "", "", "", creneaux, "");
		
		atelier.ajoutCreneau(creneau1);
		atelier.ajoutCreneau(creneau2);
		assertEquals(atelier.getCreneaux().get(1), creneau1);
		
		atelier.modifierCreneau(1, creneau3);
		assertEquals(atelier.getCreneaux().get(1), creneau3);
		
		assertEquals(atelier.getCreneaux().size(), 2);
		
		atelier.supprimerCreneau(2);
		
		assertEquals(atelier.getCreneaux().size(),1);
		
		atelier.setAdresse("add");
		atelier.setCibles("cible");
		atelier.setOrateurs("orateurs");
		atelier.setPartenaires("partenaires");
		atelier.setRemarques("remarques");
		atelier.setStatut("statut");
		atelier.setThemes("themes");
		atelier.setTitre("titre");
		atelier.setZone("zone");
		
		assertEquals(atelier.getAdresse(),"add");
		assertEquals(atelier.getCible(),"cible");
		assertEquals(atelier.getOrateurs(),"orateurs");
		assertEquals(atelier.getPartenaires(),"partenaires");
		assertEquals(atelier.getRemarques(),"remarques");
		assertEquals(atelier.getStatut(), "statut");
		assertEquals(atelier.getThemes(),"themes");
		assertEquals(atelier.getTitre(),"titre");
		assertEquals(atelier.getZone(),"zone");
		
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
