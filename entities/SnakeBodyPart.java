package entities;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBodyPart extends SquareInTheScreen {
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
        g.fillRect(getX() * getSquareSize(), getY() * getSquareSize(), getSquareSize(), getSquareSize());
    }

}
