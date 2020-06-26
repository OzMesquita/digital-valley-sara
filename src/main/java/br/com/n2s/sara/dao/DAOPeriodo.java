package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Periodo;

public class DAOPeriodo extends DAO {

	public DAOPeriodo() {}	

	public void create(Periodo periodo){
		
		super.open();
		
		String sql = "insert into sara.periodo"  
				+ "(dataInicial, dataFinal, descricao, idTrilha)"
				+ "values (?,?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(periodo.getDataInicial()));
			stmt.setDate(2, Date.valueOf(periodo.getDataFinal()));
			stmt.setString(3, periodo.getDescricao().toString());
			stmt.setInt(4, periodo.getTrilha().getIdTrilha());
			
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<Periodo> read(){ //read()
		
		super.open();
		String sql = "select * from sara.periodo";

		try{
			List<Periodo> periodos = new ArrayList<Periodo>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOTrilha daoTrilha = new DAOTrilha();

			while(rs.next()){// anda o array

				Periodo periodo = new Periodo();
				periodo.setIdPeriodo(rs.getInt("idPeriodo"));
				periodo.setDataInicial((rs.getDate("dataInicial").toLocalDate())); //toLocalDate()
				periodo.setDataFinal((rs.getDate("dataFinal").toLocalDate()));
				periodo.setDescricao(DescricaoPeriodo.valueOf(rs.getString("descricao")));
				periodo.setTrilha(daoTrilha.getTrilha(rs.getInt("idTrilha")));
				
				periodos.add(periodo);
			}

			rs.close();
			stmt.close();
			
			return periodos;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	public List<Periodo> readByIdTrilha(int id){ //read()
		
		super.open();
		String sql = "select * from sara.periodo where idtrilha = ?";

		try{
			List<Periodo> periodos = new ArrayList<Periodo>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOTrilha daoTrilha = new DAOTrilha();

			while(rs.next()){

				Periodo periodo = new Periodo();
				periodo.setIdPeriodo(rs.getInt("idPeriodo"));
				periodo.setDataInicial((rs.getDate("dataInicial").toLocalDate()));
				periodo.setDataFinal((rs.getDate("dataFinal").toLocalDate()));
				periodo.setDescricao(DescricaoPeriodo.valueOf(rs.getString("descricao")));
				periodo.setTrilha(daoTrilha.getTrilha(rs.getInt("idTrilha")));
				
				periodos.add(periodo);
			}

			rs.close();
			stmt.close();
			
			return periodos;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public Periodo getPeriodo(int idPeriodo){
		
		super.open();
		String sql = "select * from sara.periodo where idPeriodo = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idPeriodo);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOTrilha daoTrilha = new DAOTrilha();
			
			if(rs.next()){

				Periodo periodo = new Periodo();
				periodo.setIdPeriodo(rs.getInt("idPeriodo"));
				periodo.setDataFinal((rs.getDate("dataInicial").toLocalDate())); //toLocalDate()
				periodo.setDataInicial((rs.getDate("dataFinal").toLocalDate()));
				periodo.setDescricao(DescricaoPeriodo.valueOf(rs.getString("descricao")));
				periodo.setTrilha(daoTrilha.getTrilha(rs.getInt("idTrilha")));
				
				rs.close();
				stmt.close();
				
				return periodo;
				
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public void update(Periodo periodo){
		
		super.open();
		String sql = "update sara.periodo "
				+ "set dataInicial = ?, dataFinal = ?, descricao = ?, idTrilha = ?" 
				+ " where idPeriodo = ?";

		try {
			
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(periodo.getDataInicial()));
			stmt.setDate(2, Date.valueOf(periodo.getDataFinal()));
			stmt.setString(3, periodo.getDescricao().toString());
			stmt.setInt(4, periodo.getTrilha().getIdTrilha());
			stmt.setInt(5, periodo.getIdPeriodo());

			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public void delete(int idPeriodo){
		
		super.open();
		String sql = "delete from sara.periodo where idPeriodo = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idPeriodo);
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
}
