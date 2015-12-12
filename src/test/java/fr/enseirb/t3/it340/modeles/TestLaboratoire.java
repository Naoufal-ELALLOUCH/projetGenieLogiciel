package fr.enseirb.t3.it340.modeles;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLaboratoire {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLaboratoire() {

		Atelier atelier1 = new Atelier(1, 1, "titre1");
		Atelier atelier2 = new Atelier(2, 2, "titre2");
		Map<Integer, Atelier> ateliers = new HashMap<Integer, Atelier>();
		
		ateliers.put(1, atelier1);
		
	Laboratoire labo = new Laboratoire(1 , "", "", 1, "", ateliers);

	labo.setNom("Nom1");
	
	assertEquals(labo.getNom(), "Nom1");
	assertEquals(labo.getAteliers(), ateliers);
	
	labo.addAtelier(atelier2);
	
	assertEquals(labo.getAteliers().get(2), atelier2);
		
	labo.removeAtelier(atelier2.getIdAtelier());
	
	assertEquals(labo.getAteliers().size(),1);
		
	}

	@After
	public void tearDown() throws Exception {
	}
}
