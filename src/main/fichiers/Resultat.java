package main.fichiers;

import java.util.ArrayList;

import main.domain.ResultatSimulation;

public class Resultat implements ResultatSimulation { // les attributs de Resultat permettent de stocker les données résultant de la simulation et d'y avoir facilement accès 
	protected int NbdePas ; 
	protected ArrayList<ArrayList<Integer>> popAnimale; 
	protected ArrayList<ArrayList<Double>> stockEau; 
	protected ArrayList<ArrayList<Double>> stockVeg; 
	
	public Resultat(){
		popAnimale= new ArrayList<>(); 
		stockEau= new ArrayList<>(); 
		stockVeg= new ArrayList<>();
		for (int i=0; i<5; i++){
			popAnimale.add(new ArrayList<>()); 
			stockEau.add(new ArrayList<>());
			stockVeg.add(new ArrayList<>()); 
		}
	}
	
	public Resultat (int n ,ArrayList<ArrayList<Integer>> popA, ArrayList<ArrayList<Double>> stockEa, ArrayList<ArrayList<Double>> stockVe){
		popAnimale= popA;
		stockEau= stockEa; 
		stockVeg= stockVe;
		NbdePas= n; 
		
	}
	
	public ArrayList<Integer> popAnimale(int territoire){
		return popAnimale.get(territoire);
	}
    public int popAnimale( int n, int territoire ) {
        return (popAnimale.get(territoire)).get(n);
    }

    public ArrayList<Double> stockEau(int territoire){
    	return stockEau.get(territoire);
    }
    public double stockEau( int territoire, int n) {
     return (stockEau.get(territoire)).get(n);
    }
    
    public ArrayList<Double> stockVeg(int territoire){
    	return stockVeg.get(territoire);
    }

    public double stockVeg(final int territoire, final int n) {
        return (stockVeg.get(territoire)).get(n);
    }
    
    public int NbdePas (){
    	return this.NbdePas; 
    	
    }
    
   

}
