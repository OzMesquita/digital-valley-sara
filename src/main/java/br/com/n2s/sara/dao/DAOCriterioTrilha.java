package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.CriterioTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;


public class DAOCriterioTrilha extends DAO {
	

	public void create(Trilha trilha){
		super.open();
		String sql = "INSERT INTO sara.criteriotrilha(fktrilha, fkcriterio)VALUES (?,?)";
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			for(int i=0;i<trilha.getCriterios().size();i++) {
				stmt.setInt(2, trilha.getIdTrilha());
				stmt.setInt(3, trilha.getCriterios().get(i).getIdCriterio());
				stmt.execute();
			}
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	public void createCriTrilha(CriterioTrilha ct){
		super.open();
		String sql = "INSERT INTO sara.criteriotrilha(fktrilha, fkcriterio)VALUES (?,?)";
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, ct.getTrilha().getIdTrilha());
			stmt.setInt(2, ct.getCriterio().getIdCriterio());

			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<Criterio> getCriterioPorTrilha(Trilha t){
		super.open();
		String sql = "select * from sara.criteriotrilha where fktrilha = ?";
		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, t.getIdTrilha());
			ResultSet rs = stmt.executeQuery();
			super.close();
			ArrayList<Criterio> criterios = new ArrayList<Criterio>();
			while(rs.next()) {
				Criterio c = new DAOCriterio().getCriterio(rs.getInt("fkcriterio"));
				criterios.add(c);
			}
			return criterios;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	public void delete(Criterio c, Trilha t) {
		super.open();
		String sql = "delete from sara.criteriotrilha where fktrilha = ? and fkcriterio = ?";
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, t.getIdTrilha());
			stmt.setInt(2, c.getIdCriterio());
			stmt.execute();
			stmt.close();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	@Deprecated
	public List<CriterioTrilha> read(){
		
		super.open();	
		String sql = "select * from sara.criteriotrilha";

		try{
			List<CriterioTrilha> criterioTrilhas = new ArrayList<CriterioTrilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				CriterioTrilha criterioTrilha = new CriterioTrilha();

				//criterioTrilha.setIdCriterioTrilha(rs.getInt("idCriterioTrilha"));
				criterioTrilha.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
				criterioTrilha.setNome(rs.getString("nome"));
				
				criterioTrilhas.add(criterioTrilha);

			}

			rs.close();
			stmt.close();
			
			return criterioTrilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	@Deprecated
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
			
			return lastId;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	@Deprecated
	public void update(CriterioTrilha criterioTrilha){
		
		super.open();
		String sql = "update sara.criteriotrilha set dataCriacao = ? nome = ? where idCriterioTrilha = ?";
				

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(criterioTrilha.getDataCriacao()));
			stmt.setString(2, criterioTrilha.getNome());
			
			
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	@Deprecated
	public void delete(int idCriterioTrilha){
		
		super.open();
		String sql = "delete from sara.criteriotrilha where idCriterioTrilha = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idCriterioTrilha);
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}	
}