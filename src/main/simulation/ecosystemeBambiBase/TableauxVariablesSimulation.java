package main.simulation.ecosystemeBambiBase;

import java.util.ArrayList;
import java.util.List;

import main.domain.MoisEnum;
import main.domain.ResultatSimulation;

public class TableauxVariablesSimulation implements ResultatSimulation {
	
    public List<List<Integer>> tableauPopulationAnimale;

    public List<List<Double>> tableauStockEau;

    public List<List<Double>> tableauStockVeg;

	private MoisEnum mois0;
    
    public TableauxVariablesSimulation(int nbTerritoires, MoisEnum mois0) {
    	this.tableauPopulationAnimale = new ArrayList<List<Integer>>();
    	this.tableauStockEau = new ArrayList<List<Double>>();
    	this.tableauStockVeg = new ArrayList<List<Double>>();
    	
    	for (int i=0; i < nbTerritoires; i++) {
    		this.tableauPopulationAnimale.add(new ArrayList<Integer>());
        	this.tableauStockEau.add(new ArrayList<Double>());
        	this.tableauStockVeg.add(new ArrayList<Double>());
    	}
    	
    	this.mois0 = mois0;
    }

    // gets
    
    public int popAnimale(int n, int territoire) {
        return this.tableauPopulationAnimale.get(territoire).get(n);
    }

    public double stockEau(int territoire, int n) {
        return  this.tableauStockEau.get(territoire).get(n);
    }

    public double stockVeg(int territoire, int n) {
        return this.tableauStockVeg.get(territoire).get(n);
    }
    
    
    // adds
    
    public void ajouterPopAnimale(int territoire, int valeur) {
        this.tableauPopulationAnimale.get(territoire).add(valeur);
    }

    public void ajouterStockEau(int territoire, double valeur) {
        this.tableauStockEau.get(territoire).add(valeur);
    }

    public void ajouterStockVeg(int territoire, double valeur) {
        this.tableauStockVeg.get(territoire).add(valeur);
    }

	@Override
	public int NbdePas() {
		// un peu hotfix
		return this.tableauPopulationAnimale.size();
	}

	@Override
	public MoisEnum mois0() {
		return this.mois0;
	}

}
