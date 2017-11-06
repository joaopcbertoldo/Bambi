package fichiers;

import domain.MoisEnum;
import domain.ParametrageSimulation;


public class Parametres implements ParametrageSimulation {
	
	protected double[][] pluviometrie; 
	protected int nombreDePas;
	protected double[] surfaceTerritoire;
	protected int nombreIndividusAnimale0;
	protected double[] stockVegetaux; 
	protected int localisationInitiale; 
	protected double[] stockEau; 
	protected double[] tauxPerteEauEvaporation; 
	protected double stockVegetauxMinimal;
	protected double tauxNaissanceAnimalMaximal;
	protected double tauxMortalitePredateur; 
	protected double auxMortaliteParPenurieAlimentaireMaximal;
	protected MoisEnum mois0; 
	protected double besoinEauVegetal;
	protected double besoinEauAnimal; 
	protected double besoinVegetalAnimal;
	protected double tauxCroissanteVegetalMax;
	protected double tauxPerteVegetalPenurieMax; 
	
	
	
    public double pluviometrie(final int territoire, final int moisN) {
        // TODO Auto-generated return
        return pluviometrie[territoire][moisN];
    }

    public int nombreDePas() {
        // TODO Auto-generated return
        return nombreDePas;
    }

    public double surfaceTerritoire(final int territoire) {
        // TODO Auto-generated return
        return surfaceTerritoire[territoire];
    }

    public int nombreIndividusAnimale0() {
        // TODO Auto-generated return
        return nombreIndividusAnimale0;
    }

    public double stockVegetaux(final int territoire) {
        // TODO Auto-generated return
        return stockVegetaux[territoire];
    }

    public int localisationInitiale() {
        // TODO Auto-generated return
        return localisationInitiale;
    }

    public double stockEau(final int territoire) {
        // TODO Auto-generated return
        return stockEau(territoire);
    }

    public double tauxPerteEauEvaporation(final int territoire) {
        // TODO Auto-generated return
        return tauxPerteEauEvaporation[territoire];
    }

    public double stockVegetauxMinimal() {
        // TODO Auto-generated return
        return stockVegetauxMinimal;
    }

    public double tauxNaissanceAnimalMaximal() {
        // TODO Auto-generated return
        return tauxNaissanceAnimalMaximal;
    }

    public double tauxMortalitePredateur() {
        // TODO Auto-generated return
        return tauxMortalitePredateur;
    }

    public double tauxMortaliteParPenurieAlimentaireMaximal() {
        // TODO Auto-generated return
        return auxMortaliteParPenurieAlimentaireMaximal;
    }

    public MoisEnum mois0() {
        // TODO Auto-generated return
        return mois0;
    }

    public double besoinEauVegetal() {
        // TODO Auto-generated return
        return  besoinEauVegetal;
    }

    public double besoinEauAnimal() {
        // TODO Auto-generated return
        return besoinEauAnimal;
    }

    public double besoinVegetalAnimal() {
        // TODO Auto-generated return
        return besoinVegetalAnimal;
    }

    public double tauxCroissanteVegetalMax() {
        // TODO Auto-generated return
        return  tauxCroissanteVegetalMax;
    }

    public double tauxPerteVegetalPenurieMax() {
        // TODO Auto-generated return
        return tauxPerteVegetalPenurieMax;
    }

}
