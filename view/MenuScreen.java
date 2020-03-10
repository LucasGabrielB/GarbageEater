package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import entities.Player;

public class MenuScreen  extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// screen size constants
    public static final int WIDTH = 720, HEIGHT = 480;
	
	public MenuScreen(){
		setFocusable(true);
		setLayout(null); 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        showScreen();
        
	}
	
	public void showScreen() {
		JButton playButton = new JButton("Jogar");
		JButton quitButton = new JButton("Sair");
		playButton.setBounds(50, 100, 100, 40);
		quitButton.setBounds(50, 150, 100, 40);
		add(quitButton);
		add(playButton);
		
		playButton.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  Player player = new Player("Rogerinho piroca de foice");
				  GameScreen gameScreen = new GameScreen(player);
				 
				  add(gameScreen);           
			  }
		}); 
		
		quitButton.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  System.exit(0);  
			  }
		});
		
	}
	
}
