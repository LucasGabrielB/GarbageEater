package view;

import javax.swing.JFrame;
import entities.Player;

public class ShowGameScreen {
	// create the frame
	private JFrame frame;
    
	public ShowGameScreen(Player player){
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

}
