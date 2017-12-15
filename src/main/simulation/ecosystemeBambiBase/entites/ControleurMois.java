package main.simulation.ecosystemeBambiBase.entites;

import main.domain.MoisEnum;

/**
 * ControleurMois (sousclasse de Mois)
 * 
 * Objet qui controle les action d'incrémentation de la 
 * référence de mois et d'itération.
 * 
 * @author João Paulo
 *
 */
public class ControleurMois extends Mois  {
	
	/**
	 * Constructeur.
	 * 
	 * @param mois0 Mois initial de la simulation.
	 */
    public ControleurMois(MoisEnum mois0) {
    	// enum de mois
    	super.mois = mois0;
    	
    	// itération 0
    	super.iteration = 0;
    }

    /**
     * Méthode d'incrémentation d'un mois.
     */
    public void incrementer() {
    	// incrémente l'itération
        super.iteration++;
        
        // changement de mois
        super.mois = super.mois == MoisEnum.Decembre ? MoisEnum.Janvier : super.mois.next();
    }

}
