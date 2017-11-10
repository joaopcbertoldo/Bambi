package simulation;

import domain.ParametrageSimulation;
import domain.ResultatSimulation;
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

}
