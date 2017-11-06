package simulation.ecosystemeBambiBase.entites;

public class LocalisationVegetale extends Localisation {
	public LocalisationVegetale(Territoire territoire) {
		super(territoire);
		// TODO Auto-generated constructor stub
	}

// kg
    public double quantiteVegetalNonMange() {
        return this.territoire.quantiteVegetalNonMange();
    }

}
