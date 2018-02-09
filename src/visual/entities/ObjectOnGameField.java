package visual.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ObjectOnGameField extends JPanel{
	
	private BufferedImage img;

	public ObjectOnGameField(Rectangle rect, BufferedImage img){
		setBounds(rect);
		setOpaque(false);
		this.img = img;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
	}
	
	public void replaceImage(BufferedImage img){
		this.img = img;
		repaint();
	}
	
	public BufferedImage getReversedImageHorizontally(){		
        AffineTransform at = AffineTransform.getScaleInstance(-1, 1);
        at.translate(-img.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage reversedImg = op.filter(img, null);
        return reversedImg;
	}
	
	public void ReverseImageHorizontally(){		
        BufferedImage reversedImg = getReversedImageHorizontally();
        replaceImage(reversedImg);
	}
}
