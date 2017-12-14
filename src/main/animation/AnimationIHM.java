package main.animation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.List;
import java.util.ArrayList;

public class AnimationIHM extends JFrame implements ActionListener, ChangeListener, WindowListener{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton stop;
	private JButton pause;
	private JButton go;
	private JSlider slideVitesse;	
	private JPanel barreVitesse;
	
	private JButton precedent;
	private JButton suivant;
	private JSlider slideMois;	
	private JPanel barreMois;
	private JLabel labMoisActuel;
	
	private List<IhmUnTerritoire> ihmTerritoires;
	
	private AnimationControleur ctrl;
	
	public AnimationIHM(AnimationControleur ctrl, double maxEau, double maxVeg) {
		
		super("Visualiseur de résultats");	
		
		this.ctrl=ctrl;	
		ihmTerritoires = new ArrayList<IhmUnTerritoire>();
		for(int i=0 ; i<=4; i++) {
			IhmUnTerritoire t = new IhmUnTerritoire(maxEau, maxVeg);
			t.setMinimumSize(new Dimension(400,80));
			ihmTerritoires.add(t);			
		}
		
		//Ajout des territoires
		for(int i=0 ; i<=4; i++) {
			this.add(ihmTerritoires.get(i));
		}
		
		//Création de la barre d'outils pour la vitesse
		stop = new JButton("stop");
		pause = new JButton("pause");
		go = new JButton("go");
		slideVitesse = new JSlider(1,10);
		slideVitesse.setValue(1);
		barreVitesse = new JPanel();
		barreVitesse.add(stop);
		barreVitesse.add(pause);
		barreVitesse.add(go);
		barreVitesse.add(slideVitesse);
	//	barreVitesse.setMinimumSize(new Dimension(200,50));
		barreVitesse.setMaximumSize(new Dimension(500,50));
		
		stop.addActionListener(this);
		pause.addActionListener(this);
		go.addActionListener(this);
		slideVitesse.addChangeListener(this);
		
		this.add(barreVitesse);
		
		//Création du JPanel de sélecteur du mois
		precedent = new JButton("P");
		slideMois = new JSlider(0,ctrl.getDureeSimulation()-1); //durée à changer
		slideMois.setValue(0);
		
		suivant = new JButton("S");
		barreMois = new JPanel();
		barreMois.add(precedent);
		barreMois.add(slideMois);
		barreMois.add(suivant);
		
	//	barreMois.setMinimumSize(new Dimension(200,50));
		barreMois.setMaximumSize(new Dimension(500,100));	
		this.add(barreMois);
		
		labMoisActuel = new JLabel("Mois 1 de la simulation");
		this.add(labMoisActuel);
		labMoisActuel.setAlignmentX(CENTER_ALIGNMENT);
		
		suivant.addActionListener(this);
		precedent.addActionListener(this);
		slideMois.addChangeListener(this);
		
		//Mise en forme globale
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setVisible(true);
		
	}
	
	//Méthodes d'actualisation de l'IHM
	
	public void setEauEtVegEtPop(final double eau, final double veg, final int popAnimale, final int territoire) {
		ihmTerritoires.get(territoire).setEauEtVegEtPop(eau, veg, popAnimale);	
    }

	
	public void actualiserSliderMois(int n) {
		slideMois.setValue(n);		
		
	}
	
	public void actualiserSliderVitesse(int v) {
		slideVitesse.setValue(v);
	}
	
	public void actualiserLabMois(int n) {
		labMoisActuel.setText("Mois " + (n+1) + " de la simulation");
	}
    
    public void actionPerformed(ActionEvent evt) {
    	
    	Object source = evt.getSource();
    	
    	if(source==stop) {
    		ctrl.arreter();
    	}
    	
    	if(source==pause) {
    		ctrl.pause();
    	}
    	
    	if(source==go) {
    		ctrl.lancer();
    	}   
    	
    	if(source==suivant) {
    		ctrl.moisSuivant();
    		
    	}
    	
    	if(source==precedent) {
    		ctrl.moisPrecedent();    		
    	}
	}
    
    public void stateChanged(ChangeEvent evt) {
    	Object source = evt.getSource();
    	
    	if(source==slideVitesse) {
			int v = ((JSlider) source).getValue();
			ctrl.setVitesse(v);
		}
    	
    	if(source==slideMois) {
    		int mois =  ((JSlider) source).getValue();
    		ctrl.setMois(mois);
    	}
    	
    }
    
    //Permet de gérer la fermeture du thread de vitesse avant de quitter la visualisation
	public void windowClosing(WindowEvent evt) {
		ctrl.quitterAnimation();
		System.exit(0);
	}
	
	public void windowOpened(WindowEvent evt) {}
	public void windowClosed(WindowEvent evt) {}
	public void windowIconified(WindowEvent evt) {}
    public void windowDeiconified(WindowEvent evt) {}
	public void windowActivated(WindowEvent evt) {}
	public void windowDeactivated(WindowEvent evt) {}


}
