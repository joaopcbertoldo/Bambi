package simulation.ecosystemeBambiBase.entites;

import domain.MoisEnum;

public class ControleurMois extends Mois  {
    public ControleurMois(final MoisEnum mois0) {
    	super.mois = mois0;
    	super.iteration = 0;
    }

    public void incrementer() {
        super.iteration++;
        super.mois = super.mois == MoisEnum.Decembre ? MoisEnum.Janvier : super.mois.next();
    }
    
	public static void main(String[] args) {
		ControleurMois ctrl = new ControleurMois(MoisEnum.Janvier);
		Mois mois = (Mois) ctrl;
		
		for (int i = 0; i < 24; i++) {
			System.out.println(ctrl.getIteration());
			System.out.println(ctrl.getMois());
			System.out.println();
			ctrl.incrementer();
		}
	}

}
