package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.*;
import main.simulation.ecosystemeBambiBase.entitesData.*;


/******************************** CODE DES TESTES *******************************************/ 

/**
 * 
 * @author João Paulo
 */
public class TerritoireTest {
	
	private double expected, actual, delta = 0.001;
	
	/**
	 * 
	 */
	private Territoire territoire;

	/**
	 * 
	 */
	private DataTerritoire data;
	
	/**
	 * 
	 */
	private FaussePopulationVegetale fauxVeg;

	/**
	 * 
	 */
	private FaussePopulationAnimale fauxAni;
	
	/**
	 * 
	 */
	private boolean insererPopulationAnimale = true;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() {
		
		// data territoire
		data = new DataTerritoire();
		data.index                   = 0;
		data.tauxPerteEauEvaporation = 50.0;
		data.surface                 = 1.0;
		data.cumulEau                = 0.0;
		data.cumulEauMoisProchain    = 0.0;
		data.pluviometrie            = 100.0;
		
		fauxVeg = new FaussePopulationVegetale();
		
		fauxAni = new FaussePopulationAnimale();
		
		territoire = Territoire.CreerTerritoire(data, fauxVeg);
		
		if (insererPopulationAnimale)
			territoire.recevoirPopulation(fauxAni);
	}

	
	/**
	 * 
	 */
	@Test
	public void testCreerTerritoireAdjacent() {
		/*** Creer au nord ***/
		Territoire auNord = Territoire.CreerTerritoireAuNord(territoire, new DataTerritoire(), new FaussePopulationVegetale());
		assertEquals(auNord, territoire.territoireAuNord);
		
		
		/*** Creer au sud ***/
		Territoire auSud = Territoire.CreerTerritoireAuNord(territoire, new DataTerritoire(), new FaussePopulationVegetale());
		assertEquals(auSud, territoire.territoireAuSud);
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testIndex() {
		// assert 
		assertEquals(0, territoire.index());
		
		// changement
		data.index = 1;
		
		// assert 
		assertEquals(1, territoire.index());
				
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testPluviometrie() {
		
		// valeur attendue = 100
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.pluviometrie();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		data.pluviometrie = 200.0;
		
		// valeur attendue = 200
		expected = 200.0;
		
		// valeur trouvé en territoire
		actual = territoire.pluviometrie();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	
	/**
	 * 
	 */
	@Test
	public void testSurface() {
		// valeur attendue = 1
		expected = 1.0;
		
		// valeur trouvé en territoire
		actual = territoire.surface();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		data.surface = 2.0;
		
		// valeur attendue = 2
		expected = 2.0;
		
		// valeur trouvé en territoire
		actual = territoire.surface();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	
	/**
	 * 
	 */
	@Test
	public void testQuantiteEauDePluie() {
		// valeur attendue = (10^2 mm / m2) * (1 km2 * 10^3 *10^3) m2 = 10^10 litre
		expected = 100.0 * 1.0 * 1000.0 * 1000.0;
		
		// valeur trouvé en territoire
		actual = territoire.quantiteEauDePluie();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		data.pluviometrie = 2 * data.pluviometrie;
		
		// valeur attendue 
		expected = 2 * expected;
		
		// valeur trouvé en territoire
		actual = territoire.quantiteEauDePluie();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testCumulEau() {
		// cumul initial = 100
		data.cumulEau = 100.0;
		
		// valeur attendue = 100
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.cumulEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		data.cumulEau = 200.0;
		
		// valeur attendue = 200
		expected = 200.0;
		
		// valeur trouvé en territoire
		actual = territoire.cumulEau();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	
	/**
	 * 
	 */
	@Test
	public void testDisponibiliteEau() {
		// controle des conditions
		data.cumulEau = 100.0;
		data.pluviometrie = 1000.0 / 1000.0 / 1000.0;
		
		// valeur attendue = 100 (cumul) + 1000 (pluie) 
		expected = 1100.0;
		
		// valeur trouvé en territoire
		actual = territoire.disponibiliteEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement de cumul d'eau
		data.cumulEau = 200.0;
		
		// valeur attendue = 200
		expected = 1200.0;
		
		// valeur trouvé en territoire
		actual = territoire.disponibiliteEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement de pluviometrie
		data.pluviometrie = 2000.0 / 1000.0 / 1000.0;
		
		// valeur attendue = 200
		expected = 2200.0;
		
		// valeur trouvé en territoire
		actual = territoire.disponibiliteEau();
		
		// assert
		assertEquals(expected, actual, delta);
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testBesoinEau() {
		// controle des conditions
		fauxVeg.besoinEauPopulation = 100;
		fauxAni.besoinEauPopulation = 150;
		
		// valeur attendue = 100
		expected = 250.0;
		
		// valeur trouvé en territoire
		actual = territoire.besoinEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// controle des conditions
		fauxVeg.besoinEauPopulation = 10;
		fauxAni.besoinEauPopulation = 300;
		
		// valeur attendue = 100
		expected = 310.0;
		
		// valeur trouvé en territoire
		actual = territoire.besoinEau();
		
		// assert
		assertEquals(expected, actual, delta);
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testBalanceEau() {
		// controle des conditions
		fauxVeg.besoinEauPopulation = 100;
		fauxAni.besoinEauPopulation = 200;
		data.cumulEau = 400.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = 100
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.balanceEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// controle des conditions
		fauxVeg.besoinEauPopulation = 100;
		fauxAni.besoinEauPopulation = 0;
		data.cumulEau = 0.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = -100
		expected = -100.0;
		
		// valeur trouvé en territoire
		actual = territoire.balanceEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// controle des conditions
		fauxVeg.besoinEauPopulation = 0;
		fauxAni.besoinEauPopulation = 100;
		data.cumulEau = 50.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = -50
		expected = -50.0;
		
		// valeur trouvé en territoire
		actual = territoire.balanceEau();
		
		// assert
		assertEquals(expected, actual, delta);

		// controle des conditions
		// teste sans inclure la population animale
		insererPopulationAnimale = false;
		setUp();
		fauxVeg.besoinEauPopulation = 0;
		fauxAni.besoinEauPopulation = 100;
		data.cumulEau = 50.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = 50
		expected = 50.0;

		// valeur trouvé en territoire
		actual = territoire.balanceEau();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testPenurieEau() {
		// controle des conditions
		fauxVeg.besoinEauPopulation = 100;
		fauxAni.besoinEauPopulation = 200;
		data.cumulEau = 400.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = 0
		expected = 0.0;
		
		// valeur trouvé en territoire
		actual = territoire.penurieEau();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// controle des conditions
		fauxVeg.besoinEauPopulation = 100;
		fauxAni.besoinEauPopulation = 100;
		data.cumulEau = 100.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = 50
		expected = 50.0;
		
		// valeur trouvé en territoire
		actual = territoire.penurieEau();
		
		// assert
		assertEquals(expected, actual, delta);

		// controle des conditions
		fauxVeg.besoinEauPopulation = 100.0;
		fauxAni.besoinEauPopulation = 100.0;
		data.cumulEau = 0.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = 100%
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.penurieEau();
		
		// assert
		assertEquals(expected, actual, delta);

		// controle des conditions
		// teste sans inclure la population animale
		insererPopulationAnimale = false;
		setUp();
		fauxVeg.besoinEauPopulation = 200;
		fauxAni.besoinEauPopulation = 200;
		data.cumulEau = 100.0;
		data.pluviometrie = 0.0;
		
		// valeur attendue = 50%, pas 25%
		expected = 50.0;
		
		// valeur trouvé en territoire
		actual = territoire.penurieEau();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testDisponibiliteVegetal() {

		// controle des conditions
		fauxVeg.quantiteIndividus = 100.0;
		
		// valeur attendue = 100
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.disponibiliteVegetal();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		fauxVeg.quantiteIndividus = 200.0;
		
		// valeur attendue = 200
		expected = 200.0;
		
		// valeur trouvé en territoire
		actual = territoire.disponibiliteVegetal();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testBesoinVegetal() {

		// controle des conditions
		fauxAni.besoinVegetalPopulation = 100.0;
		
		// valeur attendue = 100
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.besoinVegetal();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		fauxAni.besoinVegetalPopulation = 200.0;
		
		// valeur attendue = 200
		expected = 200.0;
		
		// valeur trouvé en territoire
		actual = territoire.besoinVegetal();
		
		// assert
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testBalanceVegetal() {

		// controle des conditions
		fauxAni.besoinVegetalPopulation = 100.0;
		fauxVeg.quantiteIndividus = 200.0;
		
		// valeur attendue = 100
		expected = 100.0;
		
		// valeur trouvé en territoire
		actual = territoire.balanceVegetal();
		
		// assert
		assertEquals(expected, actual, delta);
		
		// changement
		fauxAni.besoinVegetalPopulation = 200.0;
		fauxVeg.quantiteIndividus = 100.0;

		// valeur attendue = -100
		expected = -100.0;
		
		// valeur trouvé en territoire
		actual = territoire.balanceVegetal();
		
		// assert
		assertEquals(expected, actual, delta);
	}

}


/******************************** CODE AUXILIAIRE *******************************************/ 

/**
 * 
 * @author João Paulo
 */
class FaussePopulationVegetale extends PopulationVegetale {
	
	public double besoinEauPopulation;
	
	public double quantiteIndividus;
	
	public FaussePopulationVegetale() {
		super(new DataPopulationVegetale(), new ControleurMois(MoisEnum.Janvier));
	}
	
	@Override
	public double besoinEauPopulation() {
		return besoinEauPopulation;
	}
	
	@Override
	public double quantiteIndividus() {
		return quantiteIndividus;
	}
}


/**
 * 
 * @author João Paulo
 */
class FaussePopulationAnimale extends PopulationAnimale {

	public double besoinEauPopulation;
	
	public double besoinVegetalPopulation;
	
	public FaussePopulationAnimale() {
		super(new DataPopulationAnimale(), new ControleurMois(MoisEnum.Janvier));
	}
	
	@Override
	public double besoinEauPopulation() {
		return besoinEauPopulation;
	}
	
	@Override
	public double besoinVegetalPopulation() {
		return besoinVegetalPopulation;
	}
}

