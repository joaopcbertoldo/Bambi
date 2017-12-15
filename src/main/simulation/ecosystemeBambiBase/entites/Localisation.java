package main.simulation.ecosystemeBambiBase.entites;

/**
 * Objet de localisation générique.
 * 
 * Cette classe permet les populations d'acceder des informations concernant 
 * le territoire occupé sans l'acceder directement.
 * 
 * Comme cette classe est basiquement un wrapper de quelques informations de territoire,
 * elle n'a pas de teste unitaire.
 * 
 * @author João Paulo
 */
public class Localisation {
    
	/**
     * Territoire pointé par la localisation.
     */
	protected Territoire territoire;
    
	
	/**
	 * Constructeur. Prend référence au territoire auquel il doit pointer.
	 * @param territoire Territoire pointé par la localisation.
	 */
    public Localisation(Territoire territoire) {
        this.territoire = territoire;
    }
    
    
    /**
     * Récupère l'index du territoire pointé.
     * @return Index trouvé dans le territoire.
     */
    public int indexTerritoire() {
        return this.territoire.index();
    }
    
    
    /**
     * Balance d'eau du mois actuel, c-a-d la différence entre la disponibilité et le besoin.
     * @return Balance d'eau en Litre.
     */
    public double balanceEau() {
        return this.territoire.balanceEau();
    }
    
    
    /**
     * Penurie en eau récupérée dans le territoire.
     * @return Pénurie en eau  en %.
     */
    public double penurieEau() {
        return this.territoire.penurieEau();
    }

}
