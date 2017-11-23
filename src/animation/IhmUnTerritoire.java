package animation;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class IhmUnTerritoire extends JPanel {
	
	JProgressBar jaugeEau;
	int minEau;
	int maxEau;
	
	JProgressBar jaugeVeg;
	int minVeg;
	int maxVeg;
	
	private int eau;
	private int veg;
	
	public void setEauEtVeg(int eau, int veg) {
		this.eau=eau;
		this.veg=veg;
	}
	
	public IhmUnTerritoire() {
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		 g.setColor(Color.black);
		 g.fillRect(0, 0, 100, 100);
		 
		 g.setColor(Color.blue);
		 g.fillRect(200,0, 200,50);
		 
		 g.setColor(Color.green);
		 g.fillRect(200,100, 200,50);
		
	}
	
}
