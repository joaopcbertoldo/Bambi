package TestFichiers;

import fichiers.Parametres;
import domain.ParametrageSimulation;
import fichiers.GestionnaireFichierParametrage;


public class TestInstanciationDuParametrage {

	public static void testParametresNonClimatiques(String adresse) throws Exception{
		
		GestionnaireFichierParametrage gestionnaire = new GestionnaireFichierParametrage(); 
		GestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique(adresse);
		Parametres  param=(Parametres) GestionnaireFichierParametrage.recupererParametrageSimulation();
		Parametres.afficheSaufPluviometrie(param);
		
	}
	
public static void testParametresPluviometrie(String adresse) throws Exception{
		
		GestionnaireFichierParametrage gestionnaire = new GestionnaireFichierParametrage(); 
		GestionnaireFichierParametrage.chargerPluviometrie(adresse);
		Parametres  param=(Parametres) GestionnaireFichierParametrage.recupererParametrageSimulation();
		Parametres.affichePluviometrie(param);
		
	}

public static void TestInstanciation(String adresse) throws Exception{
	GestionnaireFichierParametrage gestionnaire = new GestionnaireFichierParametrage(); 
	GestionnaireFichierParametrage.chargerPluviometrie(adresse);
	GestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique(adresse);
	Parametres param= new Parametres();
	
	
	
}

	public static void main (String[] args) throws Exception{
		testParametresNonClimatiques("C:\\Repository\\Bambi\\Clara.txt");
		testParametresPluviometrie("C:\\Repository\\Bambi\\joao.txt");
	}
	
}
