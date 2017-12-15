package main.simulation.ecosystemeBambiBase.entitesData;

import java.util.*;

import main.simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

/**
 * Spécification de la data de population pour les animaux (sousclasse de DataPopulation).
 * 
 * Quantite individu : UP = nombre d'individu
 * Besoin eau par individu : Litre / individu / mois
 * Besoin vegetal par individu : kg / individu / mois
 * 
 * @author João Paulo
 */
public class DataPopulationAnimale extends DataPopulation {
	
	/**
	 * Taux de mortalité par prédateur.
	 */
    public double tauxMortalitePredateur;
    

    /**
     * Taux de mortalité par pénurie alimentaire maximale.
     */
    public double tauxMortaliteParPenurieAlimentaireMax;

    
    /**
     * Taux de naissance maximale d'une population animale pendant un mois.
     */
    public double tauxNaissanceMax;

    
    /**
     * Historique de pénurie en nourriture. Liste de %.
     */
    public List<Double> historiquePenurieEau = new ArrayList<>();

    
    /**
     * Historique de pénurie en nourriture. Liste de %.
     */
    public List<Double> historiquePenurieNourriture = new ArrayList<>();
    
    /**
     * Status de migration des animaux.
     */
    public StatusMigrationEnum statusMigration;

}
