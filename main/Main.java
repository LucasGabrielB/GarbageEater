package main;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import entities.Player;
import view.MenuScreen;

public class Main {
	
    public static void main(String[] args) {
    	
    	// load all sprites
    	Sprites.load();
    	 
    	Player player = new Player("Digite seu nome aqui!");
    	
    	new MenuScreen(player);
    
    }
    
    // static class to load and handle all sprites
    public static class Sprites{
    	// get the class path 
    	private static final Class<? extends Sprites> classPath = new Sprites().getClass();
    	
    	// sprites
    	private static BufferedImage blueGarbage;
    	private static BufferedImage redGarbage;
    	private static BufferedImage yellowGarbage;
    	private static BufferedImage greenGarbage;
    	private static BufferedImage blueSnakeBodyPart;
    	private static BufferedImage redSnakeBodyPart;
    	private static BufferedImage yellowSnakeBodyPart;
    	private static BufferedImage greenSnakeBodyPart;
    	private static BufferedImage exitButton;
    	private static BufferedImage helpButton;
    	private static BufferedImage okButton;
    	private static BufferedImage startButton;
    	private static BufferedImage gameOver;
    	private static BufferedImage gameScreenBackground;
    	private static BufferedImage gameScreenHeader;
    	private static BufferedImage helpScreenBackground;
    	private static BufferedImage menuScreenBackground;
    	private static BufferedImage warningScreenBackground;
    	private static BufferedImage rank;
    	private static BufferedImage hitsOwnBody;
    	private static BufferedImage hitsWall;
    	private static BufferedImage wrongColor;
    	private static BufferedImage heart;
    	
    	// load all the sprites from the images folder
    	public static void load(){
	    	try {
	    		// load and set all sprites
				setBlueGarbage(ImageIO.read(classPath.getResourceAsStream("/images/blueGarbage.png")));
				setRank(ImageIO.read(classPath.getClass().getResourceAsStream("/images/rank.png")));
				setRedGarbage(ImageIO.read(classPath.getResourceAsStream("/images/redGarbage.png")));
				setYellowGarbage(ImageIO.read(classPath.getResourceAsStream("/images/yellowGarbage.png")));
				setGreenGarbage(ImageIO.read(classPath.getResourceAsStream("/images/greenGarbage.png")));
				setBlueSnakeBodyPart(ImageIO.read(classPath.getResourceAsStream("/images/blueSnakeBodyPart.png")));
				setRedSnakeBodyPart(ImageIO.read(classPath.getResourceAsStream("/images/redSnakeBodyPart.png")));
				setYellowSnakeBodyPart(ImageIO.read(classPath.getResourceAsStream("/images/yellowSnakeBodyPart.png")));
				setGreenSnakeBodyPart(ImageIO.read(classPath.getResourceAsStream("/images/greenSnakeBodyPart.png")));
				setHeart(ImageIO.read(classPath.getResourceAsStream("/images/heart.png")));
				setGameScreenBackground(ImageIO.read(classPath.getClass().getResourceAsStream("/images/gameScreenBackground.png")));
				setGameScreenHeader(ImageIO.read(classPath.getClass().getResourceAsStream("/images/gameScreenHeader.png")));
				setGameOver(ImageIO.read(classPath.getClass().getResourceAsStream("/images/gameOver.png")));
				setHitsOwnBody(ImageIO.read(classPath.getClass().getResourceAsStream("/images/hitsOwnBody.png")));
				setHitsWall(ImageIO.read(classPath.getClass().getResourceAsStream("/images/hitsWall.png")));
				setWrongColor(ImageIO.read(classPath.getClass().getResourceAsStream("/images/wrongColor.png")));
				setHelpScreenBackground(ImageIO.read(classPath.getClass().getResourceAsStream("/images/helpScreenBackground.png")));
			 	setWarningScreenBackground(ImageIO.read(classPath.getClass().getResourceAsStream("/images/warningScreenBackground.png")));
			 	setOkButton(ImageIO.read(classPath.getClass().getResource("/images/okButton.png")));
			 	setMenuScreenBackground(ImageIO.read(classPath.getClass().getResourceAsStream("/images/menuScreenBackground.png")));
				setStartButton(ImageIO.read(classPath.getClass().getResourceAsStream("/images/startButton.png")));
				setExitButton(ImageIO.read(classPath.getClass().getResourceAsStream("/images/exitButton.png")));
				setHelpButton(ImageIO.read(classPath.getClass().getResourceAsStream("/images/helpButton.png")));
				
	    	} 
	    	catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getStackTrace(), "Erro ao carregar imagens", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			
			}	
    	
    	}

    	// getters and setters
    	
		public static BufferedImage getBlueGarbage() {
			return blueGarbage;
		
		}

		private static void setBlueGarbage(BufferedImage blueGarbage) {
			Sprites.blueGarbage = blueGarbage;
		
		}

		public static BufferedImage getRedGarbage() {
			return redGarbage;
		
		}

		private static void setRedGarbage(BufferedImage redGarbage) {
			Sprites.redGarbage = redGarbage;
		
		}

		public static BufferedImage getYellowGarbage() {
			return yellowGarbage;
		
		}

		private static void setYellowGarbage(BufferedImage yellowGarbage) {
			Sprites.yellowGarbage = yellowGarbage;
		
		}

		public static BufferedImage getGreenGarbage() {
			return greenGarbage;
		
		}

		private static void setGreenGarbage(BufferedImage greenGarbage) {
			Sprites.greenGarbage = greenGarbage;
		
		}

		public static BufferedImage getBlueSnakeBodyPart() {
			return blueSnakeBodyPart;
		
		}

		private static void setBlueSnakeBodyPart(BufferedImage blueSnakeBodyPart) {
			Sprites.blueSnakeBodyPart = blueSnakeBodyPart;
		
		}

		public static BufferedImage getRedSnakeBodyPart() {
			return redSnakeBodyPart;
		
		}

		private static void setRedSnakeBodyPart(BufferedImage redSnakeBodyPart) {
			Sprites.redSnakeBodyPart = redSnakeBodyPart;
		
		}

		public static BufferedImage getYellowSnakeBodyPart() {
			return yellowSnakeBodyPart;
		
		}

		private static void setYellowSnakeBodyPart(BufferedImage yellowSnakeBodyPart) {
			Sprites.yellowSnakeBodyPart = yellowSnakeBodyPart;
		
		}

		public static BufferedImage getGreenBodyPart() {
			return greenSnakeBodyPart;
		
		}

		private static void setGreenSnakeBodyPart(BufferedImage greenBodyPart) {
			Sprites.greenSnakeBodyPart = greenBodyPart;
		
		}

		public static BufferedImage getExitButton() {
			return exitButton;
		
		}

		private static void setExitButton(BufferedImage exitButton) {
			Sprites.exitButton = exitButton;
		
		}

		public static BufferedImage getHelpButton() {
			return helpButton;
		
		}

		private static void setHelpButton(BufferedImage helpButton) {
			Sprites.helpButton = helpButton;
		
		}

		public static BufferedImage getOkButton() {
			return okButton;
		
		}

		private static void setOkButton(BufferedImage okButton) {
			Sprites.okButton = okButton;
		
		}

		public static BufferedImage getStartButton() {
			return startButton;
		
		}

		private static void setStartButton(BufferedImage startButton) {
			Sprites.startButton = startButton;
		
		}

		public static BufferedImage getGameOver() {
			return gameOver;
		
		}

		private static void setGameOver(BufferedImage gameOver) {
			Sprites.gameOver = gameOver;
		
		}

		public static BufferedImage getGameScreenBackground() {
			return gameScreenBackground;
		
		}

		private static void setGameScreenBackground(BufferedImage gameScreenBackground) {
			Sprites.gameScreenBackground = gameScreenBackground;
		
		}

		public static BufferedImage getGameScreenHeader() {
			return gameScreenHeader;
		
		}

		private static void setGameScreenHeader(BufferedImage gameScreenHeader) {
			Sprites.gameScreenHeader = gameScreenHeader;
		
		}

		public static BufferedImage getHelpScreenBackground() {
			return helpScreenBackground;
		
		}

		private static void setHelpScreenBackground(BufferedImage helpScreenBackground) {
			Sprites.helpScreenBackground = helpScreenBackground;
		
		}

		public static BufferedImage getMenuScreenBackground() {
			return menuScreenBackground;
		
		}

		private static void setMenuScreenBackground(BufferedImage menuScreenBackground) {
			Sprites.menuScreenBackground = menuScreenBackground;
		
		}

		public static BufferedImage getWarningScreenBackground() {
			return warningScreenBackground;
		
		}

		private static void setWarningScreenBackground(BufferedImage warningScreenBackground) {
			Sprites.warningScreenBackground = warningScreenBackground;
		
		}

		public static BufferedImage getRank() {
			return rank;
		
		}

		private static void setRank(BufferedImage rank) {
			Sprites.rank = rank;
		
		}

		public static BufferedImage getHitsOwnBody() {
			return hitsOwnBody;
		
		}

		private static void setHitsOwnBody(BufferedImage hitsOwnBody) {
			Sprites.hitsOwnBody = hitsOwnBody;
		
		}

		public static BufferedImage getHitsWall() {
			return hitsWall;
		
		}

		private static void setHitsWall(BufferedImage hitsWall) {
			Sprites.hitsWall = hitsWall;
		
		}

		public static BufferedImage getWrongColor() {
			return wrongColor;
		
		}

		private static void setWrongColor(BufferedImage wrongColor) {
			Sprites.wrongColor = wrongColor;
		
		}

		public static BufferedImage getHeart() {
			return heart;
		
		}

		private static void setHeart(BufferedImage heart) {
			Sprites.heart = heart;
		
		}
    
    }

}