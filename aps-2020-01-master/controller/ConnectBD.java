package controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 

public class ConnectBD {

    public static String status = "Não conectou...";
    
    public ConnectBD() {
    	 
    }
    
    public static java.sql.Connection getMySQLConnection() {

    	Connection connection = null;          	
    	try {

    		String driverName = "com.mysql.jdbc.Driver";                        

    		Class.forName(driverName);

    		String serverName = "localhost";    

    		String mydatabase = "mysql";        

    		String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

    		String username = "root";             

    		String password = "123456";      

    		connection = DriverManager.getConnection(url, username, password); 

    		if (connection != null) {

    			status = ("STATUS--->Conectado com sucesso!");

    		} else {

    			status = ("STATUS--->Não foi possivel realizar conexão");
    		}

    		return connection;

    	} catch (ClassNotFoundException e) {  

    		System.out.println("O driver expecificado nao foi encontrado.");

    		return null;

    	} catch (SQLException e) {

    		System.out.println("Nao foi possivel conectar ao Banco de Dados.");

    		return null;
    	}
    }
	
    public static boolean CloseConnection() {
        
        try {
 
            ConnectBD.getMySQLConnection().close();
 
            return true;
 
        } catch (SQLException e) {
        	
            return false;
        }   
 
    }
    
    public static String statusConnection() {
 
        return status;
 
    }

	public PreparedStatement prepareStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
