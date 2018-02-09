package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import data.Mech;
import visual.entities.ObjectOnGameField;

@SuppressWarnings("serial")
public class TSpearsController extends Timer implements ActionListener{
	
	private Mech mech;
	private TSpearBeforeNew tSpearBeforeNew;
	private TSpearMovement[] tAllSpears = new TSpearMovement[4];
	private ObjectOnGameField[][] spears;

	public TSpearsController(int delay, ActionListener listener, ObjectOnGameField[][] spears, Mech mech) {
		super(delay, listener);
		this.spears = spears;
		this.mech = mech;
		initializeAllSpearTimers();
		
		tSpearBeforeNew = new TSpearBeforeNew(100, null, this);
		tSpearBeforeNew.addActionListener(tSpearBeforeNew);
		tSpearBeforeNew.start();
	}

	private void initializeAllSpearTimers() {
		for (int i = 0; i < 4; i++) {
			createNewSpearTimer(i);
		}
	}
	
	public void stopAllSpears(){
		tSpearBeforeNew.stop();
		for (int i = 0; i < 4; i++) {
			tAllSpears[i].stop();
		}
	}
	
	public void startAllSpears(){
		tSpearBeforeNew.start();
		for (int i = 0; i < 4; i++) {
			tAllSpears[i].start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			if (!tAllSpears[i].isRunning()){
				tSpearBeforeNew.clearTimeFromMoveFinish(i);
				tSpearBeforeNew.enableIsCreationNeeded(i);
			}
		}
	}

	public void createNewSpearTimer(int i) {
		int k = (i==0)? (int)(Math.random()*10)%2 : i+1;
		TSpearMovement tSpearMovement = new TSpearMovement(0, null, spears[k], mech);
		tSpearMovement.setSpearPosition(k+3);
		tSpearMovement.addActionListener(tSpearMovement);
		tSpearMovement.start();
		tAllSpears[i] = tSpearMovement;
	}
}
