package simulation.EcosystemeBambiBase;

import java.util.ArrayList;
import java.util.List;
import domain.ParametrageSimulation;
import simulation.EcosystemeBambiBase.Entites.ControleurMois;
import simulation.EcosystemeBambiBase.Entites.PopulationAnimale;
import simulation.EcosystemeBambiBase.Entites.Territoire;
import simulation.GestionDeSimulation.*;
import simulation.GestionDeSimulation.Ecosysteme;

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
