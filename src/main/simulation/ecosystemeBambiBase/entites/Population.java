package main.simulation.ecosystemeBambiBase.entites;

import main.simulation.ecosystemeBambiBase.entitesData.DataPopulation;

public abstract class Population {
    protected DataPopulation dataPopulation;

    protected Localisation localisation;

    protected Mois mois;

    public Population(DataPopulation dataPopulation, Mois mois) {
        this.dataPopulation = dataPopulation;
        this.mois           = mois;
    }

    public void setLocalisation(Localisation localisation) throws Exception {
    	if (this.localisation != null)
    		throw new Exception();
    	else
    		this.localisation = localisation;
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
        this.dataPopulation.quantiteIndividus = this.dataPopulation.quantiteIndividusMoisProchain ;
    }

}
