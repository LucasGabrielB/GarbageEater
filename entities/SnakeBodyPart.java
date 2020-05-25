package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Main.Sprites;

public class SnakeBodyPart extends SquareInTheScreen {
	// attributes
	private RecycleBinColor color;
	
	// images
	private BufferedImage snakeBodyPartImage;
	
	// constructor
	public SnakeBodyPart(int x, int y, int squareSize, RecycleBinColor color){
		super(x, y, squareSize);
		setColor(color);
	
	}

	public SnakeBodyPart(int x, int y, RecycleBinColor color){
		super(x, y, 20);
		setColor(color);
	
	}
	
	// draw the snake body part in the game screen
	public void draw(Graphics g) {		
		switch (getColor()) {
			case RED:
				snakeBodyPartImage = Sprites.getRedSnakeBodyPart();
				break;
				
			case GREEN:
				snakeBodyPartImage = Sprites.getGreenBodyPart();
				break;
			
			case BLUE:
				snakeBodyPartImage = Sprites.getBlueSnakeBodyPart();
				break;
			
			case YELLOW:
				snakeBodyPartImage = Sprites.getYellowSnakeBodyPart();
				break;	
		
		}
		
		g.drawImage(this.snakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), null);
		
	}
	
	// getters and setters
	public RecycleBinColor getColor() {
		return this.color;
	
	}

	public void setColor(RecycleBinColor color) {
		this.color = color;
	}

}
