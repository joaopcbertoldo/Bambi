package simulation.EcosystemeBambiBase.Entites;

import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.Enums.*;

public class LocalisationAnimale extends Localisation {
// en %
    public double penurieVegetale() {
        return super.territoire.penurieVegetal();
    }

// en %
    public double penurieAlimentaire() {
        return (this.penurieVegetal() + super.penurieEau()) / 2;
    }

    public void migrerAuNord(final Population population) {
        super.territoire.deplacerPopulationAuNord(population);
    }

    public void migrerAuSud(final Population population) {
        super.territoire.deplacerPopulationAuSud(population);
    }

}
