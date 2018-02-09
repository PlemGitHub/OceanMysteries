package visual.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import data.Images;

@SuppressWarnings("serial")
public class ScorePicture extends ObjectOnGameField{
	
	private Images images;
	private BufferedImage img;
	private BufferedImage groundImage;
	private int score = 0;
	private DecimalFormat myFormatter = new DecimalFormat("0000");
	private String scoreString;
	private int tileS;

	public ScorePicture(Rectangle rect, BufferedImage img, Images images) {
		super(rect, img);
		tileS = img.getWidth();
		this.images = images;
		this.img = new BufferedImage(tileS*4, tileS, BufferedImage.TYPE_INT_ARGB);
		scoreString = myFormatter.format(score);
		defineScoreImage();
	}
	
	private void defineScoreImage(){
		groundImage = images.getGround().getSubimage(tileS, tileS, tileS, tileS);
		Graphics g2 = img.getGraphics();
		for (int i = 0; i < 4; i++) {
			int a = Character.getNumericValue(scoreString.charAt(i));
			BufferedImage tempImg = images.getDigits(a);
			g2.drawImage(groundImage, tileS*i, 0, null);
			g2.drawImage(tempImg, tileS*i, 0, null);
		}
		g2.dispose();
		replaceImage(img);
	}
	
	public void increaseScore(int inc){
		score += inc;
		scoreString = myFormatter.format(score);
		defineScoreImage();
	}	
}
