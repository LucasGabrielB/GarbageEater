package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Garbage extends SquareInTheScreen{
	// attributes
	private Colors color;
	
	// images
	private BufferedImage redGarbageImage;
	private BufferedImage greenGarbageImage;
	private BufferedImage blueGarbageImage;
	private BufferedImage yellowGarbageImage;
	
	// constructor
	public Garbage(int x, int y, Colors color, int squareSize) {
		super(x, y, squareSize);
		setColor(color);
		openImages();
	
	}
	
	// method to draw the garbage in the game screen
	public void draw(Graphics g, ImageObserver imageObserver){
		
		try{
			switch (getColor()) {
				case RED:
					g.drawImage(this.redGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case GREEN:
					g.drawImage(this.greenGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case BLUE:
					g.drawImage(this.blueGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
				
				case YELLOW:
					g.drawImage(this.yellowGarbageImage, getX() * getSquareSize(), getY() * getSquareSize(), imageObserver);
					break;
	
			}
		} 
		catch (Exception e) {
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
