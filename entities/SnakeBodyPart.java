package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SnakeBodyPart extends SquareInTheScreen {
	// attributes
	private Colors color;
	
	// images
	private BufferedImage redSnakeBodyPartImage;
	private BufferedImage greenSnakeBodyPartImage;
	private BufferedImage blueSnakeBodyPartImage;
	private BufferedImage yellowSnakeBodyPartImage;
	
	// constructor
	public SnakeBodyPart(int x, int y, int squareSize, Colors color){
		super(x, y, squareSize);
		setColor(color);
		openImages();
	
	}

	// draw the snake body part in the game screen
	public void draw(Graphics g, ImageObserver imageObserver) {
		
		try{
			switch (getColor()) {
				case RED:
					g.drawImage(this.redSnakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
					
				case GREEN:
					g.drawImage(this.greenSnakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case BLUE:
					g.drawImage(this.blueSnakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case YELLOW:
					g.drawImage(this.yellowSnakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;	
			
			}
		
		} 
		catch (Exception e) {
			// if any error occurred calls the method again
			this.draw(g, imageObserver);

		}
    
	}

	// method to load snake body part images
	public void openImages(){
		try {
			this.redSnakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/redSnakeBodyPart.png"));
			this.greenSnakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/greenSnakeBodyPart.png"));
			this.blueSnakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/blueSnakeBodyPart.png"));
			this.yellowSnakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/yellowSnakeBodyPart.png"));
		
		} 
		catch (IOException e) {
			e.printStackTrace();
		
		}
		
	}
	
	// getters and setters
	public Colors getColor() {
		return this.color;
	
	}

	public void setColor(Colors color) {
		this.color = color;
	}

}
