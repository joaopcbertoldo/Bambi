package main.core;

import java.io.File;
import java.io.IOException;

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
    
    private ResultatSimulation resultats;
    
    public Controleur() {
    	
     	gestionnaireDeSimulation = GestionnaireDeSimulation.Instance();
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
    	System.out.print("0e ligne bien executee");
    	try {
    		ParametrageSimulation p = gestionnaireFichierParametrage.recupererParametrageSimulation();
    		System.out.print("premiere ligne bien executee");
    		resultats = gestionnaireDeSimulation.Simuler(p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.err.println(e.getMessage());
		}
    }
    
    public void sauvegarderCsv(String adresse) {
    	resultats = gestionnaireFichierResultatSimulation.recupererResultatSimulation();
    	try {
			gestionnaireFichierResultatSimulation.sauvegarderResultatSimulation(resultats,adresse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Sauvegarde échouée");
		}
    	
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
    	resultats= gestionnaireFichierResultatSimulation.recupererResultatSimulation();
    	animationControleur = new AnimationControleur(resultats);
    	animationControleur.creerIHM();
    	
    }
    
    public static void main(String[] args) {
    	Controleur control = new Controleur();
    	control.creerMenuIHM();
    }
}
