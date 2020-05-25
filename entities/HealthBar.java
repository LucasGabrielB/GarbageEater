package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Main.Sprites;

public class HealthBar{
	// attributes
	private ArrayList<SquareInTheScreen> healthBar = new ArrayList<>();
	private BufferedImage heartImg;
	private boolean haveLife;
	
	// constructor
	public HealthBar(int size, int xPosition, int yPosition){
		for(int i = 0; i < size; i++){
			getHealthBar().add(new SquareInTheScreen(xPosition, yPosition));
			xPosition += 25;
		}
		
	}
	
	// method to draw the hearts in the screen
	public void draw(Graphics g){
		
		heartImg = Sprites.getHeart();
		
		for (SquareInTheScreen heart : healthBar) {
			g.drawImage(heartImg, heart.getX(), heart.getY(), null);
		
		}
	
	}
	
	// method to remove one heart of live
	public void takeDamage(){
		if(!getHealthBar().isEmpty()){
			getHealthBar().remove(getHealthBar().size() - 1);
			setHaveLife(true);
		
		}
		else{
			setHaveLife(false);
		
		}
	
	}
	
	private ArrayList<SquareInTheScreen> getHealthBar() {
		return this.healthBar;
	
	}

	private void setHaveLife(boolean haveLife) {
		this.haveLife = haveLife;
	
	}
	
	public boolean isHaveLife(){
		return this.haveLife;
	
	}
	
}
