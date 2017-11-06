package simulation.ecosystemeBambiBase.entites;

public class Localisation {
    protected Territoire territoire;

    public Localisation(Territoire territoire) {
        this.territoire = territoire;
    }

    public int indexTerritoire() {
        return this.territoire.index();
    }

    // Litre
    public double balanceEau() {
        return this.territoire.balanceEau();
    }

    // en %
    public double penurieEau() {
        return this.territoire.penurieEau();
    }

}
