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
	
	// create the garbage
    private Garbage garbage;
    
    // new random generator
    private Random random = new Random();;
    
    // game movement and control variables 
    private boolean right, left, up, down, running;
    
    // images
    private BufferedImage backgroundImage;
    private BufferedImage headerImage;
    private BufferedImage deathImage;
   
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
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/background.png"));
			headerImage = ImageIO.read(getClass().getResourceAsStream("/images/header.png"));
			deathImage = ImageIO.read(getClass().getResourceAsStream("/images/gameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    // method for create a new random garbage
    public void drawNewGarbage(){
    	int xCoor = random.nextInt(Math.round(WIDTH/SQUARESIZE));
    	int yCoor = random.nextInt(Math.round(HEIGHT/SQUARESIZE));
    	if (yCoor <= 5) yCoor = 6;
    	int type = random.nextInt(4);
		garbage = new Garbage(xCoor, yCoor, type, SQUARESIZE);
    }
    
    public void start() {
    	// reset movement variables
    	up = false;
		down = false;
		left = false;
		right = true;
		
		// reset snake variables
    	xCoor = 5;
    	yCoor = 5;
    	snakeSize = 3;
    	snake = new Snake(SQUARESIZE);
    	
    	// reset player score
    	player.setScore(0);
    	
    	// reset the health bar
    	healthBar = new HealthBar(3, 500 ,50);
    	
    	drawNewGarbage();
        
    	// set running back to true
    	running = true;
        
    	Thread thread = new Thread(this);
        thread.start();
    }
 
    public void stop(String why) {
    	// print why the snake die
    	System.err.println(why);
    	running = false;
    }

    public void run() {
        while (running) {

            tick();
            repaint();
     
        	try{
    			Thread.sleep(100);
    		} 
        	catch(InterruptedException e) {
        		Thread.currentThread().interrupt();
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
        			stop("incorect color");
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
        for(int i =0; i < snake.getLength(); i++) {
        	if(xCoor == snake.getBody().get(i).getX() && yCoor == snake.getBody().get(i).getY()) {
        		if(i != snake.getLength() - 1) {
        			stop("hits own body");
        			return;
        		}
        	}
        }
        
        // verify if the snake hits the walls
        if(xCoor < 0 || xCoor >= Math.round(WIDTH/SQUARESIZE) || yCoor < 5 || yCoor >= Math.round(HEIGHT/SQUARESIZE)){
        	stop("hits wall");
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
            bodyPart.draw(g);
        }
        
        // draw header image
        g.setColor(Color.BLACK);
        g.drawImage(headerImage, 0, 0, this);
        
        // draw player name
        g.drawString(player.getNickname(), 13, 38);
        
        // draw score
        g.drawString("Pontos: " + player.getScore() , 13, 76);
        
        healthBar.draw(g);
        
        if(!running) {
        	g.drawImage(deathImage, 180, 170, this);
        }
       
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_RIGHT && !left) {
			left = false;
			up = false;
			down = false;
			right = true;
		}
		
		else if(key == KeyEvent.VK_LEFT && !right) {
			right = false;
			up = false;
			down = false;
			left = true;
		}
		
		else if(key == KeyEvent.VK_UP && !down) {
			down = false;
			left = false;
			right = false;
			up = true;
		}
		
		else if(key == KeyEvent.VK_DOWN && !up) {
			up = false;
			left = false;
			right = false;
			down = true;
		}

		if(key == KeyEvent.VK_SPACE && !running){
			// restart the game
			start();
		}
		
		if(key == KeyEvent.VK_ESCAPE && !running){
			// back to main menu
			removeAll();
			dispose();
			new ShowMenuScreen(player);
		}
		
		if(key == KeyEvent.VK_Q){
			// red
			snake.setColor(0);
		}
		
		if(key == KeyEvent.VK_W){
			// green
			snake.setColor(1);
		}
		
		if(key == KeyEvent.VK_E){
			// blue
			snake.setColor(2);
		}
		
		if(key == KeyEvent.VK_R){
			// yellow
			snake.setColor(3);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
		
	}
	
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}

}
