package data;

import java.awt.Rectangle;

public class Rectangles implements Constants{
	
	private int tileSize;
	private Rectangle[] diverRectangles = new Rectangle[8];
	private Rectangle[] stones = new Rectangle[3];
	private Rectangle boatRect;
	private Rectangle waterSurfaceRect;
	private Rectangle waterItself;
	private Rectangle[] flowers = new Rectangle[3];
	private Rectangle[] cannons = new Rectangle[5];
	private Rectangle[][] spears = new Rectangle[5][5];
	private Rectangle scorePicture;
	private Rectangle chestRect;
	private Rectangle ground;
	private Rectangle ladder;
	private Rectangle anchor;
	private Rectangle cloud;
	private Rectangle soundsBtn, musicBtn;
	
	public Rectangles(int tileSize) {
		this.tileSize = tileSize;
		defineDiverCoords();
		defineBoatCoords();
		defineWaterCoords();
		defineStonesCoords();
		defineFlowersCoords();
		defineCannonsCoords();
		defineSpearsCoords();
		defineButtonsCoords();
		chestRect = new Rectangle(tileSize*14, tileSize*9, tileSize, tileSize);
		ladder = new Rectangle(tileSize*4, tileSize*2, tileSize, tileSize*6);
		ground = new Rectangle(0, tileSize*8, WINDOW_WIDTH, WINDOW_HEIGHT-tileSize*8);
		anchor = new Rectangle(tileSize, tileSize*2, tileSize, tileSize*6);
		scorePicture = new Rectangle(tileSize, tileSize*9, tileSize*4, tileSize);
		cloud = new Rectangle(0, 0, tileSize, tileSize);
	}

	private void defineDiverCoords() {
		diverRectangles[0] = new Rectangle(tileSize*2, tileSize, tileSize, tileSize);
		diverRectangles[1] = new Rectangle(tileSize*3, tileSize, tileSize, tileSize);
		diverRectangles[2] = new Rectangle(tileSize*4, tileSize, tileSize, tileSize);
		diverRectangles[3] = new Rectangle(tileSize*4, tileSize*5, tileSize, tileSize);
		diverRectangles[4] = new Rectangle(tileSize*4, tileSize*7, tileSize, tileSize);
		diverRectangles[5] = new Rectangle(tileSize*7, tileSize*9, tileSize, tileSize);
		diverRectangles[6] = new Rectangle(tileSize*10, tileSize*9, tileSize, tileSize);
		diverRectangles[7] = new Rectangle(tileSize*13, tileSize*9, tileSize, tileSize);
	}

	private void defineBoatCoords() {
		boatRect = new Rectangle(tileSize*3/2, tileSize*7/4, tileSize*4, tileSize);
	}
	
	private void defineWaterCoords() {
		waterSurfaceRect = new Rectangle(0, tileSize*2, WINDOW_WIDTH+tileSize*2, tileSize);
		waterItself = new Rectangle(0, tileSize*3, WINDOW_WIDTH+tileSize*2, WINDOW_HEIGHT-tileSize*3);
	}

	private void defineStonesCoords() {
		stones[0] = new Rectangle(tileSize*3, tileSize*7, tileSize, tileSize);
		stones[1] = new Rectangle(tileSize*15, tileSize*9, tileSize, tileSize);
		stones[2] = new Rectangle(tileSize*16, tileSize*9, tileSize, tileSize);
	}

	private void defineFlowersCoords() {
		flowers[0] = new Rectangle(0, tileSize*7, tileSize, tileSize);
		flowers[1] = new Rectangle(tileSize*9, tileSize*9, tileSize, tileSize);
		flowers[2] = new Rectangle(tileSize*19, tileSize*9, tileSize, tileSize);
	}

	private void defineCannonsCoords() {
		cannons[0] = new Rectangle(tileSize*7, tileSize*5, tileSize, tileSize);
		cannons[1] = new Rectangle(tileSize*7, tileSize*5, tileSize, tileSize);
		cannons[2] = new Rectangle(tileSize*8, tileSize*5, tileSize, tileSize);
		cannons[3] = new Rectangle(tileSize*10, tileSize*5, tileSize, tileSize);
		cannons[4] = new Rectangle(tileSize*13, tileSize*6, tileSize, tileSize);
	}

	private void defineSpearsCoords() {
		spears[0][0] = new Rectangle(tileSize*6, tileSize*5, tileSize, tileSize);
		spears[0][1] = new Rectangle(tileSize*5, tileSize*5, tileSize, tileSize);
		spears[0][2] = new Rectangle(tileSize*4, tileSize*5, tileSize, tileSize);
		
		spears[1][0] = new Rectangle(tileSize*6, tileSize*5, tileSize, tileSize);
		spears[1][1] = new Rectangle(tileSize*5, tileSize*5, tileSize, tileSize);
		spears[1][2] = new Rectangle(tileSize*5, tileSize*6, tileSize, tileSize*2);
		spears[1][3] = new Rectangle(tileSize*4, tileSize*7, tileSize, tileSize);
		
		spears[2][0] = new Rectangle(tileSize*8, tileSize*6, tileSize, tileSize);
		spears[2][1] = new Rectangle(tileSize*8, tileSize*7, tileSize, tileSize);	
		spears[2][2] = new Rectangle(tileSize*8, tileSize*8, tileSize, tileSize);
		spears[2][3] = new Rectangle(tileSize*7, tileSize*8, tileSize, tileSize);
		spears[2][4] = new Rectangle(tileSize*7, tileSize*9, tileSize, tileSize);
		
		spears[3][0] = new Rectangle(tileSize*10, tileSize*6, tileSize, tileSize);
		spears[3][1] = new Rectangle(tileSize*10, tileSize*7, tileSize, tileSize);
		spears[3][2] = new Rectangle(tileSize*10, tileSize*8, tileSize, tileSize);
		spears[3][3] = new Rectangle(tileSize*10, tileSize*9, tileSize, tileSize);
		
		spears[4][0] = new Rectangle(tileSize*13, tileSize*7, tileSize, tileSize);
		spears[4][1] = new Rectangle(tileSize*13, tileSize*8, tileSize, tileSize);
		spears[4][2] = new Rectangle(tileSize*13, tileSize*9, tileSize, tileSize);
	}

	private void defineButtonsCoords() {
		soundsBtn = new Rectangle(tileSize, tileSize*10, tileSize, tileSize);
		musicBtn = new Rectangle(tileSize*2, tileSize*10, tileSize, tileSize);
	}
	
	public Rectangle getDivers(int i){
		return diverRectangles[i];
	}
	
	public Rectangle getBoat(){
		return boatRect;
	}
	
	public Rectangle getChest(){
		return chestRect;
	}
	
	public Rectangle getWaterSurface(){
		return waterSurfaceRect;
	}
	
	public Rectangle getWatereItself(){
		return waterItself;
	}
	
	public Rectangle getLadder(){
		return ladder;
	}
	
	public Rectangle getGround(){
		return ground;
	}
	
	public Rectangle getAnchor(){
		return anchor;
	}
	
	public Rectangle getStones(int i){
		return stones[i];
	}
	
	public Rectangle getFlowers(int i){
		return flowers[i];
	}
	
	public Rectangle getCannons(int i){
		return cannons[i];
	}
	
	public Rectangle getSpears(int cannon, int length){
		return spears[cannon][length];
	}
	
	public Rectangle getScorePicture(){
		return scorePicture;
	}
	
	public Rectangle getCloud(){
		return cloud;
	}
	
	public Rectangle getSoundsBtn(){
		return soundsBtn;
	}
	
	public Rectangle getMusicBtn(){
		return musicBtn;
	}
}
