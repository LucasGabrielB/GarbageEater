package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Main.Sprites;

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
		switch (getColor()) {
			case RED:
				this.garbageImage = Sprites.getRedGarbage();
				break;
			
			case GREEN:		
				this.garbageImage = Sprites.getGreenGarbage();
				break;
			
			case BLUE:	
				this.garbageImage = Sprites.getBlueGarbage();
				break;
			
			case YELLOW:
				this.garbageImage = Sprites.getYellowGarbage();
				break;

		}
		
		g.drawImage(this.garbageImage, getX() * getSquareSize(), getY() * getSquareSize(), null);
		
	}	

	// getters and setters
	public RecycleBinColor getColor() {
		return this.color;
	}

	public void setColor(RecycleBinColor color) {
		this.color = color;
	}

}
