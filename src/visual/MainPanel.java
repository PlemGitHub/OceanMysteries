package visual;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import data.Constants;
import data.Images;
import data.Mech;
import timers.TDiverMove;
import visual.entities.Ground;
import visual.entities.MyButton;
import visual.entities.Cloud;
import visual.entities.Diver;
import visual.entities.ScorePicture;
import visual.entities.ObjectOnGameField;
import visual.entities.WaterItself;
import visual.entities.WaterSurface;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Constants{
	
	private MainFrame mainFrame;
	private MainPanel mainPanel = this;
	private Mech mech;
	private Images images;
	private final String EXIT = "exit";
	private final String START_MOVE_RIGHT = "start move right";
	private final String START_MOVE_LEFT = "start move left";
	private final String STOP_MOVE = "stop move";
	private TDiverMove tDiverMove = new TDiverMove(0, null, null, "");
	private MyButton musicBtn;
	private MyButton soundsBtn;
	
	public MainPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(null);
		setBackground(BACKGROUND_COLOR);
		images = new Images();
		mech = new Mech(mainFrame, images);
		addButtons();
		addSpears();
		addDiverUnderWater();
		addLadder();
		addAnchor();
		addWaterSurface();
		addBoat();
		addDiverOnBoat();
		addClouds();
		addChest();
		addScorePicture();
		addGround();
		addStones();
		addFlowers();
		addCannons();
		addWaterItself();
		
		
		setKeyBindings();
	}

	private void addButtons() {
		soundsBtn = new MyButton(mech.getRectangles().getSoundsBtn(), switchSoundsAction, images.getSound(1));
		musicBtn = new MyButton(mech.getRectangles().getMusicBtn(), switchMusicAction, images.getMusic()[1]);

		add(soundsBtn);
		add(musicBtn);
	}

	private void addLadder() {
		ObjectOnGameField ladder = new ObjectOnGameField(mech.getRectangles().getLadder(), images.getLadder());
		add(ladder);
	}

	private void addAnchor() {
		ObjectOnGameField anchor = new ObjectOnGameField(mech.getRectangles().getAnchor(), images.getAnchor());
		add(anchor);
	}

	private void addDiverOnBoat() {
		for (int i = 0; i < 3; i++) {
			Diver diver = new Diver(mech.getRectangles().getDivers(i), images.getDiverFigures(i), i);
			mech.setDiverToPosToList(i, diver);
			add(diver);
		}
	}

	private void addClouds() {
		for (int i = 0; i < Q_CLOUDS; i++) {
			Cloud cloud = new Cloud(mech.getRectangles().getCloud(), images.getCloud());
			mech.setCloud(cloud, i);
			add(cloud);
		}
		mech.createCloudTimers();
	}
	
	private void addDiverUnderWater() {
		for (int i = 3; i < 8; i++) {
			Diver diver = new Diver(mech.getRectangles().getDivers(i), images.getDiverFigures(i), i);
			mech.setDiverToList(diver);
			add(diver);
		}
	}

	private void addGround() {
		Ground ground = new Ground(mech.getRectangles().getGround(), images.getGround());
		add(ground);
	}

	private void addStones() {
		for (int i = 0; i < 3; i++) {
			ObjectOnGameField stone = new ObjectOnGameField(mech.getRectangles().getStones(i), images.getStones(i));
			add(stone);
		}
	}

	private void addFlowers() {
		for (int i = 0; i < 3; i++) {
			ObjectOnGameField flower = new ObjectOnGameField(mech.getRectangles().getFlowers(i), images.getFlowers(i));
			add(flower);
		}
	}

	private void addCannons() {
		for (int i = 0; i < 5; i++) {
			ObjectOnGameField cannon = new ObjectOnGameField(mech.getRectangles().getCannons(i), images.getCannons(i));
			add(cannon);
		}
	}

	private void addSpears() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (mech.getRectangles().getSpears(i, j) != null){
					ObjectOnGameField spear = new ObjectOnGameField(mech.getRectangles().getSpears(i, j), images.getSpears(i, j));
					add(spear);
					spear.setVisible(false);
					mech.setSpear(spear, i, j);
				}			
			}
		}
	}

	private void addWaterItself() {
		WaterItself waterItself = new WaterItself(mech.getRectangles().getWatereItself(), images.getWaterItself());
		add(waterItself);
	}

	private void addBoat() {
		ObjectOnGameField boat = new ObjectOnGameField(mech.getRectangles().getBoat(), images.getBoat());
		add(boat);
	}
	
	private void addChest() {
		ObjectOnGameField chest = new ObjectOnGameField(mech.getRectangles().getChest(), images.getChest());
		add(chest);
		mech.setChest(chest);
	}

	private void addScorePicture() {
			ScorePicture scorePicture
			= new ScorePicture(mech.getRectangles().getScorePicture(), images.getDigits(0), images);
			add(scorePicture);
			mech.setScorePicture(scorePicture);
	}

	private void addWaterSurface() {
		WaterSurface waterSurface = new WaterSurface(mech.getRectangles().getWaterSurface(), images.getWaterSurface());
		add(waterSurface);
	}

	private void setKeyBindings() {
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), START_MOVE_RIGHT);
		getActionMap().put(START_MOVE_RIGHT, startMoveRight);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), STOP_MOVE);
		getActionMap().put(STOP_MOVE, stopMove);
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), START_MOVE_LEFT);
		getActionMap().put(START_MOVE_LEFT, startMoveLeft);
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), STOP_MOVE);
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), EXIT);
		getActionMap().put(EXIT, doExit );
	}

	private Action startMoveRight = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!tDiverMove.isRunning()){
				tDiverMove = new TDiverMove(700, null, mech, "right");
				tDiverMove.addActionListener(tDiverMove);
				tDiverMove.start();
			}
		}
	};;
	
	private Action startMoveLeft = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!tDiverMove.isRunning()){
				tDiverMove = new TDiverMove(700, null, mech, "left");
				tDiverMove.addActionListener(tDiverMove);
				tDiverMove.start();
			}
		}
	};
	
	private Action stopMove = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			tDiverMove.stop();
		}
	};
	
	private Action doExit = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			mech.stopDeathAnimationTimer();
			mech.getTSpearsController().stop();
			mech.getTSpearsController().stopAllSpears();
			mech.stopCloudTimers();
			int a = JOptionPane.showConfirmDialog(mainPanel, "Quit?", "Warning", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				System.exit(0);
			}else{
				mech.startDeathAnimationAndTSpearsControllerTimers();
			}
		}
	};
	
	private Action switchSoundsAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			mech.switchIfSoundsEnabled();
		}
	};
	
	private Action switchMusicAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainFrame.getTLoopMusicPlayer().switchMusic(musicBtn, images.getMusic());
		}
	};
	
	public void disableMoveAction(){
		startMoveRight.setEnabled(false);
	}
	
	public void enableMoveAction(){
		startMoveRight.setEnabled(true);
	}
	
	public MyButton getSoundButton(){
		return soundsBtn;
	}
}
