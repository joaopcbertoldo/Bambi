package fichiers;

import java.util.ArrayList;
import java.util.List;

import domain.MoisEnum;
import domain.ParametrageSimulation;


public class Parametres implements ParametrageSimulation {
	
	protected ArrayList<ArrayList<Double>> pluviometrie; 
	protected int nombreDePas=0;
	protected ArrayList<Double> surfaceTerritoire;
	protected int nombreIndividusAnimale0;
	protected ArrayList<Double> stockVegetaux; 
	protected int localisationInitiale; 
	protected ArrayList<Double> stockEau; 
	protected double tauxPerteEauEvaporation; 
	protected double stockVegetauxMinimal;
	protected double tauxNaissanceAnimalMaximal;
	protected double tauxMortalitePredateur; 
	protected double tauxMortaliteParPenurieAlimentaireMaximal;
	protected MoisEnum mois0; 
	protected double besoinEauVegetal;
	protected double besoinEauAnimal; 
	protected double besoinVegetalAnimal;
	protected double tauxCroissanteVegetalMax;
	protected double tauxPerteVegetalPenurieMax; 
	
	
	
 

    public int nombreDePas() {
        // TODO Auto-generated return
        return nombreDePas;
    }

    public double surfaceTerritoire(final int territoire) {
        // TODO Auto-generated return
        return surfaceTerritoire.get(territoire);
    }

    public int nombreIndividusAnimale0() {
        // TODO Auto-generated return
        return nombreIndividusAnimale0;
    }

    public double stockVegetaux(final int territoire) {
        // TODO Auto-generated return
        return stockVegetaux.get(territoire);
    }

    public int localisationInitiale() {
        // TODO Auto-generated return
        return localisationInitiale;
    }

    public double stockEau(final int territoire) {
        // TODO Auto-generated return
        return stockEau(territoire);
    }

    public double tauxPerteEauEvaporation() {
        // TODO Auto-generated return
        return tauxPerteEauEvaporation;
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
        return tauxMortaliteParPenurieAlimentaireMaximal;
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

	@Override
	public double pluviometrie(int territoire, int moisN) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double tauxPerteEauEvaporation(int territoire) {
		// TODO Auto-generated method stub
		return 0;
	}

}
