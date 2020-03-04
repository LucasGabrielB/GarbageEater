package entities;

import java.util.ArrayList;

public class Snake {
	// attributes
	private ArrayList<SnakeBodyPart> body = new ArrayList<>();
	private int tyleSize;
	private int color;

	// constructor
	public Snake(int size, int tyleSize){
		setTyleSize(tyleSize);
		
		// makes a snake of the informed size
		int tempX = 5;
		for(int i = 0 ; i < size ; i++){
			getBody().add(new SnakeBodyPart(tempX, 1, tyleSize, 1));
			tempX++;	
		}
	}
	
	// getters and setters
	public int getColor() {
		return this.color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public ArrayList<SnakeBodyPart> getBody() {
		return this.body;
	}
	
	public void setBody(ArrayList<SnakeBodyPart> body) {
		this.body = body;
	}
	
	public int getTyleSize() {
		return this.tyleSize;
	}

	public void setTyleSize(int tyleSize) {
		this.tyleSize = tyleSize;
	}
	
	//  method that returns the snake size
	public int getLength() {
		return this.getBody().size();
	}
	
	// method to add a BodyPart to the snake
	public void addBodyPart(int x, int y, int tyleSize){
		getBody().add(new SnakeBodyPart(x, y, tyleSize, getColor()));
	}
}
