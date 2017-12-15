package main.simulation.ecosystemeBambiBase.entites;

import main.simulation.ecosystemeBambiBase.entitesData.*;

/**
 * PopulationVegetale (sousclasse de Population).
 * Représente une population de végétal d'un territoire.
 * 
 * @author João Paulo
 */
public class PopulationVegetale extends Population {
	
	/**
	 * Localisation Vegetale.
	 * Accede la localisation de Population en fesant le cast vers Localisation Vegetale
	 * 
	 * @return Objet de localisation de Population casté en Localisation Vegetale.
	 */
    protected LocalisationVegetale localisationVegetale() {
    	// cast
    	return (LocalisationVegetale) super.localisation;
    }
    
    
    /**
	 * Data de population végétale.
	 * Accede le data de Population en fesant le cast vers DataPopulationVegetale
	 * 
	 * @return Objet de data de Population casté en DataPopulationVegetale.
	 */
    protected DataPopulationVegetale dataPopulationVegetale() {
    	// cast
    	return (DataPopulationVegetale) super.dataPopulation;
    }

    
    /** 
     * Constructeur.
     * @param dataPopulationVegetale Objet de data de population végétale.
     * @param mois Objet de référence de mois/itération.
     */
    public PopulationVegetale(DataPopulationVegetale dataPopulationVegetale, Mois mois) {
    	// constructeur de Population
        super(dataPopulationVegetale, mois);
    }
    
    
    /**
     * Calcul le taux de croissance de la population végétale.
     * En fait, le taux de croissance est constant, donc il est récupéré dans la data.
     * 
     * @return Taux de croissance en %.
     */
    public double tauxCroissance() {
    	// récupère de la data
        return this.dataPopulationVegetale().tauxCroissanceVegetale;
    }
    
    
    /**
     * Calcul du taux de perte par pénurie.
     * Équation:
     * 		penEau * tauxMax
	 * 
	 * penEau : est la penurie en eau dans le territoire occupé
	 * tauxMax : taux de perte par pénurie d'eau max.
	 *  
     * @return Taux de perte par pénurie en %.
     */
    public double tauxPerteParPenurie() {
    	// valeurs
    	double penEau =  this.localisation.penurieEau();
    	double tauxMax = this.dataPopulationVegetale().tauxPerteVegetaleParPenurieEauMax / 100;  // correction de %
    	
    	// calcul
        return penEau * tauxMax;
    }
    
    
    /**
     * méthode qui surécrit la méthode abstraite de calcul de quantité d'individus pour l'avancement 
     * d'un pas de la simulation.
     * 
     * équation:
     * 		si balance d'eau > 0
     * 			(1 + tauxCroissance) * nonMange
     * 
     * 		si balance d'eau < 0
     * 			(1 - tauxPerte) * nonMange
     * 
     * la valeur résultante est bornée entre le max et min (dans le data)
     */
    public void calculerNouvelleQuantiteIndividus() {
    	// valeurs de calcul
        double balance        = this.localisationVegetale().balanceEau();
        double nonMange       = this.localisationVegetale().quantiteVegetalNonMange();
        double tauxCroissance = this.tauxCroissance()/100;       // correction du %
        double tauxPerte      = this.tauxPerteParPenurie()/100;  // correction du %
        
        // limites
        double min            = this.dataPopulationVegetale().populationVegetaleMinimale;
        double max 			  = this.dataPopulationVegetale().populationVegetaleMaximale();
        
        // calcul
        double nouvellePopulation = balance > 0 ? (1 + tauxCroissance) * nonMange : (1 - tauxPerte) * nonMange;
        
        // min
        if (nouvellePopulation < min)
        	nouvellePopulation = min;
        
        // max
        if (nouvellePopulation > max)
        	nouvellePopulation = max;
        
        // sauvegarde
        this.dataPopulationVegetale().quantiteIndividusMoisProchain = nouvellePopulation;
    }

}
