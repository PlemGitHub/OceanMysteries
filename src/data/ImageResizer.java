package data;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public interface ImageResizer {
	
	/**
	 * Resizes image to panel's width and height.
	 * @param img source image.
	 * @param panel panel with new sizes.
	 * @return resized image.
	 */
	public static BufferedImage resize(BufferedImage img, JPanel panel){
		Image tmp = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);		
		img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return img;
	}
	
	/**
	 * Resizes image to new width and height.
	 * @param img source image.
	 * @param width new width.
	 * @param height new height.
	 * @return resized image.
	 */
	public static BufferedImage resize(BufferedImage img, int width, int height){
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return img;
	}
}