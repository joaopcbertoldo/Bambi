package simulation.ecosystemeBambiBase.entites;

import domain.MoisEnum;

public abstract class Mois {
    protected int iteration = 0;

    protected MoisEnum mois;

    public int getIteration() {
        return this.iteration;
    }

    public MoisEnum getMois() {
        return this.mois;
    }

}
