package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO {
	private Connection connection;
	

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	protected void open() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	protected void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
