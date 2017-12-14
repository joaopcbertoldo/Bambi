package main.fichiers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import main.domain.ResultatSimulation;

public class GestionnaireFichierResultatSimulation {
    private static Resultat resultats; // Le gestionnaire a pour un atribut un objet de type Resultat dont les méthodes permettent d'avoir accès aux résultats de simulation pour la visualisation

    
    
    public  boolean chargerResultatSimulation(final String address) throws Exception {
    	// permet de créer un objet de type résultat à partir d'un fichier txt de résultats pour lancer une visualisation à partir de résultats précedement enregistrés
    	// lit le fichier txt et met dans les attributs de resultats les données correspondantes
    	resultats= new Resultat();
    	try{
    	InputStream resultatsSimu= new FileInputStream(address);
    	InputStreamReader lecteur = new InputStreamReader(resultatsSimu);
    	
    	BufferedReader lect = new BufferedReader(lecteur); 
    	int nmDePas= Integer.parseInt(lect.readLine());
    	resultats.NbdePas= nmDePas; 
    	for(int i = 0; i < nmDePas; i++){
    		Consumer<String> fun = getFunc(i);
    		fun.accept(lect.readLine());
    	}
    		resultatsSimu.close();
    		lecteur.close();
    		lect.close(); 
    		return false; 
    }
    	catch(Exception e){
    		System.out.println("Erreur");
    		return false;
    	}
    }

    	
        private static Consumer<String> getFunc(int i){
            
            return (String line) ->  { List<String> l = Arrays.asList(line.split(","));    
            		for (int r=1; r<6; r++) ((resultats.popAnimale).get(r-1)).add(Integer.parseInt(l.get(r)));  																																																																																																																																																																																																																																																																																																																																																													      	 
       				for (int u=6; u<11; u++) ((resultats.stockEau).get(u-6)).add(Double.parseDouble(l.get(u)));  																																																																																																																																																																																												
       				for (int m=11; m<16; m++) ((resultats.stockVeg).get(m-11)).add(Double.parseDouble(l.get(m)));} ; 
            	 
           			}
        
    public ResultatSimulation recupererResultatSimulation() {
        // Retourne l'objet de résultat
        return resultats;
    }

    public  boolean sauvegarderResultatSimulation(ResultatSimulation r, final String address) throws IOException {
    //écrit les résultats de simulation dans un fichier texte afin de les sauvegarder 
    	try{
        	FileOutputStream aut = new FileOutputStream(new File(address));
        	OutputStreamWriter auteur = new OutputStreamWriter(aut);
        
        	
        	BufferedWriter auto = new BufferedWriter(auteur); 
        	
        	auto.write(Integer.toString(r.NbdePas()));
        	auto.newLine();
      
        	for(int i = 0; i < r.NbdePas(); i++){
        	auto.write(Integer.toString(i+1));
        	auto.write(",");
        		for (int s=0; s<5; s++){
        			auto.write(Integer.toString(r.popAnimale(i,s)));
        			auto.write(",");
        		}
        		for (int k=0; k<5;k++){
        			auto.write(Double.toString(r.stockEau(k,i)));
        			auto.write(",");
        		}
        		for (int l=0; l<5;l++){
        			auto.write(Double.toString(r.stockVeg(l, i)));
        			auto.write(",");
        		}
        		auto.newLine();
        	}
        		
        	auto.close();
        	auteur.close(); 
        	aut.close();
        	return true; 
        }
		catch (Exception e) {
			System.out.println("Erreur");
			return false;
		}
	}

   
    

   
}
