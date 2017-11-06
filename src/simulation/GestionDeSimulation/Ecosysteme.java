package simulation.gestionDeSimulation;

import domain.ParametrageSimulation;
import domain.ResultatSimulation;

public abstract class Ecosysteme {
    private int nombreDePasExecutes = 0;

    protected ParametrageSimulation parametrageSimulation;

    protected ResultatSimulation resultatSimulation;

    protected Ecosysteme(final ParametrageSimulation parametrageSimulation) {
    }

    public static Ecosysteme getInstanceEcosysteme(final ParametrageSimulation parametres) {
        // TODO Auto-generated return
        return null;
    }

    public abstract void PrendreUnePhotoDeLaSimulation();

    public void AvancerUnPas() throws SimulationFinieException {
    }

    protected abstract void ChangerSysteme();

    public ResultatSimulation getResultatSimulation() {
        return this.resultatSimulation;
    }

}
