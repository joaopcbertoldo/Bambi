package animation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.List;
import java.util.ArrayList;

public class AnimationIHM extends JFrame implements ActionListener, ChangeListener {
    
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
		slideVitesse = new JSlider(1,100);
		barreVitesse = new JPanel();
		barreVitesse.add(stop);
		barreVitesse.add(pause);
		barreVitesse.add(go);
		barreVitesse.add(slideVitesse);
		
		stop.addActionListener(this);
		pause.addActionListener(this);
		go.addActionListener(this);
		slideVitesse.addChangeListener(this);
		
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
	
	public void setEauEtVegEtPop(final double eau, final double veg, final int popAnimale, final int territoire) {
		ihmTerritoires.get(territoire).setEauEtVegEtPop(eau, veg, popAnimale);	
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
	}
    
    public void stateChanged(ChangeEvent evt) {
    	Object source = evt.getSource();
    	
    	if(source==slideVitesse) {
			int v = ((JSlider) source).getValue();
			ctrl.setVitesse(v);
		}
    	
    }
    


}
