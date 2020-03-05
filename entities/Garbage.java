package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Garbage {
	// attributes
	private int x;
	private int y;
	private int color;
	private int squareSize;
	private BufferedImage image;
	
	// constructor
	public Garbage(int x, int y, int color, int squareSize) {
		setX(x);
		setY(y);
		setColor(color);
		setSquareSize(squareSize);
	}
	
	// getters and setters	

	public int getColor() {
		return this.color;
	}
	
	public void draw(Graphics g){
		try {
			if(getColor() == 0){
				// red
				image = ImageIO.read(getClass().getResourceAsStream("/images/redGarbage.png"));
			}
			else if(getColor() == 1){
				// green
				image = ImageIO.read(getClass().getResourceAsStream("/images/greenGarbage.png"));
			}
			else if(getColor() == 2){
				// blue
				image = ImageIO.read(getClass().getResourceAsStream("/images/blueGarbage.png"));
			}
			else if(getColor() == 3){
				// yellow
				image = ImageIO.read(getClass().getResourceAsStream("/images/yellowGarbage.png"));
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		// draw garbage in the game screen
		g.drawImage(image, getX() * getSquareSize(), getY() * getSquareSize(), null);
	}


	public void setColor(int color) {
		this.color = color;
	}

	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSquareSize() {
		return this.squareSize;
	}
	
	private void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	}

}
