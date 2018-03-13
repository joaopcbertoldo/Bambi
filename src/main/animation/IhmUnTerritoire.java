package main.animation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.*;
import java.awt.Font;
import java.awt.FontMetrics;

public class IhmUnTerritoire extends JPanel {
		
	private double eau;
	private double veg;
	private int popAnimale;
	
	private double maxEau;
	private double maxVeg;
	
	//attributs pour les dimensions des jauges
	int epaisseur =  3;
	int largeur = 25;
	int longueurMax = 300;
	int xVeg = 120;
	int yVeg = 50;
	int xEau = 120;
	int yEau = 100;
	
	
	public IhmUnTerritoire(double maxEau, double maxVeg) {
		this.setLayout(null);
		this.maxEau = maxEau;
		this.maxVeg = maxVeg;	
		
	}
	
	public void setEauEtVegEtPop(final double eau, final double veg, final int popAnimale) {
		this.eau=eau;
		this.veg=veg;
		this.popAnimale=popAnimale;
		
		repaint();

	}
	
	//M�thode de dessin appel�e � chaque actualisation des valeurs
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		if(popAnimale!=0) {
			g.setColor(Color.red);
		}
		
		else g.setColor(Color.blue);
		
		//On trace le territoire
		g.fillRect(20, yVeg-epaisseur, 80, (yEau-yVeg) + largeur);
		
		// On calcule la longueur des jauges
		int longueurEau = (int) ((eau/maxEau)*longueurMax);
		int longueurVeg = (int) ((veg/maxVeg)*longueurMax);
		
		//On dessine les jauges de v�g�taux
		g.setColor(Color.black);
		g.fillRect(xVeg-epaisseur,yVeg-epaisseur,longueurMax+2*epaisseur,2*epaisseur + largeur);
		g.setColor(Color.green);
		g.fillRect(xVeg, yVeg,longueurVeg, largeur);		
		g.setColor(Color.black);
		g.fillRect(xEau-epaisseur,yEau-epaisseur,longueurMax+2*epaisseur,2*epaisseur + largeur);
		g.setColor(Color.blue);
		g.fillRect(xEau, yEau,longueurEau, largeur);
		
		//On dessine le texte qui indique les quantit�s
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
		g.drawString(String.valueOf(popAnimale), 60, 90);
		g.setColor(Color.white);
		
		//On affiche dans les barres la quantit� num�rique correspondante
		double eauArrondi = Math.floor(eau);
		double vegArrondi = Math.floor(veg);
		g.drawString(String.valueOf(eauArrondi),xVeg + longueurMax-120, yEau+15);
		g.drawString(String.valueOf(vegArrondi),xVeg + longueurMax-120, yVeg+15);
		
	}
}