package fr.enseirb.t3.it340.modeles;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEnseignant {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestEnseignant() {
	
		Enseignant ens = new Enseignant(1, 2, "nom", "prenom");
		
		assertEquals(ens.getIdUtilisateur(),2);
		ens.setIdUtilisateur(3);
		
		assertEquals(ens.getIdUtilisateur(),3);
		
		ens.setNom("nom1");
		ens.setPrenom("prenom1");
		
		assertEquals(ens.getNom(),"nom1");
		assertEquals(ens.getPrenom(), "prenom1");
		
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
