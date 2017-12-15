package main.simulation.ecosystemeBambiBase.entitesData;

/**
 * Spécification de la data de population pour les végétaux (sousclasse de DataPopulation).
 * 
 * Quantite individu : UP = kg
 * Besoin eau par individu : Litre / kg / mois
 * Besoin vegetal par individu :-
 * 
 * @author João Paulo
 */
public class DataPopulationVegetale extends DataPopulation {
	
	/**
	 * Taux de croissance de la population végétal dans un mois. En %.
	 */
    public double tauxCroissanceVegetale;


    /**
     * Taux de perte de végétal par pénurie en eau maximale. En %.
     */
    public double tauxPerteVegetaleParPenurieEauMax;


    /**
     * Population végétale minimale dans un territoire. En tonne.
     */
    public double populationVegetaleMinimale;
    
    /**
     * Population végétale maximale dans un territoire. En tonne.
     * 1000 fois la quantité minimale.
     * @return Population végétale maximale en tonne.
     */
    public double populationVegetaleMaximale() {
    	return 1000 * populationVegetaleMinimale;
    };
}
