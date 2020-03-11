package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Garbage extends SquareInTheScreen{
	// attributes
	private BufferedImage garbageImage;
	
	// constructor
	public Garbage(int x, int y, int color, int squareSize) {
		setX(x);
		setY(y);
		setColor(color);
		setSquareSize(squareSize);
	}
	
	// method to draw the garbage in the game screen
	public void draw(Graphics g){
		try {
			if(getColor() == 0){
				// red
				garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/redGarbage.png"));
			}
			else if(getColor() == 1){
				// green
				garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/greenGarbage.png"));
			}
			else if(getColor() == 2){
				// blue
				garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/blueGarbage.png"));
			}
			else if(getColor() == 3){
				// yellow
				garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/yellowGarbage.png"));
			}
			
			// draw the garbage in the game screen
			g.drawImage(garbageImage, getX() * getSquareSize(), getY() * getSquareSize(), null);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
