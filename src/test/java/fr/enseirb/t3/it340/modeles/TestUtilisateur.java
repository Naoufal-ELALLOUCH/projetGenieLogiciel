package fr.enseirb.t3.it340.modeles;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUtilisateur {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestUtilisateur() {
		Utilisateur utilisateur = new Utilisateur(1, "email", "mdp");
	
		utilisateur.setEmail("em");
		utilisateur.setMotDePasse("mdp");
		
		assertEquals(utilisateur.getEmail(),"em");
		assertEquals(utilisateur.getMotDePasse(),"mdp");
	
	}

	@After
	public void tearDown() throws Exception {
	}

	
}
