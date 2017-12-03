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
		 g.setColor(Color.blue);
		 g.fillRect(0, 0, 80, 80);
		
	}
	
}
