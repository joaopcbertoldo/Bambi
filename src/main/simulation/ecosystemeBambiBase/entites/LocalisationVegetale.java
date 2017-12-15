package main.simulation.ecosystemeBambiBase.entites;

/**
 * Objet de localisation végétale.
 * Sousclasse de Localisation avec des particularités pour les végétaux.
 * 
 * Cette classe permet les populations d'acceder des informations concernant 
 * le territoire occupé sans l'acceder directement.
 * 
 * Comme cette classe est basiquement un wrapper de quelques informations de territoire
 * elle n'a pas de teste unitaire.
 * 
 * @author João Paulo
 */
public class LocalisationVegetale extends Localisation {
	
	/**
	 * Constructeur.
	 * Appelle le constructeur de Localisation.
	 * @param territoire Le territoire pointé par la localisation.
	 */
	public LocalisationVegetale(Territoire territoire) {
		super(territoire);
	}

    /**
     * Quantité de végétal non mangé pendant le mois actuel.
     * @return Quantité de végétal non mangé en kg.
     */
    public double quantiteVegetalNonMange() {
        return this.territoire.quantiteVegetalNonMange();
    }

}
