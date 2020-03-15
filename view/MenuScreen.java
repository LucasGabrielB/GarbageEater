package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import entities.Player;

public class MenuScreen extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// screen size constants
    private static final int WIDTH = 720, HEIGHT = 480;
    
    // create the player
    private Player player;
    
    // images
    private BufferedImage backgroundImage;
    private BufferedImage startButtonImage;
    private BufferedImage exitButtonImage;
    private BufferedImage helpButtonImage;
    private BufferedImage rankImage;
	
	public MenuScreen(Player player){
		this.player = player;
		setFocusable(true);
		setLayout(null); 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        // read images
        try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/menuScreenBackground.png"));
			startButtonImage = ImageIO.read(getClass().getResourceAsStream("/images/startButton.png"));
			exitButtonImage = ImageIO.read(getClass().getResourceAsStream("/images/exitButton.png"));
			helpButtonImage = ImageIO.read(getClass().getResourceAsStream("/images/helpButton.png"));
			rankImage = ImageIO.read(getClass().getResourceAsStream("/images/rank.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
           
        // initializes all screen components
        initComponents();
       
	}
        
	public void initComponents() {	
		// create the components
		JButton playButton = new JButton(new ImageIcon(startButtonImage));
		JButton helpButton = new JButton(new ImageIcon(helpButtonImage));
		JButton exitButton = new JButton(new ImageIcon(exitButtonImage));
		JLabel backgorundLabel = new JLabel(new ImageIcon(backgroundImage));
		JLabel rankBackgroundLabel = new JLabel(new ImageIcon(rankImage));
		JTextField playerNameTextField = new JTextField(player.getNickname(), 20);
		JTextArea rankText = new JTextArea("1° -\n2° -\n3° -\n4° -\n5° -\n6° -\n7° -\n8° -\n9° -\n10° -");
		
		// define the position and size of the components
		playerNameTextField.setBounds(40, 410, 200, 40);
		playButton.setBounds(40, 250, 100, 40);
		helpButton.setBounds(40, 300, 100, 40);
		exitButton.setBounds(40, 350, 100, 40);
		backgorundLabel.setBounds(0, 0, 720, 480);
		rankBackgroundLabel.setBounds(440, 140, 275, 350);
		rankText.setBounds(450, 200, 275, 350);
		
		// define components properties
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);		
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		playerNameTextField.setOpaque(false);
		playerNameTextField.setForeground(Color.white);
		rankText.setOpaque(false);
		rankText.setForeground(Color.WHITE);
		rankText.setFont(new Font("Arial Bold", Font.ITALIC, 22));
		rankText.setEnabled(false);
		
		// add the components in the screen
		add(rankText);
		add(rankBackgroundLabel);
		add(playerNameTextField);
		add(exitButton);
		add(playButton);
		add(helpButton);
		add(backgorundLabel);
		
		// method if the play button is pushed		
		playButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  String playerName = playerNameTextField.getText().trim();
				  // check if the player typed a name
				  if(playerName.length() == 0 || playerNameTextField.getText().equals("Digite seu nome aqui!")){
					  new WarningScreen("ERRO:",  "Digite um nome para o jogador!");
				  }
				  
				  else if(playerName.length() > 20){
					  new WarningScreen("ERRO:", "Nome do jogador muito GRANDE!");
				  }
				  
				  else{
					  player.setNickname(playerName);
					  dispose();
					  removeAll();
					  new ShowGameScreen(player);
				  }
			  }
		}); 
		
		// method if the exit button is pushed
		exitButton.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  System.exit(0);  
			  }
		});
		
		// method executed every time a new character is typed,
		// to prevent the text from exceeding the limit size
		playerNameTextField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		    	// if the text size is greater than 20 does t let the player type
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
