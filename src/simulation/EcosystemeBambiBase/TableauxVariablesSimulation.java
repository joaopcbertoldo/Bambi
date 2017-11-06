package simulation.ecosystemeBambiBase;

import java.util.List;
import domain.ResultatSimulation;

public class TableauxVariablesSimulation implements ResultatSimulation {
    public List<List<Integer>> tableauPopulationAnimale;

    public List<List<Double>> tableauStockEau;

    public List<List<Double>> tableauStockVeg;

    public int popAnimale(final int n, final int territoire) {
        return this.tableauPopulationAnimale.get(territoire).get(n);
    }

    public double stockEau(final int territoire, final int n) {
        return  this.tableauStockEau.get(territoire).get(n);
    }

    public double stockVeg(final int territoire, final int n) {
        return this.tableauStockVeg.get(territoire).get(n);
    }

}
