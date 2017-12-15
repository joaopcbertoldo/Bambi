package main.simulation.ecosystemeBambiBase.entites;

import main.simulation.ecosystemeBambiBase.entitesData.DataPopulation;


/**
 * Classe abstraite d'une population générique.
 * Contient les attribus et méthodes en commun aux 2 types de population (végétale ou animale).
 * 
 * @author João Paulo
 */
public abstract class Population {
	
	/**
	 * Data de population. 
	 * Les infos qui ne sont pas en fonction directe d'autres valeurs.
	 */
    protected DataPopulation dataPopulation;
    
    /**
     * Objet de localisation.
     * Encapsule les infos référentes au territoire occupé.
     * Les sousclasses font référence au même objet mais avec les variables spécifiques.
     */
    protected Localisation localisation;
    
    /**
     * Référence de temps de la simulation.
     */
    protected Mois mois;
    

    /**
     * Constructeur.
     * 
     * @param dataPopulation Data de population.
     * @param mois Objet de mois (réf de temps).
     */
    public Population(DataPopulation dataPopulation, Mois mois) {
    	// data
        this.dataPopulation = dataPopulation;
        
        // mois
        this.mois = mois;
    }
    
    
    /**
     * Méthode qui prend un objet de localisation.
     * Elle ne peut être appellée que si l'objet de localisation n'est pas settée.
     * 
     * @param localisation L'objet de localisation.
     * @throws Exception Exception lancée en cas d'appel si la localisation est déjà settée.
     */
    public void setLocalisation(Localisation localisation) throws Exception {
    	
    	// vérifie s'il y a un objet déjà accepté
    	if (this.localisation != null)
    		// lance exception
    		throw new Exception("Localisation already set.");
    	
    	// sinon
    	else
    		// affecte la variable
    		this.localisation = localisation;
    }
    
    
    /**
     * Quantité d'individus dans la population.
     * 
     * @return Quantité d'individus de la data.
     */
    public double quantiteIndividus() {
        return this.dataPopulation.quantiteIndividus;
    }

    /**
     * Besoin d'eau de la population.
     * 
     * @return Produit entre le besoin par individu et nombre d'individu.
     */
    public double besoinEauPopulation() {
        return this.dataPopulation.besoinEauParIndividu * this.dataPopulation.quantiteIndividus;
    }

    /**
     * Besoin de végétal de la population.
     * 
     * @return Produit entre le besoin par individu et nombre d'individu.
     */
    public double besoinVegetalPopulation() {
        return this.dataPopulation.besoinVegetalParIndividu * this.dataPopulation.quantiteIndividus;
    }

    /**
     * Récupère l'index du territoire occupé.
     * 
     * @return L'index trouvé dans la localisation.
     */
    public Integer indexTerritoireOccuppe() {
        return this.localisation.indexTerritoire();
    }

    /**
     * Méthode abstraite de calcul de quantité d'individus du mois prochain.
     * Chaque type de population doit avoir sa propre loi.
     * Cette méthode n'affecte pas directement la quantité d'individus parce que 
     * ceci pourrait changer le résultat d'un autre calcul. En effet, le résultat 
     * est sauvegardé dans la data pour être affecté ultérièrement.
     */
    public abstract void calculerNouvelleQuantiteIndividus();

    /**
     * Affecte la quantité d'individus du mois prochain dans la qtté actuelle.	
     */
    public void affecterQuantiteIndividus() {
    	// copie
        this.dataPopulation.quantiteIndividus = this.dataPopulation.quantiteIndividusMoisProchain;
    }

}
