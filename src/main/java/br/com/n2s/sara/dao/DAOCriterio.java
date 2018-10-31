package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Criterio;

public class DAOCriterio {
	
	private Connection connection;

	public DAOCriterio(){}

	public void create(Criterio criterio){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "insert into sara.Criterio"  
				+ "(descricao, peso, idCriterioTrilha)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, criterio.getDescricao());
			stmt.setInt(2, criterio.getPeso());
			stmt.setInt(3, criterio.getCriterioTrilha().getIdCriterioTrilha());
			
			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Criterio> read(){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Criterio";

		try{
			List<Criterio> criterios = new ArrayList<Criterio>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOCriterioTrilha criterioTrilhaController = new DAOCriterioTrilha();

			while(rs.next()){

				Criterio criterio = new Criterio();

				criterio.setIdCriterio(rs.getInt("idCriterio"));
				criterio.setDescricao(rs.getString("descricao"));
				criterio.setPeso(rs.getInt("peso"));
				criterio.setCriterioTrilha(criterioTrilhaController.getCriterioTrilha(rs.getInt("idCriterioTrilha")));
				
				criterios.add(criterio);

			}

			rs.close();
			stmt.close();
			connection.close();
			return criterios;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Criterio getCriterio(int idCriterio){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Criterio where idCriterio = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idCriterio);
			ResultSet rs = stmt.executeQuery();
			DAOCriterioTrilha criterioTrilhaController = new DAOCriterioTrilha();

			if(rs.next()){
				
				Criterio criterio = new Criterio();

				criterio.setIdCriterio(rs.getInt("idCriterio"));
				criterio.setDescricao(rs.getString("descricao"));
				criterio.setPeso(rs.getInt("peso"));
				criterio.setCriterioTrilha(criterioTrilhaController.getCriterioTrilha(rs.getInt("idCriterioTrilha")));
			
				rs.close();
				stmt.close();
				connection.close();
				return criterio;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Criterio> obterCriteriosPorTrilha(int idCriterioTrilha){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Criterio where idcriteriotrilha = ?";

		try{
			List<Criterio> criterios = new ArrayList<Criterio>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idCriterioTrilha);
			ResultSet rs = stmt.executeQuery();
			DAOCriterioTrilha criterioTrilhaController = new DAOCriterioTrilha();

			while(rs.next()){

				Criterio criterio = new Criterio();

				criterio.setIdCriterio(rs.getInt("idCriterio"));
				criterio.setDescricao(rs.getString("descricao"));
				criterio.setPeso(rs.getInt("peso"));
				criterio.setCriterioTrilha(criterioTrilhaController.getCriterioTrilha(rs.getInt("idCriterioTrilha")));
				
				criterios.add(criterio);

			}

			rs.close();
			stmt.close();
			connection.close();
			return criterios;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(Criterio criterio){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "update sara.Criterio set descricao = ?, peso = ?, idCriterioTrilha = ?"
				+ " where idCriterio = ?";
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, criterio.getDescricao());
			stmt.setInt(2, criterio.getPeso());
			stmt.setInt(3, criterio.getCriterioTrilha().getIdCriterioTrilha());
			stmt.setInt(4, criterio.getIdCriterio());
			
			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getLastId(){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "Select max(idCriterio) from sara.Criterio";
		
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


	public void delete(Criterio criterio){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "delete from sara.Criterio where idCriterio = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, criterio.getIdCriterio());
			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}