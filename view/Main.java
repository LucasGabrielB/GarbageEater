package view;

import javax.swing.JFrame;
import entities.Player;

public class Main {
	
	public static final int WIDTH = 720, HEIGHT = 480;
	
	// create the player
	public Player player;
    
	public Main() {
    	JFrame frame = new JFrame();
    	player = new Player("Rogerinho piroca de foice");
    	GameScreen screen = new GameScreen(player);
    	
    	frame.add(screen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Garbage Drop");// TODO change game name !!!
    	frame.setResizable(false);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);  	
    	
    }
    public static void main(String[] args) {
        new Main();
    }
}