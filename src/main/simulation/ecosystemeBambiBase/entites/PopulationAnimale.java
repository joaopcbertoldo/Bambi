package main.simulation.ecosystemeBambiBase.entites;

import main.domain.MoisEnum;
import main.simulation.ecosystemeBambiBase.entitesData.DataPopulationAnimale;
import main.simulation.ecosystemeBambiBase.enums.StatusMigrationEnum;

/**
 * PopulationAnimale (sousclasse de Population).
 * Repr�sente une population de bambis.
 * 
 * @author Jo�o Paulo
 *
 */
public class PopulationAnimale extends Population {
	
	/**
	 * Localisation Animale.
	 * Accede la localisation de Population en fesant le cast vers Localisation Animale
	 * 
	 * @return Objet de localisation de Population cast� en Localisation Animale.
	 */
    protected LocalisationAnimale localisationAnimale() {
    	// cast
    	return (LocalisationAnimale) super.localisation;
    }
    
    
    /**
	 * Data de population Animale.
	 * Accede le data de Population en fesant le cast vers DataPopulationAnimale
	 * 
	 * @return Objet de data de Population cast� en DataPopulationAnimale.
	 */
    protected DataPopulationAnimale dataPopulationAnimale() {
    	// cast
    	return (DataPopulationAnimale) super.dataPopulation;
    }
    

    /** 
     * Constructeur.
     * @param dataPopulationAnimale Objet de data de population animale.
     * @param mois Objet de r�f�rence de mois/it�ration.
     */
    public PopulationAnimale(DataPopulationAnimale dataPopulationAnimale, Mois mois) {
    	// constructeur de Population
        super(dataPopulationAnimale, mois);
    }
    

    /**
     * M�sure de p�nurie en eau cumull�e au long des 6 derniers mois v�cus par la population animale
     * 
     * @return P�nurie cumul�e en %.
     */
    public double penurieEauCumulee() {
    	// prend les 6 derni�res valeurs de l'historique et les somme
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
     * M�sure de p�nurie en v�g�tal cumull�e au long des 6 derniers mois v�cus par la population animale
     * 
     * @return P�nurie cumul�e en %.
     */
    public double penurieVegetaleCumulee() {
    	// prend les 6 derni�res valeurs de l'historique et les somme
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
     * Le taux de naissance est calcul� avec l'�quation:
     * (.1/penEau * .1/penVeg) * TauxMAX 
     *
     * penEau, penVeg NE SONT PAS en %
     * la fonction sature en TauxMAX
     * 
     * @return Taux de naissance en %.
     */
    public double tauxNaissance() {
    	// r�cup�re le taux de naissance max
        double tauxMax = this.dataPopulationAnimale().tauxNaissanceMax;
        
        // correction des % vers fraction 
        double penurieEauCumu = this.penurieEauCumulee() / 100;
        double penurieVegCumu = this.penurieVegetaleCumulee() / 100;
        
        // �quation normalis� (sans le taux max)
        double res = (0.1 / penurieEauCumu) * (0.1 / penurieVegCumu);
        
        // saturation
        res = res > 1 ? 1 : res;
        
        // multiplication par le taux max
        res = res *  tauxMax;
        
        return res;
    }
    

    /**
     * Le taux de mortalit� est calcul� avec l'�quation:
     * tauxPred + tauxPenMax * penAlim
     *
     * il sature � 100%
     * 
     * @return Taux de mortalit� en %.
     */
    public double tauxMortalite() {
        // taux de mortalit� par p�nurie max
    	double tauxPenMax = this.dataPopulationAnimale().tauxMortaliteParPenurieAlimentaireMax;
        
    	// taux de mortalit� par pr�dateur
    	double tauxPred   = this.dataPopulationAnimale().tauxMortalitePredateur;
        
    	// penurie alimentaire (atravers la localisation)
    	double penAlim    = this.localisationAnimale().penurieAlimentaire() / 100;
        
        // calcul
        double res = tauxPred + tauxPenMax * penAlim;
        
        // saturation � 100%
        if (res > 100.0)
        	res = 100.0;
        
        return res;
    }
    
    /**
     * etatDeMigration
     * 
     * r�cup�re l'�tat de migration en data.
     * 
     * @retur �tat (Status) de migration
     */
    public StatusMigrationEnum etatDeMigration() {
        return this.dataPopulationAnimale().statusMigration;
    }
    
    
    /**
     * m�thode qui sur�crit la m�thode abstraite de calcul de quantit� d'individus pour l'avancement 
     * d'un pas de la simulation.
     * 
     * �quation:
     * 		actuel * (1 + tauxNaissance - tauxMortalite)
     */
    public void calculerNouvelleQuantiteIndividus() {
    	// p�nurie en eau
    	double penEau = this.localisationAnimale().penurieEau();
    	
    	// p�nurie en v�g�tal
    	double penVeg = this.localisationAnimale().penurieVegetale();
    	
    	// ajoute les p�nuries dans l'historique
		this.dataPopulationAnimale().historiquePenurieEau.add(penEau);
    	this.dataPopulationAnimale().historiquePenurieNourriture.add(penVeg);
    	
    	// quantit� actuel d'individus
        double actuel = this.dataPopulationAnimale().quantiteIndividus;
        
        // calcul
        double nouvelle = actuel * (1 + this.tauxNaissance()/100 - this.tauxMortalite()/100);
        
        // affectation du r�sultat dans la data
        this.dataPopulationAnimale().quantiteIndividusMoisProchain = Math.round(nouvelle);
    }

    /**
     * migrer
     * 
     * M�thode de prise de d�cision concernant la migration.
     * Cette m�thode affect l'�tat de migration et fait appel � la localisation animale 
     * effectuer le changement de territoire.
     */
    public void migrer() {
    	// switch selon l'�tat actuel
        switch (this.dataPopulationAnimale().statusMigration) {
        
        	// Fixe ****************************************************************
        	case Fixe:
        		// passage � MigrantAuSud (condition: mois de septembre)
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.MigrantAuSud;
        			
        			// changement de territoire
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		
        		// passage � MigrantAuNord (condition: p�nurie alimentaire) 
        		else if (this.localisationAnimale().penurieAlimentaire() > 0) {
        			
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.MigrantAuNord;
        			
        			// changement de territoire
        			this.localisationAnimale().migrerAuNord(this);
        		}
        		
        		break;
        
        	// MigrantAuNord ****************************************************************
        	case MigrantAuNord:
        		// passage � MigrantAuSud
        		if (super.indexTerritoireOccuppe() > 1 && super.mois.getMois() == MoisEnum.Septembre) {
        			// status de migration
        			this.dataPopulationAnimale().statusMigration = StatusMigrationEnum.MigrantAuSud;
        			
        			// changement de territoire
        			this.localisationAnimale().migrerAuSud(this);
        		}
        		
        		// passage � Fixe
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
        		// passage � Fixe
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
