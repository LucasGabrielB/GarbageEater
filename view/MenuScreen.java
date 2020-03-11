package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import entities.Player;

public class MenuScreen  extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// screen size constants
    public static final int WIDTH = 720, HEIGHT = 480;
    
    // create the player
    public Player player;
	
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
		JButton playButton = new JButton("Jogar");
		JButton quitButton = new JButton("Sair");
		JTextField playerNameTextField = new JTextField(player.getNickname(), 20);
		
		// define the position and size of the components
		playerNameTextField.setBounds(10, 10, 200, 40);
		playButton.setBounds(50, 100, 100, 40);
		quitButton.setBounds(50, 150, 100, 40);
		
		// add the components in the screen
		add(playerNameTextField);
		add(quitButton);
		add(playButton);
		
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
