package view;

import javax.swing.JFrame;

public class Main {
	
	public static final int WIDTH = 720, HEIGHT = 480;
    
	public Main() {
    	//ImageIcon img = new ImageIcon(getClass().getResource("fundo.png"));
		//JLabel label = new JLabel(img);
    	JFrame frame = new JFrame();
    	GameScreen screen = new GameScreen();
    	
    	frame.add(screen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Snake game");
    	frame.setResizable(false);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);  	
    	
    	//frame.removeAll(); para adicionar outra tela?
    	
    }
    public static void main(String[] args) {
        new Main();
    }
}