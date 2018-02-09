package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import data.PausablePlayer;
import javazoom.jl.decoder.JavaLayerException;
import visual.entities.MyButton;

@SuppressWarnings("serial")
public class TLoopMusicPlayer extends Timer implements ActionListener{
	
	private PausablePlayer musicPlayer;
	private boolean ifPaused = false;
	private boolean alreadyPlaying;

	public TLoopMusicPlayer(int delay, ActionListener listener) {
		super(delay, listener);
		createMusicPlayer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (musicPlayer != null
			&& musicPlayer.getIsMusicFinished())
			createMusicPlayer();
	}

	private void createMusicPlayer() {
		if (!alreadyPlaying)
			try {
				musicPlayer = new PausablePlayer();
				musicPlayer.play();
				alreadyPlaying = true;
			} catch (JavaLayerException e) {
				e.printStackTrace();
		}
	}
	
	public void switchMusic(MyButton button, BufferedImage[] img){
		if (ifPaused){
			try {
				musicPlayer.play();
				button.setNewIcon(img[1]);
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
			ifPaused = false;
		}else{
			musicPlayer.pause();
			button.setNewIcon(img[0]);
			ifPaused = true;
		}
	}
}
