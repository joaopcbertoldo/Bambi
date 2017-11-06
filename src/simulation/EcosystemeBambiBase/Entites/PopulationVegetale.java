package simulation.EcosystemeBambiBase.Entites;

import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.EntitesData.DataPopulationVegetale;
import simulation.EcosystemeBambiBase.Enums.*;

public class PopulationVegetale extends Population {
    protected DataPopulationVegetale dataPopulationVegetale;

    protected LocalisationVegetale localisationVegetal;

    public PopulationVegetale(final DataPopulationVegetale dataPopulationVegetale, final LocalisationVegetale localisationVegetale) {
        super(dataPopulationVegetale, localisationVegetale);
        this.dataPopulationVegetale = dataPopulationVegetale;
        this.localisationVegetale = localisationVegetale;
    }

// en %
    public double tauxCroissance() {
        return this.dataPopulationVegetale.tauxCroissanceVegetale;
    }

// en %
    public double tauxPerteParPenurie() {
        return this.localisation.penurieEau() * this.dataPopulationVegetale.tauxPerteVegetaleParPenurieMax;
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
