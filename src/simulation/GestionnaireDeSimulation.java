package simulation;

import java.io.IOException;

import domain.ParametrageSimulation;
import domain.ResultatSimulation;
import fichiers.GestionnaireFichierParametrage;
import simulation.gestionDeSimulation.Ecosysteme;
import simulation.gestionDeSimulation.SimulationFinieException;

public class GestionnaireDeSimulation {
    private Ecosysteme ecosysteme;

    private static GestionnaireDeSimulation instance;

    private GestionnaireDeSimulation() {
    }

    public static GestionnaireDeSimulation Instance() {
        if (instance == null)
        	instance = new GestionnaireDeSimulation();
        return instance;
    }

    public ResultatSimulation Simuler(ParametrageSimulation parametres) {
    	this.ecosysteme = Ecosysteme.getInstanceEcosysteme(parametres);
    	
    	try {
    		System.out.println("Simulation va commencer !");
    		while(true) {
    			this.ecosysteme.PrendreUnePhotoDeLaSimulation();
    			this.ecosysteme.AvancerUnPas();
    		}
    	} 
    	catch(SimulationFinieException ex) {
    		// imprimer qq part ?
    		System.out.println("Simulation finie !");
    	}
    	
        return this.ecosysteme.getResultatSimulation();
    }
    
    public static void main(String[] args) {
    	// supprimer
    	try {
			GestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique("C:\\Repository\\Bambi\\parametres.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	GestionnaireDeSimulation.Instance().Simuler(GestionnaireFichierParametrage.recupererParametrageSimulation());
    }

}
