package test.simulation.ecosystemeBambiBase.entites;

import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.ControleurMois;
import main.simulation.ecosystemeBambiBase.entites.LocalisationAnimale;
import main.simulation.ecosystemeBambiBase.entites.Mois;
import main.simulation.ecosystemeBambiBase.entites.Population;
import main.simulation.ecosystemeBambiBase.entites.PopulationAnimale;
import main.simulation.ecosystemeBambiBase.entites.Territoire;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationAnimale;
import main.simulation.ecosystemeBambiBase.entitesData.DataTerritoire;
import main.simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

//Tests
public class PopulationAnimaleTest {
    // valeur attendue
    double expected;

    // vraie valeur
    double actual;

    // tolÃ©rance de diffÃ©rence entre 2 doubles
    double delta = 0.001;

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
		data.besoinEauParIndividu          = 10.0;  // 10 L/indiv
		data.besoinVegetalParIndividu      = 10.0;  // 10 kg/indiv
		data.quantiteIndividus             = 100;   // 100 indiv

        /* particularitï¿½s de la data population Animale
         *  tauxMortalitePredateur                     = 0%
         *  taux Mortalite Par Penurie Alimentaire Max = 50%
         *  taux Naissance Max                         = 100%
         *  historique Penurie Eau --> en %
         *  historique Penurie Nourriture --> en %
         *  status Migration = Fixe
        **/
		data.tauxMortalitePredateur = 0;
	    data.tauxMortaliteParPenurieAlimentaireMax = 50;
	    data.tauxNaissanceMax = 100;
	    data.historiquePenurieEau = Arrays.asList();
	    data.historiquePenurieNourriture = Arrays.asList();

	    data.statusMigration = StatusMigrationEnum.Fixe;

		// population
		population = new PopulationAnimale(data, ctrl);

		// localisation de la population
		population.setLocalisation(fausseLocAni);
	}

    /*
        MÃ©thodes Ã  tester :
            penurieEauCumulee
            penurieVegetaleCumulee
            tauxNaissance
            tauxMortalite
            calculerNouvelleQuantiteIndividus
            migrer + etatDeMigration
    */

    @Test
    public void testPenurieEauCumulee() {
        // la penurie d'eau cumulÃ©e est la somme des 6 derniers penurie d'eau (en %)

        /* particularitï¿½s de la data population Animale
         *  tauxMortalitePredateur                     = 0%
         *  taux Mortalite Par Penurie Alimentaire Max = 50%
         *  taux Naissance Max                         = 100%
         *  historique Penurie Eau        --> en %
         *  historique Penurie Nourriture --> en %
         *  status Migration = Fixe
        **/

        /***************************************************************************/
        // controle de conditions externes
        data.historiquePenurieEau = Arrays.asList(0., 0., 0., 0., 0., 0.);

        // expected
        expected = 0.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieEauCumulee();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/
        // controle de conditions externes
        data.historiquePenurieEau = Arrays.asList(0., 0., 0., 0., 0., 100.);

        // expected
        expected = 100.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieEauCumulee();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/
        // controle de conditions externes
        data.historiquePenurieEau = Arrays.asList(0., 0., 100., 0., 100., 0.);

        // expected
        expected = 200.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieEauCumulee();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/
        // controle de conditions externes - avec + q 6 valeurs
        data.historiquePenurieEau = Arrays.asList(100., 0., 0., 0., 0., 100., 100.);

        // expected
        expected = 200.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieEauCumulee();

        // assert
        assertEquals(expected, actual, delta);
    }

    @Test
    public void testPenurieVegetaleCumulee() {
        // la penurie végétale acumulée est la somme des 6 dernieres penuries végétales (en %)

        /* particularitï¿½s de la data population Animale
         *  tauxMortalitePredateur                     = 0%
         *  taux Mortalite Par Penurie Alimentaire Max = 50%
         *  taux Naissance Max                         = 100%
         *  historique Penurie Eau        --> en %
         *  historique Penurie Nourriture --> en %
         *  status Migration = Fixe
        **/

        /***************************************************************************/
        // controle de conditions externes
        data.historiquePenurieNourriture = Arrays.asList(0., 0., 0., 0., 0., 0.);

        // expected
        expected = 0.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieVegetaleCumulee();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/
        // controle de conditions externes
        data.historiquePenurieNourriture = Arrays.asList(0., 0., 0., 0., 0., 100.);

        // expected
        expected = 100.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieVegetaleCumulee();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/
        // controle de conditions externes
        data.historiquePenurieNourriture = Arrays.asList(0., 0., 100., 0., 100., 0.);

        // expected
        expected = 200.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieVegetaleCumulee();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/
        // controle de conditions externes - avec + q 6 valeurs
        data.historiquePenurieNourriture = Arrays.asList(100., 0., 0., 0., 0., 100., 100.);

        // expected
        expected = 200.0;  //  0

        // recupï¿½re la vraie valeur
        actual = this.population.penurieVegetaleCumulee();

        // assert
        assertEquals(expected, actual, delta);
    }
    
    @Test
    public void testTauxNaissance() {
        // le taux de naissance doit suivre la suivante équation:
    	//     (.1/penEau * .1/penVeg) * TauxMAX 
    	// penEau et penVeg pas en %
    	// mais la fonction sature en TauxMAX

        /* particularitï¿½s de la data population Animale
         *  tauxMortalitePredateur                     = 0%
         *  taux Mortalite Par Penurie Alimentaire Max = 50%
         *  taux Naissance Max                         = 100%
         *  historique Penurie Eau        --> en %
         *  historique Penurie Nourriture --> en %
         *  status Migration = Fixe
        **/
    	
        // controle de conditions externes - max
    	data.historiquePenurieEau = Arrays.asList(10., 0., 0., 0., 0., 0.);
        data.historiquePenurieNourriture = Arrays.asList(10., 0., 0., 0., 0., 0.);

        // expected
        expected = 100.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes - under max
    	data.historiquePenurieEau = Arrays.asList(100., 0., 0., 0., 0., 0.);
        data.historiquePenurieNourriture = Arrays.asList(100., 0., 0., 0., 0., 0.);

        // expected
        expected = 1.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes - over max
    	data.historiquePenurieEau = Arrays.asList(5., 0., 0., 0., 0., 0.);
        data.historiquePenurieNourriture = Arrays.asList(5., 0., 0., 0., 0., 0.);

        // expected
        expected = 100.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes - un quart
    	data.historiquePenurieEau = Arrays.asList(20., 0., 0., 0., 0., 0.);
        data.historiquePenurieNourriture = Arrays.asList(20., 0., 0., 0., 0., 0.);

        // expected
        expected = 25.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);
    }
    
    @Test
    public void testTauxMortalite() {
        // le taux de naissance doit suivre la suivante équation:
    	//     tauxPred + tauxPenMax * penAlim
    	// penAlim pas en %
    	// il faut saturer a 100%

        /* particularitï¿½s de la data population Animale
         *  taux Mortalite Par Penurie Alimentaire Max = 50%
         *  taux Naissance Max                         = 100%
         *  historique Penurie Eau        --> en %
         *  historique Penurie Nourriture --> en %
         *  status Migration = Fixe
        **/
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 0.0;
    	fausseLocAni.penurieAlimentaire = 100.0;
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 50.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 0.0;
    	fausseLocAni.penurieAlimentaire = 50.0;
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 25.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes
    	data.tauxMortalitePredateur = 50.0;
    	fausseLocAni.penurieAlimentaire = 50.0;
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 75.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes
    	data.tauxMortalitePredateur = 75.0;
    	fausseLocAni.penurieAlimentaire = 100.0;
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 100.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes
    	data.tauxMortalitePredateur = 75.0;
    	fausseLocAni.penurieAlimentaire = 0.0;
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 75.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
        // controle de conditions externes
    	data.tauxMortalitePredateur = 0.0;
    	fausseLocAni.penurieAlimentaire = 0.0;
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 0.0;  //  0

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
    }
    
    @Test
    public void testCalculerNouvelleQuantiteIndividus() {
        // le taux de naissance doit suivre la suivante équation:
    	//     tauxPred + tauxPenMax * penAlim
    	// penAlim pas en %
    	// il faut saturer a 100%

        /* particularitï¿½s de la data population Animale
         *  taux Mortalite Par Penurie Alimentaire Max = 50%
         *  taux Naissance Max                         = 100%
         *  historique Penurie Eau        --> en %
         *  historique Penurie Nourriture --> en %
         *  status Migration = Fixe
        **/
    	
        // controle de conditions externes
    	data.quantiteIndividus = 100.0;
    	data.tauxMortalitePredateur = 0.0;
    	fausseLocAni.penurieAlimentaire = 100.0;
    	data.historiquePenurieEau = Arrays.asList(20., 0., 0., 0., 0., 0.);
        data.historiquePenurieNourriture = Arrays.asList(20., 0., 0., 0., 0., 0.);
    	// taux Mortalite Par Penurie Alimentaire Max = 50%
    	
        // expected
        expected = 50.0;  //  0

        // recupére la vraie valeur
        population.calculerNouvelleQuantiteIndividus();
        population.affecterQuantiteIndividus();
        actual = this.population.quantiteIndividus();

        // assert
        assertEquals(expected, actual, delta);

        /***************************************************************************/        
    }
}

//Classe qui simule un fonctionnement idï¿½al et controlï¿½ de localisation
class FausseLocalisationAnimale extends LocalisationAnimale {
	
	public int index = 0;
	public double balanceEau = 0;
	public double penurieEau = 0;
	public double penurieVegetale = 0;
	public double penurieAlimentaire = 0;
	
	// Constructeur
	public FausseLocalisationAnimale() {
		// ï¿½a ne sert ï¿½ rien, cest juste pour respecter le super
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
	
	// penurieVegetale (en %)
	 @Override
    public double penurieVegetale() {
        return penurieVegetale;
    }

    // penurieAlimentaire (en %)
    @Override
    public double penurieAlimentaire() {
        return penurieAlimentaire;
    }

    // migrerAuNord
    @Override
    public void migrerAuNord(Population population) {
    	index++;
    }

    // migrerAuSud
    @Override
    public void migrerAuSud(Population population) {
    	index--;
    }
}
