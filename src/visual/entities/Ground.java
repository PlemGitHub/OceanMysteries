package visual.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import data.Constants;

@SuppressWarnings("serial")
public class Ground extends JPanel implements Constants{
	
	private int tileSize;
	private BufferedImage img;
	private BufferedImage surface;
	private BufferedImage ground;
	private BufferedImage groundCorner;
	private Point groundPoint, surfacePoint;

	public Ground(Rectangle groundRect, BufferedImage img) {
		setBounds(groundRect);
		setOpaque(false);
		this.img = img;
		
		tileSize = img.getHeight()/2;
		
		surface = img.getSubimage(0, 0, tileSize, tileSize);
		ground = img.getSubimage(tileSize, tileSize, tileSize, tileSize);
		groundCorner = img.getSubimage(0, tileSize, tileSize, tileSize);
		
		groundPoint = new Point(0, groundRect.x+tileSize*2);
		surfacePoint = new Point(img.getWidth(), img.getHeight());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
		int qHorizontaly = (WINDOW_WIDTH-tileSize*7)/tileSize+1;
		int qHorizontallyFull = WINDOW_WIDTH/tileSize+1;
		int qVertically = WINDOW_HEIGHT-tileSize*11;

		for (int i = 0; i < qHorizontallyFull; i++) {
			for (int j = 0; j < qVertically; j++) {
				g.drawImage(ground, groundPoint.x+i*tileSize, groundPoint.y+j*tileSize, null);
			}
		}
		
		for (int i = 0; i < qHorizontaly; i++) {
			g.drawImage(surface, surfacePoint.x+i*tileSize, surfacePoint.y, null);
			g.drawImage(ground, surfacePoint.x+i*tileSize, surfacePoint.y+tileSize, null);
		}
		g.drawImage(ground, 0, tileSize, null);
		g.drawImage(groundCorner, tileSize*6, tileSize*2, null);
	}
}
