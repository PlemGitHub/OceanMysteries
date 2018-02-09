package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

import visual.entities.Diver;

@SuppressWarnings("serial")
public class GrabGoldTimer extends Timer implements ActionListener, ImageResizer{
	private Diver player;
	private int t = 0;
	private BufferedImage imgStand, imgGrab;

	public GrabGoldTimer(int delay, ActionListener listener, Diver player, 
			Images images) {
		super(delay, listener);
		this.player = player;
		imgStand = images.getDiverFigures(7);
		imgGrab = images.getDiverGrabGold();
		actionPerformed(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (t) {
			case 0: {
				imgGrab = ImageResizer.resize(imgGrab, player);
				player.replaceImage(imgGrab);
				t++;
			}break;
			case 1:{
				imgStand = ImageResizer.resize(imgStand, player);
				player.replaceImage(imgStand);
				stop();
			}break;
		}
	}
}
