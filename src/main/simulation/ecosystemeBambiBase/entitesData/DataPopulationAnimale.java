package main.simulation.ecosystemeBambiBase.entitesData;

import java.util.ArrayList;
import java.util.List;

import main.simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

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
    public List<Double> historiquePenurieEau = new ArrayList<>();

    // liste en %
    public List<Double> historiquePenurieNourriture = new ArrayList<>();

    public StatusMigrationEnum statusMigration;

}
