package simulation.EcosystemeBambiBase.Entites;

import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.EntitesData.DataPopulation;
import simulation.EcosystemeBambiBase.Enums.*;

public abstract class Population {
    protected DataPopulation dataPopulation;

    protected Localisation localisation;

    protected Mois mois;

    public Population(final DataPopulation dataPopulation, final Localisation localisation, final Mois mois) {
        this.dataPopulation = dataPopulation;
        this.localisation = localisation;
        this.mois = mois;
    }

    public double quantiteIndividus() {
        return this.dataPopulation.quantiteIndividus;
    }

    public double besoinEauPopulation() {
        return this.dataPopulation.besoinEauParIndividu * this.dataPopulation.quantiteIndividus;
    }

    public double besoinVegetalPopulation() {
        return this.dataPopulation.besoinVegetalParIndividu * this.dataPopulation.quantiteIndividus;
    }

    public Integer indexTerritoireOccuppe() {
        return this.localisation.indexTerritoire();
    }

    public abstract void calculerNouvelleQuantiteIndividus();

    public void affecterQuantiteIndividus() {
        this.dataPopulation.quantiteIndividu = this.dataPopulation.quantiteIndividusMoisProchain ;
    }

}
