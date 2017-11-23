package animation;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class AnimationIHM extends JFrame implements ActionListener {
    
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
	
	private List<IhmUnTerritoire> ihmTerritoires;
	
	private AnimationControleur ctrl;
	
	//Constructeur
	public AnimationIHM(AnimationControleur ctrl) {
		
		super("Visualiseur de résultats");	
		
		this.ctrl=ctrl;	
		ihmTerritoires = new ArrayList();
		for(int i=0 ; i<=4; i++) {
			ihmTerritoires.add(new IhmUnTerritoire());			
		}
		
		//ajout des territoires
		for(int i=0 ; i<=4; i++) {
			this.add(ihmTerritoires.get(i));
		}
		
		//création de la barre d'outils pour la vitesse
		stop = new JButton("stop");
		pause = new JButton("pause");
		go = new JButton("go");
		slideVitesse = new JSlider(0,100);
		barreVitesse = new JPanel();
		barreVitesse.add(stop);
		barreVitesse.add(pause);
		barreVitesse.add(go);
		barreVitesse.add(slideVitesse);
		
		stop.addActionListener(this);
		pause.addActionListener(this);
		go.addActionListener(this);
		
		this.add(barreVitesse);
		
		//création du JPanel de sélecteur du mois
		precedent = new JButton("P");
		slideMois = new JSlider(0,100); //durée à changer
		suivant = new JButton("S");
		barreMois = new JPanel();
		barreMois.add(precedent);
		barreMois.add(slideMois);
		barreMois.add(suivant);
	
		this.add(barreMois);
		
		//Mise en forme globale
		GridLayout g = new GridLayout(7,1);
		this.setLayout(g);
		
	}
	
	public void setEau(final double eau, final int territoire) {
    }

    public void setVegetal(final double veg, final int territoire) {
    }

    public void positionnerAnimaux(final int position, final int nombre) {
    }
    
    public void actionPerformed(ActionEvent evt) {
    	
    	if(evt.getSource()==stop) {
    		ctrl.arreter();
    	}
    	
    	if(evt.getSource()==pause) {
    		ctrl.pause();
    	}
    	
    	if(evt.getSource()==go) {
    		ctrl.lancer();
    	}
    	
    
	}
    


}
