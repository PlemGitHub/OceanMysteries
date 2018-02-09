package visual.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import data.Constants;

@SuppressWarnings("serial")
public class WaterItself extends JPanel implements Constants{
	
	private BufferedImage img;
	/**
	 * How much water tiles in WINDOW_WIDTH.
	 */
	private int qHorizontally;
	/**
	 * How much water tiles in WINDOW_HEIGHT.
	 */
	private int qVertically;
	private int tileSize;
	
	public WaterItself(Rectangle waterItselfRect, BufferedImage img) {
		setBounds(waterItselfRect);
		this.img = img;
		
		tileSize = (WINDOW_HEIGHT-waterItselfRect.height)/3;
		qHorizontally = WINDOW_WIDTH/tileSize+2;
		qVertically = WINDOW_HEIGHT/tileSize+1;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < qHorizontally; i++) {
			for (int j = 0; j < qVertically; j++) {
				g.drawImage(img, tileSize*i, tileSize*j, null);	
			}	
		}
	}
}
