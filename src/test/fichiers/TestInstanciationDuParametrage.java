package test.fichiers;

import main.domain.ParametrageSimulation;
import main.fichiers.GestionnaireFichierParametrage;
import main.fichiers.Parametres;


public class TestInstanciationDuParametrage {

	public static void testParametresNonClimatiques(String adresse) throws Exception{
		
		GestionnaireFichierParametrage gestionnaire = new GestionnaireFichierParametrage(); 
		GestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique(adresse);
		Parametres  param=(Parametres) GestionnaireFichierParametrage.recupererParametrageSimulation();
		//Parametres.afficheSaufPluviometrie(param);
		
	}
	
public static void testParametresPluviometrie(String adresse) throws Exception{
		
		GestionnaireFichierParametrage gestionnaire = new GestionnaireFichierParametrage(); 
		GestionnaireFichierParametrage.chargerPluviometrie(adresse);
		Parametres  param=(Parametres) GestionnaireFichierParametrage.recupererParametrageSimulation();
		//Parametres.affichePluviometrie(param);
		
	}

public static void TestInstanciation(String adresse) throws Exception{

	
	
}

}
