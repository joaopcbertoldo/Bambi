package test.simulation.ecosystemeBambiBase.entites;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.ControleurMois;
import main.simulation.ecosystemeBambiBase.entites.LocalisationAnimale;
import main.simulation.ecosystemeBambiBase.entites.LocalisationVegetale;
import main.simulation.ecosystemeBambiBase.entites.Mois;
import main.simulation.ecosystemeBambiBase.entites.Population;
import main.simulation.ecosystemeBambiBase.entites.PopulationAnimale;
import main.simulation.ecosystemeBambiBase.entites.PopulationVegetale;
import main.simulation.ecosystemeBambiBase.entites.Territoire;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationAnimale;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationVegetale;
import main.simulation.ecosystemeBambiBase.entitesData.DataTerritoire;

//Tests
public class PopulationAnimaleTest {

	// data de population vegetale
	private DataPopulationAnimale data;
	
	// fausse localisation
	private FausseLocalisationAnimale fausseLocAni = new FausseLocalisationAnimale();
	
	// controleur de moi
	private Mois ctrl;
	
	// mois 0
	private MoisEnum mois0 = MoisEnum.Janvier;
	
	// population
	private PopulationAnimale population;
	
	@Before
	public void setUp() throws Exception {
		// controleur de mois
		ctrl = new ControleurMois(mois0);
		
		// data population
		data = new DataPopulationAnimale();
		data.besoinEauParIndividu          = 10.0; // 10 L/kg
		data.besoinVegetalParIndividu      = 0.0;  // nul
		data.quantiteIndividus             = 100;   // 100 kg
		
		// particularités de la data population Animale
	  
		// population
		population = new PopulationAnimale(data, ctrl);
		
		// localisation de la population
		population.setLocalisation(fausseLocAni);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

//Classe qui simule un fonctionnement idéal et controlé de localisation
class FausseLocalisationAnimale extends LocalisationAnimale {
	
	public int index = 0;
	public double balanceEau = 0;
	public double penurieEau = 0;
	public double penurieVegetale = 0;
	public double penurieAlimentaire = 0;
	
	// Constructeur
	public FausseLocalisationAnimale() {
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
	
	// en %
	 @Override
    public double penurieVegetale() {
        return penurieVegetale;
    }

    // en %
    public double penurieAlimentaire() {
        return penurieAlimentaire;
    }

    public void migrerAuNord(Population population) {
    	index++;
    }

    public void migrerAuSud(Population population) {
    	index--;
    }
}
