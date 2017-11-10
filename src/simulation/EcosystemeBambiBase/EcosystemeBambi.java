package simulation.ecosystemeBambiBase;

import java.util.ArrayList;
import java.util.List;
import domain.ParametrageSimulation;
import simulation.ecosystemeBambiBase.entites.ControleurMois;
import simulation.ecosystemeBambiBase.entites.PopulationAnimale;
import simulation.ecosystemeBambiBase.entites.PopulationVegetale;
import simulation.ecosystemeBambiBase.entites.Territoire;
import simulation.ecosystemeBambiBase.entitesData.DataPopulationVegetale;
import simulation.ecosystemeBambiBase.entitesData.DataTerritoire;
import simulation.gestionDeSimulation.Ecosysteme;

public final class EcosystemeBambi extends Ecosysteme {
    private TableauxVariablesSimulation tableauxVariablesSimulation;

    private ControleurMois controleurMois;

    private List<Territoire> territoires = new ArrayList<Territoire>();

    private PopulationAnimale bambis;

    public EcosystemeBambi(ParametrageSimulation parametrageSimulation) {
    	super(parametrageSimulation);
    	this.tableauxVariablesSimulation = new TableauxVariablesSimulation();
    	super.resultatSimulation = tableauxVariablesSimulation;
    	
    	this.controleurMois = new ControleurMois(parametrageSimulation.mois0());
    	
    	this.territoires = new ArrayList<Territoire>();
    	for (int i = 0; i < 5; i++) {
    		
    		// Data Vegetation
    		DataPopulationVegetale dpv = new DataPopulationVegetale();
    		dpv.besoinEauParIndividu = parametrageSimulation.besoinEauVegetal();
    		dpv.besoinVegetalParIndividu = 0;  // vegetal ne mange pas vegetal
    		dpv.populationVegetaleMinimale = parametrageSimulation.stockVegetauxMinimal();
    		dpv.quantiteIndividus = parametrageSimulation.stockVegetaux(i);
    		dpv.tauxCroissanceVegetale = parametrageSimulation.tauxCroissanteVegetal();
    		dpv.tauxPerteVegetaleParPenurieEauMax = parametrageSimulation.tauxPerteVegetalPenurieMax();
    		
    		// Vegetation
    		PopulationVegetale v = new PopulationVegetale(dataPopulationVegetale, localisationVegetale, mois);
    		
    		// DataTerritoire
    		DataTerritoire dt = new DataTerritoire();
    		dt.index = parametrageSimulation.i;
    		dt.cumulEau = parametrageSimulation.stockEau(i);
    		dt.pluviometrie = parametrageSimulation.pluviometrie(i, 0);
    		dt.surface = parametrageSimulation.surfaceTerritoire(i);
    		dt.tauxPerteEauEvaporation = parametrageSimulation.tauxPerteEauEvaporation(i);
    		
    		Territoire t = i == 0 ? 
    				Territoire.CreerTerritoire(dt, vegetation) :
    				Territoire.CreerTerritoireAuNord(this.territoires.get(i-1), dataTerritoire, populationVegetale);
			this.territoires.add(t);
    	}
    	
    	this.bambis = new PopulationAnimale(dataPopulationAnimale, localisationAnimale, mois);
    }

    public void PrendreUnePhotoDeLaSimulation() {
    }

    protected final void ChangerSysteme() {
    }

}
