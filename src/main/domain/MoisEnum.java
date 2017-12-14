package main.domain;


public enum MoisEnum {
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
	
	public MoisEnum next() {
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
	
	public static MoisEnum str2mois (String str){
		return MoisEnum.valueOf(str);
	}
	
	public static void test(String[] args) {
	}
	
	/*public static MoisEnum fromString(String s ){
		switch(s) {
		case "Janvier":   return Janvier;
			
		case "Fevrier":   return Fevrier;
			
		case "Mars":      return Mars;
			
		case "Avril":     return Avril;
			
		case "Mai":       return Mai;
			
		case "Juin":      return Juin;
			
		case "Juillet":   return Juillet;
			
		case "Aout":      return Aout;
			
		case "Septembre": return Septembre;
			
		case "Octobre":   return Octobre;
			
		case "Novembre":  return Novembre;
			
		case" Decembre":  return Decembre;
	}*/
}
