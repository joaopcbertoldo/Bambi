package main.fichiers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import main.domain.MoisEnum;
import main.domain.ParametrageSimulation;

public class GestionnaireFichierParametrage {
    private Parametres parametres;
    
 
    public  ParametrageSimulation recupererParametrageSimulation() throws Exception {
    	if (parametres== null){
    		throw new Exception ("Chargement incomplet");
    	}
    	if (parametres.pluviometrie== null){
    	
    		throw new Exception("Pluviometrie non charge") ;
    	}
    	if (parametres.surfaceTerritoire==null){
    		System.out.println(parametres.surfaceTerritoire);
    		throw new Exception("Parametres non climatiques non charges"); 
    	}
        return parametres ;
    }

    public boolean chargerParametrageSimulationNonClimatique(final String address) throws Exception {
    	if (parametres == null) {
    		parametres= new Parametres(); 
    	}
    	
    	try {
    		InputStream paramNonClim= new FileInputStream(address);
    		InputStreamReader lect= new InputStreamReader(paramNonClim);
    		BufferedReader lecteur = new BufferedReader(lect); 
 
	    	for(int i = 0; i < 17; i++){
	    		Consumer<String> fun = getFunc(i);
	    		fun.accept(lecteur.readLine());
	    	}
    
	    	lecteur.close();
	    	lect.close();
	    	paramNonClim.close(); 
			return true;
    	}
    	catch(Exception e){
    		System.out.println("Erreur");
    	}
	
		return false;
    }
    
    private Consumer<String> getFunc(int i){
    	switch (i){
    		case 0 : return (String line) -> parametres.nombreDePas=Integer.parseInt(line); 
    		case 1 : return (String line) ->  { List<String> l = Arrays.asList(line.split(",")); parametres.surfaceTerritoire= new ArrayList<>(); 
    											l.forEach(j ->  parametres.surfaceTerritoire.add(Double.parseDouble(j))); };      	 
    		case 2 : return (String line)-> {parametres.mois0 = MoisEnum.valueOf(line);} ; 
    		case 3 : return (String line)-> parametres.nombreIndividusAnimale0= Integer.parseInt(line);
    		case 4 : return (String line)-> {parametres.stockVegetaux= new ArrayList<>(); Arrays.asList(line.split(",")).forEach(j -> parametres.stockVegetaux.add(Double.parseDouble(j)));} ; 
    		case 5 : return (String line) -> parametres.localisationInitiale= Integer.parseInt(line);
    		case 6 : return (String line) -> {parametres.stockEau= new ArrayList<>(); Arrays.asList(line.split(",")).forEach(j-> parametres.stockEau.add(Double.parseDouble(j))) ; } ; 
    		case 7 : return (String line) ->{parametres.tauxPerteEauEvaporation= new ArrayList<>(); Arrays.asList(line.split(",")).forEach(j -> parametres.tauxPerteEauEvaporation.add(Double.parseDouble(j)));}; 
    		case 8 : return (String line) -> parametres.stockVegetauxMinimal= Double.parseDouble(line);
    		case 9 : return (String line) -> parametres.tauxNaissanceAnimalMaximal= Double.parseDouble(line);
    		case 10: return (String line) -> parametres.tauxMortalitePredateur= Double.parseDouble(line);
    		case 11: return (String line) -> parametres.tauxMortaliteParPenurieAlimentaireMaximal= Double.parseDouble(line); 
    		case 12 : return (String line) -> parametres.besoinEauVegetal= Double.parseDouble(line);
    		case 13: return (String line) -> parametres.besoinEauAnimal= Double.parseDouble(line); 
    		case 14: return (String line) -> parametres.besoinVegetalAnimal= Double.parseDouble(line); 
    		case 15: return (String line) -> parametres.tauxCroissanteVegetal= Double.parseDouble(line); 
    		case 16: return (String line) -> parametres.tauxPerteVegetalPenurieMax= Double.parseDouble(line);
    		default : return (String line)-> System.out.println("Erreur");
    		}    	
    		
    	}
    

    public boolean chargerPluviometrie(final String address) throws IOException {
    	if (parametres== null) {
    		parametres= new Parametres(); 
    	}
    	
    	//try{
    		parametres.pluviometrie= new  ArrayList(); 
    		for (int g=0; g<5; g++){
    			parametres.pluviometrie.add(new ArrayList<Double>());
    			
    		}
	    	InputStream pluviometrie= new FileInputStream(address);
	    	InputStreamReader lecteur= new InputStreamReader(pluviometrie);
	    	BufferedReader lecteur1 = new BufferedReader(lecteur); 
	    	for(int i = 0; i < 5; i++){
	    		Consumer<String> fun = getFunc2(i);
	    		fun.accept(lecteur1.readLine());
	    	}
	    	lecteur.close();
	    	lecteur1.close();
	    	pluviometrie.close(); 
	    	
			return false;
	  /*  }
	    catch(Exception e){
			System.out.println("Erreur");
			return false;
		}*/
    }
    
    private Consumer<String> getFunc2(int i){
    
     return (String line) ->  { 
    	 List<String> l = Arrays.asList(line.split(",")); 
    	 l.forEach(j ->  parametres.pluviometrie.get(i).add(Double.parseDouble(j))); };      	 
    }
      

   

    public static void main (String[] args) throws Exception{
    	GestionnaireFichierParametrage g = new GestionnaireFichierParametrage();
    	g.chargerPluviometrie("C:\\Repository\\Bambi\\joao.txt");
    	System.out.println(g.parametres.pluviometrie);
    }
}
