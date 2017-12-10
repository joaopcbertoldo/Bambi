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
    
    private boolean enMarche=false;
    
    
    public AnimationControleur(final ResultatSimulation resultatSimulation) {
    	
    	ctrlT = new ctrlThread();
    	ctrlT.start();
    	
    	resultats = resultatSimulation;
    	dureeSimulation=resultats.NbdePas();
    	
    	
    	System.out.println("duree de la simulation : " + dureeSimulation);
    	
    }
    

    public void creerIHM() {
    	
    	double maxEau=0;
    	double maxVeg=0;
    	double eau;
    	double veg;
    	for(int n=0; n<4; n++) {
    		for(int j=0; j<dureeSimulation; j++) {
    			eau=resultats.stockEau(n, j);
    			veg=resultats.stockVeg(n, j);
    			
    			if(maxEau<eau) {
    				maxEau=eau;
    			}
    			if(maxVeg<veg) {
    				maxVeg=veg;
    			}    			
    		}
    	}
    	
    	animationIHM = new AnimationIHM(this, maxEau, maxVeg);
    	animationIHM.setSize(500,900);
    	animationIHM.setVisible(true);
    }
    public void arreter() {
    	enMarche=false;
    	setMois(0);
    	
    }

    public void pause() {
    	enMarche=false;
    }

    public void lancer() {
    	
    	enMarche=true;
    }

    public void setMois(final int mois) {
    	moisActuel=mois;
    	animationIHM.actualiserSliderMois(moisActuel);
    	actualiserDonneesIHM();
    }

    public void setVitesse(final int vitesse) {
    	this.vitesse=vitesse;
    }

    public void moisSuivant() {
    	if(moisActuel<dureeSimulation-1) {
    		setMois(moisActuel+1);
        	
    	}
    	
    	else {
    		enMarche=false;
    	}

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
    	
    	public void setStop(boolean stop) {
    		this.stop = stop;
    	}
    	public void run() {
    			
    			while(stop == false) {
		   		 
        		//	System.out.println(enMarche);
        			while(enMarche) {
        	    		try {
        	    			System.out.println("vitesse : " + vitesse);
        					TimeUnit.MILLISECONDS.sleep(1/vitesse*1000);
        					System.out.print("attend 1/v");
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        	    		
        	    		System.out.println("syst fonctionne");
        	    		actualiserDonneesIHM();
        	    		moisSuivant();
        	    		System.out.println("mois actuel : " + moisActuel);
        	    		}// fin du while
        			try {
    					TimeUnit.SECONDS.sleep(1);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        	    		
        			}  //fin de run
    			
    			
    		
    		}
    }
    
    public static void main (String[] args) {
    	AnimationControleur ac = new AnimationControleur(null);
    	ac.creerIHM();
    	
    }
    
            

}
