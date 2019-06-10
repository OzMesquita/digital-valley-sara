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


public class DAOCriterioTrilha extends DAO {
	

	public DAOCriterioTrilha(){}


	public CriterioTrilha create(CriterioTrilha criterioTrilha){
		
		super.open();
		String sql = "insert into sara.criteriotrilha"  
				+ "(dataCriacao, nome)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(criterioTrilha.getDataCriacao()));
			stmt.setString(2, criterioTrilha.getNome());
			
			stmt.execute();
			stmt.close();
			super.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return criterioTrilha;
	}

	public List<CriterioTrilha> read(){
		
		super.open();	
		String sql = "select * from sara.criteriotrilha";

		try{
			List<CriterioTrilha> criterioTrilhas = new ArrayList<CriterioTrilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
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
			super.close();
			return criterioTrilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public CriterioTrilha getCriterioTrilha(int idCriterioTrilha){
		
		super.open();
		String sql = "select * from sara.criteriotrilha where idCriterioTrilha = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idCriterioTrilha);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				
				CriterioTrilha criterioTrilha = new CriterioTrilha();

				criterioTrilha.setIdCriterioTrilha(rs.getInt("idCriterioTrilha"));
				criterioTrilha.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
				criterioTrilha.setNome(rs.getString("nome"));
			
				rs.close();
				stmt.close();
				super.close();
				return criterioTrilha;
				
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public int getLastId(){
		
		super.open();
		String sql = "Select max(idcriteriotrilha) from sara.criteriotrilha";
		
		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int lastId = rs.getInt(1);

			stmt.close();
			rs.close();
			super.close();
			return lastId;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(CriterioTrilha criterioTrilha){
		
		super.open();
		String sql = "update sara.criteriotrilha set dataCriacao = ? nome = ?"
				+ " where idCriterioTrilha = ?";
				

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(criterioTrilha.getDataCriacao()));
			stmt.setString(2, criterioTrilha.getNome());
			stmt.setInt(3, criterioTrilha.getIdCriterioTrilha());
			
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(int idCriterioTrilha){
		
		super.open();
		String sql = "delete from sara.criteriotrilha where idCriterioTrilha = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idCriterioTrilha);
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}