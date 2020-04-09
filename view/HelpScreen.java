package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpScreen  extends JFrame implements KeyListener{
	private static final long serialVersionUID = -1333646209090714823L;
	
	// images
    private BufferedImage backgroundImage;

	public HelpScreen(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(620, 380));
		setUndecorated(true);
		setResizable(false);
		setLayout(null); 
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	setFocusable(true);
    	addKeyListener(this);
    	
    	try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/helpScreenBackground.png"));
		
    	} 
    	catch (IOException e) {
			e.printStackTrace();
		
    	}
    	
    	// initializes all screen components
        initComponents();
	}
	
	public void initComponents(){
		// create the components
		JButton okButton = new JButton();
		JLabel backgorundLabel = new JLabel(new ImageIcon(backgroundImage));
		
		// define components properties
		okButton.setBounds(295, 340, 55, 30);
		backgorundLabel.setBounds(0, 0, 620, 380);
		
		setFont(new Font("arial", Font.PLAIN, 10));
		okButton.setText("Ok");
		
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
