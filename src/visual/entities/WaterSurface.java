package visual.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import data.Constants;

@SuppressWarnings("serial")
public class WaterSurface extends JPanel implements Constants{
	
	private BufferedImage img;
	/**
	 * How much water surface tiles in WINDOW_WIDTH.
	 */
	private int qHorizontally;
	private int tileSize;
	
	public WaterSurface(Rectangle waterSurfaceRect, BufferedImage img) {
		setBounds(waterSurfaceRect);
		setOpaque(false);
		this.img = img;
		
		tileSize = waterSurfaceRect.height;
		qHorizontally = WINDOW_WIDTH/tileSize+2;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < qHorizontally; i++) {
			g.drawImage(img, tileSize*i, 0, null);		
		}
	}
}
