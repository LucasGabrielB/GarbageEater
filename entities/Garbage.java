package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Garbage extends SquareInTheScreen{
	// attributes
	private RecycleBinColor color;
	
	// images
	private BufferedImage garbageImage;
	
	// constructor
	public Garbage(int x, int y, RecycleBinColor color, int squareSize) {
		super(x, y, squareSize);
		setColor(color);
	
	}
	
	// method to draw the garbage in the game screen
	public void draw(Graphics g){	
		try{
			switch (getColor()) {
				case RED:
					this.garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/redGarbage.png"));
					break;
				
				case GREEN:		
					this.garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/greenGarbage.png"));
					break;
				
				case BLUE:	
					this.garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/blueGarbage.png"));
					break;
				
				case YELLOW:
					this.garbageImage = ImageIO.read(getClass().getResourceAsStream("/images/yellowGarbage.png"));
					break;
	
			}
			
			g.drawImage(this.garbageImage, getX() * getSquareSize(), getY() * getSquareSize(), null);
			
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
