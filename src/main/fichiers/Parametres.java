package main.fichiers;

import java.util.ArrayList;
import java.util.List;

import main.domain.MoisEnum;
import main.domain.ParametrageSimulation;


public class Parametres implements ParametrageSimulation { 
	
	//Les paramètres de simulation sont stockés comme attribut d'un objet de type Paramètres. Des méthodes permettent d'y accéder
	
	protected ArrayList<ArrayList<Double>> pluviometrie; 
	protected int nombreDePas=0;
	protected ArrayList<Double> surfaceTerritoire;
	protected int nombreIndividusAnimale0;
	protected ArrayList<Double> stockVegetaux; 
	protected int localisationInitiale; 
	protected ArrayList<Double> stockEau; 
	protected ArrayList<Double> tauxPerteEauEvaporation; 
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
	
	/*public Parametres(int a, ArrayList<Double> sur, int b, ArrayList<Double> stockV, int c, ArrayList<Double> stockE, ArrayList<Double> tauxP,double d, double e, double f, double h, MoisEnum i, double j, double k, double l,double m){
		 	nombreDePas= a;
			for (int i=0; i<5 ; i++){
			surfaceTerritoire.add(sur.get(i));
			stockVegetaux.add(stockV.get(i));
			stockEau.add(stockE.get(i));
			tauxPerteEauEvaporation.add(tauxP.get(i));

			}
			nombreIndividusAnimale0=b;
			tauxNaissanceAnimalMaximal=m;
			tauxMortalitePredateur=d;
			tauxMortaliteParPenurieAlimentaireMaximal=e;
			mois0=f;
			tauxPerteVegetalPenurieMax=g;
			besoinEauAnimal=h;
			besoinEauVegetal=i;
			besoinVegetalAnimal=j;
			tauxCroissanteVegetal=k;
			tauxPerteVegetalPenurieMax=l; 
			localisationInitiale = c; 
			
			
			
		}

	}*/

	
	


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

    public double tauxPerteEauEvaporation(int territoire) {
        return tauxPerteEauEvaporation.get(territoire);
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
		// TODO Auto-generated method stub
		return (pluviometrie.get(territoire)).get(moisN);
	}


	
	public static void afficheSaufPluviometrie (Parametres param ){
		System.out.println("NombreDePas:"+" "+ param.nombreDePas);
		for (int i=0; i<5 ; i++){
		System.out.println();
		System.out.print("SurfaceTerritoire"+i+":"+" "+ param.surfaceTerritoire(i)+"   ");
		System.out.print("StockVegetaux"+i+":"+param.stockVegetaux(i)+"   ");
		System.out.print("StockEau"+i+":"+param.stockEau(i)+"   ");
		System.out.print("TauxPerteEvaporation"+i+":"+param.tauxPerteEauEvaporation(i)+"   ");

		}
		System.out.println("NombreAnimauxInitial"+" "+ param.nombreIndividusAnimale0);
		System.out.println("tauxNaissanceAnimalMaximal"+" "+ param.tauxNaissanceAnimalMaximal);
		System.out.println("tauxMortalitePredateur"+" "+ param.tauxMortalitePredateur);
		System.out.println("tauxMortaliteParPenurieAlimentaireMaximal"+" "+ param.tauxMortaliteParPenurieAlimentaireMaximal);
		System.out.println("MoisDebutSimulation"+" "+ param.mois0);
		System.out.println("tauxPerteVegetalPenurieMax"+" "+ param.tauxPerteVegetalPenurieMax);
		System.out.println("besoinEauAnimal"+" "+ param.besoinEauAnimal);
		System.out.println("besoinEauVegetal"+" "+ param.besoinEauVegetal);
		System.out.println("besoinVegetalAnimal"+" "+ param.besoinVegetalAnimal);
		System.out.println("tauxCroissanteVegetal"+" "+ param.tauxCroissanteVegetal);
		
		
		
	}
<<<<<<< Updated upstream:src/main/fichiers/Parametres.java
=======


	public static void affichePluviometrie (Parametres param ){
		for (int i=0; i<5 ; i++){
		System.out.print("Pluviometrie territoire"+i+"  ");
		System.out.println(param.pluviometrie.get(i));
		}
	}

>>>>>>> Stashed changes:src/fichiers/Parametres.java
}
//Les méthodes affiche nous permettent de voir si on a bien créé l'objet qu'on voulait 