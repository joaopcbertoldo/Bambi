package test.fichiers;

import static org.junit.Assert.*;

import java.io.IOException;

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
	
	
	@Test()
	public void testSeulementNonClimatique() {
		String message = "";
		
		try {
			gestionnaire.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
			ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		assertEquals("Pluviometrie non charge", message);
	}
	
	@Test
	public void testSeulementPluviometrie() {
		String message= "";
		try{
			gestionnaire.chargerPluviometrie (addressePluviometrie);
			ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		} 
		catch (Exception e) {
			message = e.getMessage();
		}
		assertEquals("Parametres non climatiques non charges", message);
	}
	
	@Test
	public void testNonClimatiquePluviometrie() {
		String message = "";
		
		try {
			gestionnaire.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
			gestionnaire.chargerPluviometrie (addressePluviometrie);
			ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		assertEquals("", message);
	}
	
	
	@Test
	public void testPluviometrieNonClimatique() {
	String message = "";
		
		try {
			gestionnaire.chargerPluviometrie (addressePluviometrie);
			gestionnaire.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
			ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		assertEquals("", message);
	}


	@Test
	public void testRecupererParametrageSimulation() throws Exception {
		
	}

	@Test
	public void testChargerParametrageSimulationNonClimatique() throws Exception {
		
		gestionnaire.chargerPluviometrie (addressePluviometrie);
		gestionnaire.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
		ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		assertEquals(12, param.nombreDePas());
		assertEquals(1, param.surfaceTerritoire(1),0.01);
		assertEquals(2, param.surfaceTerritoire(2),0.01);
		assertEquals(3, param.surfaceTerritoire(3),0.01);
		assertEquals(4, param.surfaceTerritoire(4),0.01);
		assertEquals(5, param.surfaceTerritoire(5),0.01);
		assertEquals("Juin", param.mois0().toString());
		assertEquals(1234, param.nombreIndividusAnimale0());
		assertEquals(34, param.stockVegetaux(1),0.01);
		assertEquals(35, param.stockVegetaux(2),0.01);
		assertEquals(36, param.stockVegetaux(3),0.01);
		assertEquals(37, param.stockVegetaux(4),0.01);
		assertEquals(38, param.stockVegetaux(5),0.01);
		assertEquals(2, param.localisationInitiale());
		assertEquals(23, param.stockEau(1),0.01);
		assertEquals(24, param.stockEau(2),0.01);
		assertEquals(25, param.stockEau(3),0.01);
		assertEquals(26, param.stockEau(4),0.01);
		assertEquals(27, param.stockEau(5),0.01);
		assertEquals(78, param.tauxPerteEauEvaporation(1),0.01);
		assertEquals(77, param.tauxPerteEauEvaporation(2),0.01);
		assertEquals(76, param.tauxPerteEauEvaporation(3),0.01);
		assertEquals(74, param.tauxPerteEauEvaporation(4),0.01);
		assertEquals(71, param.tauxPerteEauEvaporation(5),0.01);
		assertEquals(678, param.stockVegetauxMinimal(),0.01);
		assertEquals(9, param.tauxNaissanceAnimalMaximal(),0.01);
		assertEquals(22, param.tauxMortalitePredateur(),0.01);
		assertEquals(46, param.tauxMortaliteParPenurieAlimentaireMaximal(),0.01); 
		assertEquals(67, param.besoinEauVegetal(),0.01);
		assertEquals(89, param.besoinEauAnimal(),0.01);
		assertEquals(54, param.besoinVegetalAnimal(),0.01);
		assertEquals(32, param.tauxCroissanteVegetal(),0.01);
		assertEquals(90, param.tauxPerteVegetalPenurieMax(),0.01);

		
	}

	@Test
	public void testChargerPluviometrie() throws Exception {
		gestionnaire.chargerPluviometrie (addressePluviometrie);
		gestionnaire.chargerParametrageSimulationNonClimatique(addresseNonClimatiques);
		ParametrageSimulation param = gestionnaire.recupererParametrageSimulation();
		assertEquals(25, param.tauxPerteVegetalPenurieMax(),0.01);
		assertEquals(45, param.tauxPerteVegetalPenurieMax(),0.01);
		assertEquals(65, param.tauxPerteVegetalPenurieMax(),0.01);
		assertEquals(97, param.tauxPerteVegetalPenurieMax(),0.01);
		assertEquals(12, param.tauxPerteVegetalPenurieMax(),0.01);
		assertEquals(10, param.tauxPerteVegetalPenurieMax(),0.01);
		
	}

}
