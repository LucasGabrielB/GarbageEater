package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WarningScreen extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	// attributes
	private String message;
	
	public WarningScreen(String message) {
		this.message = message;
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
    	
    	// initializes all screen components
        initComponents();
	}
	
	private void initComponents() {
		// create the components
		JButton okButton = new JButton();
		JLabel messageLabel = new JLabel(message);
		
		// define components properties
		okButton.setBounds(128, 65, 55, 30);
		messageLabel.setBounds(20, 20, 280, 100);
		messageLabel.setVerticalAlignment(SwingConstants.TOP);
		setFont(new Font("arial", Font.PLAIN, 10));
		okButton.setText("Ok");
		
		// add the components in the screen
		add(messageLabel);
		add(okButton);
		
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
