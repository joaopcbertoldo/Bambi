package animation;

import java.util.concurrent.TimeUnit;

import core.Controleur;
import domain.ResultatSimulation;


public class AnimationControleur {
   
	private int vitesse=1;
    private int moisActuel;
    private int dureeSimulation;
    private AnimationIHM animationIHM;
    private ctrlThread ctrlT;
    private ResultatSimulation resultats;
    
    private boolean enMarche;
    
    
    public AnimationControleur(final ResultatSimulation resultatSimulation) {
    	ctrlT = new ctrlThread();
    	ctrlT.start();
    	
    	resultats = resultatSimulation;
    	//dureeSimulation = resultats.getDureeSim();
    	dureeSimulation=20;
    	
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
    }

    public void setVitesse(final int vitesse) {
    	this.vitesse=vitesse;
    }

    public void moisSuivant() {
    	if(moisActuel<dureeSimulation) moisActuel++;
    }

    public void moisPrecedent() {
    	if(moisActuel!=0) {
    		moisActuel--;
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
    	    		
    	    		for(int i=0; i<4; i++) {
    	    			double eau = resultats.stockEau(i, moisActuel);
    	    			double veg = resultats.stockVeg(i, moisActuel);
    	    			int popAnimale = resultats.popAnimale(moisActuel, i);
    	    			animationIHM.setEauEtVegEtPop(eau, veg, popAnimale, i);
    	    			
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
    }
   
    
    public static void main (String[] args) {
    	AnimationControleur ac = new AnimationControleur(null);
    	ac.creerIHM();
    	
    }
    
            

}
