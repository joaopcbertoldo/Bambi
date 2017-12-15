package main.domain;

/**
 * Enum�ration des mois en fran�ais.
 * 
 * @author Jo�o Paulo
 *
 */
public enum MoisEnum {
	/** Valeurs en s�quence. */
    Janvier,
    Fevrier,
    Mars,
    Avril,
    Mai,
    Juin,
    Juillet,
    Aout,
    Septembre,
    Octobre,
    Novembre,
    Decembre;
	
	
	/**
	 * M�thode qui donne le mois suivant en terme del'�num�ration.
	 * @return Enum du mois suivant.
	 */
	public MoisEnum next() {
		
		// switch de la valeur m�me
		switch(this) {
			case Janvier:   return Fevrier;
				
			case Fevrier:   return Mars;
				
			case Mars:      return Avril;
				
			case Avril:     return Mai;
				
			case Mai:       return Juin;
				
			case Juin:      return Juillet;
				
			case Juillet:   return Aout;
				
			case Aout:      return Septembre;
				
			case Septembre: return Octobre;
				
			case Octobre:   return Novembre;
				
			case Novembre:  return Decembre;
				
			case Decembre:  return Janvier;
			
			default:        return Janvier;
		}
	}
	
	
	/**
	 * Caster de string vers valeur de l'�numeration.
	 * @param str Nom du mois en String (premi�re lettre en majuscule)
	 * @return Mois en objet d'�num�ration.
	 */
	public static MoisEnum str2mois(String str) {
		return MoisEnum.valueOf(str);
	}
}
