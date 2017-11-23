package animation;

import java.util.concurrent.TimeUnit;

import core.Controleur;
import domain.ResultatSimulation;


public class AnimationControleur {
   
	private int vitesse;
    private int moisActuel;
    private AnimationIHM animationIHM;
    
    private boolean enMarche;
    
    
    public AnimationControleur(final ResultatSimulation resultatSimulation) {
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
    	run();
    }

    public void setMois(final int mois) {
    }

    public void setVitesse(final int vitesse) {
    	this.vitesse=vitesse;
    }

    public void moisSuivant() {
    	moisActuel++; //rajouter exception
    }

    public void moisPrecedent() {
    	if(moisActuel!=0) {
    		moisActuel--;
    	}
    }
    
    public void run() {
    	while(enMarche) {
    		try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("syst fonctionne");
    		
    	}
    }
    
    public static void main (String[] args) {
    	AnimationControleur ac = new AnimationControleur(null);
    	ac.creerIHM();
    }
        

}
