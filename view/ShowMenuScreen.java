package view;

import javax.swing.JFrame;

import database.DatabaseConnection;
import entities.Player;

public class ShowMenuScreen {
	// create the frame
	private JFrame frame;
    
	public ShowMenuScreen(Player player, DatabaseConnection databaseConnection) {
		// create a new menu screen
		MenuScreen menuScreen = new MenuScreen(player, databaseConnection);
    	
		frame = new JFrame();
    	frame.add(menuScreen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Garbage Eater");
    	frame.setResizable(false);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true); 
    	
    }

}
