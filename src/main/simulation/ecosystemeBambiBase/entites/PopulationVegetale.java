package main.simulation.ecosystemeBambiBase.entites;

import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationVegetale;

public class PopulationVegetale extends Population {
    protected DataPopulationVegetale dataPopulationVegetale;

    protected LocalisationVegetale localisationVegetale() {
    	return (LocalisationVegetale) super.localisation;
    }

    public PopulationVegetale(DataPopulationVegetale dataPopulationVegetale, 
    						  Mois mois) {
        super(dataPopulationVegetale, mois);
        this.dataPopulationVegetale = dataPopulationVegetale;
    }

    // en %
    public double tauxCroissance() {
        return this.dataPopulationVegetale.tauxCroissanceVegetale;
    }

    // en %
    public double tauxPerteParPenurie() {
        return this.localisation.penurieEau() * this.dataPopulationVegetale.tauxPerteVegetaleParPenurieEauMax;
    }

    public void calculerNouvelleQuantiteIndividus() {
        double balance        = this.localisationVegetale().balanceEau();
        double nonMange       = this.localisationVegetale().quantiteVegetalNonMange();
        double tauxCroissance = this.tauxCroissance();
        double tauxPerte      = this.tauxPerteParPenurie();
        double min            = this.dataPopulationVegetale.populationVegetaleMinimale;
        
        double nouvellePopulation = balance > 0 ? (1 + tauxCroissance) * nonMange : (1 - tauxPerte) * nonMange;
        
        if (nouvellePopulation < min)
        	nouvellePopulation = min;
        
        this.dataPopulationVegetale.quantiteIndividusMoisProchain = nouvellePopulation;
    }

}
