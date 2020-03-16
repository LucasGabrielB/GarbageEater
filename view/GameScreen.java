package view;

import java.awt.Color;
import entities.Garbage;
import entities.HealthBar;
import entities.Player;
import entities.Snake;
import entities.SnakeBodyPart;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class GameScreen extends JPanel implements Runnable, KeyListener {
	
    private static final long serialVersionUID = 1L;
    
    // screen size constants
    private static final int WIDTH = 720, HEIGHT = 480, SQUARESIZE = 20;
    
    // create the snake
    private int xCoor, yCoor, snakeSize;
	private Snake snake;
	
    private Player player;
    private HealthBar healthBar;
    private int deathReason;
	
	// create the garbage
    private Garbage garbage;
    private long garbageDelayTime = System.currentTimeMillis();
    
    // new random generator
    private Random random = new Random();;
    
    // game movement and control variables 
    private boolean right, left, up, down, running;
    
    // images
    private BufferedImage backgroundImage;
    private BufferedImage headerImage;
    private BufferedImage deathImage;
    private BufferedImage deathReason1Image; // hits own body
    private BufferedImage deathReason2Image; // hits wall
    private BufferedImage deathReason3Image; // wrong color
   
    // constructor
    public GameScreen(Player player) {
    	this.player = player;
    	setFocusable(true);   	
    	addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	setVisible(true);
        
        start();
    	
        // load the images
        try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/gameScreenBackground.png"));
			headerImage = ImageIO.read(getClass().getResourceAsStream("/images/gameScreenHeader.png"));
			deathImage = ImageIO.read(getClass().getResourceAsStream("/images/gameOver.png"));
			deathReason1Image = ImageIO.read(getClass().getResourceAsStream("/images/hitsOwnBody.png"));
			deathReason2Image = ImageIO.read(getClass().getResourceAsStream("/images/hitsWall.png"));
			deathReason3Image = ImageIO.read(getClass().getResourceAsStream("/images/wrongColor.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    // method for create a new random garbage
    public void drawNewGarbage(){
    	int xCoor = random.nextInt(Math.round(WIDTH/SQUARESIZE));
    	int yCoor = random.nextInt(Math.round(HEIGHT/SQUARESIZE));
    	if (yCoor < 6) yCoor = 6;
    	int type = random.nextInt(4);
		this.garbage = new Garbage(xCoor, yCoor, type, SQUARESIZE);
		this.garbageDelayTime = System.currentTimeMillis();
		
    }
    
    public void start() {
    	// reset movement variables
    	resetMovement();
		this.right = true;
		
		// reset snake variables
    	this.xCoor = 5;
    	this.yCoor = 5;
    	this.snakeSize = 3;
    	this.snake = new Snake(SQUARESIZE);
    	
    	// reset player score
    	this.player.setScore(0);
    	
    	// reset the health bar
    	this.healthBar = new HealthBar(3, 500 ,50);
    	
    	drawNewGarbage();
        
    	// set running back to true
    	this.running = true;
        
    	Thread thread = new Thread(this);
        thread.start();
    }
 
    public void stop(int deathReason) {
    	this.deathReason = deathReason;
    	this.running = false;
    }
    
    public void run() {
    	long delayTime = System.currentTimeMillis();
     
    	while(running) {
    		// get current time in milliseconds
        	long currentTime = System.currentTimeMillis();
        	
        	// if the trash stays on the screen for more than 7 seconds draws a new garbage
            if (currentTime - garbageDelayTime >= 7000){
            	garbageDelayTime = currentTime;
            	drawNewGarbage();
            }
            
            // if pass 100 milliseconds update game
            if (currentTime - delayTime >= 101){
            	delayTime = currentTime;
            	
    			tick();
    			repaint();
            }
        }
    }
    
    // tick method
    public void tick() {
    	
    	// movement the snake
    	snake.addBodyPart(xCoor, yCoor);	
    	if(snake.getLength() > snakeSize) {
    		snake.getBody().remove(0); 	
        }
    	
    	// verify if the snake collects the garbage
    	if(xCoor == garbage.getX() && yCoor == garbage.getY()) {
    		
    		// verify if the snake color is different of the garbage color
    		if(snake.getColor() != garbage.getColor()){
    			
    			// remove one heart of the life bar
    			healthBar.takeDamage();
    			
    			// verify if the player don't have life
    			if(!healthBar.isHaveLife()){
        			stop(3);
        			return;
    			}
    			else{
    				drawNewGarbage();
    			}
    		}
    		else{ 
    			// if the color of the snake is the same of the garbage
	    		snakeSize++;
	    		drawNewGarbage();
	    		player.setScore(player.getScore() + 1);
    		}
    	}
    	
    	// verify if the snake hits her self
        for(int i = snake.getLength() - 2; i >= 0; i--) {
        	if(xCoor == snake.getBody().get(i).getX() && yCoor == snake.getBody().get(i).getY()) {
    			stop(1);
    			break;
        	}
        }
        
        // verify if the snake hits the walls
        if(xCoor < 0 || xCoor > 35 || yCoor < 5 || yCoor > 22){
        	stop(2);
        }
        
        // variables for movement the snake
    	if(right && !down && !up && !left){
    		xCoor++;
    	}
    	else if(left && !right && !down && !right){
    		xCoor--;
    	}
    	else if(up && !down && !right && !left){
    		yCoor--;
    	}
    	else if(down && !up && !right && !left){
    		yCoor++;
    	}
    	
    }
    
    // render method
    public void paint(Graphics g) {
    	
    	// clear the screen
    	g.clearRect(0, 0, WIDTH+10, HEIGHT+10);
    	
    	// draw background image
      	g.drawImage(backgroundImage, 0, 40, this);
    	
    	// paint the lines 
        g.setColor(new Color(62, 59, 53));
        for (int i = 0; i < WIDTH / SQUARESIZE; i++) {
            g.drawLine(i * SQUARESIZE, 0, i * SQUARESIZE, HEIGHT);
        }
        
        for (int i = 0; i < HEIGHT / SQUARESIZE; i++) {
            g.drawLine(0, i * SQUARESIZE, WIDTH, i * SQUARESIZE);
        }
        
        // draw the garbage in the screen
        garbage.draw(g);
        
        // paint the snake
        for (SnakeBodyPart bodyPart : snake.getBody()) {
            bodyPart.draw(g, this);
        }
        
        // draw header image
        g.setColor(Color.BLACK);
        g.drawImage(headerImage, 0, 0, this);
        
        // draw player name
        g.drawString(player.getNickname(), 13, 38);
        
        // draw score
        g.drawString("Pontos: " + player.getScore() , 13, 76);
        
        healthBar.draw(g);
        
        // verify if the player dies, paint the game over images
        if(!running) {
        	g.drawImage(deathImage, 180, 170, this);
        	
        	if(deathReason == 1){
        		// hits own body
        		g.drawImage(deathReason1Image, 190, 192,this);
        	}
        	
        	else if(deathReason == 2){
        		// hits wall
        		g.drawImage(deathReason2Image, 200, 193,this);
        	}
        	
        	else if(deathReason == 3){
        		// wrong color
        		g.drawImage(deathReason3Image, 190, 193,this);
        	}
        
        }
       
    }
	
    @Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key){
		
			case KeyEvent.VK_RIGHT:
				resetMovement();
				this.right = true;
				break;
			
			case KeyEvent.VK_LEFT:
				resetMovement();
				this.left = true;
				break;
				
			case KeyEvent.VK_UP:
				resetMovement();
				this.up = true;
				break;
				
			case KeyEvent.VK_DOWN:
				resetMovement();
				this.down = true;
				break;
				
			case KeyEvent.VK_SPACE:
				if(!running){
					// restart the game
					start();
				}
				break;
			
			case KeyEvent.VK_ESCAPE:
				if(!running){
					// back to main menu
					removeAll();
					dispose();
					new ShowMenuScreen(player);
				}
				break;
				
			case KeyEvent.VK_Q:
				// red
				snake.setColor(0);
				break;
			
			case KeyEvent.VK_W:
				// green
				snake.setColor(1);
				break;
				
			case KeyEvent.VK_E:
				// blue
				snake.setColor(2);
				break;
				
			case KeyEvent.VK_R:
				// yellow
				snake.setColor(3);
				break;
		}

	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
		
	}
	
	// set all movement variables to false
	public void resetMovement(){
		this.up = false;
		this.left = false;
		this.right = false;
		this.down = false;
	}
	
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}

}
