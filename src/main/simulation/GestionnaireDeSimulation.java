package main.simulation;

import java.io.IOException;

import main.domain.ParametrageSimulation;
import main.domain.ResultatSimulation;
import main.fichiers.GestionnaireFichierParametrage;
import main.simulation.ecosystemeBambiBase.EcosystemeBambi;
import main.simulation.gestionDeSimulation.Ecosysteme;
import main.simulation.gestionDeSimulation.SimulationFinieException;

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
    	//this.ecosysteme = Ecosysteme.getInstanceEcosysteme(parametres);
    	this.ecosysteme = new EcosystemeBambi(parametres);
    	
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

}
