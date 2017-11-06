package simulation.ecosystemeBambiBase.entites;

import domain.MoisEnum;

public class ControleurMois extends Mois  {
    public ControleurMois(final MoisEnum mois0) {
    }

    public void incrementer() {
        super.iteration++;
        super.mois = super.mois == MoisEnum.Decembre ? MoisEnum.Janvier : super.mois.next();
    }

}
