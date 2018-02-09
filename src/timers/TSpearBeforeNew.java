package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class TSpearBeforeNew extends Timer implements ActionListener{
	
	private TSpearsController tSpearsController;
	private int[] timeFromMoveFinish = new int[4];
	private boolean[] isCreationNeeded = new boolean[4];
	private final int timeBeforeCreation = 5; // timeBeforeCreation*delay = time in ms before new Spear

	public TSpearBeforeNew(int delay, ActionListener listener, TSpearsController tSpearsController) {
		super(delay, listener);
		this.tSpearsController = tSpearsController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			timeFromMoveFinish[i]++;
			if (isCreationNeeded[i]
				&& timeFromMoveFinish[i] > timeBeforeCreation){
				tSpearsController.createNewSpearTimer(i);
				isCreationNeeded[i] = false;
			}
		}
	}
	
	public void clearTimeFromMoveFinish(int i){
		if (!isCreationNeeded[i])
			timeFromMoveFinish[i] = 0;
	}
	
	public void enableIsCreationNeeded(int i){
		isCreationNeeded[i] = true;
	}
}
