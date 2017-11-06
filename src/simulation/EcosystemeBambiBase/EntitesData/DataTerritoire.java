package simulation.EcosystemeBambiBase.EntitesData;

import simulation.EcosystemeBambiBase.Enums.*;

public class DataTerritoire {
// i = 1, 2, 3, 4, 5
    public int index;

// en %
    public double tauxPerteEauEvaporation;

// km^2
    public double surface;

// Litre
    public double cumulEau;

// Litre
    protected double cumulEauMoisProchain;

// mm / m^2 / mois
    public double pluviometrie;

}
