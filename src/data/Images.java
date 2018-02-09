package data;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images implements Constants, ImageResizer{
	
	private int coordsK;
	private BufferedImage spriteSheet;
	private BufferedImage tempImg;
	private BufferedImage[] diverFigures = new BufferedImage[8];
	private BufferedImage diverGrabGold;
	private BufferedImage boat;
	private BufferedImage ladder;
	private BufferedImage anchor;
	private BufferedImage waterSurface;
	private BufferedImage waterItself;
	private BufferedImage ground;
	private BufferedImage chest;
	private BufferedImage chestEmpty;
	private BufferedImage cloud;
	private BufferedImage[] stones = new BufferedImage[3];
	private BufferedImage[] flowers = new BufferedImage[3];
	private BufferedImage[] cannons = new BufferedImage[5];
	private BufferedImage[][] spears = new BufferedImage[5][5];
	private BufferedImage[] digits = new BufferedImage[10];
	private BufferedImage[] deadDiver = new BufferedImage[2];
	private BufferedImage[] sound = new BufferedImage[2];
	private BufferedImage[] music = new BufferedImage[2];
	private int tileSize;
	
	public Images() {
		defineCoordsVariables();
		openSpriteSheet();
		subImageDiverFigures();
		subImageWater();
		subImageChest();
		subImageGround();
		subImageLadder();
		subImageBoat();
		subImageAnchor();
		subImageStones();
		subImageFlowers();
		subImageCannons();
		subImageSpears();
		subImageDigits();
		subImageDeadDiver();
		subImageCloud();
		subImageSound();
		subImageMusic();
	}

	private void defineCoordsVariables() {
		do {
			coordsK++;
		} while ((coordsK+GK)*ASSET_SIZE < WINDOW_HEIGHT/10);
		tileSize = ASSET_SIZE*coordsK;
	}

	private void openSpriteSheet() {
		try {  
		spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/assets/spriteSheet.png"));
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

	private void subImageDiverFigures() {
		tempImg = spriteSheet.getSubimage(0, 0, ASSET_SIZE, ASSET_SIZE);
		for (int i = 0; i < 3; i++) {
			diverFigures[i] = ImageResizer.resize(tempImg, tileSize, tileSize);
		}
		
		tempImg = spriteSheet.getSubimage(21, 0, ASSET_SIZE, ASSET_SIZE);
		diverFigures[3] = ImageResizer.resize(tempImg, tileSize, tileSize);
		
		tempImg = spriteSheet.getSubimage(42, 0, ASSET_SIZE, ASSET_SIZE);
		diverFigures[4] = ImageResizer.resize(tempImg, tileSize, tileSize);
	
		tempImg = spriteSheet.getSubimage(63, 0, ASSET_SIZE, ASSET_SIZE);
		diverFigures[5] = ImageResizer.resize(tempImg, tileSize, tileSize);
	
		tempImg = spriteSheet.getSubimage(84, 0, ASSET_SIZE, ASSET_SIZE);
		diverFigures[6] = ImageResizer.resize(tempImg, tileSize, tileSize);
		
		tempImg = spriteSheet.getSubimage(63, 0, ASSET_SIZE, ASSET_SIZE);
		diverFigures[7] = ImageResizer.resize(tempImg, tileSize, tileSize);
		
		tempImg = spriteSheet.getSubimage(105, 0, ASSET_SIZE, ASSET_SIZE);
		diverGrabGold = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageWater() {
		tempImg = spriteSheet.getSubimage(0, 21, ASSET_SIZE, ASSET_SIZE);
		waterSurface = ImageResizer.resize(tempImg, tileSize, tileSize);

		tempImg = spriteSheet.getSubimage(21, 21, ASSET_SIZE, ASSET_SIZE);
		waterItself = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageChest() {
		tempImg = spriteSheet.getSubimage(0, 42, ASSET_SIZE, ASSET_SIZE);
		chest = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(147, 42, ASSET_SIZE, ASSET_SIZE);
			chestEmpty = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageGround() {
		tempImg = spriteSheet.getSubimage(0, 84, ASSET_SIZE*7, ASSET_SIZE*2);
		ground = ImageResizer.resize(tempImg, tileSize*7, tileSize*2);
	}

	private void subImageLadder() {
		ladder = new BufferedImage(tileSize, tileSize*7, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = ladder.getGraphics();
		
		tempImg = spriteSheet.getSubimage(84, 21, ASSET_SIZE, ASSET_SIZE);
		tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		g2.drawImage(tempImg, 0, 0, null);
			tempImg = spriteSheet.getSubimage(105, 21, ASSET_SIZE, ASSET_SIZE);
			tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		for (int i = 1; i <= 5; i++) {
			g2.drawImage(tempImg, 0, tileSize*i, null);
		}
		g2.dispose();
	}

	private void subImageBoat() {
		boat = new BufferedImage(tileSize*4, tileSize, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = boat.getGraphics();
		
		tempImg = spriteSheet.getSubimage(0, 63, ASSET_SIZE, ASSET_SIZE);
		tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		g2.drawImage(tempImg, 0, 0, null);
			tempImg = spriteSheet.getSubimage(21, 63, ASSET_SIZE, ASSET_SIZE);
			tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
			g2.drawImage(tempImg, tileSize, 0, null);
		tempImg = spriteSheet.getSubimage(42, 63, ASSET_SIZE, ASSET_SIZE);
		tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		g2.drawImage(tempImg, tileSize*2, 0, null);
			tempImg = spriteSheet.getSubimage(63, 63, ASSET_SIZE, ASSET_SIZE);
			tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
			g2.drawImage(tempImg, tileSize*3, 0, null);
		g2.dispose();
	}

	private void subImageAnchor() {
		anchor = new BufferedImage(tileSize, tileSize*6, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = anchor.getGraphics();
		
		tempImg = spriteSheet.getSubimage(42, 21, ASSET_SIZE, ASSET_SIZE);
		tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		for (int i = 0; i < 5; i++) {
			g2.drawImage(tempImg, 0, tileSize*i, null);
		}
			tempImg = spriteSheet.getSubimage(63, 21, ASSET_SIZE, ASSET_SIZE);
			tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
			g2.drawImage(tempImg, 0, tileSize*5, null);
		g2.dispose();
	}

	private void subImageStones() {
		tempImg = spriteSheet.getSubimage(84, 42, ASSET_SIZE, ASSET_SIZE);
		stones[0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(105, 42, ASSET_SIZE, ASSET_SIZE);
			stones[1] = ImageResizer.resize(tempImg, tileSize, tileSize);
		stones[2] = stones[0];
	}

	private void subImageFlowers() {
		tempImg = spriteSheet.getSubimage(21, 42, ASSET_SIZE, ASSET_SIZE);
		flowers[0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(42, 42, ASSET_SIZE, ASSET_SIZE);
			flowers[1] = ImageResizer.resize(tempImg, tileSize, tileSize);
		tempImg = spriteSheet.getSubimage(63, 42, ASSET_SIZE, ASSET_SIZE);
		flowers[2] = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageCannons() {
		tempImg = spriteSheet.getSubimage(147, 21, ASSET_SIZE, ASSET_SIZE);
		cannons[0] = ImageResizer.resize(tempImg, tileSize, tileSize);
		cannons[1] = cannons[0];
			tempImg = spriteSheet.getSubimage(126, 21, ASSET_SIZE, ASSET_SIZE);
			cannons[2] = ImageResizer.resize(tempImg, tileSize, tileSize);
		cannons[3] = cannons[2];
		cannons[4] = cannons[2];
	}

	private void subImageSpears() {
		tempImg = spriteSheet.getSubimage(105, 63, ASSET_SIZE, ASSET_SIZE);
		spears[0][0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(126, 63, ASSET_SIZE, ASSET_SIZE);
			spears[0][1] = ImageResizer.resize(tempImg, tileSize, tileSize);
		tempImg = spriteSheet.getSubimage(168, 105, ASSET_SIZE, ASSET_SIZE);
		spears[0][2] = ImageResizer.resize(tempImg, tileSize, tileSize);

		tempImg = spriteSheet.getSubimage(105, 63, ASSET_SIZE, ASSET_SIZE);
		spears[1][0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(147, 84, ASSET_SIZE, ASSET_SIZE);
			spears[1][1] = ImageResizer.resize(tempImg, tileSize, tileSize);
		spears[1][2] = new BufferedImage(tileSize, tileSize*2, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = spears[1][2].getGraphics();
		tempImg = spriteSheet.getSubimage(84, 63, ASSET_SIZE, ASSET_SIZE);
		tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		g2.drawImage(tempImg, 0, 0, null);
		tempImg = spriteSheet.getSubimage(168, 84, ASSET_SIZE, ASSET_SIZE);
		tempImg = ImageResizer.resize(tempImg, tileSize, tileSize);
		g2.drawImage(tempImg, 0, tileSize, null);
		g2.dispose();
			tempImg = spriteSheet.getSubimage(168, 105, ASSET_SIZE, ASSET_SIZE);
			spears[1][3] = ImageResizer.resize(tempImg, tileSize, tileSize);

		tempImg = spriteSheet.getSubimage(84, 63, ASSET_SIZE, ASSET_SIZE);
		spears[2][0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			spears[2][1] = spears[2][0];
		tempImg = spriteSheet.getSubimage(168, 84, ASSET_SIZE, ASSET_SIZE);
		spears[2][2] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(168, 63, ASSET_SIZE, ASSET_SIZE);
			spears[2][3] = ImageResizer.resize(tempImg, tileSize, tileSize);
		tempImg = spriteSheet.getSubimage(147, 105, ASSET_SIZE, ASSET_SIZE);
		spears[2][4] = ImageResizer.resize(tempImg, tileSize, tileSize);
		
		tempImg = spriteSheet.getSubimage(84, 63, ASSET_SIZE, ASSET_SIZE);
		spears[3][0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			spears[3][1] = spears[3][0];
		tempImg = spriteSheet.getSubimage(147, 63, ASSET_SIZE, ASSET_SIZE);
		spears[3][2] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(147, 105, ASSET_SIZE, ASSET_SIZE);
			spears[3][3] = ImageResizer.resize(tempImg, tileSize, tileSize);
			
		tempImg = spriteSheet.getSubimage(84, 63, ASSET_SIZE, ASSET_SIZE);
		spears[4][0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(147, 63, ASSET_SIZE, ASSET_SIZE);
			spears[4][1] = ImageResizer.resize(tempImg, tileSize, tileSize);
		tempImg = spriteSheet.getSubimage(147, 105, ASSET_SIZE, ASSET_SIZE);
		spears[4][2] = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageDigits() {
		for (int i = 0; i < 10; i++) {
			tempImg = spriteSheet.getSubimage(0+21*i, 126, ASSET_SIZE, ASSET_SIZE);
			digits[i] = ImageResizer.resize(tempImg, tileSize, tileSize);
		}
	}

	private void subImageDeadDiver() {
		tempImg = spriteSheet.getSubimage(126, 0, ASSET_SIZE, ASSET_SIZE);
		deadDiver[0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(147, 0, ASSET_SIZE, ASSET_SIZE);
			deadDiver[1] = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageCloud() {
		tempImg = spriteSheet.getSubimage(126, 42, ASSET_SIZE, ASSET_SIZE);
		cloud = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageSound() {
		tempImg = spriteSheet.getSubimage(189, 0, ASSET_SIZE, ASSET_SIZE);
		sound[0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(168, 0, ASSET_SIZE, ASSET_SIZE);
			sound[1]= ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	private void subImageMusic() {
		tempImg = spriteSheet.getSubimage(189, 21, ASSET_SIZE, ASSET_SIZE);
		music[0] = ImageResizer.resize(tempImg, tileSize, tileSize);
			tempImg = spriteSheet.getSubimage(168, 21, ASSET_SIZE, ASSET_SIZE);
			music[1] = ImageResizer.resize(tempImg, tileSize, tileSize);
	}

	public BufferedImage getBoat() {
		return boat;
	}
	
	public BufferedImage getWaterSurface(){
		return waterSurface;
	}
	
	public BufferedImage getWaterItself(){
		return waterItself;
	}
	
	public BufferedImage getChest(){
		return chest;
	}
	
	public BufferedImage getChestEmpty(){
		return chestEmpty;
	}
	
	public BufferedImage getGround(){
		return ground;
	}
	
	public BufferedImage getLadder(){
		return ladder;
	}
	
	public BufferedImage getAnchor(){
		return anchor;
	}
	
	public BufferedImage getDiverFigures(int i){
		return diverFigures[i];
	}
	
	public BufferedImage getStones(int i){
		return stones[i];
	}
	
	public BufferedImage getFlowers(int i){
		return flowers[i];
	}
	
	public BufferedImage getCannons(int i){
		return cannons[i];
	}
	
	public BufferedImage getSpears(int i, int j){
		return spears[i][j];
	}
	
	public BufferedImage getDigits(int i){
		return digits[i];
	}
	
	public BufferedImage getDiverGrabGold(){
		return diverGrabGold;
	}
	
	public BufferedImage getDeadDiver(int i){
		return deadDiver[i];
	}
	
	public BufferedImage getCloud(){
		return cloud;
	}
	
	public BufferedImage getSound(int i){
		return sound[i];
	}
	
	public BufferedImage[] getMusic(){
		return music;
	}	
}
