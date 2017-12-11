package test.fichiers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import main.fichiers.GestionnaireFichierResultatSimulation;
import main.fichiers.Resultat;

public class GestionnaireFichierResultatSimulationTest {
	
	private String adresseResultats = "C:\\Repository\\Bambi\\exempleResultat.txt";
	private GestionnaireFichierResultatSimulation gestionnaire= new GestionnaireFichierResultatSimulation();
	private GestionnaireFichierResultatSimulation gestionnaire1= new GestionnaireFichierResultatSimulation();
	
	private Resultat resu ;
	private String adresseSauv ="C:\\Repository\\Bambi\\parametres.txt";
	
			@Test
	 public void setUp() throws Exception {
		ArrayList<Integer> pop0 = new ArrayList<Integer>();
		ArrayList<Integer> pop1 = new ArrayList<Integer>();
		ArrayList<Integer> pop2 = new ArrayList<Integer>();
		ArrayList<Integer> pop3 = new ArrayList<Integer>();
		ArrayList<Integer> pop4 = new ArrayList<Integer>();
		pop0.add(1200);
		pop0.add(0);
		pop0.add(100);
		pop0.add(0);
		pop1.add(0);
		pop1.add(0);
		pop1.add(0);
		pop1.add(0);
		pop2.add(0);
		pop2.add(1000);
		pop2.add(0);
		pop2.add(0);
		pop3.add(0);
		pop3.add(0);
		pop3.add(0);
		pop3.add(0);
		pop4.add(0);
		pop4.add(0);
		pop4.add(0);
		pop4.add(3000);
		ArrayList<Double> stockEau0 = new ArrayList<Double>();
		ArrayList<Double> stockEau1 = new ArrayList<Double>();
		ArrayList<Double> stockEau2 = new ArrayList<Double>();
		ArrayList<Double> stockEau3 = new ArrayList<Double>();
		ArrayList<Double> stockEau4 = new ArrayList<Double>();
		stockEau0.add(450.0);
		stockEau0.add(40.0);
		stockEau0.add(450.0);
		stockEau0.add(450.0);
		stockEau1.add(567.0);
		stockEau1.add(67.0);
		stockEau1.add(5.0);
		stockEau1.add(567.0);
		stockEau2.add(789.0);
		stockEau2.add(79.0);
		stockEau2.add(789.0);
		stockEau2.add(789.0);
		stockEau3.add(567.0);
		stockEau3.add(67.0);
		stockEau3.add(5.0);
		stockEau3.add(547.0);
		stockEau4.add(789.0);
		stockEau4.add(78.0);
		stockEau4.add(789.0);
		stockEau4.add(786.0);
		ArrayList<Double> stockVeg0 = new ArrayList<Double>();
		ArrayList<Double> stockVeg1 = new ArrayList<Double>();
		ArrayList<Double> stockVeg2 = new ArrayList<Double>();
		ArrayList<Double> stockVeg3 = new ArrayList<Double>();
		ArrayList<Double> stockVeg4 = new ArrayList<Double>();
		stockVeg0.add(1000.0);
		stockVeg0.add(4000.0);
		stockVeg0.add(1000.0);
		stockVeg0.add(1000.0);
		stockVeg1.add(2000.0);
		stockVeg1.add(1000.0);
		stockVeg1.add(20.0);
		stockVeg1.add(2000.0);
		stockVeg2.add(1000.0);
		stockVeg2.add(3000.0);
		stockVeg2.add(1000.0);
		stockVeg2.add(1000.0);
		stockVeg3.add(2000.0);
		stockVeg3.add(300.0);
		stockVeg3.add(20.0);
		stockVeg3.add(2003.0);
		stockVeg4.add(1000.0);
		stockVeg4.add(1000.0);
		stockVeg4.add(1000.0);
		stockVeg4.add(1004.0);
		ArrayList<ArrayList<Integer>> popA = new ArrayList<>();
		popA.add(pop0);
		popA.add(pop1);
		popA.add(pop2);
		popA.add(pop3);
		popA.add(pop4);
		ArrayList<ArrayList<Double>> stockEa = new ArrayList<>();
		stockEa.add(stockEau0);
		stockEa.add(stockEau1);
		stockEa.add(stockEau2);
		stockEa.add(stockEau3);
		stockEa.add(stockEau4);
		ArrayList<ArrayList<Double>> stockVe = new ArrayList<>();
		stockVe.add(stockVeg0);
		stockVe.add(stockVeg1);
		stockVe.add(stockVeg2);
		stockVe.add(stockVeg3);
		stockVe.add(stockVeg4);
		 resu= new Resultat(4,popA, stockEa, stockVe);
		}
	
	
	
	public void chargerResultatsSimulationTest() throws Exception {
		gestionnaire.chargerResultatSimulation(adresseResultats);
		Resultat r = (Resultat) gestionnaire.recupererResultatSimulation();
		assertEquals(r, resu);
		
	}
	
	public void sauvegarderResultatSimulationTest( ) throws Exception{
		gestionnaire1.sauvegarderResultatSimulation(resu, adresseSauv); 
		gestionnaire1.chargerResultatSimulation(adresseSauv);
		assertEquals(gestionnaire1.recupererResultatSimulation(), resu);
		
	}

}
