package main;

import database.DatabaseConnection;
import entities.Player;
import view.MenuScreen;

public class Main {
	
    public static void main(String[] args) {
    	 
    	DatabaseConnection databaseConnection = new DatabaseConnection();
    	Player player = new Player("Digite seu nome aqui!");
    	
    	new MenuScreen(player, databaseConnection);
    }

}