package visual.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import data.Constants;
import data.ImageResizer;

@SuppressWarnings("serial")
public class Diver extends ObjectOnGameField implements Constants, ImageResizer{
	
	public Diver(Rectangle rect, BufferedImage img, int i) {
		super(rect, img);
		setVisibleFalseOrTrue(i);
	}	
	
	private void setVisibleFalseOrTrue(int i) {
		switch (i) {
		case 0:
		case 1:
		case 2: 
			setVisible(true);
			break;
		default: setVisible(false);
			break;
		}
	}
}
