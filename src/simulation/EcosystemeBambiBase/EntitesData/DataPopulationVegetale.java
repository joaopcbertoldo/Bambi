package simulation.ecosystemeBambiBase.entitesData;

import simulation.ecosystemeBambiBase.enums.*;

// Quantite individu : UP = kg
// Besoin eau par individu : Litre / kg / mois
// Besoin vegetal par individu : kg / kg / mois = 0
public class DataPopulationVegetale extends DataPopulation {
// en %
    public double tauxCroissanceVegetale;

// en %
    public double tauxPerteVegetaleParPenurieEauMax;

// en tonne
    public double populationVegetaleMinimale;

}
