package domain;


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
			case Janvier: return Fevrier;
				
			case Fevrier: return Mars;
				
			case Mars: return Avril;
				
			case Avril: return Mai;
				
			case Mai: return Juin;
				
			case Juin: return Juillet;
				
			case Juillet: return Aout;
				
			case Aout: return Septembre;
				
			case Septembre: return Octobre;
				
			case Octobre: return Novembre;
				
			case Novembre: return Decembre;
				
			case Decembre: return Janvier;
			
			default: return Janvier;
		}
	}
}
