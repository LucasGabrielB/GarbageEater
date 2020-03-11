package view;

import javax.swing.JFrame;
import entities.Player;

public class Main {
	
	// create the frame
	private JFrame frame;
    
	public void showMenuScreen(Player player) {
		// create a new menu screen
		MenuScreen menuScreen = new MenuScreen(player);
    	
		frame = new JFrame();
    	frame.add(menuScreen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Garbage Eater");
    	frame.setResizable(false);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true); 
    	
    }
	
	public void showGameScren(Player player){
		// create a new game screen	
    	GameScreen gameScreen = new GameScreen(player);
		
    	frame = new JFrame();
    	frame.add(gameScreen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Garbage Eater");
    	frame.setResizable(false);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true); 
		
	}
	
    public static void main(String[] args) {
    	Player player = new Player("Digite seu nome aqui!");
    	new Main().showMenuScreen(player);
    }

}