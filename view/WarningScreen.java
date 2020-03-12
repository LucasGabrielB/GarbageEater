package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WarningScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	
	// attributes
	private String message;
	
	public WarningScreen(String title, String message) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(300, 130));
		setTitle(title);
		this.message = message;
		setResizable(false);
		setLayout(null); 
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	setFocusable(true);	
    	
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

}
