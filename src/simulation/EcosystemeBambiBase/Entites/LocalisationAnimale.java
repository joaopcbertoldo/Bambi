package simulation.ecosystemeBambiBase.entites;

public class LocalisationAnimale extends Localisation {
	
	public LocalisationAnimale(Territoire territoire) {
		super(territoire);
		// TODO Auto-generated constructor stub
	}

	// en %
    public double penurieVegetale() {
        return super.territoire.penurieVegetal();
    }

// en %
    public double penurieAlimentaire() {
        return (this.penurieVegetale() + super.penurieEau()) / 2;
    }

    public void migrerAuNord(final Population population) {
        super.territoire.deplacerPopulationAuNord(population);
    }

    public void migrerAuSud(final Population population) {
        super.territoire.deplacerPopulationAuSud(population);
    }

}
