package main.simulation.ecosystemeBambiBase.entites;

import main.domain.MoisEnum;

public class ControleurMois extends Mois  {
    public ControleurMois(MoisEnum mois0) {
    	super.mois = mois0;
    	super.iteration = 0;
    }

    public void incrementer() {
        super.iteration++;
        super.mois = super.mois == MoisEnum.Decembre ? MoisEnum.Janvier : super.mois.next();
    }

}
