package main.animation;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class IhmUnTerritoire extends JPanel {
		
	private double eau;
	private double veg;
	
	private JLabel labEau;
	private JLabel labVeg;
	
	private int popAnimale;
	
	public void setEauEtVegEtPop(final double eau, final double veg, final int popAnimale) {
		this.eau=eau;
		this.veg=veg;
		this.popAnimale=popAnimale;
		labEau.setText(String.valueOf(eau));
		labVeg.setText(String.valueOf(veg));
		
		repaint();
	}
	
	
	public IhmUnTerritoire() {
		labEau = new JLabel("labEau");
		labVeg = new JLabel("labVeg");
		
		this.add(labEau);
		this.add(labVeg);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		if(popAnimale!=0) {
			g.setColor(Color.red);
		}
		 g.setColor(Color.blue);
		 g.fillRect(0, 0, 100, 100);
		
	}
	
}
