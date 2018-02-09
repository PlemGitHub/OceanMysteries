package visual;

import java.awt.Frame;
import javax.swing.JFrame;

import timers.TLoopMusicPlayer;

public class MainFrame {
		
	private JFrame fr;
	private MainPanel mp;
	private TLoopMusicPlayer tLoopMusicPlayer;
	
	public MainFrame() {
		mp = new MainPanel(this);
		
		fr = new JFrame("Ocean Mysteries");
		fr.setContentPane(mp);
		fr.setExtendedState(Frame.MAXIMIZED_BOTH);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMusicTimer();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainFrame scr = new MainFrame();
	}
	
	public void recreatMainPanel(){
		fr.remove(mp);
		mp = new MainPanel(this);
		fr.setContentPane(mp);		
	}
	
	public MainPanel getMainPanel(){
		return mp;
	}
	
	public void disableMoveActions(){
		mp.disableMoveAction();
	}
	
	public void enableMoveActions(){
		mp.enableMoveAction();
	}
	
	private void createMusicTimer() {
		if (tLoopMusicPlayer == null){
			tLoopMusicPlayer = new TLoopMusicPlayer(1000, null);
			tLoopMusicPlayer.addActionListener(tLoopMusicPlayer);
			tLoopMusicPlayer.start();
		}
	}
	
	public TLoopMusicPlayer getTLoopMusicPlayer(){
		return tLoopMusicPlayer;
	}
}
