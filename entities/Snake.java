package entities;

import java.util.ArrayList;

public class Snake {
	// attributes
	private ArrayList<SnakeBodyPart> body = new ArrayList<>();
	private RecycleBinColor color;

	// constructor
	public Snake(){
		setColor(RecycleBinColor.RED);
		
	}
	
	// getters and setters
	public RecycleBinColor getColor() {
		return this.color;
	
	}

	public void setColor(RecycleBinColor color) {
		this.color = color;
		// change the color of all snake body parts
		for(SnakeBodyPart bodyPart : getBody()){
			bodyPart.setColor(this.getColor());
		
		}
	
	}
	
	public ArrayList<SnakeBodyPart> getBody() {
		return this.body;
	
	}
	
	//  method that returns the snake size
	public int getLength() {
		return this.getBody().size();
	
	}
	
	// method to add a new BodyPart to the snake
	public void addBodyPart(int x, int y){
		getBody().add(new SnakeBodyPart(x, y, getColor()));
	
	}

}
