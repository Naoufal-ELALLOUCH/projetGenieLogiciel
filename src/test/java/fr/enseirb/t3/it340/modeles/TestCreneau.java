package fr.enseirb.t3.it340.modeles;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCreneau {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void TestCreneau() {
	
		Creneau creneau = new Creneau(1, new Date(), 50);
		
		creneau.setCapacite(55);
		creneau.setDate(new Date(11112011));
		
		assertEquals(creneau.getIdCreneau(), 1);
		assertEquals(creneau.getCapacite(), 55);
		assertEquals(creneau.getDate(), new Date(11112011));
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
