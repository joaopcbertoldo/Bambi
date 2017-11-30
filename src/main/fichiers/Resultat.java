package main.fichiers;

import java.util.ArrayList;

import main.domain.ResultatSimulation;

public class Resultat implements ResultatSimulation {
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
    public int popAnimale( int n, int territoire ) {
        // TODO Auto-generated return
        return (popAnimale.get(territoire)).get(n);
    }

    public double stockEau( int territoire, int n) {
        // TODO Auto-generated return
     return (stockEau.get(territoire)).get(n);
    }

    public double stockVeg(final int territoire, final int n) {
        // TODO Auto-generated return
        return (stockVeg.get(territoire)).get(n);
    }
    
    public int NbdePas (){
    	return this.NbdePas; 
    	
    }
    
   

}
