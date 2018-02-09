package data;

import java.io.InputStream;
import javazoom.jl.player.Player;

public class SoundsPlayer extends Thread{
	private String name;
	private InputStream fis;
	private Player playMP3;
	
	public SoundsPlayer(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		super.run();
		createPlayerAndStart();
	}
	
	private void createPlayerAndStart(){
		try {
			fis = this.getClass().getResourceAsStream("/assets/sounds/"+name+".mp3");
			playMP3 = new Player(fis);
				playMP3.play();
			fis.close();
	    } catch(Exception e) {/* NOP */}
	}
	
	public boolean getIsComplete(){
		if (playMP3 == null)
			return true;
		else
			return false;
	}
	
	public int pause(){
		int frame = playMP3.getPosition();
		playMP3.close();
		return frame;
	}
}
