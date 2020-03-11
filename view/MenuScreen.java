package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.javafx.tk.Toolkit;

import entities.Player;

public class MenuScreen  extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// screen size constants
    public static final int WIDTH = 720, HEIGHT = 480;
    
    // create the player
    public Player player;
    private Image backgroundImg;
    private Image startButton;
    private Image exitButton;
	
	public MenuScreen(Player player){
		this.player = player;
		setFocusable(true);
		setLayout(null); 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        
        // initializes all screen components
        initComponents();      
	}
        
	public void initComponents() {	
		// create the components
		JButton playButton = new JButton(new ImageIcon("images/btnStart.png"));
		JButton helpButton = new JButton(new ImageIcon("images/btnHelp.png"));
		JButton quitButton = new JButton(new ImageIcon("images/btnExit.png"));
		JLabel back = new JLabel(new ImageIcon("images/menuBackground.gif")); 
		JTextField playerNameTextField = new JTextField(player.getNickname(), 20);
		// define the position and size of the components
		playerNameTextField.setBounds(40, 410, 200, 40);
		playButton.setBounds(40, 250, 100, 40);
		helpButton.setBounds(40, 300, 100, 40);
		quitButton.setBounds(40, 350, 100, 40);
		back.setBounds(0,0,720,480);
		
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);		
		quitButton.setOpaque(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setBorderPainted(false);
		playerNameTextField.setOpaque(false);
		playerNameTextField.setForeground(Color.white);
		
		// add the components in the screen
		add(playerNameTextField);
		add(quitButton);
		add(playButton);
		add(helpButton);
		add(back);
		
		// method if the play button is pushed		
		playButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  String playerName = playerNameTextField.getText().trim();
				  // check if the size of the player's name is not too short or too big
				  if(playerName.length() < 3){
					  JOptionPane.showMessageDialog(null, "Nome do jogador muito CURTO!", "ERRO:", 0);
				  }
				  
				  else if(playerName.length() > 20){
					  JOptionPane.showMessageDialog(null, "Nome do jogador muito GRANDE!", "ERRO:", 0);
				  }
				  
				  else{
					  player.setNickname(playerName);
					  dispose();
					  removeAll();
					  new Main().showGameScren(player);
				  }
			  }
		}); 
		
		// method if the quit button is pushed
		quitButton.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  System.exit(0);  
			  }
		});
		
		// method executed every time a new character is typed,
		// to prevent the text from exceeding the limit size
		playerNameTextField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		    	// if the text size is greater than 20 doesn’t let the player type
		        if (playerNameTextField.getText().length() >= 20 )
		            e.consume();
		    }  
		});
		
		// method to automatically clear the text field when when get focus
		playerNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (playerNameTextField.getText().equals("Digite seu nome aqui!")){
					playerNameTextField.setText("");
                }
			}
		});
	}
	
	// method to dispose this JPanel
	public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}
	
}
