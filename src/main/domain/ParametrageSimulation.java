package main.domain;


public interface ParametrageSimulation {
    double pluviometrie(final int territoire, final int moisN);

    int nombreDePas();

    double surfaceTerritoire(final int territoire);

    MoisEnum mois0();

    int nombreIndividusAnimale0();

    double stockVegetaux(final int territoire);

    int localisationInitiale();

    double stockEau(final int territoire);

    double tauxPerteEauEvaporation(final int territoire);

    double stockVegetauxMinimal();

    double tauxNaissanceAnimalMaximal();

    double tauxMortalitePredateur();

    double tauxMortaliteParPenurieAlimentaireMaximal();

    double besoinEauVegetal();

    double besoinEauAnimal();

    double besoinVegetalAnimal();

    double tauxCroissanteVegetal();

    double tauxPerteVegetalPenurieMax();

}
