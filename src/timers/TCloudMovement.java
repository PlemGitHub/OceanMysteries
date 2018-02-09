package timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import data.Constants;
import visual.entities.Cloud;

@SuppressWarnings("serial")
public class TCloudMovement extends Timer implements ActionListener, Constants{
	private Cloud cloud;
	private int size;
	private int startX, y;
	private final int step = 2;
	private int dX;
	private Timer cloudTimer = this;

	public TCloudMovement(int delay, ActionListener listener, Cloud cloud) {
		super(delay, listener);
		this.cloud = cloud;
		size = cloud.getWidth();	
		
		createCloudMovementProperties();
	}
	
	private void createCloudMovementProperties(){
		setCloudLocation();
		randomizeDelay();
	}

	private void setCloudLocation() {
		defineStartSide();
		y = -size/2+2*(int)(Math.random()*size);
		cloud.setLocation(startX, y);
	}

		private void defineStartSide() {
			int a = (int)(Math.random()*2);
			switch (a) {
				case 0:{
					startX = -size;
					dX = step;
					cloud.setImageToRight();
					}break;
				case 1:{
					startX = WINDOW_WIDTH;
					dX = -step;
					cloud.setImageToLeft();
					}break;
			}
		}
	
	private void randomizeDelay() {
		int newDelay = 15+(int)(Math.random()*16);
		setDelay(newDelay);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cloud.setLocation(cloud.getX()+dX, y);
		if (cloud.getX() > WINDOW_WIDTH
			|| cloud.getX() < -size){
			stop();
			int t = 700+(int)(Math.random()*700);
			TimerBeforeNewCloud timerBeforeNewCloud = new TimerBeforeNewCloud(t, null);
			timerBeforeNewCloud.addActionListener(timerBeforeNewCloud);
			timerBeforeNewCloud.start();
		}
	}
	
	private class TimerBeforeNewCloud extends Timer implements ActionListener{

		public TimerBeforeNewCloud(int delay, ActionListener listener) {
			super(delay, listener);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			createCloudMovementProperties();
			cloudTimer.start();
			stop();
		}
		
	}
}
