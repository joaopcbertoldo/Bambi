package main.simulation.ecosystemeBambiBase.entites;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationAnimale;
import main.simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

/**
 * PopulationAnimale (sousclasse de Population).
 * Représente une population de bambis.
 * 
 * @author João Paulo
 *
 */
public class PopulationAnimale extends Population {
	
	/**
	 * Localisation Animale.
	 * Accede la localisation de Population en fesant le cast vers Localisation Animale
	 * 
	 * @return Objet de localisation de Population casté en Localisation Animale.
	 */
    protected LocalisationAnimale localisationAnimale() {
    	// cast
    	return (LocalisationAnimale) super.localisation;
    }
    
    
    /**
	 * Data de population Animale.
	 * Accede le data de Population en fesant le cast vers DataPopulationAnimale
	 * 
	 * @return Objet de data de Population casté en DataPopulationAnimale.
	 */
    protected DataPopulationAnimale dataPopulationAnimale() {
    	// cast
    	return (DataPopulationAnimale) super.dataPopulation;
    }
    

    /** 
     * Constructeur.
     * @param dataPopulationAnimale Objet de data de population animale.
     * @param mois Objet de référence de mois/itération.
     */
    public PopulationAnimale(DataPopulationAnimale dataPopulationAnimale, Mois mois) {
    	// constructeur de Population
        super(dataPopulationAnimale, mois);
    }
    

    /**
     * Mésure de pénurie en eau cumullée au long des 6 derniers mois vécus par la population animale
     * 
     * @return Pénurie cumulée en %.
     */
    public double penurieEauCumulee() {
    	// prend les 6 dernières valeurs de l'historique et les somme
        return dataPopulationAnimale()
      		   .historiquePenurieEau
      		   .stream()
      		   // prend les 6 derniers
      		   .skip(Math.max(0, dataPopulationAnimale().historiquePenurieEau.size() - 6))
      		   .limit(6)
     		   // somme
      		   .reduce(0.0, (a,b) -> a + b);
    }
 
    
    /**
     * Mésure de pénurie en végétal cumullée au long des 6 derniers mois vécus par la population animale
     * 
     * @return Pénurie cumulée en %.
     */
    public double penurieVegetaleCumulee() {
    	// prend les 6 dernières valeurs de l'historique et les somme
        return dataPopulationAnimale()
     		   .historiquePenurieNourriture
     		   .stream()
      		   // prend les 6 derniers
     		   .skip(Math.max(0, dataPopulationAnimale().historiquePenurieNourriture.size() - 6))
     		   .limit(6)
     		   // somme
     		   .reduce(0.0, (a,b) -> a + b);
    }

    
    /**
     * Le taux de naissance est calculé avec l'équation:
     * (.1/penEau * .1/penVeg) * TauxMAX 
     *
     * penEau, penVeg NE SONT PAS en %
     * la fonction sature en TauxMAX
     * 
     * @return Taux de naissance en %.
     */
    public double tauxNaissance() {
    	// récupère le taux de naissance max
        double tauxMax = this.dataPopulationAnimale().tauxNaissanceMax;
        
        // correction des % vers fraction 
        double penurieEauCumu = this.penurieEauCumulee() / 100;
        double penurieVegCumu = this.penurieVegetaleCumulee() / 100;
        
        // équation normalisé (sans le taux max)
        double res = (0.1 / penurieEauCumu) * (0.1 / penurieVegCumu);
        
        // saturation
        res = res > 1 ? 1 : res;
        
        // multiplication par le taux max
        res = res *  tauxMax;
        
        return res;
    }
    

    /**
     * Le taux de mortalité est calculé avec l'équation:
     * tauxPred + tauxPenMax * penAlim
     *
     * il sature à 100%
     * 
     * @return Taux de mortalité en %.
     */
    public double tauxMortalite() {
        // taux de mortalité par pénurie max
    	double tauxPenMax = this.dataPopulationAnimale().tauxMortaliteParPenurieAlimentaireMax;
        
    	// taux de mortalité par prédateur
    	double tauxPred   = this.dataPopulationAnimale().tauxMortalitePredateur;
        
    	// penurie alimentaire (atravers la localisation)
    	double penAlim    = this.localisationAnimale().penurieAlimentaire() / 100;
        
        // calcul
        double res = tauxPred + tauxPenMax * penAlim;
        
        // saturation à 100%
        if (res > 100.0)
        	res = 100.0;
        
        return res;
    }
    
    /**
     * etatDeMigration
     * 
     * récupère l'état de migration en data.
     * 
     * @retur État (Status) de migration
     */
    public StatusMigrationEnum etatDeMigration() {
        return this.dataPopulationAnimale().statusMigration;
    }
    
    
    /**
     * méthode qui surécrit la méthode abstraite de calcul de quantité d'individus pour l'avancement 
     * d'un pas de la simulation.
     * 
     * équation:
     * 		actuel * (1 + tauxNaissance - tauxMortalite)
     */
    public void calculerNouvelleQuantiteIndividus() {
    	// pénurie en eau
    	double penEau = this.localisationAnimale().penurieEau();
    	
    	// pénurie en végétal
    	double penVeg = this.localisationAnimale().penurieVegetale();
    	
    	// ajoute les pénuries dans l'historique
		this.dataPopulationAnimale().historiquePenurieEau.add(penEau);
    	this.dataPopulationAnimale().historiquePenurieNourriture.add(penVeg);
    	
    	// quantité actuel d'individus
        double actuel = this.dataPopulationAnimale().quantiteIndividus;
        
        // calcul
        double nouvelle = actuel * (1 + this.tauxNaissance()/100 - this.tauxMortalite()/100);
        
        // affectation du résultat dans la data
        this.dataPopulationAnimale().quantiteIndividusMoisProchain = Math.round(nouvelle);
    }

    /**
     * migrer
     * 
     * Méthode de prise de décision concernant la migration.
     * Cette méthode affect l'état de migration et fait appel à la localisation animale 
     * effectuer le changement de territoire.
     */
    public void migrer() {
    	// switch selon l'état actuel
        switch (this.dataPopulationAnimale().statusMigration) {
        
        	// Fixe ****************************************************************
        	case Fixe:
        		// passage à MigrantAuSud (condition: mois de septembre)
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.MigrantAuSud;
        			
        			// changement de territoire
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		
        		// passage à MigrantAuNord (condition: pénurie alimentaire) 
        		else if (this.localisationAnimale().penurieAlimentaire() > 0) {
        			
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.MigrantAuNord;
        			
        			// changement de territoire
        			this.localisationAnimale().migrerAuNord(this);
        		}
        		
        		break;
        
        	// MigrantAuNord ****************************************************************
        	case MigrantAuNord:
        		// passage à MigrantAuSud
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.MigrantAuSud;
        			
        			// changement de territoire
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		
        		// passage à Fixe
        		else if (super.indexTerritoireOccuppe() == 5) {
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.Fixe;
        		}
        		
        		// sinon, MigrandAuNord
        		else {
        			// changement de territoire
        			this.localisationAnimale().migrerAuNord(this);
        		}
        		
        		break;

            // MigrantAuSud ****************************************************************
        	case MigrantAuSud:
        		// passage à Fixe
        		if (super.indexTerritoireOccuppe() == 1) {
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.Fixe;
        		}
        		
        		// sinon, MigrantAuSud
        		else {
        			// changement de territoire
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		
        		break;
        
        	// default
        	default: break;
        }
    }

}
