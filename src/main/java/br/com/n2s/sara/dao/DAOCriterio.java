package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;

public class DAOCriterio extends DAO {
	

	public DAOCriterio(){}

	public void create(Criterio criterio){
		
		super.open();
		String sql = "insert into sara.criterio"  
				+ "(descricao, peso)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, criterio.getDescricao());
			stmt.setInt(2, criterio.getPeso());
			
			
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<Criterio> read(){
		
		super.open();
		String sql = "select * from sara.criterio";

		try{
			List<Criterio> criterios = new ArrayList<Criterio>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOCriterioTrilha criterioTrilhaController = new DAOCriterioTrilha();

			while(rs.next()){

				Criterio criterio = new Criterio();

				criterio.setIdCriterio(rs.getInt("idCriterio"));
				criterio.setDescricao(rs.getString("descricao"));
				criterio.setPeso(rs.getInt("peso"));
				
				criterios.add(criterio);

			}

			rs.close();
			stmt.close();
			
			return criterios;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public Criterio getCriterio(int idCriterio){
		
		super.open();
		String sql = "select * from sara.criterio where idCriterio = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idCriterio);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOCriterioTrilha criterioTrilhaController = new DAOCriterioTrilha();

			if(rs.next()){
				
				Criterio criterio = new Criterio();

				criterio.setIdCriterio(rs.getInt("idCriterio"));
				criterio.setDescricao(rs.getString("descricao"));
				criterio.setPeso(rs.getInt("peso"));
				criterio.setItens( (ArrayList<Item>) new DAOItem().readById(idCriterio));
				rs.close();
				stmt.close();
				for (Item i : criterio.getItens()) {
					i.setCriterio(criterio);
				}
				return criterio;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	/*
	 * public List<Criterio> obterCriteriosPorTrilha(int idCriterioTrilha){
	 * 
	 * super.open(); String sql =
	 * "select * from sara.criterio where idcriteriotrilha = ?";
	 * 
	 * try{ List<Criterio> criterios = new ArrayList<Criterio>(); PreparedStatement
	 * stmt = super.getConnection().prepareStatement(sql); stmt.setInt(1,
	 * idCriterioTrilha); ResultSet rs = stmt.executeQuery(); DAOCriterioTrilha
	 * criterioTrilhaController = new DAOCriterioTrilha();
	 * 
	 * while(rs.next()){
	 * 
	 * Criterio criterio = new Criterio();
	 * 
	 * criterio.setIdCriterio(rs.getInt("idCriterio"));
	 * criterio.setDescricao(rs.getString("descricao"));
	 * criterio.setPeso(rs.getInt("peso"));
	 * criterio.setCriterioTrilha(criterioTrilhaController.getCriterioTrilha(rs.
	 * getInt("idCriterioTrilha")));
	 * 
	 * criterios.add(criterio);
	 * 
	 * }
	 * 
	 * rs.close(); stmt.close();  return criterios;
	 * 
	 * }catch(SQLException e){ throw new RuntimeException(e); } }
	 */

	public void update(Criterio criterio){
		
		super.open();
		String sql = "update sara.criterio set descricao = ?, peso = ? where idCriterio = ?";
				
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, criterio.getDescricao());
			stmt.setInt(2, criterio.getPeso());
			stmt.setInt(4, criterio.getIdCriterio());
			
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	public int getLastId(){
		
		super.open();
		String sql = "Select max(idCriterio) from sara.criterio";
		
		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			super.close();
			rs.next();
			int lastId = rs.getInt(1);

			stmt.close();
			rs.close();
			
			return lastId;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}


	public void delete(Criterio criterio){
		
		super.open();
		String sql = "delete from sara.criterio where idCriterio = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, criterio.getIdCriterio());
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

}