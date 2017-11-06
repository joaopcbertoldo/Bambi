package fichiers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import domain.ParametrageSimulation;

public class GestionnaireFichierParametrage {
    private Parametres parametres;

    public ParametrageSimulation recupererParametrageSimulation() {
        // TODO Auto-generated return
        return null;
    }

    public boolean chargerParametrageSimulationNonClimatique(final String address) throws IOException {
    	if (parametres!= null) {
    		parametres= new Parametres(); 
    		
    	}
    	InputStream paramNonClim= new FileInputStream(address);
    	InputStreamReader lect= new InputStreamReader(paramNonClim);
    	
    	BufferedReader lecteur = new BufferedReader(lect); 
    	String ligne;
    	for(int i = 0; i < 17; i++){
    		Consumer<String> fun = getFunc(i);
    		fun.accept(lecteur.readLine());
    	}
    	
		return false;
    }
    
    private Consumer<String> getFunc(int i){
    	switch (i){
    		case 1 : return (String line)-> parametres.nombreDePas()=Integer.parsInt(line);
    		case 0 : return (String line) -> (Arrays.asList(line.split("-"))).forEach(i-> parametres.plu) 
    	}
    	
    }

    public boolean chargerPluviometrie(final String address) {
        // TODO Auto-generated return
        return false;
    }

    public void setParametres(final Parametres value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.parametres = value;
    }

}
