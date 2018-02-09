package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import data.Mech;

@SuppressWarnings("serial")
public class TDiverMove extends Timer implements ActionListener{
	private Mech mech;
	private String side;
	
	public TDiverMove(int delay, ActionListener listener, Mech mech, String side) {
		super(delay, listener);
		this.mech = mech;
		this.side = side;
		actionPerformed(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (side) {
			case "right": 
				if (mech != null)
					mech.moveRight();
				break;
			case "left":
				if (mech != null)
					mech.moveLeft();
				break;
		}
	}

}
