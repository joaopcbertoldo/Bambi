package main.animation;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class IhmUnTerritoire extends JPanel {
		
	private double eau;
	private double veg;
	
	private JLabel labLegendeEau;
	private JLabel labQEau;
	private JLabel labLegendeVeg;
	private JLabel labQVeg;
	
	private int popAnimale;
	
	public void setEauEtVegEtPop(final double eau, final double veg, final int popAnimale) {
		this.eau=eau;
		this.veg=veg;
		this.popAnimale=popAnimale;
		
		labQEau.setText(String.valueOf(eau));
		labQVeg.setText(String.valueOf(veg));

	}
	
	
	public IhmUnTerritoire() {
		
		labLegendeEau = new JLabel("Quantité eau : ");
		this.add(labLegendeEau);
		
		labQEau = new JLabel();
		this.add(labQEau);
		
		labLegendeVeg = new JLabel("Quantités végétaux : ");
		this.add(labLegendeVeg);
		
		labQVeg = new JLabel();
		this.add(labQVeg);		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		if(popAnimale!=0) {
			g.setColor(Color.red);
		}
		
		else g.setColor(Color.blue);
		
		//On trace le territoire
		g.fillRect(0, 0, 80, 80);
		
		// On trace les jauges 
		int epaisseur =  3;
		int largeur = 30;
		int longueur = 300;
		int xVeg = 50;
		int yVeg = 50;
		//Jauge de vegetaux
		g.setColor(Color.black);
		g.fillRect(xVeg-epaisseur,yVeg-epaisseur,longueur+2*epaisseur,2*epaisseur + largeur);
		g.setColor(Color.green);
		g.fillRect(xVeg, yVeg,100, largeur);
		
		g.setColor(Color.black);
	//	g.fillRect(x-epaisseur,y-epaisseur,longueur+2*epaisseur,2*epaisseur + largeur);
		g.setColor(Color.green);
	//	g.fillRect(x, y,100, largeur);
		
	}
}