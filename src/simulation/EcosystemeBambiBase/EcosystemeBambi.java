package simulation.ecosystemeBambiBase;

import java.util.ArrayList;
import java.util.List;
import domain.ParametrageSimulation;
import simulation.ecosystemeBambiBase.entites.ControleurMois;
import simulation.ecosystemeBambiBase.entites.PopulationAnimale;
import simulation.ecosystemeBambiBase.entites.Territoire;
import simulation.gestionDeSimulation.Ecosysteme;

public final class EcosystemeBambi extends Ecosysteme {
    private TableauxVariablesSimulation tableauxVariablesSimulation;

    private ControleurMois controleurMois;

    private List<Territoire> territoires = new ArrayList<Territoire> ();

    private PopulationAnimale bambis;

    protected EcosystemeBambi(final ParametrageSimulation parametrageSimulation) {
    	super(parametrageSimulation);
    }

    public void PrendreUnePhotoDeLaSimulation() {
    }

    protected final void ChangerSysteme() {
    }

}
