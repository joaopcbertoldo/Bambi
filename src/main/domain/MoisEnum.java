package main.domain;

/**
 * Enumération des mois en français.
 * 
 * @author Joï¿½o Paulo
 *
 */
public enum MoisEnum {
	/** Valeurs en séquence. */
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
	 * Mï¿½thode qui donne le mois suivant en terme del'ï¿½numï¿½ration.
	 * @return Enum du mois suivant.
	 */
	public MoisEnum next() {
		
		// switch de la valeur mï¿½me
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
	
	public static MoisEnum getMois(MoisEnum m, int n) {
		for(int i=0 ; i<n ; i++) m = m.next();
		return m;
	}
	
	
	/**
	 * Caster de string vers valeur de l'ï¿½numeration.
	 * @param str Nom du mois en String (premiï¿½re lettre en majuscule)
	 * @return Mois en objet d'ï¿½numï¿½ration.
	 */
	public static MoisEnum str2mois(String str) {
		return MoisEnum.valueOf(str);
    }
}
