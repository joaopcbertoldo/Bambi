package main.simulation.gestionDeSimulation;

import java.lang.reflect.*;

import main.domain.ParametrageSimulation;
import main.domain.ResultatSimulation;

public abstract class Ecosysteme {
    private int nombreDePasExecutes = 0;

    protected ParametrageSimulation parametrageSimulation;

    protected ResultatSimulation resultatSimulation;

    protected Ecosysteme(ParametrageSimulation parametrageSimulation) {
    	this.parametrageSimulation = parametrageSimulation;	
    }

    public static Ecosysteme getInstanceEcosysteme(ParametrageSimulation parametres) {
    	try {
    		// bricolage
    		// remplacer par une vraie reflexion qui trouve les sousclasses de Ecosysteme
			Class<?> classe = Class.forName("simulation.ecosystemeBambiBase." + "EcosystemeBambi");
	    	
			Constructor<?> constructeur = classe.getDeclaredConstructors()[0];
	    	
			Ecosysteme eco = (Ecosysteme) constructeur.newInstance(parametres);
			
			return eco;
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    public abstract void PrendreUnePhotoDeLaSimulation();

    protected abstract void ChangerSysteme();

    public void AvancerUnPas() throws SimulationFinieException {
    	if (this.nombreDePasExecutes++ > this.parametrageSimulation.nombreDePas())
    		throw new SimulationFinieException();
    	else
    		this.ChangerSysteme();
    }

    public ResultatSimulation getResultatSimulation() {
        return this.resultatSimulation;
    }
    
}
