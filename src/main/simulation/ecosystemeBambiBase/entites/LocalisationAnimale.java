package main.simulation.ecosystemeBambiBase.entites;

/**
 * Objet de localisation animale.
 * Sousclasse de Localisation avec des particularités pour les animaux.
 * 
 * Cette classe permet les populations d'acceder des informations concernant 
 * le territoire occupé sans l'acceder directement.
 * 
 * Comme cette classe est basiquement un wrapper de quelques informations de territoire
 * et ses fonction sont bien simples elle n'a pas de teste unitaire.
 * 
 * @author João Paulo
 */
public class LocalisationAnimale extends Localisation {
	
	/**
	 * Constructeur.
	 * Appelle le constructeur de Localisation.
	 * @param territoire Le territoire pointé par la localisation.
	 */
	public LocalisationAnimale(Territoire territoire) {
		super(territoire);
	}

	
	/**
	 * Penurie végétale du territoire actuelle.
	 * @return Pénurie végétale en %.
	 */
    public double penurieVegetale() {
        return super.territoire.penurieVegetal();
    }

    
    /**
	 * Penurie alimentaire du territoire actuelle. 
	 * La pénurie alimentaire est la moyenne entre la pénurie d'eau et végétale.
	 * @return Pénurie alimentaire en %.
	 */
    public double penurieAlimentaire() {
        return (this.penurieVegetale() + super.penurieEau()) / 2;
    }

    
    /**
     * Fait appel à la fonction de migration au Nord dans le territoire occupé.
     * La population est un argument car le territoire n'a pas de référence de
     * la population à qu'elle il appartient.
     * @param population La population qui doit migrer.
     */
    public void migrerAuNord(Population population) {
        super.territoire.deplacerPopulationAuNord(population);
    }

    
    /**
     * Fait appel à la fonction de migration au Sud dans le territoire occupé.
     * La population est un argument car le territoire n'a pas de référence de
     * la population à qu'elle il appartient.
     * @param population La population qui doit migrer.
     */
    public void migrerAuSud(Population population) {
        super.territoire.deplacerPopulationAuSud(population);
    }

}
