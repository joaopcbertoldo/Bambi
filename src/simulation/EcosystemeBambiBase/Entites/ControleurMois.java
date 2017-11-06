package simulation.EcosystemeBambiBase.Entites;

import domain.MoisEnum;
import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.Enums.*;

public class ControleurMois extends Mois implements Temps {
    public ControleurMois(final MoisEnum mois0) {
    }

    public void incrementer() {
        super.iteration++;
        super.mois = super.mois == MoisEnum.Decembre ? MoisEnum.Janvier : super.mois + 1;
    }

}
