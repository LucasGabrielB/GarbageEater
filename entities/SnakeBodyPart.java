package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
		try{
			switch (getColor()) {
				case RED:
					snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/redSnakeBodyPart.png"));
					break;
					
				case GREEN:
					snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/greenSnakeBodyPart.png"));
					break;
				
				case BLUE:
					snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/blueSnakeBodyPart.png"));
					break;
				
				case YELLOW:
					snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/yellowSnakeBodyPart.png"));
					break;	
			
			}
			
			g.drawImage(this.snakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), null);
		
		} 
		catch (Exception e) {
			// if any error occurred calls the method again
			this.draw(g);

		}
    
	}
	
	// getters and setters
	public RecycleBinColor getColor() {
		return this.color;
	
	}

	public void setColor(RecycleBinColor color) {
		this.color = color;
	}

}
