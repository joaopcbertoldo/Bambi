package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.*;
import main.simulation.ecosystemeBambiBase.entitesData.*;

// Tests
public class PopulationVegetaleTest {

	// data de population vegetale
	private DataPopulationVegetale data;
	
	// fausse localisation
	private FausseLocalisationVegetale fausseLocVeg = new FausseLocalisationVegetale();
	
	// controleur de moi
	private Mois ctrl;
	
	// mois 0
	private MoisEnum mois0 = MoisEnum.Janvier;
	
	// population
	private PopulationVegetale population;
	
	@Before
	public void setUp() throws Exception {
		// controleur de mois
		ctrl = new ControleurMois(mois0);
		
		// data population
		data = new DataPopulationVegetale();
		data.besoinEauParIndividu          = 10.0; // 10 L/kg
		data.besoinVegetalParIndividu      = 0.0;  // nul
		data.quantiteIndividus             = 100;   // 100 kg
		
		// particularit�s de la data population v�g�tale
		data.tauxCroissanceVegetale            = 50.0;  // 50 %
		data.tauxPerteVegetaleParPenurieEauMax = 50.0;  // 50 %
		data.populationVegetaleMinimale        = 2.0;   // 2kg
	  
		// population
		population = new PopulationVegetale(data, ctrl);
		
		// localisation de la population
		population.setLocalisation(fausseLocVeg);
	}

	@Test
	public void testTauxCroissance1() {
		// le taux de croissance doit �tre constant
		
		// valeur attendue
		double expected = 50.0;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 0;
		fausseLocVeg.penurieEau = 0;
		fausseLocVeg.quantiteVegetalNonMange = 0;
		
		// recup�re la vraie valeur
		actual = this.population.tauxCroissance();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxCroissance2() {
		// le taux de croissance doit �tre constant
		
		// valeur attendue
		double expected = 50.0;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 10;
		fausseLocVeg.penurieEau = 0;
		fausseLocVeg.quantiteVegetalNonMange = 10;
		
		// recup�re la vraie valeur
		actual = this.population.tauxCroissance();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxCroissance3() {
		// le taux de croissance doit �tre constant
		
		// valeur attendue
		double expected = 50.0;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = -10;
		fausseLocVeg.penurieEau = 10;
		fausseLocVeg.quantiteVegetalNonMange = 10;
		
		// recup�re la vraie valeur
		actual = this.population.tauxCroissance();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxPerteParPenurie1() {
		// le taux de croissance doit �tre : penurie en eau * taux de perte par penurie d'eau max
		// taux de perte par penurie d'eau max = 50 %
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.penurieEau = 10;
		
		// expected
		expected = 5.0;  // 10% * 50% = 5%
		
		// recup�re la vraie valeur
		actual = this.population.tauxPerteParPenurie();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxPerteParPenurie2() {
		// le taux de croissance doit �tre : penurie en eau * taux de perte par penurie d'eau max
		// taux de perte par penurie d'eau max = 50 %
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.penurieEau = 50;
		
		// expected
		expected = 25.0;  // 50% * 50% = 25%
		
		// recup�re la vraie valeur
		actual = this.population.tauxPerteParPenurie();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testTauxPerteParPenurie3() {
		// le taux de croissance doit �tre : penurie en eau * taux de perte par penurie d'eau max
		// taux de perte par penurie d'eau max = 50 %
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.penurieEau = 0;
		
		// expected
		expected = 0.0;  //  0
		
		// recup�re la vraie valeur
		actual = this.population.tauxPerteParPenurie();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxPerteParPenurie4() {
		// le taux de croissance doit �tre : penurie en eau * taux de perte par penurie d'eau max
		// taux de perte par penurie d'eau max = 50 %
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.penurieEau = 100;
		
		// expected
		expected = 50.0;  // 100% * 50% = 25%
		
		// recup�re la vraie valeur
		actual = this.population.tauxPerteParPenurie();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testcalculerNouvelleQuantiteIndividusCroissance() {
		// population original = 100 kg
		// besoinEauParIndividu = 10 L/kg
		// tauxCroissanceVegetale = 50 %
		// tauxPerteVegetaleParPenurieEauMax = 50 %
		// populationVegetaleMinimale = 2kg
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 1.0;  // balance > 0, donc croissance

		// le calcul doit se baser sur la qtt non mang�
		fausseLocVeg.quantiteVegetalNonMange = 100.0;  // 100 kg
		
		// expected
		expected = 150.0;  
		
		// recup�re la vraie valeur
		population.calculerNouvelleQuantiteIndividus();  // calcul
		population.affecterQuantiteIndividus();          // affectation
		actual = population.quantiteIndividus();		 // recup�re
		
		// assert
		assertEquals(expected, actual, 0.01);
		
		// -------------------------------------------------
		
		// le calcul doit se baser sur la qtt non mang�
		fausseLocVeg.quantiteVegetalNonMange = 200.0;  // 200 kg
		
		// expected
		expected = 300.0; 
		
		// recup�re la vraie valeur
		population.calculerNouvelleQuantiteIndividus();  // calcul
		population.affecterQuantiteIndividus();          // affectation
		actual = population.quantiteIndividus();		 // recup�re
		
		// assert
		assertEquals(expected, actual, 0.01);
	}
	
	@Test
	public void testcalculerNouvelleQuantiteIndividusDecroissance() {
		/* calcul th�orique
		 * 
		*/
		
		// population original = 100 kg
		// besoinEauParIndividu = 10 L/kg
		// tauxCroissanceVegetale = 50 %
		// tauxPerteVegetaleParPenurieEauMax = 50 % !!!!!!!!!!
		// populationVegetaleMinimale = 2kg
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = - 1.0;  // balance < 0, donc decroissance

		// le calcul doit se baser sur la qtt non mang�
		fausseLocVeg.quantiteVegetalNonMange = 100.0;  // 100 kg
		
		// penurie de 10% donc perte de 5% (10% * 50%)
		fausseLocVeg.penurieEau = 10;  
		
		// expected
		expected = 95.0;  
		
		// recup�re la vraie valeur
		population.calculerNouvelleQuantiteIndividus();  // calcul
		population.affecterQuantiteIndividus();          // affectation
		actual = population.quantiteIndividus();		 // recup�re
		
		// assert
		assertEquals(expected, actual, 0.01);
		
		// -------------------------------------------------

		// le calcul doit se baser sur la qtt non mang�
		fausseLocVeg.quantiteVegetalNonMange = 100.0;  // 100 kg
		
		// penurie de 50% donc perte de 25% (50% * 50%)
		fausseLocVeg.penurieEau = 50;  // 
		
		// expected
		expected = 75.0; 
		
		// recup�re la vraie valeur
		population.calculerNouvelleQuantiteIndividus();  // calcul
		population.affecterQuantiteIndividus();          // affectation
		actual = population.quantiteIndividus();		 // recup�re
		
		// assert
		assertEquals(expected, actual, 0.01);

		// -------------------------------------------------

		// le calcul doit se baser sur la qtt non mang�
		fausseLocVeg.quantiteVegetalNonMange = 2.0;  // 2 kg (le minimum!!!!)
		
		// penurie de 100% donc perte de 50% (100% * 50%)
		fausseLocVeg.penurieEau = 100;  // 
		
		// expected
		expected = 2.0;  // car �a r�duit � 1 mais le minimum est 2
		
		// recup�re la vraie valeur
		population.calculerNouvelleQuantiteIndividus();  // calcul
		population.affecterQuantiteIndividus();          // affectation
		actual = population.quantiteIndividus();		 // recup�re
		
		// assert
		assertEquals(expected, actual, 0.01);
	}
	
}

//Classe qui simule un fonctionnement id�al et control� de localisation
class FausseLocalisationVegetale extends LocalisationVegetale {
	
	public int index = 0;
	public double balanceEau = 0;
	public double penurieEau = 0;
	public double quantiteVegetalNonMange = 0;
	
	// Constructeur
	public FausseLocalisationVegetale() {
		// �a ne sert � rien, cest juste pour respecter le super
		super(new Territoire(new DataTerritoire()));
	}
	
	// index
	@Override
	 public int indexTerritoire() {
		 return index;
	 }

	// balanceEau (Litre)
	@Override
	 public double balanceEau() {
		 return balanceEau;
	 }

	// penurieEau (en %)
	 @Override
	 public double penurieEau() {
		 return penurieEau;
	 }

	 // quantiteVegetalNonMange (kg)
	 @Override
	 public double quantiteVegetalNonMange() {
		 return quantiteVegetalNonMange;
	 }
}

