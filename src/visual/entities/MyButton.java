package visual.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton{

	public MyButton(Rectangle rect, Action action, BufferedImage img) {
		setAction(action);
		setBounds(rect);
		setFocusable(false);
		setIcon(new ImageIcon(img));
	}
	
	public void setNewIcon(BufferedImage img){
		setIcon(new ImageIcon(img));
	}
}
