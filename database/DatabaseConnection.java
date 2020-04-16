package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.Player;

public final class DatabaseConnection {
	// temporary DatabaseConnection object use to get a safer class path 
	private static final DatabaseConnection tempDBC = new DatabaseConnection();
	private static final String databaseFilePath = tempDBC.getClass().getResource("db/GarbageEater.db").toString();
	// database URL
	private static final String url = "jdbc:sqlite::resource:" + DatabaseConnection.databaseFilePath;
	
	public static final void postPlayer(Player player){
		try {
			Connection connection = getConnection();
			
			if(playerExistInDatabase(player, connection)){	
				// if the current player's score is higher than the score saved in the bank
				if(player.getScore() > getPlayerScore(player, connection)){
				
					// sql query for update the player score
					String sql = "update player set score = ? where nickname = ?";
					PreparedStatement statment = connection.prepareStatement(sql);			
					statment.setInt(1, player.getScore());
					statment.setString(2, player.getNickname());
					
					// execute sql query
					statment.execute();
				
					// close connections
					statment.close();
					connection.close();
					
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
			
				// close connections
				statment.close();
				connection.close();
				
			}
		
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		
		}
		
	}
	
	// method that return the top 10 players order by score
	public static final ArrayList<Player> getTop10() throws Exception{

		Connection connection = getConnection();
		
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
		
		// close connections
		connection.close();
		statment.close();
		
		return top10;
		
	}
	
	// method to verify if a player already exist in the database 
	private static final boolean playerExistInDatabase(Player player, Connection connection) throws SQLException{
		
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
	private static final int getPlayerScore(Player player, Connection connection) throws SQLException{
		
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
	private static final Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(DatabaseConnection.url);
		return connection;

	} 

}
