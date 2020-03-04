package view;

import java.awt.Color;
import entities.Garbage;
import entities.Snake;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class GameScreen extends JPanel implements Runnable, KeyListener {
 
    private static final long serialVersionUID = 1L;
    
    // screen size constants
    public static final int WIDTH = 720, HEIGHT = 480, SQUARESIZE = 20;
    
    // create the snake
    private int xCoor = 5, yCoor = 5, snakeSize = 5;
	Snake snake = new Snake(snakeSize, SQUARESIZE);
	
	// create the garbage
    private Garbage garbage;
    
    // new random generator
    private Random random = new Random();;
    
    // game movement and control variables 
    private boolean right = true, left = false, up = false, down =false,
    		running = false, spaceBar = false;
    
    // imagens
    private BufferedImage backgroundImg;
    private BufferedImage headerImg;
   
    // constructor
    public GameScreen() {
    	setFocusable(true);   	
    	addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        drawNewGarbage();
        start();
    	// load the imagens
        try {
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("/images/background.png"));
			headerImg = ImageIO.read(getClass().getResourceAsStream("/images/header.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    // method for create a new random garbage
    public void drawNewGarbage(){
    	int xCoor = random.nextInt(Math.round(WIDTH/SQUARESIZE));
    	int yCoor = random.nextInt(Math.round(HEIGHT/SQUARESIZE));
    	if (yCoor < 5) yCoor = 5;
    	int type = random.nextInt(4);
		garbage = new Garbage(xCoor, yCoor, type,SQUARESIZE);
    }
    
    // tick method
    public void tick() {
    	
    	// movement the snake
    	snake.addBodyPart(xCoor, yCoor, SQUARESIZE);	
    	if(snake.getLength() > snakeSize) {
    		snake.getBody().remove(0); 	
        }
    	
    	// verify if the snake collects the garbage
    	if(xCoor == garbage.getX() && yCoor == garbage.getY()) {
    		// verify if the snake color is different of the garbage color
    		if(snake.getColor() != garbage.getType()){
    			stop("incorect color");
    			return;
    		}
    		else{
	    		snakeSize++;
	    		drawNewGarbage(); 
    		}
    	}
    	
    	// verify if the snake hits her self
        for(int i =0; i < snake.getLength(); i++) {
        	if(xCoor == snake.getBody().get(i).getX() && yCoor == snake.getBody().get(i).getY()) {
        		if(i != snake.getLength() - 1) {
        			stop("hits own body");
        		}
        	}
        }
        
        // verify if the snake hits the walls
        if(xCoor < 0 || xCoor > Math.round(WIDTH/SQUARESIZE) || yCoor < 5 || yCoor > Math.round(HEIGHT/SQUARESIZE)){
        	stop("hits wall");
        }
        
        // variables for movement the snake
    	if(right){
    		xCoor++;
    	}
    	if(left){
    		xCoor--;
    	}
    	if(up){
    		yCoor--;
    	}
    	if(down){
    		yCoor++;
    	}
    	
    	// space bar pressed, change snake color
    	if(spaceBar){
    		snake.changeColor();
    		spaceBar = false;
    	}
    	
    }
    
    // render method
    public void paint(Graphics g) {
    	// clear the screen
    	g.clearRect(0, 0, WIDTH, HEIGHT);
    	
    	//draw background image
      	g.drawImage(backgroundImg, 0, 0, this);
    	
    	// paint the lines 
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < WIDTH / SQUARESIZE; i++) {
            g.drawLine(i * SQUARESIZE, 0, i * SQUARESIZE, HEIGHT);
        }
        
        for (int i = 0; i < HEIGHT / SQUARESIZE; i++) {
            g.drawLine(0, i * SQUARESIZE, WIDTH, i * SQUARESIZE);
        }
        
        // paint the garbage
        garbage.draw(g);
        
        // paint the snake
        for (int i = 0; i < snake.getLength(); i++) {
            snake.getBody().get(i).draw(g);
        }
        // draw header image
        g.setColor(Color.BLACK);
        g.drawImage(headerImg, 0, 0, this);
        // draw score
        g.drawString("Pontos : " + (snakeSize - 5), 40, 30);
    }
 
    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }
 
    public void stop(String why) {
    	// TODO add death screen !!!
    	System.out.println(why);
    	running = false;
    }
 
    public void run() {
        while (running) {
            tick();
            repaint();
            
            // delay of 100 milliseconds
        	try{
    			Thread.sleep(100);
    		} 
        	catch(InterruptedException e) {
    			e.printStackTrace();
    		}         
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT && !left) {
			up = false;
			down = false;
			right = true;
		}
		
		if(key == KeyEvent.VK_LEFT && !right) {
			up = false;
			down = false;
			left = true;
		}
		
		if(key == KeyEvent.VK_UP && !down) {
			left = false;
			right = false;
			up = true;
		}
		
		if(key == KeyEvent.VK_DOWN && !up) {
			left = false;
			right = false;
			down = true;
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			// TODO add exit to start screen
		}
		
		if(key == KeyEvent.VK_SPACE){
			spaceBar = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
		
	}

}
