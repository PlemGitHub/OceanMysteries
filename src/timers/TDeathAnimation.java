package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import data.Images;
import data.Mech;
import visual.entities.Diver;

@SuppressWarnings("serial")
public class TDeathAnimation extends Timer implements ActionListener{
	private Mech mech;
	private Diver diver;
	private Images images;
	private int q;

	public TDeathAnimation(int delay, ActionListener listener, Mech mech) {
		super(delay, listener);
		this.diver = mech.getDiverOnDeathPos();
		this.images = mech.getImages();
		this.mech = mech;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (q % 2 == 0) {
			diver.replaceImage(images.getDeadDiver(1));
		}else{
			diver.replaceImage(images.getDeadDiver(0));
		}
		
		q++;
		if (q == 8) {
			diver.replaceImage(images.getDiverFigures(mech.getDeathPos()));
			mech.doDeathPart2();
			mech.getMainFrame().enableMoveActions();
			stop();
		}
	}

}
