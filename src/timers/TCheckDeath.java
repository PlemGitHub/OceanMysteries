package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import data.Mech;

@SuppressWarnings("serial")
public class TCheckDeath extends Timer implements ActionListener{
	
	private Mech mech;
	private int spearPosition;

	public TCheckDeath(int delay, ActionListener listener, Mech mech, int spearPosition) {
		super(delay, listener);
		this.mech = mech;
		this.spearPosition = spearPosition;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (mech.getCurrentPos() == spearPosition){
			mech.doDeathPart1();	
		}
		stop();
	}

}
