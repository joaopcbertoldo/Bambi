package simulation.ecosystemeBambiBase.entites;

import simulation.ecosystemeBambiBase.entitesData.*;
import simulation.ecosystemeBambiBase.entitesData.DataPopulationVegetale;
import simulation.ecosystemeBambiBase.enums.*;

public class PopulationVegetale extends Population {
    protected DataPopulationVegetale dataPopulationVegetale;

    protected LocalisationVegetale localisationVegetale;

    public PopulationVegetale(final DataPopulationVegetale dataPopulationVegetale, final LocalisationVegetale localisationVegetale, final Mois mois) {
        super(dataPopulationVegetale, localisationVegetale, mois);
        this.dataPopulationVegetale = dataPopulationVegetale;
        this.localisationVegetale = localisationVegetale;
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
        double balance             = this.localisationVegetale.balanceEau();
        double nonMange        = this.localisationVegetale.quantiteVegetalNonMange();
        double tauxCroissance = this.tauxCroissance();
        double tauxPerte           = this.tauxPerteParPenurie();
        double min                    = this.dataPopulationVegetale.populationVegetaleMinimale;
        
        double nouvellePopulation = balance > 0 ? (1 + tauxCroissance) * nonMange : (1 - tauxPerte) * nonMange;
        
        if (nouvellePopulation < min)
        	nouvellePopulation = min;
        
        this.dataPopulationVegetale.quantiteIndividusMoisProchain = nouvellePopulation;
    }

}
