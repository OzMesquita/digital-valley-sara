package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.n2s.sara.util.Facade;
import util.Constantes;




public class ConnectionFactory {
	
	
	public Connection getConnection()  {
		Connection conn = null;
		try {		
			String bd [] = Facade.lerArquivoBancoDeDados();
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(bd[0], bd[1], bd[2]);
			return conn;
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}