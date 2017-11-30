package test.fichiers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.ParametrageSimulation;
import main.fichiers.GestionnaireFichierParametrage;
import main.fichiers.Parametres;

public class GestionnaireFichierParametrageTest {
	
	private String addresseNonClimatiques = "C:\\Repository\\Bambi\\Clara.txt";
	private String addressePluviometrie = "C:\\Repository\\Bambi\\joao.txt";
	private GestionnaireFichierParametrage gestionnaire = new GestionnaireFichierParametrage();

	@Before
	public void setUp() throws Exception {
		GestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
		GestionnaireFichierParametrage.chargerPluviometrie(addressePluviometrie);
		
	}
	
	@Test()
	public void testSeulementNonClimatique() {
		String message = "";
		
		try {
			gestionnaire.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
			ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		assertEquals();
	}
	
	@Test
	public void testSeulementPluviometrie() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNonClimatiquePluviometrie() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testPluviometrieNonClimatique() {
		fail("Not yet implemented");
	}


	@Test
	public void testRecupererParametrageSimulation() {
		fail("Not yet implemented");
	}

	@Test
	public void testChargerParametrageSimulationNonClimatique() {
		fail("Not yet implemented");
	}

	@Test
	public void testChargerPluviometrie() {
		fail("Not yet implemented");
	}

}
