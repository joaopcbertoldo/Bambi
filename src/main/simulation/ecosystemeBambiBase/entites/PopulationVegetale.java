package main.simulation.ecosystemeBambiBase.entites;

import main.simulation.ecosystemeBambiBase.entitesData.*;

/**
 * PopulationVegetale (sousclasse de Population).
 * Repr�sente une population de v�g�tal d'un territoire.
 * 
 * @author Jo�o Paulo
 */
public class PopulationVegetale extends Population {
	
	/**
	 * Localisation Vegetale.
	 * Accede la localisation de Population en fesant le cast vers Localisation Vegetale
	 * 
	 * @return Objet de localisation de Population cast� en Localisation Vegetale.
	 */
    protected LocalisationVegetale localisationVegetale() {
    	// cast
    	return (LocalisationVegetale) super.localisation;
    }
    
    
    /**
	 * Data de population v�g�tale.
	 * Accede le data de Population en fesant le cast vers DataPopulationVegetale
	 * 
	 * @return Objet de data de Population cast� en DataPopulationVegetale.
	 */
    protected DataPopulationVegetale dataPopulationVegetale() {
    	// cast
    	return (DataPopulationVegetale) super.dataPopulation;
    }

    
    /** 
     * Constructeur.
     * @param dataPopulationVegetale Objet de data de population v�g�tale.
     * @param mois Objet de r�f�rence de mois/it�ration.
     */
    public PopulationVegetale(DataPopulationVegetale dataPopulationVegetale, Mois mois) {
    	// constructeur de Population
        super(dataPopulationVegetale, mois);
    }
    
    
    /**
     * Calcul le taux de croissance de la population v�g�tale.
     * En fait, le taux de croissance est constant, donc il est r�cup�r� dans la data.
     * 
     * @return Taux de croissance en %.
     */
    public double tauxCroissance() {
    	// r�cup�re de la data
        return this.dataPopulationVegetale().tauxCroissanceVegetale;
    }
    
    
    /**
     * Calcul du taux de perte par p�nurie.
     * �quation:
     * 		penEau * tauxMax
	 * 
	 * penEau : est la penurie en eau dans le territoire occup�
	 * tauxMax : taux de perte par p�nurie d'eau max.
	 *  
     * @return Taux de perte par p�nurie en %.
     */
    public double tauxPerteParPenurie() {
    	// valeurs
    	double penEau =  this.localisation.penurieEau();
    	double tauxMax = this.dataPopulationVegetale().tauxPerteVegetaleParPenurieEauMax / 100;  // correction de %
    	
    	// calcul
        return penEau * tauxMax;
    }
    
    
    /**
     * m�thode qui sur�crit la m�thode abstraite de calcul de quantit� d'individus pour l'avancement 
     * d'un pas de la simulation.
     * 
     * �quation:
     * 		si balance d'eau > 0
     * 			(1 + tauxCroissance) * nonMange
     * 
     * 		si balance d'eau < 0
     * 			(1 - tauxPerte) * nonMange
     * 
     * la valeur r�sultante est born�e entre le max et min (dans le data)
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
        if (nouvellePopulation < 1000*min)
        	nouvellePopulation = min;
        
        // max
        if (nouvellePopulation > 1000*max)
        	nouvellePopulation = max;
        
        // sauvegarde
        this.dataPopulationVegetale().quantiteIndividusMoisProchain = nouvellePopulation;
    }

}
