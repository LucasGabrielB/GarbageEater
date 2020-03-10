package entities;

import java.util.ArrayList;

public class Snake {
	// attributes
	private ArrayList<SnakeBodyPart> body = new ArrayList<>();
	private int squareSize;
	private int color;

	// constructor
	public Snake(int squareSize){
		setSquareSize(squareSize);
	}
	
	// getters and setters
	public int getColor() {
		return this.color;
	}

	public void setColor(int color) {
		this.color = color;
		// change the color of all snake body parts
		for(SnakeBodyPart bodyPart : getBody()){
			bodyPart.setColor(this.getColor());
		}
	}
	
	public ArrayList<SnakeBodyPart> getBody() {
		return this.body;
	}
	
	public int getSquareSize() {
		return this.squareSize;
	}

	public void setSquareSize(int squareSize) {
		this.squareSize = squareSize;
	}
	
	//  method that returns the snake size
	public int getLength() {
		return this.getBody().size();
	}
	
	// method to add a new BodyPart to the snake
	public void addBodyPart(int x, int y){
		getBody().add(new SnakeBodyPart(x, y, getSquareSize(), getColor()));
	}
}
