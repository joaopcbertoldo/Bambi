package fichiers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import domain.ResultatSimulation;

public class GestionnaireFichierResultatSimulation {
    private static Resultat resultats;

    public static boolean chargerResultatSimulation(final String address) throws IOException {

    	InputStream resultatsSimu= new FileInputStream(address);
    	InputStreamReader lecteur = new InputStreamReader(resultatsSimu);
    	
    	BufferedReader lect = new BufferedReader(lecteur); 
    	int nmDePas= Integer.parseInt(lect.readLine());
    	for(int i = 0; i < nmDePas; i++){
    		Consumer<String> fun = getFunc(i);
    		fun.accept(lect.readLine());
    	}
    		return false; 
    }

    	
        private static Consumer<String> getFunc(int i){
            
            return (String line) ->  { List<String> l = Arrays.asList(line.split(",")); 
       				for (int r=1; r<6; r++) ((resultats.popAnimale).get(r)).add(Integer.parseInt(l.get(r)));  																																																																																																																																																																																																																																																																																																																																																													      	 
       				for (int u=6; u<11; u++) ((resultats.stockEau).get(u)).add(Double.parseDouble(l.get(u)));  																																																																																																																																																																																												
       				for (int m=11; m<16; m++) ((resultats.stockVeg).get(m)).add(Double.parseDouble(l.get(m)));} ; 
           			}
        
    public ResultatSimulation recupererResultatSimulation() {
        // TODO Auto-generated return
        return resultats;
    }

    public boolean sauvegarderResultatSimulation(final String address) {
        // TODO Auto-generated return
        return false;
    }

    public static void main (String[] args) throws IOException{
    	chargerResultatSimulation("C:\\Repository\\Bambi\\florentin.txt");
    	System.out.println(resultats.popAnimale); 
    }
}
