package view;

import javax.swing.JFrame;

import database.DatabaseConnection;
import entities.Player;

public class ShowGameScreen {
	// create the frame
	private JFrame frame;
    
	public ShowGameScreen(Player player, DatabaseConnection databaseConnection){
		// create a new game screen	
    	GameScreen gameScreen = new GameScreen(player, databaseConnection);
		
    	frame = new JFrame();
    	frame.setSize(725, 488);
    	frame.add(gameScreen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Garbage Eater");
    	frame.pack();
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true); 

	}

}
