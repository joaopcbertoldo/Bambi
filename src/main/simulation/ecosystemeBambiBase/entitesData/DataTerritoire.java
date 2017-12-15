package main.simulation.ecosystemeBambiBase.entitesData;

/**
 * Ensemble d'informations non calculable en fonction des autres pour un territoire.
 * @author João Paulo
 */
public class DataTerritoire {

	/**
	 * Valeur variant de 1 (sud) à 5 (nord).
	 */
    public int index;


    /**
     * Taux de perte d'eau par évaporation. En %.
     */
    public double tauxPerteEauEvaporation;


    /**
     * Surface du territoire. En km^2.
     */
    public double surface;


    /**
     * Cumul d'eau dans le territoire pour le mois actuel. En Litre.
     */
    public double cumulEau;


    /**
     * Cumul d'eau du mois prochain. En litre.
     * Cette valeur sert comme un buffert pour garder le résultat de calcul
     * d'avancement sans changer les calculs des autres entités.
     */
    public double cumulEauMoisProchain;


    /**
     * Pluviométrie du mois actuel. En mm / m^2 / mois.
     * La pluviométrie est supposée uniforme dans tout le territoire.
     */
    public double pluviometrie;
}
