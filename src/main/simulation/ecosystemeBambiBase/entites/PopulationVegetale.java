package main.simulation.ecosystemeBambiBase.entites;

import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationVegetale;

public class PopulationVegetale extends Population {
	
	// Data Population Vegetale 
    protected DataPopulationVegetale dataPopulationVegetale;

    // Localisation Vegetale
    protected LocalisationVegetale localisationVegetale() {
    	return (LocalisationVegetale) super.localisation;
    }

    // Constructeur
    public PopulationVegetale(DataPopulationVegetale dataPopulationVegetale, Mois mois) {
    	// Super
        super(dataPopulationVegetale, mois);
        
        // Data Population Vegetale 
        this.dataPopulationVegetale = dataPopulationVegetale;
    }

    // en %
    public double tauxCroissance() {
    	// récupère de la data
        return this.dataPopulationVegetale.tauxCroissanceVegetale;
    }

    // en %
    public double tauxPerteParPenurie() {
    	// penurie en eau * taux de perte par penurie d'eau max
        return this.localisation.penurieEau() * this.dataPopulationVegetale.tauxPerteVegetaleParPenurieEauMax / 100;
    }

    public void calculerNouvelleQuantiteIndividus() {
        double balance        = this.localisationVegetale().balanceEau();
        double nonMange       = this.localisationVegetale().quantiteVegetalNonMange();
        double tauxCroissance = this.tauxCroissance();
        double tauxPerte      = this.tauxPerteParPenurie();
        double min            = this.dataPopulationVegetale.populationVegetaleMinimale;
        
        double nouvellePopulation = balance > 0 ? (1 + tauxCroissance/100) * nonMange : (1 - tauxPerte/100) * nonMange;
        
        if (nouvellePopulation < min)
        	nouvellePopulation = min;
        
        this.dataPopulationVegetale.quantiteIndividusMoisProchain = nouvellePopulation;
    }

}
