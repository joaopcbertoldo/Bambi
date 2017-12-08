package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.ControleurMois;
import main.simulation.ecosystemeBambiBase.entites.LocalisationVegetale;
import main.simulation.ecosystemeBambiBase.entites.Mois;
import main.simulation.ecosystemeBambiBase.entites.PopulationVegetale;
import main.simulation.ecosystemeBambiBase.entites.Territoire;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulation;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationVegetale;
import main.simulation.ecosystemeBambiBase.entitesData.DataTerritoire;

// Classe qui simule un fonctionnement idéal et controlé de localisation
class FausseLocalisationVegetale extends LocalisationVegetale {
	
	public int index = 0;
	public double balanceEau = 0;
	public double penurieEau = 0;
	public double quantiteVegetalNonMange = 0;
	
	// Constructeur
	public FausseLocalisationVegetale() {
		// ça ne sert à rien, cest juste pour respecter le super
		super(new Territoire(new DataTerritoire()));
	}
	
	// index
	@Override
    public int indexTerritoire() {
        return index;
    }

	// balanceEau
    // Litre
	@Override
    public double balanceEau() {
        return balanceEau;
    }

	// penurieEau
    // en %
    @Override
    public double penurieEau() {
        return penurieEau;
    }

    // quantiteVegetalNonMange
    // kg
    @Override
    public double quantiteVegetalNonMange() {
        return quantiteVegetalNonMange;
    }
}

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
	
	private PopulationVegetale population;
	
	@Before
	public void setUp() throws Exception {
		// controleur de mois
		ctrl = new ControleurMois(mois0);
		
		// data population
		data = new DataPopulationVegetale();
		data.besoinEauParIndividu          = 10.0; // 10 L/kg
		data.besoinVegetalParIndividu      = 0.0;  // nul
		data.quantiteIndividus             = 10;   // 10 kg
		data.quantiteIndividusMoisProchain = 0;    // 0 kg --> donc 2kg (min)
		
		// particularités de la data population végétale
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
		// le taux de croissance doit être constant
		
		// valeur attendue
		double expected = 50.0;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 0;
		fausseLocVeg.penurieEau = 0;
		fausseLocVeg.quantiteVegetalNonMange = 0;
		
		// recupère la vraie valeur
		actual = this.population.tauxCroissance();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxCroissance2() {
		// le taux de croissance doit être constant
		
		// valeur attendue
		double expected = 50.0;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 10;
		fausseLocVeg.penurieEau = 0;
		fausseLocVeg.quantiteVegetalNonMange = 10;
		
		// recupère la vraie valeur
		actual = this.population.tauxCroissance();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxCroissance3() {
		// le taux de croissance doit être constant
		
		// valeur attendue
		double expected = 50.0;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = -10;
		fausseLocVeg.penurieEau = 10;
		fausseLocVeg.quantiteVegetalNonMange = 10;
		
		// recupère la vraie valeur
		actual = this.population.tauxCroissance();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxPerteParPenurie1() {
		// le taux de croissance doit être : penurie en eau * taux de perte par penurie d'eau max
		// taux de perte par penurie d'eau max = 50 %
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 0;
		fausseLocVeg.penurieEau = 10;
		fausseLocVeg.quantiteVegetalNonMange = 10;
		
		// expected
		expected = 5.0; // 10% * 50% = 5%
		
		// recupère la vraie valeur
		actual = this.population.tauxPerteParPenurie();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
	@Test
	public void testTauxPerteParPenurie2() {
		// le taux de croissance doit être : penurie en eau * taux de perte par penurie d'eau max
		// taux de perte par penurie d'eau max = 50 %
		
		// valeur attendue
		double expected;
		
		// vraie valeur 
		double actual;
		
		// controle des valeurs de la localisation
		fausseLocVeg.balanceEau = 0;
		fausseLocVeg.penurieEau = 50;
		fausseLocVeg.quantiteVegetalNonMange = 10;
		
		// expected
		expected = 25.0; // 50% * 50% = 25%
		
		// recupère la vraie valeur
		actual = this.population.tauxPerteParPenurie();
		
		// assert
		assertEquals(expected, actual, 0.0);
	}
	
}
