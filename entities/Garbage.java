package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Garbage {
	// attributes
	private int x;
	private int y;
	private int type;
	private BufferedImage image;

	// constructor
	public Garbage(int x, int y, int type,BufferedImage image) {
		setX(x);
		setY(y);
		setType(type);
		setImage(image);
	}
	
	// drawn the garbage
	public void draw(Graphics g) {
		// verify what is the current color  
		if(getType() == 0){
			g.setColor(Color.RED);
		}
		else if(getType() == 1){
			g.setColor(Color.GREEN);
		}
		else if(getType() == 2){
			g.setColor(Color.BLUE);
		}
		else if(getType() == 3){
			g.setColor(Color.YELLOW);
		}
	 
		 g.fillRect(x * getSquareSize(), y * getSquareSize(), getSquareSize(), getSquareSize());
		
	}

	// getters and setters	

	public int getType() {
		return this.type;
	}

	private BufferedImage getImage() {
		return this.image;
	}

	private void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setType(int type) {
		this.type = type;
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
	
	public int getSquareSize() {return 20;}
	
}
