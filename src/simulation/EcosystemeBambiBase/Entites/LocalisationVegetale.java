package simulation.EcosystemeBambiBase.Entites;

import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.Enums.*;

public class LocalisationVegetale extends Localisation {
// kg
    public double quantiteVegetalNonMange() {
        return this.territoire.quantiteVegetalNonMange();
    }

}
