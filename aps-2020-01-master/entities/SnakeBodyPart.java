package entities;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBodyPart {
	// attributes
	private int x;
	private int y;
	private int squareSize;
	private int color;
	
	// constructor
	public SnakeBodyPart(int x, int y, int squareSize, int color){
		setX(x);
		setY(y);
		setSquareSize(squareSize);
		setColor(color);
	}

	// draw method
	public void draw(Graphics g) {
		// verify what is the current color 
		if(getColor() == 0){
			g.setColor(Color.RED);
		}
		else if(getColor() == 1){
			g.setColor(Color.GREEN);
		}
		else if(getColor() == 2){
			g.setColor(Color.BLUE);
		}
		else if(getColor() == 3){
			g.setColor(Color.YELLOW);
		}
		// draw the snake square
        g.fillRect(x * getSquareSize(), y * getSquareSize(), getSquareSize(), getSquareSize());
    }
	
	// getters and setters
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

	public void setSquareSize(int tyleSize) {
		this.squareSize = tyleSize;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}

}
