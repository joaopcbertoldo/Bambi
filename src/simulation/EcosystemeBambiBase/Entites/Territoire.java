package simulation.ecosystemeBambiBase.entites;

import java.util.ArrayList;
import java.util.List;
import simulation.ecosystemeBambiBase.entitesData.DataTerritoire;

public class Territoire {
    protected DataTerritoire dataTerritoire;

    protected List<Population> populations = new ArrayList<Population>();

    protected PopulationVegetale vegetation;

    public Territoire territoireAuNord;

    public Territoire territoireAuSud;

    private Territoire(final DataTerritoire dataTerritoire) {
        this.dataTerritoire = dataTerritoire;
    }

    public static Territoire CreerTerritoire(final DataTerritoire dataTerritoire, final PopulationVegetale vegetation) {
        Territoire t = new Territoire(dataTerritoire);
        t.vegetation = vegetation;
        t.populations.add(vegetation);
        return t;
    }

    public static Territoire CreerTerritoireAuNord(final Territoire origine, final DataTerritoire dataTerritoire, final PopulationVegetale populationVegetale) {
        Territoire t = Territoire.CreerTerritoire(dataTerritoire, populationVegetale);
        origine.territoireAuNord = t;
        t.territoireAuSud = origine;
        return t;
    }

    public static Territoire CreerTerritoireAuSud(final Territoire origine, final DataTerritoire dataTerritoire, final PopulationVegetale populationVegetale) {
        Territoire t = Territoire.CreerTerritoire(dataTerritoire, populationVegetale);
        origine.territoireAuSud = t;
        t.territoireAuNord = origine;
        return t;
    }
    
    public int index() {
    	return this.dataTerritoire.index;
    }

    // mm / m^2
    public double pluviometrie() {
        return dataTerritoire.pluviometrie;
    }

    // km^2
    public double surface() {
        return dataTerritoire.surface;
    }

    // Litre
    public double quantiteEauDePluie() {
        return (this.pluviometrie() / 1000.0) * (this.surface() * 1000000.0) * 1000;
    }

    // Litre
    public double cumulEau() {
        return this.dataTerritoire.cumulEau;
    }

    // Litre
    public double disponibiliteEau() {
        return cumulEau() + quantiteEauDePluie();
    }

    // Litre
    public double besoinEau() {
        return this.populations
        		   .stream()
        		   .map(Population::besoinEauPopulation)
        		   .reduce(0.0,  (a,b) -> a + b);
    }

    // Litre
    public double balanceEau() {
        return disponibiliteEau() - besoinEau();
    }

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

    // kg
    public double disponibiliteVegetal() {
        return this.vegetation.quantiteIndividus();
    }

    // kg
    public double besoinVegetal() {
        return this.populations
        		   .stream()
        		   .filter(p -> p instanceof PopulationAnimale)
        		   .map(Population::besoinVegetalPopulation)
        		   .reduce(0.0,  (a,b) -> a + b);
    }

    // kg
    public double balanceVegetal() {
        return disponibiliteVegetal() - besoinVegetal();
    }

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

    public void recalculerPopulations() {
        for (Population p : this.populations)
        	p.calculerNouvelleQuantiteIndividus();
    }

    public void affecterPopulations() {
        for (Population p : this.populations)
        	p.affecterQuantiteIndividus();
    }

    public void recalculerCumulEau() {
        double balance = this.balanceEau();
        double tauxPerte = this.dataTerritoire.tauxPerteEauEvaporation;
        
        this.dataTerritoire.cumulEauMoisProchain = balance  <= 0 ? 0 : balance * tauxPerte;
    }

    public void affecterCumulEau() {
        this.dataTerritoire.cumulEau = this.dataTerritoire.cumulEauMoisProchain;
    }

    public void mettreAJourPluviometrie(final double pluviometrie) {
        this.dataTerritoire.pluviometrie = pluviometrie;
    }

    // Ajoute une population dans la liste de populations du territoire.
    public void recevoirPopulation(final Population population) {
        this.populations.add(population);
    }

    // Enlève la population donnée de sa liste et la dépose dans le territoire voisin au Nord.
    public void deplacerPopulationAuNord(final Population population) {
        this.populations.remove(population);
        this.territoireAuNord.recevoirPopulation(population);
    }

    // Enlève la population donnée de sa liste et la dépose dans le territoire voisin au Sud.
    public void deplacerPopulationAuSud(final Population population) {
        this.populations.remove(population);
        this.territoireAuSud.recevoirPopulation(population);
    }

}
