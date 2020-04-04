package entities;

public class SquareInTheScreen {
	// attributes
	private int x;
	private int y;
	private int squareSize;
	
	// constructor
	public SquareInTheScreen(int x, int y, int squareSize) {
		setX(x);
		setY(y);
		setSquareSize(squareSize);
	
	}
	
	// constructor
	public SquareInTheScreen(int x, int y){
		setX(x);
		setY(y);
	
	}
  
	// getters and setters
	public final int getX() {
		return this.x;
	
	}

	public final void setX(int x) {
		this.x = x;
	
	}

	public final int getY() {
		return this.y;
	
	}

	public final void setY(int y) {
		this.y = y;
	
	}
	
	public final int getSquareSize() {
		return this.squareSize;
	
	}

	public final void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	
	}

}
