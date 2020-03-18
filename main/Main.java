package main;

import database.DatabaseConnection;
import entities.Player;
import view.ShowMenuScreen;

public class Main {
	
    public static void main(String[] args) {
    	
    	DatabaseConnection databaseConnection = new DatabaseConnection();
    	Player player = new Player("Digite seu nome aqui!");
    	
    	new ShowMenuScreen(player, databaseConnection);
    }

}