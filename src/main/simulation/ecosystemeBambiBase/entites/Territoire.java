package main.simulation.ecosystemeBambiBase.entites;

import java.util.ArrayList;
import java.util.List;

import main.simulation.ecosystemeBambiBase.entitesData.DataTerritoire;

/**
 * 
 * @author Jo�o Paulo
 */
public class Territoire {
    /**
     * 
     */
	protected DataTerritoire dataTerritoire;

	
	/**
	 * 
	 */
    protected List<Population> populations = new ArrayList<Population>();

    
    /**
     * 
     */
    protected PopulationVegetale vegetation;

    
    /**
     * 
     */
    public Territoire territoireAuNord;

    
    /**
     * 
     */
    public Territoire territoireAuSud;

    
    /**
     * 
     * @param dataTerritoire
     */
    public Territoire(DataTerritoire dataTerritoire) {
        this.dataTerritoire = dataTerritoire;
    }

    
    /**
     * 
     * @param dataTerritoire
     * @param vegetation
     * @return
     */
    public static Territoire CreerTerritoire(DataTerritoire dataTerritoire, PopulationVegetale vegetation) {
        Territoire t = new Territoire(dataTerritoire);
        t.vegetation = vegetation;
        LocalisationVegetale local = new LocalisationVegetale(t);
        // bricolage
        try {
			t.vegetation.setLocalisation(local);
		} catch (Exception e) {
			e.printStackTrace();
		}
        t.populations.add(vegetation);
        return t;
    }

    
    /**
     * 
     * @param origine
     * @param dataTerritoire
     * @param populationVegetale
     * @return
     */
    public static Territoire CreerTerritoireAuNord(Territoire origine, DataTerritoire dataTerritoire, PopulationVegetale populationVegetale) {
        Territoire t = Territoire.CreerTerritoire(dataTerritoire, populationVegetale);
        origine.territoireAuNord = t;
        t.territoireAuSud = origine;
        return t;
    }

    
    /**
     * 
     * @param origine
     * @param dataTerritoire
     * @param populationVegetale
     * @return
     */
    public static Territoire CreerTerritoireAuSud(Territoire origine, DataTerritoire dataTerritoire, PopulationVegetale populationVegetale) {
        Territoire t = Territoire.CreerTerritoire(dataTerritoire, populationVegetale);
        origine.territoireAuSud = t;
        t.territoireAuNord = origine;
        return t;
    }

    
    /**
     * 
     * @return
     */
    public int index() {
    	return this.dataTerritoire.index;
    }

    
    /**
     * 
     * @return
     */
    // mm / m^2
    public double pluviometrie() {
        return dataTerritoire.pluviometrie;
    }

    
    /**
     * 
     * @return
     */
    // km^2
    public double surface() {
        return dataTerritoire.surface;
    }

    
    /**
     * 
     * @return
     */
    // Litre
    public double quantiteEauDePluie() {
        return (this.pluviometrie() / 1000.0) * (this.surface() * 1000000.0) * 1000;
    }

    
    /**
     * 
     * @return
     */
    // Litre
    public double cumulEau() {
        return this.dataTerritoire.cumulEau;
    }

    
    /**
     * 
     * @return
     */
    // Litre
    public double disponibiliteEau() {
        return cumulEau() + quantiteEauDePluie();
    }

    
    /**
     * 
     * @return
     */
    // Litre
    public double besoinEau() {
        return this.populations
        		   .stream()
        		   .map(Population::besoinEauPopulation)
        		   .reduce(0.0,  (a,b) -> a + b);
    }

    
    /**
     * 
     * @return
     */
    // Litre
    public double balanceEau() {
        return disponibiliteEau() - besoinEau();
    }

    
    /**
     * 
     * @return
     */
    public double penurieEau() {
        //en %
        double res;
        if (balanceEau() <= 0) {
        	res = 0;
        }
        else {
        	res =  1 - disponibiliteEau() / besoinEau();
        }
        return res;
    }

    
    /**
     * 
     * @return
     */
    // kg
    public double disponibiliteVegetal() {
        return this.vegetation.quantiteIndividus();
    }

    
    /**
     * 
     * @return
     */
    // kg
    public double besoinVegetal() {
        return this.populations
        		   .stream()
        		   .filter(p -> p instanceof PopulationAnimale)
        		   .map(Population::besoinVegetalPopulation)
        		   .reduce(0.0,  (a,b) -> a + b);
    }

    
    /**
     * 
     * @return
     */
    // kg
    public double balanceVegetal() {
        return disponibiliteVegetal() - besoinVegetal();
    }

    
    /**
     * 
     * @return
     */
    // en %
    public double penurieVegetal() {
        double res;
        if (balanceVegetal() <= 0) {
        	res = 0;
        }
        else {
        	res =  1 - disponibiliteVegetal() / besoinVegetal();
        }
        return res;
    }

    
    /**
     * 
     * @return
     */
    // kg
    public double quantiteVegetalNonMange() {
        double res;
        if (balanceVegetal() <= 0) {
        	res = 0;
        }
        else {
        	res =  balanceVegetal();
        }
        return res;
    }

    
    /**
     * 
     */
    public void recalculerPopulations() {
        for (Population p : this.populations)
        	p.calculerNouvelleQuantiteIndividus();
    }

    
    /**
     * 
     */
    public void affecterPopulations() {
        for (Population p : this.populations)
        	p.affecterQuantiteIndividus();
    }

    
    /**
     * 
     */
    public void recalculerCumulEau() {
        double balance = this.balanceEau();
        double tauxPerte = this.dataTerritoire.tauxPerteEauEvaporation;
        
        this.dataTerritoire.cumulEauMoisProchain = balance  <= 0 ? 0 : balance * tauxPerte;
    }

    
    /**
     * 
     */
    public void affecterCumulEau() {
        this.dataTerritoire.cumulEau = this.dataTerritoire.cumulEauMoisProchain;
    }

    
    /**
     * 
     * @param pluviometrie
     */
    public void mettreAJourPluviometrie(double pluviometrie) {
        this.dataTerritoire.pluviometrie = pluviometrie;
    }

    
    /**
     * 
     * @param population
     */
    // Ajoute une population dans la liste de populations du territoire.
    public void recevoirPopulation(Population population) {
        this.populations.add(population);
    }

    
    /**
     * 
     * @param population
     */
    // Enlève la population donnée de sa liste et la dépose dans le territoire voisin au Nord.
    public void deplacerPopulationAuNord(Population population) {
        this.populations.remove(population);
        this.territoireAuNord.recevoirPopulation(population);
    }

    
    /**
     * 
     * @param population
     */
    // Enlève la population donnée de sa liste et la dépose dans le territoire voisin au Sud.
    public void deplacerPopulationAuSud(Population population) {
        this.populations.remove(population);
        this.territoireAuSud.recevoirPopulation(population);
    }

}
