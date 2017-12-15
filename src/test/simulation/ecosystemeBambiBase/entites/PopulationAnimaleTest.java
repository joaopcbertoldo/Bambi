package test.simulation.ecosystemeBambiBase.entites;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entites.*;
import main.simulation.ecosystemeBambiBase.entitesData.*;
import main.simulation.ecosystemeBambiBase.enums.*;


/******************************** CODE DES TESTES *******************************************/ 

/**
 * Classe de teste de Population Animale
 * 
 * IMPORTANT
 * La <<population animale>> dépend de la <<localisation animale>>, donc, pour ne pas tester
 * deux classes à la fois, on a crée une classe auxiliaire qui se comporte comme une 
 * fausse localisation, dont on peut controler facilement les retours. Cf. la fin du fichier.
 * 
 * ORGANISATION
 * Chaque méthode de la classe de teste sert à tester une méthode de la classe <<population animale>>
 * ou une fonctionnalité que l'on obtient à partir de quelques appels de méthodes différentes.
 */
public class PopulationAnimaleTest {
	
	/******************** ATTRIBUS AUXILIAIRES ********************/
	
    /** 
     * valeurs : attendue, réele, tolérance de différence
     */
    double expected, actual, delta = 0.001;
    
    /**
     * état de migration attendu
     */
    StatusMigrationEnum expectedMigration;
    
    /**
     * index de localisation attendu
     */
    int expectedIndex;

	/**
	 * data de population vegetale
	 */
	private DataPopulationAnimale data;

	/**
	 * fausse localisation
	 */
	private FausseLocalisationAnimale fausseLocAni;

	/**
	 * controleur de moi
	 */
	private ControleurMois ctrl;

    // population
	private PopulationAnimale population;

	
	/******************** MÉTHODES AUXILIAIRES ********************/
	
	/**
	 *  setUp
	 * Méthode auxiliaire pour initialiser un objet de population animale.
	 * Cette méthode est appelée avant chaque teste.
	 */
	@Before
	public void setUp() throws Exception {
		/* Valeurs d'initialisation:
		 * 		mois 0                   = Janvier
		 * 		besoinEauParIndividu     = 10 L/indiv
		 * 		besoinVegetalParIndividu = 10 kg/indiv
		 * 		quantiteIndividus        = 100 indiv
		 * 		tauxMortalitePredateur   = 0%
		 * 		tauxMortaliteParPenurieAlimentaireMax = 50%
		 * 		tauxNaissanceMax         = 100%
		 * 		status Migration         = Fixe
		 * 
		 * 		historiquePenurie, historiquePenurieNourriture --> listes vide
		 * */
		
		// controleur de mois
		ctrl = new ControleurMois(MoisEnum.Janvier);
		
		// data population
		data = new DataPopulationAnimale();
		
		// valeurs fixes
		data.besoinEauParIndividu     = 10.0;
		data.besoinVegetalParIndividu = 10.0; 
		data.quantiteIndividus        = 100;
		data.tauxMortalitePredateur   = 0;
	    data.tauxMortaliteParPenurieAlimentaireMax = 50;
	    data.tauxNaissanceMax         = 100;
	    data.statusMigration          = StatusMigrationEnum.Fixe;
	    
	    // historiques vides
	    data.historiquePenurieEau        = new ArrayList<Double>();
	    data.historiquePenurieNourriture = new ArrayList<Double>();
	    
		// population
		population = new PopulationAnimale(data, ctrl);
		
		
		// localisation de la population
    	fausseLocAni = new FausseLocalisationAnimale();
    	fausseLocAni.index              = 0;
    	fausseLocAni.balanceEau         = 0.0;
    	fausseLocAni.penurieEau         = 0.0;
    	fausseLocAni.penurieVegetale    = 0.0;
    	fausseLocAni.penurieAlimentaire = 0.0;

    	// set dans la population
		population.setLocalisation(fausseLocAni);
	}
	
	/**
	 * Méthode auxiliaire qui incrément le mois N fois.
	 * 
	 * @param N Nombre d'incrément
	 */
	private void incrementer(int N) {
		// contage
		for (int i = 0; i < N; i++)
			
			// appel de la méthode
			this.ctrl.incrementer();	
	}

	
	/******************** TESTES ********************/
	
	/**
	 *  testPenurieCumulee
	 * Cette méthode teste les calculs de penurie cumulée d'eau et Vegetale.
	 * Ils sont ensemble car le calcul est exactement le même dans les 2.
	 */
    @Test
    public void testPenurieCumulee() {
        // la penurie cumulée est la somme des 6 derniers penurie (en %)

        /****** Situation 1 : historique nul. ******/
        // controle de conditions externes
        data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(0., 0., 0., 0., 0., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(0., 0., 0., 0., 0., 0.));

        // expected = 0
        expected = 0.0;

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);
        
        
        /****** Situation 2 : 1 seule penurie ******/
        // controle de conditions externes
        data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(0., 0., 0., 0., 0., 100.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(0., 0., 0., 0., 0., 100.));
        
        // expected = 100
        expected = 100.0;

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);

        
        /****** Situation 3 : 2 penuries. ******/
        // controle de conditions externes
        data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(0., 0., 100., 0., 100., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(0., 0., 100., 0., 100., 0.));

        // expected = 200
        expected = 200.0;  //  0

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);
        

        /****** Situation 4 : liste avec + que 6 registres. ******/
        // controle de conditions externes - avec + q 6 valeurs
        data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(100., 100., 100., 100., 100., 100., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(100., 100., 100., 100., 100., 100., 0.));

        // expected = 500
        expected = 500.0;  //  0

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);
    }
    
    
    /**
     * test Enregistrement Penurie
	 * Cette méthode teste l'enregistrement des penuries d'eau et Vegetale dans les historiques.
	 * Ils sont ensemble car le calcul est exactement le même dans les 2.
	 */
    @Test
    public void testEnregistrementPenurie() throws Exception {
    	// à chaque calcul de nouvelle population, la penurie de la localisation actuelle 
    	// doit être enregistrée dans l'historique
    	
    	/****** Situation 1 : historique nul. ******/
    	// le setUp initialise les historiques avec des listes nulles
    	
        // expected = 0
        expected = 0.0;

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);
        
        
    	/****** Situation 2 : 1 enregistrement. ******/
    	fausseLocAni.penurieEau      = 100;
    	fausseLocAni.penurieVegetale = 100;
        try {
			population.calculerNouvelleQuantiteIndividus();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
        
        // expected = 100
        expected = 100.0;

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);
        
        
    	/****** Situation 3 : liste avec plus de 6 enregistrements. ******/
    	fausseLocAni.penurieEau      = 100;
    	fausseLocAni.penurieVegetale = 100;
        population.calculerNouvelleQuantiteIndividus();  // 2eme 
        population.calculerNouvelleQuantiteIndividus();
        population.calculerNouvelleQuantiteIndividus();
        population.calculerNouvelleQuantiteIndividus();
        population.calculerNouvelleQuantiteIndividus();
        population.calculerNouvelleQuantiteIndividus();
        population.calculerNouvelleQuantiteIndividus();  // 8eme
        
        // expected = 600
        expected = 600.0;

        // assert - eau
        assertEquals(expected, this.population.penurieEauCumulee(), delta);

        // assert - nourriture
        assertEquals(expected, this.population.penurieVegetaleCumulee(), delta);
        
    }

    
    /**
     * testTauxNaissance
	 * Cette méthode teste les calculs de taux de naissance.
	 */
    @Test
    public void testTauxNaissance() {
        // le taux de naissance doit suivre la suivante équation:
    	//     (.1/penEau * .1/penVeg) * TauxMAX 
    	
    	// penEau, penVeg NE SONT PAS en %
    	// la fonction sature en TauxMAX

    	
    	/****** Situation 1 : 100%. ******/
    	
        // controle de conditions externes
    	
    	// penurieEauCumulee      = 10%
    	// penurieVegetaleCumulee = 10%
    	data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(10., 0., 0., 0., 0., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(10., 0., 0., 0., 0., 0.));
	    
        // taxu naisssance max = 100%
        data.tauxNaissanceMax = 100;

        // expected = 100%
        expected = 100.0; 

        // recupére la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);

        
        /****** Situation 2 : 1%. ******/     
    	
        // controle de conditions externes
    	
    	// penurieEauCumulee      = 100%
    	// penurieVegetaleCumulee = 100%
    	data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(100., 0., 0., 0., 0., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(100., 0., 0., 0., 0., 0.));
	    
        // taxu naisssance max = 100%
        data.tauxNaissanceMax = 100;

        // expected = 1%
        expected = 1.0; 

        // recupére la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);
        

        /****** Situation 3 : saturation. ******/  
    	
        // controle de conditions externes
    	
    	// penurieEauCumulee      = 5%
    	// penurieVegetaleCumulee = 5%
    	data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(5., 0., 0., 0., 0., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(5., 0., 0., 0., 0., 0.));
	    
        // taxu naisssance max = 100%
        data.tauxNaissanceMax = 100;

        // expected = 100% (saturation en taux max)
        expected = 100.0; 

        // recupère la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);

        
        /****** Situation 4 : un quart du max. ******/  
    	
        // controle de conditions externes
    	
    	// penurieEauCumulee      = 20%
    	// penurieVegetaleCumulee = 20%
    	data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(20., 0., 0., 0., 0., 0.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(20., 0., 0., 0., 0., 0.));
	    
        // taxu naisssance max = 100%
        data.tauxNaissanceMax = 100;

        // expected = 25% 
        expected = 25.0; 

        // recupère la vraie valeur
        actual = this.population.tauxNaissance();

        // assert
        assertEquals(expected, actual, delta);
    }

    
    /**
     * test Taux Mortalite
	 * Cette méthode teste les calculs de taux de mortalité.
	 */
    @Test
    public void testTauxMortalite() {
        // le taux de naissance doit suivre la suivante équation:
    	//     tauxPred + tauxPenMax * penAlim
    	
    	// il faut saturer a 100%
    	
    	
    	/****** Situation 1 : penurie alimentaire max. ******/
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 0.0;
    	data.tauxMortaliteParPenurieAlimentaireMax = 50.0;
    	
    	// penurieAlimentaire = 100%
    	fausseLocAni.penurieAlimentaire = 100.0;
    	
        // expected = 50%
        expected = 50.0; 

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
        

        /****** Situation 2 : 50% de penurie alimentaire max. ******/
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 0.0;
    	data.tauxMortaliteParPenurieAlimentaireMax = 50.0;
    	
    	// penurieAlimentaire = 50%
    	fausseLocAni.penurieAlimentaire = 50.0;
    	
        // expected = 25%
        expected = 25.0; 

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
        

        /****** Situation 3 : taux de predateur. ******/
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 50.0;
    	data.tauxMortaliteParPenurieAlimentaireMax = 50.0;
    	
    	// penurie Alimentaire = 50%
    	fausseLocAni.penurieAlimentaire = 50.0;
    	
        // expected = 75%
        expected = 75.0; 

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
        

        /****** Situation 4 : taux de predateur 2. ******/
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 75.0;
    	data.tauxMortaliteParPenurieAlimentaireMax = 50.0;
    	
    	// penurie Alimentaire = 50%
    	fausseLocAni.penurieAlimentaire = 50.0;
    	
        // expected = 100%
        expected = 100.0; 

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
       
        
        /****** Situation 5 : saturation. ******/
    	
        // controle de conditions externes
    	data.tauxMortalitePredateur = 75.0;
    	data.tauxMortaliteParPenurieAlimentaireMax = 50.0;
    	
    	// penurie Alimentaire = 100%
    	fausseLocAni.penurieAlimentaire = 100.0;
    	
        // expected = 100%
        expected = 100.0; 

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
        
        
        /****** Situation 6 : zero. ******/      
        // controle de conditions externes
    	data.tauxMortalitePredateur = 0.0;
    	data.tauxMortaliteParPenurieAlimentaireMax = 50.0;
    	
    	// penurie Alimentaire = 0%
    	fausseLocAni.penurieAlimentaire = 0.0;
    	
        // expected = 0%
        expected = 0.0; 

        // recupére la vraie valeur
        actual = this.population.tauxMortalite();

        // assert
        assertEquals(expected, actual, delta);
    }
    
    
    /**
     * Ce teste va tester à la fois 2 choses : 
     * 		1) le calcul de population en fonction des conditions du système
     * 		2) l'affectation de population seulement avec l'appel la fonction correspondante
     * */
    @Test
    public void testCalculerNouvelleQuantiteIndividus() throws Exception {
        // le calcul de population est selon l'équation:
    	//     actuel * (1 + tauxNaissance - tauxMortalite)
    	
    	
    	/****** Situation 1 : seulement naissance. ******/
        // controle de conditions externes
    	
    	// population = 100 (cf setUp)
    	// taux de naissance = MAX = 100% (historiques de pénurie nuls, cf setUp)
    	// taux de mortalité = 0 (pas de prédateur, pénurie alimentaire null, cf setUp)
    	setUp();

        // recupére la vraie valeur
        population.calculerNouvelleQuantiteIndividus();
        
        // expected = 100 (avant l'affectation)
        expected = 100.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
        
        // affectation
        population.affecterQuantiteIndividus();

        // expected = 200 (après l'affectation)
        expected = 200.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
    	
    	
    	/****** Situation 2 : seulement mort. ******/
        // controle de conditions externes
    	
    	// population = 100 (cf setUp)
        setUp();
        
    	// taux de naissance = 0%
        data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(100., 100., 100., 100., 100., 100.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(100., 100., 100., 100., 100., 100.));
        
    	// taux de mortalité = 50%
    	data.tauxMortalitePredateur = 50;

        // recupére la vraie valeur
        population.calculerNouvelleQuantiteIndividus();
        
        // expected = 100 (avant l'affectation)
        expected = 100.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
        
        // affectation
        population.affecterQuantiteIndividus();

        // expected = 50 (après l'affectation)
        expected = 50.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
    	
    	
    	/****** Situation 3 : ni l'un ni l'autre. ******/
        // controle de conditions externes
    	
    	// population = 100 (cf setUp)
        setUp();
        
    	// taux de naissance = 0%
        data.historiquePenurieEau        = new ArrayList<>(Arrays.asList(100., 100., 100., 100., 100., 100.));
        data.historiquePenurieNourriture = new ArrayList<>(Arrays.asList(100., 100., 100., 100., 100., 100.));
        
    	// taux de mortalité = 0% (cf setUp)

        // recupére la vraie valeur
        population.calculerNouvelleQuantiteIndividus();
        
        // expected = 100 (avant l'affectation)
        expected = 100.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
        
        // affectation
        population.affecterQuantiteIndividus();

        // expected = 100 (après l'affectation)
        expected = 100.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
    	
    	
    	/****** Situation 4 : les 2 avec plus de naissance. ******/
        // controle de conditions externes
    	
    	// population = 100 (cf setUp)
        setUp();
        
    	// taux de naissance = 100% (cf setUp)    	
        // taux de mortalité = 50%
    	data.tauxMortalitePredateur = 50;
        
    	// recupére la vraie valeur
        population.calculerNouvelleQuantiteIndividus();
        
        // expected = 100 (avant l'affectation)
        expected = 100.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
        
        // affectation
        population.affecterQuantiteIndividus();

        // expected = 150 (après l'affectation)
        expected = 150.0;

        // assert
        assertEquals(expected, population.quantiteIndividus(), delta);
    }
    
    
    /**
     * test Migrer
     * 
     * Cette méthode teste si la décision de migration est correctement prise dans l'état <<Fixe>>.
     * */
    @Test
    public void testMigrerFixeResteFixe() throws Exception {
    	// état initial : Fixe
    	// changement à MigrantAuSud doit se faire s'il est plus tard d'ans l'année que Septembre
    	// changement à MigrantAuNord doit se faire s'il y a de la pénurie
    	
    	/****** Situation 1 : rester fixe. ******/
        // controle de conditions externes
    	
    	// initialisation
    	// mois = Janvier
    	// pénurie = 0
    	// index de territoire = 0
    	setUp();
    	
    	// état initial
    	data.statusMigration = StatusMigrationEnum.Fixe;
        
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.Fixe;
        expectedIndex = 0;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
    	
        
        /****** Situation 1' : rester fixe avec autre mois. ******/
        // controle de conditions externes
    	
    	// initialisation
    	// pénurie = 0
    	// index de territoire = 0
    	setUp();
    	
    	// état initial
    	data.statusMigration = StatusMigrationEnum.Fixe;

    	// mois = Mars
    	incrementer(2);
    	
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.Fixe;
        expectedIndex = 0;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
    }

    
    /**
     * test Migrer Au Nord
     * 
     * Cette méthode teste si la décision de migration au nord est correctement prise 
     * et si l'objet de localisation est correctement appellé.
     * */
    @Test
    public void testMigrerVersNord() throws Exception {
    	/****** Situation 1 : migrer au nord à partir de fixe . ******/
        // controle de conditions externes
    	
    	// initialisation
    	setUp();
    	
    	// ajout de pénurie --> ce qui force la migration vers le nord
    	fausseLocAni.penurieAlimentaire = 1;
    	
    	// état initial
    	data.statusMigration = StatusMigrationEnum.Fixe;
        
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuNord;
        expectedIndex = 1;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
    	
        
        /** vérification s`il va jusqu`au fond (5eme) **/
        
        // migrer
    	population.migrer();
    	population.migrer();
    	population.migrer();
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuNord;
        expectedIndex = 5;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
    	
        
        /** vérification qu`il s`arrete au 5eme**/

        // migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.Fixe;
        expectedIndex = 5;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
    	        

    	/****** Situation 2 : migrer au nord à partir de fixe et renverser le sens en septembre. ******/
        // controle de conditions externes
    	
    	// initialisation
        // mois = Janvier
    	setUp();

    	// ajout de pénurie --> ce qui force la migration vers le nord
    	fausseLocAni.penurieAlimentaire = 1;
    	
    	// état initial
    	data.statusMigration = StatusMigrationEnum.Fixe;
        
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuNord;
        expectedIndex = 1;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
        
        /****** la popultaion bouge un peu ******/ 
    	// migrer
    	population.migrer();
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuNord;
        expectedIndex = 3;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
        
        
        /****** changement de sens en septembre ******/ 
        
        // passage au mois de septembre
        incrementer(8);
        
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuSud;
        expectedIndex = 2;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
        
        
        /****** vérification qu'ils s'arretent******/ 
    	// migrer
    	population.migrer();  // ils arrivent au 1er 
    	population.migrer();  // ils doivent s'arreter
    	
        // expected
        expectedMigration = StatusMigrationEnum.Fixe;
        expectedIndex = 1;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
    }
    
    
    /**
     * test Migrer Au Sud
     * 
     * Cette méthode teste si la décision de migration au sud est correctement prise 
     * et si l'objet de localisation est correctement appellé.
     * */
    @Test
    public void testMigrerVersSud() throws Exception {
    	
    	/****** Situation 1 : rester fixe au nord. ******/
        // controle de conditions externes
    	
    	// initialisation
    	// mois 0 = Janvier
    	setUp();
    	
    	// index du territoire occupé
    	fausseLocAni.index = 5;
    	
    	// état initial
    	data.statusMigration = StatusMigrationEnum.Fixe;
        
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.Fixe;
        expectedIndex = 5;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
        
        
    	/****** Situation 2 : migrer au sud à partir de fixe (en partant du 5eme). ******/
        // avancement jq'au mois de septembre
        incrementer(8);
        
    	// migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuSud;
        expectedIndex = 4;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
        
        
        /** vérification s`il va jusqu`au fond (1er) **/
        
        // migrer
    	population.migrer();  // 3
    	population.migrer();  // 2
    	population.migrer();  // 1
    	
        // expected
        expectedMigration = StatusMigrationEnum.MigrantAuSud;
        expectedIndex = 1;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
    	
        
        /** vérification qu`il s`arrete au 1er**/

        // migrer
    	population.migrer();
    	
        // expected
        expectedMigration = StatusMigrationEnum.Fixe;
        expectedIndex = 1;

        // assert
        assertEquals(expectedMigration, population.etatDeMigration());
        assertEquals(expectedIndex, fausseLocAni.index);
    }
}


/******************************** CODE AUXILIAIRE *******************************************/ 

/**
 * Classe qui simule un fonctionnement idï¿½al et controlï¿½ de localisation.
 */
class FausseLocalisationAnimale extends LocalisationAnimale {
	
	public int index;
	public double balanceEau;
	public double penurieEau;
	public double penurieVegetale;
	public double penurieAlimentaire;
	
	// Constructeur
	public FausseLocalisationAnimale() {
		// ça ne sert à rien, cest juste pour respecter le super
		super(new Territoire(new DataTerritoire()));
	}
	
	 /**
	 * retourne indexTerritoire
	 */
	@Override
	 public int indexTerritoire() {
	     return index;
	 }
	
	 /**
	 * retourne balanceEau
	 */
	@Override
	 public double balanceEau() {
	     return balanceEau;
	 }
	
	 /**
	 * retourne penurieEau
	 */
	 @Override
	 public double penurieEau() {
	     return penurieEau;
	 }
	
	 /**
	 * retourne penurieVegetale
	 */
	 @Override
    public double penurieVegetale() {
        return penurieVegetale;
    }

	/**
	 * retourne penurieAlimentaire
	 */
    @Override
    public double penurieAlimentaire() {
        return penurieAlimentaire;
    }

    /**
     * incrément l'index
     */
    @Override
    public void migrerAuNord(Population population) {
    	index++;
    }

    /**
     * decremente l'index
     */
    @Override
    public void migrerAuSud(Population population) {
    	index--;
    }
}
