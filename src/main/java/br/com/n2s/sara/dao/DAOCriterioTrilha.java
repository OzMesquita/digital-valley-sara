package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.CriterioTrilha;
import br.com.n2s.sara.model.Usuario;


public class DAOCriterioTrilha {
	
	private Connection connection;

	public DAOCriterioTrilha(){}


	public CriterioTrilha create(CriterioTrilha criterioTrilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "insert into sara.CriterioTrilha"  
				+ "(dataCriacao, nome)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(criterioTrilha.getDataCriacao()));
			stmt.setString(2, criterioTrilha.getNome());
			
			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return criterioTrilha;
	}

	public List<CriterioTrilha> read(){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.CriterioTrilha";

		try{
			List<CriterioTrilha> criterioTrilhas = new ArrayList<CriterioTrilha>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				CriterioTrilha criterioTrilha = new CriterioTrilha();

				criterioTrilha.setIdCriterioTrilha(rs.getInt("idCriterioTrilha"));
				criterioTrilha.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
				criterioTrilha.setNome(rs.getString("nome"));
				
				criterioTrilhas.add(criterioTrilha);

			}

			rs.close();
			stmt.close();
			connection.close();
			return criterioTrilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public CriterioTrilha getCriterioTrilha(int idCriterioTrilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.CriterioTrilha where idCriterioTrilha = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idCriterioTrilha);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				
				CriterioTrilha criterioTrilha = new CriterioTrilha();

				criterioTrilha.setIdCriterioTrilha(rs.getInt("idCriterioTrilha"));
				criterioTrilha.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
				criterioTrilha.setNome(rs.getString("nome"));
			
				rs.close();
				stmt.close();
				connection.close();
				return criterioTrilha;
				
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public int getLastId(){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "Select max(idcriteriotrilha) from sara.CriterioTrilha";
		
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int lastId = rs.getInt(1);

			stmt.close();
			rs.close();
			connection.close();
			return lastId;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(CriterioTrilha criterioTrilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "update sara.CriterioTrilha set dataCriacao = ? nome = ?"
				+ " where idCriterioTrilha = ?";
				

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(criterioTrilha.getDataCriacao()));
			stmt.setString(2, criterioTrilha.getNome());
			stmt.setInt(3, criterioTrilha.getIdCriterioTrilha());
			
			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(int idCriterioTrilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "delete from sara.CriterioTrilha where idCriterioTrilha = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCriterioTrilha);
			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}