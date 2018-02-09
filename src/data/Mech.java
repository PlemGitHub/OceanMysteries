package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import timers.TCloudMovement;
import timers.TDeathAnimation;
import timers.TSpearsController;
import visual.MainFrame;
import visual.entities.Cloud;
import visual.entities.Diver;
import visual.entities.ScorePicture;
import visual.entities.ObjectOnGameField;

public class Mech implements Constants{
	
	private MainFrame mainFrame;
	private ScorePicture scorePicture;
	private Rectangles rectangles;
	private Images images;
	private int coordsK;
	private int tileSize;
	private int lifes = 3;
	private ArrayList<Diver> diverList = new ArrayList<>();
	private ObjectOnGameField[][] spears = new ObjectOnGameField[5][5];
	private Cloud[] clouds = new Cloud[Q_CLOUDS];
	private TCloudMovement[] tClouds = new TCloudMovement[Q_CLOUDS];
	private int currentPos = FIRST_DIVER_INDEX;
	private int deathPos;
	private GrabGoldTimer grabGoldTimer;
	private boolean isChestOpenedOnce;
	private TDeathAnimation tDeathAnimation;
	private TSpearsController tSpearsController;
	private ObjectOnGameField chest;
	private boolean isChestEmpty;
	private int goldsInPocket;
	private boolean ifSoundsEnabled = true;
		
	public Mech(MainFrame mainFrame, Images images) {
		this.mainFrame = mainFrame;
		this.images = images;
		defineCoordsVariables();
		rectangles = new Rectangles(tileSize);
		createSpearsControllerTimer();
	}

	private void defineCoordsVariables() {
		do {
			coordsK++;
		} while ((coordsK+GK)*ASSET_SIZE < WINDOW_HEIGHT/10);
		tileSize = ASSET_SIZE*coordsK;
	}

	private void createSpearsControllerTimer() {
	
		tSpearsController = new TSpearsController(50, null, spears, this);
		tSpearsController.addActionListener(tSpearsController);
		tSpearsController.start();
	}

	public void createCloudTimers() {
		for (int i = 0; i < Q_CLOUDS; i++) {
			int t = (int)(Math.random()*10000);
			tClouds[i] = new TCloudMovement(t, null, clouds[i]);
			tClouds[i].addActionListener(tClouds[i]);
			tClouds[i].start();
		}
	}
	
	public Rectangles getRectangles(){
		return rectangles;
	}

	public void setDiverToList(Diver diver){
		diverList.add(diver);
	}
		public void setDiverToPosToList(int i, Diver diver){
			diverList.add(i, diver);
		}
		
	public void setSpear(ObjectOnGameField spear, int i, int j){
		spears[i][j] = spear;
	}
	
	public ObjectOnGameField[] getSpear(int i){
		return spears[i];
	}
	
	public int getCurrentPos(){
		return currentPos;
	}
	
	public int getDeathPos(){
		return deathPos;
	}

	public void moveRight() {
		if (currentPos == 7
			&& (grabGoldTimer == null || !grabGoldTimer.isRunning())
			&& !isChestEmpty){
				createMusicPlayer("collect");
			grabGoldTimer = new GrabGoldTimer(350, null, diverList.get(currentPos), images);
			grabGoldTimer.addActionListener(grabGoldTimer);
			grabGoldTimer.start();
			scorePicture.increaseScore(1);
			goldsInPocket++;
			isChestOpenedOnce = true;
			checkIfChestIsEmpty();
		}
		
		if (currentPos < 7){
			diverList.get(currentPos).setVisible(false);
			diverList.get(currentPos+1).setVisible(true);
			currentPos++;
			checkInstantDeath();
			createMusicPlayer("tap");
		}
	}

	public void moveLeft() {
		if (currentPos > 2
			&& (grabGoldTimer == null || !grabGoldTimer.isRunning())){
			diverList.get(currentPos).setVisible(false);
			diverList.get(currentPos-1).setVisible(true);
			currentPos--;
			createMusicPlayer("tap");
			if (currentPos > 2)
				checkInstantDeath();
		}
		
		if (currentPos == 2
			&& isChestOpenedOnce){
			createMusicPlayer("drop");
			drawChestFull();
			scorePicture.increaseScore(3);
			isChestOpenedOnce = false;
		}
	}

	private void checkIfChestIsEmpty() {
		if (goldsInPocket == 10){
			drawChestEmpty();
		}
	}
	
	private void drawChestEmpty() {
		isChestEmpty = true;
		chest.replaceImage(images.getChestEmpty());
	}
	
	private void drawChestFull() {
		isChestEmpty = false;
		goldsInPocket = 0;
		chest.replaceImage(images.getChest());
	}

	private void checkInstantDeath() {
		int maxIndex = defineMaxIndex(spears[currentPos-3]);
		if (spears[currentPos-3][maxIndex].isVisible())
			doDeathPart1();
	}

	private int defineMaxIndex(ObjectOnGameField[] spear) {
		int maxIndex = -1;
		for (ObjectOnGameField segment : spear) {
			if (segment == null)
				return maxIndex;
			else
				maxIndex++;
		}
		return 4;
	}

	public void doDeathPart1(){
		isChestOpenedOnce = false;
		deathPos = currentPos;
		currentPos = 2;
		tSpearsController.stop();
		tSpearsController.stopAllSpears();
		createMusicPlayer("fail");
		createDeathAnimationTimer();
	}
	
	public void doDeathPart2(){
		lifes--;
		diverList.get(deathPos).setVisible(false);
		drawChestFull();
		if (lifes ==  0){
			doGameOver();
			return;
		}
		
		boolean b = false;	// initializing
		for (int i = 0; i < 3; i++) {
			if (i == 3-lifes)
				b = true;
			diverList.get(i).setVisible(b);
		}
		tSpearsController.startAllSpears();
		tSpearsController.start();
		tDeathAnimation = null;
	}
	
	private void createDeathAnimationTimer(){
		mainFrame.disableMoveActions();
		tDeathAnimation = new TDeathAnimation(150, null, this);
		tDeathAnimation.addActionListener(tDeathAnimation);
		tDeathAnimation.start();
	}
	
	public void stopDeathAnimationTimer(){
		if (tDeathAnimation != null)
			tDeathAnimation.stop();
	}
	public void startDeathAnimationAndTSpearsControllerTimers(){
		if (tDeathAnimation != null)
			tDeathAnimation.start();
		else{
			tSpearsController.startAllSpears();
			tSpearsController.start();
		}
		startCloudTimers();
	}
	
	public void setScorePicture(ScorePicture scorePicture){
		this.scorePicture = scorePicture;
	}

	private void doGameOver() {
		createMusicPlayer("game over");
		JOptionPane.showMessageDialog(mainFrame.getMainPanel(), "Game Over!");
		mainFrame.recreatMainPanel();
	}
	
	public Images getImages(){
		return images;
	}
	
	public Diver getDiverOnDeathPos(){
		return diverList.get(deathPos);
	}
	
	public TSpearsController getTSpearsController(){
		return tSpearsController;
	}
	
	public MainFrame getMainFrame(){
		return mainFrame;
	}
	
	public void setChest(ObjectOnGameField chest){
		this.chest = chest;
	}
	
	public void setCloud(Cloud cloud, int i){
		clouds[i] = cloud;
	}
	
	private void createMusicPlayer(String name) {
		if (ifSoundsEnabled){
			SoundsPlayer musicPlayer = new SoundsPlayer(name);
			musicPlayer.start();
		}
	}
	
	public void stopCloudTimers(){
		for (int i = 0; i < Q_CLOUDS; i++) {
			tClouds[i].stop();
		}
	}
	
	public void startCloudTimers(){
		for (int i = 0; i < Q_CLOUDS; i++) {
			tClouds[i].start();
		}
	}
	
	public void switchIfSoundsEnabled(){
		BufferedImage newIcon;
		if (ifSoundsEnabled){
			ifSoundsEnabled = false;
			newIcon = images.getSound(0);
		}else{
			ifSoundsEnabled = true;
			newIcon = images.getSound(1);
		}
		mainFrame.getMainPanel().getSoundButton().setNewIcon(newIcon);
	}
}
