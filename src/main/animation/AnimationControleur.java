package main.animation;

import java.util.concurrent.TimeUnit;

import main.core.Controleur;
import main.domain.ResultatSimulation;


public class AnimationControleur {
   
	private int vitesse=1;
    private int moisActuel=0;
    private int dureeSimulation;
    private AnimationIHM animationIHM;
    private ctrlThread ctrlT;
    private ResultatSimulation resultats;
    
    private boolean enMarche;
    
    
    public AnimationControleur(final ResultatSimulation resultatSimulation) {
    	ctrlT = new ctrlThread();
    	ctrlT.start();
    	
    	resultats = resultatSimulation;
    	dureeSimulation=resultats.NbdePas();
    	System.out.println("duree de la simulation : " + dureeSimulation);
    	
    }
    

    public void creerIHM() {
    	animationIHM = new AnimationIHM(this);
    	animationIHM.setSize(500,500);
    	animationIHM.setVisible(true);
    }
    public void arreter() {
    	enMarche=false;
    }

    public void pause() {
    	enMarche=false;
    }

    public void lancer() {
    	enMarche=true;
    }

    public void setMois(final int mois) {
    	moisActuel=mois;
    	actualiserDonneesIHM();
    }

    public void setVitesse(final int vitesse) {
    	this.vitesse=vitesse;
    }

    public void moisSuivant() {
    	if(moisActuel<dureeSimulation-1) moisActuel++;
    	animationIHM.actualiserSliderMois(moisActuel);
    	actualiserDonneesIHM();
    }

    public void moisPrecedent() {
    	if(moisActuel!=0) moisActuel--;
    	animationIHM.actualiserSliderMois(moisActuel);
    	actualiserDonneesIHM();
    }
    
    public int getDureeSimulation() {
    	return dureeSimulation;
    }
    
    public void actualiserDonneesIHM() {
    	for(int i=0; i<5;i++) {
    		animationIHM.setEauEtVegEtPop(
    				resultats.stockEau(i, moisActuel), 
    				resultats.stockVeg(i, moisActuel), 
    				resultats.popAnimale(moisActuel, i), 
    				i);    		
    	}
    	
    }
    
    class ctrlThread extends Thread {
    	boolean stop = false;
    	
    	public void run() {
    		
    		while(!stop) {    		 
    	    	
    			while(enMarche) {
    	    		try {
    					TimeUnit.SECONDS.sleep(1/vitesse);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    		
    	    		System.out.println("syst fonctionne");
    	    		actualiserDonneesIHM();
    	    		moisActuel++;
    	    		animationIHM.actualiserSliderMois(moisActuel);
    	    		}
    	    		
    			} // fin du while enMarche
    	    	
    	    	try {
    	    		//pour pas que le thread tourne tout le temps alors qu'il n'a rien à faire
			    	TimeUnit.SECONDS.sleep(1);
		    	} catch (InterruptedException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			   }
    	   }
    	  		
    }
   
    
    public static void main (String[] args) {
    	AnimationControleur ac = new AnimationControleur(null);
    	ac.creerIHM();
    	
    }
    
            

}
