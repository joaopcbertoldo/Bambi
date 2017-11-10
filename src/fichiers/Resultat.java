package fichiers;

import java.util.ArrayList;

import domain.ResultatSimulation;

public class Resultat implements ResultatSimulation {
	
	protected ArrayList<ArrayList<Integer>> popAnimale; 
	protected ArrayList<ArrayList<Double>> stockEau; 
	protected ArrayList<ArrayList<Double>> stockVeg; 
	
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

}
