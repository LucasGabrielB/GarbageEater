package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SnakeBodyPart extends SquareInTheScreen {
	// attributes
	private BufferedImage snakeBodyPartImage;
	
	// constructor
	public SnakeBodyPart(int x, int y, int squareSize, int color){
		setX(x);
		setY(y);
		setSquareSize(squareSize);
		setColor(color);
	}

	// draw method
	public void draw(Graphics g, ImageObserver imageObserver) {
		// verify what is the current color 
		try {
			if(getColor() == 0){
				// red
				snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/redSnakeBodyPart.png"));
			}
			else if(getColor() == 1){
				// green
				snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/greenSnakeBodyPart.png"));
			}
			else if(getColor() == 2){
				// blue
				snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/blueSnakeBodyPart.png"));
			}
			else if(getColor() == 3){
				// yellow
				snakeBodyPartImage = ImageIO.read(getClass().getResourceAsStream("/images/yellowSnakeBodyPart.png"));
			}
			
			// draw the snake body part in the game screen
			g.drawImage(snakeBodyPartImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    
	}

}
