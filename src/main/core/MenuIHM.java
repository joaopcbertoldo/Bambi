package main.core;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;

public class MenuIHM extends JFrame implements ActionListener {

//Attributs
	private JButton btChargerPluie;
    private JButton btChargerParamPasClim;
    private JButton btLancer;
    private JButton btSauvegardeCsv;
    private JButton btChargerResultat;
    private JButton btAnimation;
    
    //Selecteur de fichiers
    final JFileChooser fc = new JFileChooser();
    
    private Controleur c;
    private GridLayout grille;
   
    public MenuIHM(Controleur c) {
    	super("Menu Bambi");
    	
    	this.c=c;
    	btChargerPluie = new JButton("Charger pluie");
    	btChargerParamPasClim = new JButton("Charger paramètres");
    	btLancer = new JButton("Lancer simulation");
    	btSauvegardeCsv = new JButton("Sauvegarder résultats");
    	btChargerResultat = new JButton("Charger résultats");
    	btAnimation = new JButton("Visualiser les résultats");
    	
    	btChargerPluie.addActionListener(this);
    	btChargerParamPasClim.addActionListener(this);
    	btLancer.addActionListener(this);
    	btSauvegardeCsv.addActionListener(this);
    	btChargerResultat.addActionListener(this);
    	btAnimation.addActionListener(this);
    	
    	grille = new GridLayout(6,1);
    	this.setLayout(grille);
    	this.add(btChargerPluie);
    	this.add(btChargerParamPasClim);
    	this.add(btLancer);
    	this.add(btSauvegardeCsv);
    	this.add(btChargerResultat);
    	this.add(btAnimation);
    	
    	
    }
    
	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getSource()==btChargerPluie) {
			
			fc.setDialogTitle("Ouvrir");
			int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            c.chargerPluie(file);
	        } 
		}
		else if(evt.getSource()==btChargerParamPasClim) {
			
			fc.setDialogTitle("Ouvrir");
			int returnVal = fc.showOpenDialog(this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				fc.setDialogTitle("Ouvrir");
				c.chargerParamPasClim(file);
			}
		}
		else if(evt.getSource()==btLancer) c.lancerSimulation();
		
		else if(evt.getSource()==btSauvegardeCsv) {
			
			fc.setDialogTitle("Sauvegarder le fichier de résultats");
			int returnVal = fc.showOpenDialog(this);			
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				c.sauvegarderCsv(file.getAbsolutePath());
			}			
		}
	
		else if(evt.getSource()==btChargerResultat) {
			int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            c.chargerResultat(file);
	        } 
		}
		else if(evt.getSource()==btAnimation) c.animation();
		
	}

}
