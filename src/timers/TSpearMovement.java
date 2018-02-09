package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import data.Mech;
import visual.entities.ObjectOnGameField;

@SuppressWarnings("serial")
public class TSpearMovement extends Timer implements ActionListener{
	
	private Mech mech;
	private TCheckDeath tCheckDeath;
	private ObjectOnGameField[] spear;
	private boolean showOrHide = true;
	private int maxIndex;
	private int currentIndex = 0;
	private int dIndex = 1;
	private int spearPosition;
	
	public TSpearMovement(int delay, ActionListener listener, ObjectOnGameField[] spear, Mech mech) {
		super(delay, listener);
		this.spear = spear;
		this.mech = mech;
		setDelay(randomizeDelay());
	}

	private void defineMaxIndex() {
		maxIndex = -1;
		for (ObjectOnGameField segment : spear) {
			if (segment == null)
				return;
			else
				maxIndex++;
		}
	}

	private int randomizeDelay() {
		int newDelay = 700+(int)(Math.random()*700);
		return newDelay;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		defineMaxIndex();
		if (currentIndex > maxIndex){
			currentIndex = maxIndex;
			dIndex = -1;
			showOrHide = false;
		}
		
		if (currentIndex >= 0)
			spear[currentIndex].setVisible(showOrHide);
		currentIndex += dIndex;		
		if (currentIndex == maxIndex+1)
			startTimerToCheckDeath();
		
		if (currentIndex < 0){
			stop();
		}
	}
	
	public boolean getIsLastSectionVisible(){
		return spear[maxIndex-1].isVisible();
	}
	
	private void startTimerToCheckDeath(){
		tCheckDeath = new TCheckDeath(500, null, mech, spearPosition);
		tCheckDeath.addActionListener(tCheckDeath);
		tCheckDeath.start();
	}
	
	public void setSpearPosition(int position){
		spearPosition = position;
	}
}
