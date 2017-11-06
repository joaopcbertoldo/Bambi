package simulation.EcosystemeBambiBase.Entites;

import domain.MoisEnum;
import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.Enums.*;

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
