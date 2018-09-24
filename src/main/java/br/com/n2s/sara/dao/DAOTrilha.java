package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Trilha;

public class DAOTrilha {

	private Connection connection;

	public DAOTrilha(){}

	public void create(Trilha trilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "insert into sara.Trilha"  
				+ "(nome, descricao, idEvento)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, trilha.getNome());
			stmt.setString(2, trilha.getDescricao());
			stmt.setInt(3, trilha.getEvento().getIdEvento());
			
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Trilha> read(){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.Trilha";

		try{
			List<Trilha> trilhas = new ArrayList<Trilha>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
			this.connection.close();
			return trilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
public List<Trilha> readById(int id){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.Trilha where idEvento = ?";
		
		try{
			List<Trilha> trilhas = new ArrayList<Trilha>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
			this.connection.close();
			return trilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Trilha getTrilha(int idTrilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.Trilha where idTrilha = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
				this.connection.close();
				return trilha;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(Trilha trilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "update sara.Trilha set nome = ?, descricao = ?, idEvento = ?, "
				+ "idCriterioTrilha = ? where idTrilha = ?";


		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, trilha.getNome());
			stmt.setString(2, trilha.getDescricao());
			stmt.setInt(3, trilha.getEvento().getIdEvento());
			stmt.setInt(4, trilha.getCriterioTrilha().getIdCriterioTrilha());
			stmt.setInt(5, trilha.getIdTrilha());

			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(int idTrilha){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "delete from sara.Trilha where idTrilha = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idTrilha);
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
