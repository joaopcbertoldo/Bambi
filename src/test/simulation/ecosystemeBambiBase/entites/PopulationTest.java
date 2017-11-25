package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*; 	

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.ControleurMois;
import main.simulation.ecosystemeBambiBase.entites.Mois;
import main.simulation.ecosystemeBambiBase.entites.Population;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulation;

class PopulationVide extends Population {

	public PopulationVide(DataPopulation dataPopulation, Mois mois) {
		super(dataPopulation, mois);
	}

	@Override
	public void calculerNouvelleQuantiteIndividus() {
	}
}

public class PopulationTest {

	private DataPopulation dp;
	private Mois ctrl;
	private MoisEnum mois0;
	private PopulationVide p;

	@Before
	public void setUp() throws Exception {
		// controleur de mois
		mois0 = MoisEnum.Janvier;
		ctrl = new ControleurMois(mois0);
		
		// data population
		dp = new DataPopulation();
		dp.besoinEauParIndividu     = 180;  // 6 * 30
		dp.besoinVegetalParIndividu = 150;  // 5 * 30
		dp.quantiteIndividus        = 100;
		// dataPopulation.quantiteIndividusMoisProchain...
		
		// population
		p = new PopulationVide(dp, ctrl);
	}

	@Test
	public void testInitialisation() {
		assertNotNull(p);
	}
	
	@Test
	public void testSetLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testBesoinEauPopulation() {
		double actual = p.besoinEauPopulation();
		assertEquals(180.0 * 100.0, actual, 0.5);
	}

	@Test
	public void testBesoinVegetalPopulation() {
		double actual = p.besoinVegetalPopulation();
		assertEquals(150.0 * 100.0, actual, 0.5);
	}

	@Test
	public void testIndexTerritoireOccuppe() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculerNouvelleQuantiteIndividus() {
		fail("Not yet implemented");
	}

	@Test
	public void testProchainePopulation0() {
		double actual;
		
		actual = p.quantiteIndividus();
		assertEquals(100.0, actual, 0.0);
		
		p.affecterQuantiteIndividus();
		
		actual = p.quantiteIndividus();
		assertEquals(0.0, actual, 0.0);
	}

}
