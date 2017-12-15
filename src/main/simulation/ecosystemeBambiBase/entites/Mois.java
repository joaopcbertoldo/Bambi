package main.simulation.ecosystemeBambiBase.entites;

import main.domain.MoisEnum;


/**
 * Mois
 * 
 * Objet de référence de temps (itération et mois de l'année)
 * pour les objets de la simulation.
 * 
 * @author João Paulo
 */
public abstract class Mois {
	
	/**
	 * Itération (initialisée à 0).
	 */
    protected int iteration = 0;
    
    
    /**
     * Enumération de mois.
     */
    protected MoisEnum mois;
    
    
    /**
     * Getter d'itération.
     * @return Itération actuelle.
     */
    public int getIteration() {
        return this.iteration;
    }

    
    /**
     * Getter d'énumération de mois.
     * @return Mois actuel en enum.
     */
    public MoisEnum getMois() {
        return this.mois;
    }
}
