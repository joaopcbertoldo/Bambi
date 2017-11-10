package simulation.ecosystemeBambiBase.entites;

import domain.MoisEnum;
import simulation.ecosystemeBambiBase.entitesData.DataPopulationAnimale;
import simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

public class PopulationAnimale extends Population {
	
    protected LocalisationAnimale localisationAnimale() {
    	return (LocalisationAnimale) super.localisation;
    }

    protected DataPopulationAnimale dataPopulationAnimale;

    public PopulationAnimale(DataPopulationAnimale dataPopulationAnimale, 
    						 LocalisationAnimale localisation,
    						 Mois mois) {
        super(dataPopulationAnimale, mois);
        this.dataPopulationAnimale = dataPopulationAnimale;
        
        // bricolage
        try {
			super.setLocalisation(localisation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // en %
    public double penurieEauCumulee() {
        return this.dataPopulationAnimale
        		   .historiquePenurieEau
        		   .stream()
        		   .limit(6)
        		   .reduce(0.0, (a,b) -> a + b);
    }

    // en %
    public double penurieVegetaleCumulee() {
        return this.dataPopulationAnimale
     		   .historiquePenurieNourriture
     		   .stream()
     		   .limit(6)
     		   .reduce(0.0, (a,b) -> a + b);
    }

    // en %
    public double tauxNaissance() {
        double tauxMax = this.dataPopulationAnimale.tauxNaissanceMax;
        double res = (0.1 / this.penurieEauCumulee()) * (0.1 / penurieVegetaleCumulee());
        res = res > 1 ? 1 : res;
        res = res *  tauxMax;
        return res;
    }

    // en %
    public double tauxMortalite() {
        double tauxPenMax = this.dataPopulationAnimale.tauxMortaliteParPenurieAlimentaireMax;
        double tauxPred   = this.dataPopulationAnimale.tauxMortalitePredateur;
        double penAlim    = this.localisationAnimale().penurieAlimentaire();
        
        double res = tauxPred + tauxPenMax * penAlim;
        return res;
    }

    public StatusMigrationEnum etatDeMigration() {
        return this.dataPopulationAnimale.statusMigration;
    }

    public void calculerNouvelleQuantiteIndividus() {
    	this.dataPopulationAnimale.historiquePenurieEau.add(0, this.localisationAnimale().penurieEau());
    	this.dataPopulationAnimale.historiquePenurieNourriture.add(0, this.localisationAnimale().penurieVegetale());
    	
        double actuel = this.dataPopulationAnimale.quantiteIndividus;
        
        double nouvelle = actuel * (1 + this.tauxNaissance() - this.tauxMortalite());
        
        this.dataPopulationAnimale.quantiteIndividusMoisProchain = nouvelle;
    }

    public void migrer() {
        switch (this.dataPopulationAnimale.statusMigration) {
        
        	case Fixe:
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			this.dataPopulationAnimale.statusMigration = StatusMigrationEnum.MigrantAuSud;
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		else if (this.localisationAnimale().penurieAlimentaire() > 0) {
        			this.dataPopulationAnimale.statusMigration = StatusMigrationEnum.MigrantAuNord;
        			this.localisationAnimale().migrerAuNord(this);
        		}
        		break;
        
        	case MigrantAuNord:
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			this.dataPopulationAnimale.statusMigration = StatusMigrationEnum.MigrantAuSud;
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		else if (super.indexTerritoireOccuppe() == 5) {
        			this.dataPopulationAnimale.statusMigration = StatusMigrationEnum.Fixe;
        		}
        		else {
        			this.localisationAnimale().migrerAuNord(this);
        		}
        		break;
        
        	case MigrantAuSud:
        		if (super.indexTerritoireOccuppe() == 1) {
        			this.dataPopulationAnimale.statusMigration = StatusMigrationEnum.Fixe;
        		}
        		else {
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		break;
        
        	default: break;
        }
    }

}
