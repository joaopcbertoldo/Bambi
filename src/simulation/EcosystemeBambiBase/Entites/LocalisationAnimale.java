package simulation.ecosystemeBambiBase.entites;

public class LocalisationAnimale extends Localisation {
	
	public LocalisationAnimale(Territoire territoire) {
		super(territoire);
	}

	// en %
    public double penurieVegetale() {
        return super.territoire.penurieVegetal();
    }

    // en %
    public double penurieAlimentaire() {
        return (this.penurieVegetale() + super.penurieEau()) / 2;
    }

    public void migrerAuNord(Population population) {
        super.territoire.deplacerPopulationAuNord(population);
    }

    public void migrerAuSud(Population population) {
        super.territoire.deplacerPopulationAuSud(population);
    }

}
