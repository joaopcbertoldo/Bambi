package main.simulation.ecosystemeBambiBase.entites;

import main.domain.MoisEnum;

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
