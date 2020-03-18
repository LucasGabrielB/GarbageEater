package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Player;

public class DatabaseConnection {
	private final String url = "jdbc:postgresql://localhost:5432/GarbageEater";
	private final String password = "yNBt3Vx4bm@_";
	private final String user = "postgres";
	private Connection connection;
	private boolean connected;
	
	public DatabaseConnection(){
		try {
			this.connection = getConnection();
			this.connected = true;
			
		} 
		catch (SQLException e) {
			this.connected = false;
		
		}

	}
	
	public void postPlayer(Player player){
		// if database is not connected return
		if (!connected) return;
		
		try {
			if(playerExistInDatabase(player)){	
				// if the current player's score is higher than the score saved in the bank
				if(player.getScore() > getPlayerScore(player)){
				
					// sql query for update the player score
					String sql = "update player set score = ? where nickname = ?";
					PreparedStatement statment = connection.prepareStatement(sql);			
					statment.setInt(1, player.getScore());
					statment.setString(2, player.getNickname());
					
					// execute sql query
					statment.execute();

				}
			
			}
			
			else{
				// sql query to insert a new player in the database
				String sql = "insert into player(nickname, score) values(?, ?)";
				PreparedStatement statment = connection.prepareStatement(sql);
				statment.setString(1, player.getNickname());
				statment.setInt(2, player.getScore());
				
				// execute sql query
				statment.execute();
			
			}
		
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		
		}
		
	}
	
	// method that return the top 10 players order by score
	public ArrayList<Player> getTop10(){
		// if database is not connected return a empty ArrayList
		if(!connected) return new ArrayList<Player>();
		
		try {
			// sql query to return the top 10 players order by score in descend order
			String sql = "select nickname, score from player order by score desc limit 10;";
			PreparedStatement statment = connection.prepareStatement(sql);
			
			// execute sql query
			statment.execute();
			
			// get the result of the query execution
			ResultSet resultSet = statment.getResultSet();
			
			// create the ArrayList containing the players
			ArrayList<Player> top10 = new ArrayList<Player>();
			
			// adds the 10 players to the array list
			while(resultSet.next()){
				Player player = new Player(resultSet.getString("nickname"), resultSet.getInt("score"));
				top10.add(player);
			}
			
			return top10;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			// if any error happens it returns an empty ArrayList
			return new ArrayList<Player>();
		}	
		
	}
	
	// method to verify if a player already exist in the database 
	private boolean playerExistInDatabase(Player player) throws SQLException{
		// sql query for select the player score
		String sql = "select nickname from player where nickname = ?;";
		PreparedStatement statment = connection.prepareStatement(sql);
		statment.setString(1, player.getNickname());
		
		// execute sql query
		statment.execute();
		
		// get the result of the query execution
		ResultSet resultSet = statment.getResultSet();
		
		// return if the player exist in the database
		return resultSet.next();

	}
	
	// method to get the score of one player in the database
	private int getPlayerScore(Player player) throws SQLException{
		// sql query for select the player score
		String sql = "select score from player where nickname = ?;";
		PreparedStatement statment = connection.prepareStatement(sql);
		statment.setString(1, player.getNickname());
		
		// execute sql query
		statment.execute();
		
		// get the result of the query execution
		ResultSet resultSet = statment.getResultSet();
		
		// return the player score
		resultSet.next();
		return resultSet.getInt("score");
		
	}
	
	// method to conecte to the database
	private Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;

	} 

}
