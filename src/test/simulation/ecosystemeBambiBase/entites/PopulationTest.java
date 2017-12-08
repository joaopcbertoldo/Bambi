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

	private DataPopulation dataPopulation;
	private Mois ctrl;
	private MoisEnum mois0;
	private PopulationVide p;

	@Before
	public void setUp() throws Exception {
		// controleur de mois
		mois0 = MoisEnum.Janvier;
		ctrl = new ControleurMois(mois0);
		
		// data population
		dataPopulation = new DataPopulation();
		dataPopulation.besoinEauParIndividu     = 180;  // 6 * 30
		dataPopulation.besoinVegetalParIndividu = 150;  // 5 * 30
		dataPopulation.quantiteIndividus        = 100;  
		dataPopulation.quantiteIndividusMoisProchain = 0;
		
		// population
		p = new PopulationVide(dataPopulation, ctrl);
	}

	@Test
	public void testInitialisation() {
		// teste si l'objet a été construit
		assertNotNull(p);
	}

	@Test
	public void testBesoinEauPopulation() {
		// récupère la valeur de la population
		double actual = p.besoinEauPopulation();
		
		// comparaison
		assertEquals(180.0 * 100.0, actual, 0.5);
	}

	@Test
	public void testBesoinVegetalPopulation() {
		// récupère la valeur de la population
		double actual = p.besoinVegetalPopulation();

		// comparaison
		assertEquals(150.0 * 100.0, actual, 0.5);
	}

	@Test
	public void testProchainePopulation0() {
		double actual;
		
		// 
		actual = p.quantiteIndividus();
		assertEquals(100, (int)actual);
		
		p.affecterQuantiteIndividus();
		
		actual = p.quantiteIndividus();
		assertEquals(0, (int)actual);
	}

}
