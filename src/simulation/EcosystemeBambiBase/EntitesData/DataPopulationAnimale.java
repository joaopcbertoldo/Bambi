package simulation.ecosystemeBambiBase.entitesData;

import simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

// Quantite individu : UP = nombre d'individu
// Besoin eau par individu : Litre / individu / mois
// Besoin vegetal par individu : kg / individu / mois
public class DataPopulationAnimale extends DataPopulation {
// en %
    public double tauxMortalitePredateur;

// en %
    public double tauxMortaliteParPenurieAlimentaireMax;

// en %
    public double tauxNaissanceMax;

// liste en %
    public double[] historiquePenurieEau;

// liste en %
    public double[] historiquePenurieNourriture;

    public StatusMigrationEnum statusMigration;

}
