package main.core;

import java.io.File;

import javax.swing.JButton;

import main.animation.AnimationControleur;
import main.domain.*;
import main.fichiers.GestionnaireFichierParametrage;
import main.fichiers.GestionnaireFichierResultatSimulation;
import main.simulation.GestionnaireDeSimulation;

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
    	try {
			gestionnaireFichierParametrage.chargerPluviometrie(adresse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void chargerParamPasClim(File f) {
    	String adresse = f.getAbsolutePath();
    	try {
			gestionnaireFichierParametrage.chargerParametrageSimulationNonClimatique(adresse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void lancerSimulation() {
    	try {
			ResultatSimulation r = gestionnaireDeSimulation.Simuler(gestionnaireFichierParametrage.recupererParametrageSimulation());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("erreur lors du lancement de la simulation");
		}
    //	gestionnaireFichierResultatSimulation.setResultat(r);
    	
    	
    }
    
    public void sauvegarderCsv(String adresse) {
    	ResultatSimulation r = gestionnaireFichierResultatSimulation.recupererResultatSimulation();
    //	gestionnaireFichierResultatSimulation.sauvegarderResultatSimulation(r,adresse);
    	
    }
    
    public void chargerResultat(File f) {
    	String adresse = f.getAbsolutePath();
    	try {
			gestionnaireFichierResultatSimulation.chargerResultatSimulation(adresse);
			System.out.println("Resultats chargés");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void animation() {
    	
    	animationControleur = new AnimationControleur(gestionnaireFichierResultatSimulation.recupererResultatSimulation());
    	animationControleur.creerIHM();
    	
    }
    
    public static void main(String[] args) {
    	Controleur control = new Controleur();
    	control.creerMenuIHM();
    }
}
