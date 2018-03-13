package main.simulation.ecosystemeBambiBase.entites;

/**
 * Objet de localisation animale.
 * Sousclasse de Localisation avec des particularit�s pour les animaux.
 * 
 * Cette classe permet les populations d'acceder des informations concernant 
 * le territoire occup� sans l'acceder directement.
 * 
 * Comme cette classe est basiquement un wrapper de quelques informations de territoire
 * et ses fonction sont bien simples elle n'a pas de teste unitaire.
 * 
 * @author Jo�o Paulo
 */
public class LocalisationAnimale extends Localisation {
	
	/**
	 * Constructeur.
	 * Appelle le constructeur de Localisation.
	 * @param territoire Le territoire point� par la localisation.
	 */
	public LocalisationAnimale(Territoire territoire) {
		super(territoire);
	}

	
	/**
	 * Penurie v�g�tale du territoire actuelle.
	 * @return P�nurie v�g�tale en %.
	 */
    public double penurieVegetale() {
        return super.territoire.penurieVegetal();
    }

    
    /**
	 * Penurie alimentaire du territoire actuelle. 
	 * La p�nurie alimentaire est la moyenne entre la p�nurie d'eau et v�g�tale.
	 * @return P�nurie alimentaire en %.
	 */
    public double penurieAlimentaire() {
        return (this.penurieVegetale() + super.penurieEau()) / 2;
    }

    
    /**
     * Fait appel � la fonction de migration au Nord dans le territoire occup�.
     * La population est un argument car le territoire n'a pas de r�f�rence de
     * la population � qu'elle il appartient.
     * @param population La population qui doit migrer.
     */
    public void migrerAuNord(Population population) {
        super.territoire.deplacerPopulationAuNord(population);
    }

    
    /**
     * Fait appel � la fonction de migration au Sud dans le territoire occup�.
     * La population est un argument car le territoire n'a pas de r�f�rence de
     * la population � qu'elle il appartient.
     * @param population La population qui doit migrer.
     */
    public void migrerAuSud(Population population) {
        super.territoire.deplacerPopulationAuSud(population);
    }

}
