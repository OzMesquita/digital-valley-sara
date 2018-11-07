package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trilha;

public class DAOTrilha extends DAO {

	public DAOTrilha(){}

	public void create(Trilha trilha){
		super.open();
		String sql = "insert into sara.Trilha"  
				+ "(nome, descricao, idEvento)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, trilha.getNome());
			stmt.setString(2, trilha.getDescricao());
			stmt.setInt(3, trilha.getEvento().getIdEvento());
			
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<Trilha> read(){
		
		super.open();
		String sql = "select * from sara.Trilha";

		try{
			List<Trilha> trilhas = new ArrayList<Trilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOEvento daoEvento = new DAOEvento();
			DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();

			while(rs.next()){

				Trilha trilha = new Trilha();

				trilha.setIdTrilha(rs.getInt("idTrilha"));
				trilha.setNome(rs.getString("nome"));
				trilha.setDescricao(rs.getString("descricao"));
				trilha.setEvento(daoEvento.getEvento(rs.getInt("idEvento")));
				trilha.setCriterioTrilha(daoCriterioTrilha.getCriterioTrilha(rs.getInt("idCriterioTrilha")));
				trilhas.add(trilha);

			}

			rs.close();
			stmt.close();
			return trilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
public List<Trilha> readById(int id){
		
		super.open();
		String sql = "select * from sara.Trilha where idEvento = ?";
		
		try{
			List<Trilha> trilhas = new ArrayList<Trilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			DAOEvento daoEvento = new DAOEvento();
			DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
			
			while(rs.next()){

				Trilha trilha = new Trilha();

				trilha.setIdTrilha(rs.getInt("idTrilha"));
				trilha.setNome(rs.getString("nome"));
				trilha.setDescricao(rs.getString("descricao"));
				trilha.setEvento(daoEvento.getEvento(rs.getInt("idEvento")));
				trilha.setCriterioTrilha(daoCriterioTrilha.getCriterioTrilha(rs.getInt("idCriterioTrilha")));
				
				trilhas.add(trilha);

			}

			rs.close();
			stmt.close();
			return trilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public Trilha getTrilha(int idTrilha){
		super.open();
		String sql = "select * from sara.Trilha where idTrilha = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrilha);
			ResultSet rs = stmt.executeQuery();
			DAOEvento daoEvento = new DAOEvento();
			DAOCriterioTrilha daoCriterioTrilha = new DAOCriterioTrilha();
			DAOPeriodo daoPeriodo = new DAOPeriodo();
			if(rs.next()){

				Trilha trilha = new Trilha();

				trilha.setIdTrilha(rs.getInt("idTrilha"));
				trilha.setNome(rs.getString("nome"));
				trilha.setDescricao(rs.getString("descricao"));
				trilha.setEvento(daoEvento.getEvento(rs.getInt("idEvento")));
				trilha.setCriterioTrilha(daoCriterioTrilha.getCriterioTrilha(rs.getInt("idCriterioTrilha")));
				rs.close();
				stmt.close();
				
				return trilha;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public void update(Trilha trilha){
		super.open();
		String sql = "update sara.Trilha set nome = ?, descricao = ?, idEvento = ?, "
				+ "idCriterioTrilha = ? where idTrilha = ?";


		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, trilha.getNome());
			stmt.setString(2, trilha.getDescricao());
			stmt.setInt(3, trilha.getEvento().getIdEvento());
			stmt.setInt(4, trilha.getCriterioTrilha().getIdCriterioTrilha());
			stmt.setInt(5, trilha.getIdTrilha());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}


	public void delete(int idTrilha){
		super.open();
		String sql = "delete from sara.Trilha where idTrilha = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrilha);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}

	}
}
