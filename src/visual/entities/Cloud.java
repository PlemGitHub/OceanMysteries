package visual.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Cloud extends ObjectOnGameField{
	BufferedImage imgToLeft, imgToRight;

	public Cloud(Rectangle rect, BufferedImage img) {
		super(rect, img);
		imgToLeft = img;
		imgToRight = getReversedImageHorizontally();
	}

	public void setImageToRight(){
		replaceImage(imgToRight);
	}

	public void setImageToLeft(){
		replaceImage(imgToLeft);
	}
}
