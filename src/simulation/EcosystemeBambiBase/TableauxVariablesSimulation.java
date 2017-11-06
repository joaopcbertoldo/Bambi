package simulation.EcosystemeBambiBase;

import java.util.List;
import domain.ResultatSimulation;
import simulation.GestionDeSimulation.*;

public class TableauxVariablesSimulation implements ResultatSimulation {
    public List tableauPopulationAnimale;

    public List tableauStockEau;

    public List tableauStockVeg;

    public int popAnimale(final int n, final int territoire) {
        return (int) this.tableauPopulationAnimale.get(territoire).get(n);
    }

    public double stockEau(final int territoire, final int n) {
        return this.tableauStockEau.get(territoire).get(n);
    }

    public double stockVeg(final int territoire, final int n) {
        return this.tableauStockVeg.get(territoire).get(n);
    }

}
