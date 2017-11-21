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
	protected double tauxCroissanteVegetal;
	protected double tauxPerteVegetalPenurieMax; 
	
	
	
 

    public int nombreDePas() {
        return nombreDePas;
    }

    public double surfaceTerritoire(final int territoire) {
        return surfaceTerritoire.get(territoire);
    }

    public int nombreIndividusAnimale0() {
        return nombreIndividusAnimale0;
    }

    public double stockVegetaux(final int territoire) {
        return stockVegetaux.get(territoire);
    }

    public int localisationInitiale() {
        return localisationInitiale;
    }

    public double stockEau(final int territoire) {
        return stockEau.get(territoire);
    }

    public double tauxPerteEauEvaporation() {
        return tauxPerteEauEvaporation;
    }

    public double stockVegetauxMinimal() {
        return stockVegetauxMinimal;
    }

    public double tauxNaissanceAnimalMaximal() {
        return tauxNaissanceAnimalMaximal;
    }

    public double tauxMortalitePredateur() {
        return tauxMortalitePredateur;
    }

    public double tauxMortaliteParPenurieAlimentaireMaximal() {
        return tauxMortaliteParPenurieAlimentaireMaximal;
    }

    public MoisEnum mois0() {
        return mois0;
    }

    public double besoinEauVegetal() {
        return  besoinEauVegetal;
    }

    public double besoinEauAnimal() {
        return besoinEauAnimal;
    }

    public double besoinVegetalAnimal() {
        return besoinVegetalAnimal;
    }

    public double tauxCroissanteVegetal() {
        return  tauxCroissanteVegetal;
    }

    public double tauxPerteVegetalPenurieMax() {
        return tauxPerteVegetalPenurieMax;
    }

	@Override
	public double pluviometrie(int territoire, int moisN) {
		return 0;
	}

	@Override
	public double tauxPerteEauEvaporation(int territoire) {
		return 0;
	}

	@Override
	public double tauxCroissanteVegetal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
