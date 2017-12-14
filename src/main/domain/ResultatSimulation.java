package main.domain;


public interface ResultatSimulation {
    int popAnimale(final int n, final int territoire);

    double stockEau(final int territoire, final int n);

    double stockVeg(final int territoire, final int n);

    int NbdePas(); 
    
    MoisEnum mois0();
}
