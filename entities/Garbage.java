package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Garbage extends SquareInTheScreen{
	// images
	private BufferedImage redGarbageImage;
	private BufferedImage greenGarbageImage;
	private BufferedImage blueGarbageImage;
	private BufferedImage yellowGarbageImage;
	
	// constructor
	public Garbage(int x, int y, int color, int squareSize) {
		setX(x);
		setY(y);
		setColor(color);
		setSquareSize(squareSize);
		openImages();
	}
	
	// method to draw the garbage in the game screen
	public void draw(Graphics g, ImageObserver imageObserver){
		
		try{
			switch (getColor()) {
				case 0:
					// red
					g.drawImage(this.redGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case 1:
					// green
					g.drawImage(this.greenGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case 2:
					// blue
					g.drawImage(this.blueGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case 3:
					// yellow
					g.drawImage(this.yellowGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
	
			}
		} catch (Exception e) {
			// if any error occurred calls the method again
			this.draw(g, imageObserver);
		}
		
	}
	
	// method to load the garbage images 
	public void openImages(){
		try {
			this.redGarbageImage = ImageIO.read(getClass().getResourceAsStream("/images/redGarbage.png"));
			this.greenGarbageImage = ImageIO.read(getClass().getResourceAsStream("/images/greenGarbage.png"));
			this.blueGarbageImage = ImageIO.read(getClass().getResourceAsStream("/images/blueGarbage.png"));
			this.yellowGarbageImage = ImageIO.read(getClass().getResourceAsStream("/images/yellowGarbage.png"));
		
		} catch (IOException e) {
			e.printStackTrace();
		
		}
		
	}

}
