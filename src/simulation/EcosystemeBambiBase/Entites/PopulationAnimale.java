package simulation.EcosystemeBambiBase.Entites;

import simulation.EcosystemeBambiBase.EntitesData.*;
import simulation.EcosystemeBambiBase.EntitesData.DataPopulationAnimale;
import simulation.EcosystemeBambiBase.Enums.*;
import simulation.EcosystemeBambiBase.Enums.StatusMigrationEnum;

public class PopulationAnimale extends Population {
    protected LocalisationAnimale localisationAnimale;

    protected DataPopulationAnimale dataPopulationAnimale;

    public PopulationAnimale(final DataPopulationAnimale dataPopulationAnimale, final LocalisationAnimale localisationAnimale) {
        super(dataPopulationAnimale, localisationAnimale);
        this.dataPopulationAnimale = dataPopulationAnimale;
        this.localisationAnimale = localisationAnimale;
    }

// en %
    public double penurieEauCumulee(final int k) {
        // TODO Auto-generated return
        return 0;
    }

// en %
    public double penurieVegetaleCumulee(final int k) {
        // TODO Auto-generated return
        return 0;
    }

// en %
    public double tauxNaissance() {
        double tauxMax = this.dataPopulationAnimale.tauxNaissanceMax;
        double res = (0.1 / penurieEauCumulee()) * (0.1 / penurieVegetaleCumulee());
        res = res > 1 ? 1 : res;
        res = res *  tauxMax;
        return res;
    }

// en %
    public double tauxMortalite() {
        double tauxPenMax = this.dataPopulationAnimale.tauxMortaliteParPenurieAlimentaireMax;
        double tauxPred       = this.dataPopulationAnimale.tauxMortalitePredator;
        double penAlim       = this.localisationAnimale.penurieAlimentaire();
        
        double res = tauxPred + tauxPenMax * penAlim;
        return res;
    }

    public StatusMigrationEnum etatDeMigration() {
        return this.dataPopulationAnimale.statusMigration;
    }

    public void calculerNouvelleQuantiteIndividus() {
        double actuel = this.dataPopulationAnimale.quantiteIndividus;
        double nouvel = actuel * (1 + this.tauxNaissance() - this.tauxMortalite());
        this.dataPopulationAnimale.quantiteIndividusMoisProchain = nouvel;
    }

    public void migrer() {
        switch (this.statusMigration) {
        	case StatusMigration.Fixe:
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			this.dataPopulationAnimale.statusMigration = StatusMigration.MigrantAuSud;
        			this.localisationAnimale.migrerAuSud(this);
        		}
        		else if (this.localisationAnimale.penurieAlimentaire() > 0) {
        			this.dataPopulationAnimale.statusMigration = StatusMigration.MigrantAuNord;
        			this.localisationAnimale.migrerAuNord(this);
        		}
        		break;
        
        	case StatusMigration.MigrantAuNord:
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			this.dataPopulationAnimale.statusMigration = StatusMigration.MigrantAuSud;
        			this.localisationAnimale.migrerAuSud(this);
        		}
        		else if (super.indexTerritoireOccuppe() == 5) {
        			this.dataPopulationAnimale.statusMigration = StatusMigration.Fixe;
        		}
        		break;
        
        	case StatusMigration.MigrantAuSud:
        		if (super.indexTerritoireOccuppe() == 1) {
        			this.dataPopulationAnimale.statusMigration = StatusMigration.Fixe;
        		}
        		break;
        
        	default: break;
        }
    }

}
