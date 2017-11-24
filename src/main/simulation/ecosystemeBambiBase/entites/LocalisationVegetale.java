package main.simulation.ecosystemeBambiBase.entites;

public class LocalisationVegetale extends Localisation {
	public LocalisationVegetale(Territoire territoire) {
		super(territoire);
	}

    // kg
    public double quantiteVegetalNonMange() {
        return this.territoire.quantiteVegetalNonMange();
    }

}
