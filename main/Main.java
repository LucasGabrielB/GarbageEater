package main;

import entities.Player;
import view.ShowMenuScreen;

public class Main {
	
    public static void main(String[] args) {
    	Player player = new Player("Digite seu nome aqui!");
    	new ShowMenuScreen(player);
    }

}