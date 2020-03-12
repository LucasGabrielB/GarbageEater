package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class WarningScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public WarningScreen(String title, String message) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 100));
		setTitle(title);
		setResizable(false);
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	
    	// initializes all screen components
        initComponents();
	}
	
	private void initComponents() {
		// TODO add components
	}


}
