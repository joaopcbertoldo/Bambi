package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*; 	

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.*;
import main.simulation.ecosystemeBambiBase.entitesData.*;


/******************************** CODE DES TESTES *******************************************/ 

/**
 * Classe de teste de Population.
 * Comme cette classe est abstraite, une classe concrète vide a été implémentée
 * pour pouvoir la tester tel qu'elle est.
 * 
 * @author João Paulo
 */
public class PopulationTest {

	/******************** ATTRIBUS AUXILIAIRES ********************/
	
    /** 
     * valeurs : attendue, réele, tolérance de différence
     */
    double expected, actual, delta = 0.001;
    
	/**
	 * Objete de data de population.
	 */
	private DataPopulation dataPopulation;
	
	/**
	 * Controleur de mois.
	 */
	private ControleurMois ctrl;
	
	/**
	 * Objet de population.
	 */
	private PopulationVide population;
	
	
	/**
	 * Méthode d'initialisation des valeurs de teste.
	 * 		besoinEauParIndividu          = 180
	 * 		besoinVegetalParIndividu      = 150
	 * 		quantiteIndividus             = 100
	 * 		quantiteIndividusMoisProchain = 0
	 * 		
	 * 		mois0 = Janvier
	 * 		
	 * @throws Exception (méthode de set localisation).
	 */
	@Before
	public void setUp() throws Exception {
		// controleur de mois]
		ctrl = new ControleurMois(MoisEnum.Janvier);
		
		// data population
		dataPopulation = new DataPopulation();
		dataPopulation.besoinEauParIndividu     = 180;  // 6 * 30
		dataPopulation.besoinVegetalParIndividu = 150;  // 5 * 30
		dataPopulation.quantiteIndividus        = 100;  
		dataPopulation.quantiteIndividusMoisProchain = 0;
		
		// population
		population = new PopulationVide(dataPopulation, ctrl);
	}
	
	
	/**
	 * Teste la méthode de calcul de bésoin d'eau.
	 */
	@Test
	public void testBesoinEauPopulation() {
		// expected
		expected = 180.0 * 100.0;
		
		// récupère la valeur de la population
		actual = population.besoinEauPopulation();
		
		// comparaison
		assertEquals(expected, actual, delta);
	}
	
	
	/**
	 * Teste la méthode de calcul de bésoin de végétal.
	 */
	@Test
	public void testBesoinVegetalPopulation() {
		// expected
		expected = 150.0 * 100.0;
		
		// récupère la valeur de la population
		actual = population.besoinVegetalPopulation();
		
		// comparaison
		assertEquals(expected, actual, delta);
	}
	
	
	/**
	 * Teste l'affectation de la quantité d'invidus.
	 */
	@Test
	public void testProchainePopulation0() {
		// expected
		expected = 100;
		
		// récupère la valeur de la population
		actual = population.quantiteIndividus();
		
		// comparaison
		assertEquals((int)expected, (int)actual);
		
		// affectation
		population.affecterQuantiteIndividus();

		// expected
		expected = 0;
		
		// récupère la valeur de la population
		actual = population.quantiteIndividus();
		
		// comparaison
		assertEquals((int)expected, (int)actual);
	}

}


/******************************** CODE AUXILIAIRE *******************************************/ 

/**
 * Classe vide qui implémente la clases abstraite pour la tester.
 * @author João Paulo
 */
class PopulationVide extends Population {

	/**
	 * Constructeur qui appelle celui de Population.
	 */
	public PopulationVide(DataPopulation dataPopulation, Mois mois) {
		super(dataPopulation, mois);
	}

	/**
	 * Fonction qui ne fait rien du tout.
	 */
	@Override
	public void calculerNouvelleQuantiteIndividus() {
	}
}
