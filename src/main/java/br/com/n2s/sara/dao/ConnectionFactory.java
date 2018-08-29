package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	
	
	private String url = "jdbc:postgresql://192.169.1.2:5432/n2s-ufc";
	private String usuario = "postgres";
	private String senha = "postgres";
	
	public Connection getConnection()  {
		
		try {
			
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url, usuario, senha);
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}