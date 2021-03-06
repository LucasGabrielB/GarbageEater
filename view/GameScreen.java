package view;

import java.awt.Color;
import entities.RecycleBinColor;
import entities.DeathReason;
import entities.Direction;
import entities.Garbage;
import entities.HealthBar;
import entities.Player;
import entities.Snake;
import entities.SnakeBodyPart;
import main.Main.Sprites;
import soundEffects.SoundEffect;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import database.DatabaseConnection;
 
public class GameScreen {
	// create the frame
	private JFrame frame;
	
	// screen size constants
	private final int SCREEN_WIDTH = 720, SCREEN_HEIGHT = 480;
    
	public GameScreen(Player player){
		// create a new game screen	
    	JPanelGameScreen showgameScreen = new JPanelGameScreen(player);
		
    	frame = new JFrame();
    	frame.pack();
    	frame.setSize(SCREEN_WIDTH + 5, SCREEN_HEIGHT + 8);
    	frame.add(showgameScreen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Garbage Eater");
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true); 

	}
	// game logic main class
	public class JPanelGameScreen extends JPanel implements Runnable, KeyListener {
		
		private static final long serialVersionUID = 5185200045295559812L;

		// square size constants
	    private final int SQUARESIZE = 20;
	    
	    // create the snake
	    private int xCoor, yCoor, snakeSize;
		private Snake snake;
		
		// create the player
	    private Player player;
	    private HealthBar healthBar;
	    private DeathReason deathReason;
		
		// create the garbage
	    private Garbage garbage;
	    private long garbageDelayTime = System.currentTimeMillis();
	    
	    // new random generator
	    private Random random = new Random();;
	    
	    // game movement and directions control
	    private LinkedList<Direction> directions;
	    private boolean running;
	    
	    // images
	    private BufferedImage backgroundImage;
	    private BufferedImage headerImage;
	    private BufferedImage deathImage;
	    private BufferedImage deathReason1Image; // hits own body
	    private BufferedImage deathReason2Image; // hits wall
	    private BufferedImage deathReason3Image; // wrong color
	    
	    // sound effects
	    private SoundEffect backgroundMusicSound;
	    private SoundEffect deathSound;
	    private SoundEffect eatSound;
	    private SoundEffect wrongColorSound;
	   
	    // constructor
	    public JPanelGameScreen(Player player) {
	    	this.player = player;
	    	setFocusable(true);   	
	    	addKeyListener(this);
	        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	    	setVisible(true);
	    	
	        // load images
			backgroundImage = Sprites.getGameScreenBackground();
			headerImage = Sprites.getGameScreenHeader();
			deathImage = Sprites.getGameOver();
			deathReason1Image = Sprites.getHitsOwnBody();
			deathReason2Image = Sprites.getHitsWall();
			deathReason3Image = Sprites.getWrongColor();

	        // initialize sounds
	        backgroundMusicSound = new SoundEffect("/soundEffects/sounds/backgroundMusic.wav");
	        deathSound = new SoundEffect("/soundEffects/sounds/death.wav");
	        eatSound = new SoundEffect("/soundEffects/sounds/eat.wav");
	        wrongColorSound = new SoundEffect("/soundEffects/sounds/wrongColor.wav");
	
	        // play background music continuously
	        backgroundMusicSound.loop();
	        
	    	// start the game
	        start();
	    
	    }
	   
	    // method for create a new random garbage
	    public void drawNewGarbage(){
	    	int xCoor = random.nextInt(Math.round(SCREEN_WIDTH/SQUARESIZE));
	    	int yCoor = random.nextInt(Math.round(SCREEN_HEIGHT/SQUARESIZE));
	    	if (yCoor < 6) yCoor = 6;
	    	
	    	// check if the garbage will spawn in a place where the snake is
	    	for (SnakeBodyPart bodyPart : snake.getBody()) {
	    		//if so generate another garbage
	            if(xCoor == bodyPart.getX() && yCoor == bodyPart.getY()){
	            	drawNewGarbage();
	            	return;
	             
	            }
	    	
	    	}
	    	
	    	RecycleBinColor color = RecycleBinColor.getRandomColor();
			this.garbage = new Garbage(xCoor, yCoor, color, SQUARESIZE);
			this.garbageDelayTime = System.currentTimeMillis();
			
	    }
	    
	    public void start() {
	    	// reset movement, set snake to move right
	    	this.directions = new LinkedList<>();
			directions.add(Direction.RIGHT);
			
			// reset snake variables
	    	this.xCoor = 5;
	    	this.yCoor = 5;
	    	this.snakeSize = 3;
	    	this.snake = new Snake();
	    	
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
	 
	    public void stop(DeathReason deathReason) {
	    	// post player in the database
	    	DatabaseConnection.postPlayer(player);
	    	
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
	            if (currentTime - delayTime >= 100){
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
	    				// play death sound play
	    				deathSound.play();
	        			
	    				stop(DeathReason.WRONG_COLOR);
	        			return;
	    			
	    			}
	    			else{
	    				// play wrong color sound
	        			wrongColorSound.play();
	    				
	        			drawNewGarbage();
	    			
	    			}
	    		
	    		}
	    		else{ 
	    			// if the color of the snake is the same of the garbage
	    			eatSound.play();
		    		snakeSize++;
		    		drawNewGarbage();
		    		player.setScore(player.getScore() + 1);
	    		
	    		}
	    	
	    	}
	    	
	    	// verify if the snake hits her self
	        for(int i = snake.getLength() - 2; i >= 0; i--) {
	        	if(xCoor == snake.getBody().get(i).getX() && yCoor == snake.getBody().get(i).getY()) {
	        		// play death sound play
	    			deathSound.play();
	    			
	    			stop(DeathReason.HITS_OWN_BODY);
	    			break;
	        	
	        	}
	        
	        }
	        
	        // verify if the snake hits the walls
	        if(xCoor < 0 || xCoor > 35 || yCoor < 5 || yCoor > 22){
	        	// play death sound play
				deathSound.play();
	        	
				stop(DeathReason.HITS_WALL);
	        
	        }
	        
	        // movement the snake
	        Direction direction = directions.peekFirst();
	        
	        switch (direction) {
				case UP:
					yCoor--;
					break;
		
				case DOWN:
					yCoor++;
					break;
					
				case RIGHT:
					xCoor++;
		    		break;
		    		
				case LEFT:
					xCoor--;
		    		break;
			
	        }
	        
			 // if more than one direction is in the queue, poll it to read new input
	        if(directions.size() > 1) {
				directions.poll();
			
	        }
	    	
	    }
	    
	    // render method
	    public void paint(Graphics g) {
	    	
	    	// clear the screen
	    	g.clearRect(0, 0, SCREEN_WIDTH+10, SCREEN_HEIGHT+10);
	    	
	    	// draw background image
	      	g.drawImage(backgroundImage, 0, 40, this);
	    	
	    	// paint the lines 
	        g.setColor(new Color(62, 59, 53));
	        for (int i = 0; i < SCREEN_WIDTH / SQUARESIZE; i++) {
	            g.drawLine(i * SQUARESIZE, 0, i * SQUARESIZE, SCREEN_HEIGHT);
	        
	        }
	        
	        for (int i = 0; i < SCREEN_HEIGHT / SQUARESIZE; i++) {
	            g.drawLine(0, i * SQUARESIZE, SCREEN_WIDTH, i * SQUARESIZE);
	        
	        }
	        
	        // draw the garbage in the screen
	        garbage.draw(g);
	        
	        snake.draw(g);
	        
	        // draw header image
	        g.drawImage(headerImage, 0, 0, this);
	        
	        // draw player name and score
	        g.setColor(Color.BLACK);
	        g.setFont(new Font("Arial", Font.BOLD, 12));
	        g.drawString(player.getNickname(), 13, 38);
	        g.drawString("Score: " + player.getScore() , 13, 76);
	        
	        // draw health bar
	        healthBar.draw(g);
	        
	        // verify if the player dies, paint the game over images
	        if(!running) {
	        	g.drawImage(deathImage, 180, 170, this);
	        	
	        	switch(deathReason){
	        	
		        	case HITS_OWN_BODY:
		        		g.drawImage(deathReason1Image, 190, 192,this);
		        		break;
		        	
		        	case HITS_WALL:
		        		g.drawImage(deathReason2Image, 200, 193,this);
		        		break;
		        	
		        	case WRONG_COLOR:
		        		g.drawImage(deathReason3Image, 190, 193,this);
		        		break;
		        	
	        	}
	        
	        }
	       
	    }
		
	    @Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			Direction last = directions.peekLast();
			
			switch(key){
			
				case KeyEvent.VK_RIGHT:				
					if(last != Direction.LEFT && last != Direction.RIGHT) {
						directions.addLast(Direction.RIGHT);
					
					}	
					break;
				
				case KeyEvent.VK_LEFT:			
					if(last != Direction.LEFT && last != Direction.RIGHT) {
						directions.addLast(Direction.LEFT);
					
					}
					break;
					
				case KeyEvent.VK_UP:		
					if(last != Direction.DOWN && last != Direction.UP) {
						directions.addLast(Direction.UP);
					
					}
					break;
					
				case KeyEvent.VK_DOWN:		
					if(last != Direction.DOWN && last != Direction.UP) {
						directions.addLast(Direction.DOWN);
					
					}
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
						backgroundMusicSound.stop();
						new MenuScreen(this.player);
					
					}
					break;
					
				case KeyEvent.VK_Q:
					snake.setColor(RecycleBinColor.RED);
					break;
				
				case KeyEvent.VK_W:
					snake.setColor(RecycleBinColor.GREEN);
					break;
					
				case KeyEvent.VK_E:
					snake.setColor(RecycleBinColor.BLUE);
					break;
					
				case KeyEvent.VK_R:
					snake.setColor(RecycleBinColor.YELLOW);
					break;
			
			}
	
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {	
			
		}
		// method to dispose this JPanel
		public void dispose() {
		    JFrame parent = (JFrame) this.getTopLevelAncestor();
		    parent.dispose();
		
		}
	
	}

}