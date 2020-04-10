package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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
		try{
			heartImg = ImageIO.read(getClass().getResourceAsStream("/images/heart.png"));
			
			for (SquareInTheScreen heart : healthBar) {
				g.drawImage(heartImg, heart.getX(), heart.getY(), null);
			
			}
		
		}
		catch(IOException e){
			e.printStackTrace();
		
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
