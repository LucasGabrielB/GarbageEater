package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.Main.Sprites;

public class WarningScreen extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	
    // images
    private BufferedImage backgroundImage;
    private BufferedImage okButtonImage;
    
	
	public WarningScreen() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(300, 130));
		setUndecorated(true);
		setResizable(false);
		setLayout(null); 
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	setFocusable(true);
    	addKeyListener(this);
    	
		backgroundImage = Sprites.getWarningScreenBackground();
		okButtonImage = Sprites.getOkButton();
			
    	
    	// initializes all screen components
        initComponents();
	}
	
	private void initComponents() {
		// create the components
		JButton okButton = new JButton();
		JLabel backgorundLabel = new JLabel(new ImageIcon(backgroundImage));
		
		// define components properties
		okButton.setBounds(115, 100, 70, 20);
		backgorundLabel.setBounds(0, 0, 300, 130);
		
		// redimensionaste "OK" button image
		ImageIcon okButtonIcon = new ImageIcon(okButtonImage);
		okButtonIcon.setImage(okButtonIcon.getImage().getScaledInstance(70, 20, 1));
		// add image to the button
		okButton.setIcon(okButtonIcon);
		okButton.setOpaque(false);
		okButton.setContentAreaFilled(false);
		okButton.setBorderPainted(false);
		
		// add the components in the screen
		add(okButton);
		add(backgorundLabel);
		
		// method to close this window
		okButton.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  dispose(); 
			  }
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER){
			dispose();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
