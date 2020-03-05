package entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.ConnectBD;

public class Player {
	
	private String username; 
	private int score; 
	
	private String getUsername() {
		return this.username; 
	}
	private void setUsername(String username) {
		this.username = username; 
	}
	private int getScore() {
		return this.score;
	}
	
	private void setScore(int score) {
		this.score = score;
	}
	
	private void insertPlayer(String username, int score) {
		this.username = username;
		this.score = score;
		ConnectBD conn = new ConnectBD();
		conn.getMySQLConnection();
		System.out.println(conn.statusConnection());
		
	    String sql = "INSERT INTO player(username, score) VALUES(?,?)";
	    try { 
	       PreparedStatement stmt = conn.prepareStatement(sql);
	       stmt.setString(1, this.username);
	       stmt.setInt(2, this.score);
	       stmt.execute();
	       stmt.close();
	       conn.CloseConnection();
	       
	     }catch (SQLException u) { 
	    	 throw new RuntimeException(u);
	     } 
	        
	 } 	

}
	
	
	
	
	

