package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.*;

/**
 * Classe de teste du controleur de mois et mois.
 * 
 * Cette classe teste au même temps la classe qui contrôle l'avancement des mois
 * et celle qui sert seulement comme référence de temps (pour récupérer le mois actuel).
 */
public class ControleurMoisTest {

	/**
	 * controleur de mois
	 */
	private ControleurMois ctrlMois;
	
	/**
	 * mois - objet de référence de temps
	 */
	private Mois mois;
	
	
	/**
	 * crée un controleur et fait l'objet de mois pointer vers le même objet
	 */
	@Before
	public void setUp() throws Exception {
		// controleur
		this.ctrlMois = new ControleurMois(MoisEnum.Janvier);
		
		// mois
		this.mois = this.ctrlMois;
	}
	
	
	/**
	 * Fonction auxiliaire qui incrément le mois N fois.
	 * 
	 * @param N Nombre de mois à incrémenter
	 */
	private void incrementer(int N) {
		// compteur
		for (int i = 0; i < N; i++)
			
			// appel à la méthode d'incrément
			this.ctrlMois.incrementer();	
	}

	
	/**
	 * méthode de test des getters dans les 2 classes
	 */
	@Test
	public void testGetters() {
		// getIteration
		// iteration = 0
		assertEquals(0, this.mois.getIteration());
		assertEquals(0, this.ctrlMois.getIteration());
		
		// getMois
		// mois enum = Janvier
		assertEquals(MoisEnum.Janvier, this.mois.getMois());
		assertEquals(MoisEnum.Janvier, this.ctrlMois.getMois());
	}

	
	/**
	 * teste l'incrémentation 1 fois
	 */
	@Test
	public void testIncrementer1() {
		// incremente 1 fois
		incrementer(1);

		// iteration = 1
		assertEquals(1, this.mois.getIteration());
		assertEquals(1, this.ctrlMois.getIteration());

		// mois enum = Fevrier
		assertEquals(MoisEnum.Fevrier, this.mois.getMois());
		assertEquals(MoisEnum.Fevrier, this.ctrlMois.getMois());
	}
	
	
	/**
	 * teste l'incrémentation 11 fois (décembre)
	 */
	@Test
	public void testIncrementer11() {
		// incremente 11 fois
		incrementer(11);

		// getIteration
		// iteration = 11
		assertEquals(11, this.mois.getIteration());
		assertEquals(11, this.ctrlMois.getIteration());
		
		// getMois
		// mois enum = Decembre
		assertEquals(MoisEnum.Decembre, this.mois.getMois());
		assertEquals(MoisEnum.Decembre, this.ctrlMois.getMois());
	}
	
	
	/**
	 * teste l'incrémentation 12 fois (janvier)
	 */
	@Test
	public void testIncrementer12() {
		// incremente 12 fois
		incrementer(12);

		// getIteration
		// iteration = 12
		assertEquals(12, this.mois.getIteration());
		assertEquals(12, this.ctrlMois.getIteration());
		
		// getMois
		// mois enum = Janvier
		assertEquals(MoisEnum.Janvier, this.mois.getMois());
		assertEquals(MoisEnum.Janvier, this.ctrlMois.getMois());
	}


	/**
	 * teste l'incrémentation 24 fois (janvier)
	 */
	@Test
	public void testIncrementer24() {
		// incremente 24 fois
		incrementer(24);

		// getIteration
		// iteration = 24
		assertEquals(24, this.mois.getIteration());
		assertEquals(24, this.ctrlMois.getIteration());
		
		// getMois
		// mois enum = Janvier
		assertEquals(MoisEnum.Janvier, this.mois.getMois());
		assertEquals(MoisEnum.Janvier, this.ctrlMois.getMois());
	}
	
	
	/**
	 * teste de chaque mois
	 */
	@Test
	public void testTousLesMois() {
		// Janvier
		assertEquals(MoisEnum.Janvier, this.mois.getMois());
		
		// Fevrier
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Fevrier, this.mois.getMois());

		// Mars
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Mars, this.mois.getMois());

		// Avril
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Avril, this.mois.getMois());

		// Mai
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Mai, this.mois.getMois());

		// Juin
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Juin, this.mois.getMois());

		// Juillet
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Juillet, this.mois.getMois());

		// Aout
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Aout, this.mois.getMois());

		// Septembre
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Septembre, this.mois.getMois());

		// Octobre
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Octobre, this.mois.getMois());

		// Novembre
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Novembre, this.mois.getMois());

		// Decembre
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Decembre, this.mois.getMois());

		// Janvier
		this.ctrlMois.incrementer();  // icrementer
		assertEquals(MoisEnum.Janvier, this.mois.getMois());
	}
}
