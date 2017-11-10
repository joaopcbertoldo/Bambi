package core;

import java.io.File;

import javax.swing.JButton;

import animation.AnimationControleur;
import fichiers.GestionnaireFichierParametrage;
import fichiers.GestionnaireFichierResultatSimulation;
import simulation.GestionnaireDeSimulation;
import domain.*;

public class Controleur {
	
    private GestionnaireDeSimulation gestionnaireDeSimulation;
    private GestionnaireFichierParametrage gestionnaireFichierParametrage;
    private GestionnaireFichierResultatSimulation gestionnaireFichierResultatSimulation;
    private MenuIHM menuIHM;
    private AnimationControleur animationControleur;
    
    public Controleur() {
    	
   // 	gestionnaireDeSimulation = GestionnaireDeSimulation.Instance();
    	gestionnaireFichierParametrage = new GestionnaireFichierParametrage();
    	gestionnaireFichierResultatSimulation = new GestionnaireFichierResultatSimulation();
    	
    }
    
    public void creerMenuIHM() {
    	menuIHM = new MenuIHM(this);
    	menuIHM.setSize(500,500);
    	menuIHM.setVisible(true);
    	
    }
    
    //Méthodes appelées après réaction de l'ihm
    public void chargerPluie(File f) {
    	String adresse = f.getAbsolutePath();
    	gestionnaireFichierParametrage.chargerPluviometrie(adresse);

    }
    
    public void chargerParamPasClim(File f) {
    	String adresse = f.getAbsolutePath();
    	gestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique(adresse);
    	
    }
    
    public void lancerSimulation() {
    	ResultatSimulation r = gestionnaireDeSimulation.Simuler(gestionnaireFichierParametrage.recupererParametrageSimulation());
    //	gestionnaireFichierResultatSimulation.setResultat(r);
    	
    	
    }
    
    public void sauvegarderCsv(String adresse) {
    	
   // 	gestionnaireFichierResultatSimulation.sauvegarderResultatSimulation(adresse);
    	
    }
    
    public void chargerResultat(File f) {
    	String adresse = f.getAbsolutePath();
    	gestionnaireFichierResultatSimulation.chargerResultatSimulation(adresse);
    	
    }
    
    public void animation() {
    	
    }
    
    public static void main(String[] args) {
    	Controleur control = new Controleur();
    	control.creerMenuIHM();
    }
}
