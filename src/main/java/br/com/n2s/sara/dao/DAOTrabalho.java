package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;

public class DAOTrabalho {

	private Connection connection;

	public DAOTrabalho(){}

	public void create(Trabalho trabalho){

		this.connection = new ConnectionFactory().getConnection(); 	

		String sql = "insert into sara.Usuario"  
				+ "(titulo, palavrasChaves, resumo, status, endereco, idTrilha)"
				+ "values (?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, trabalho.getTitulo());
			stmt.setString(2, trabalho.getPalavrasChaves());
			stmt.setString(3, trabalho.getResumo());
			stmt.setString(4, trabalho.getStatus().toString());
			stmt.setString(5, trabalho.getEndereco().toString());
			stmt.setInt(6, trabalho.getTrilha().getIdTrilha());

			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Trabalho> read(){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Trabalho";

		try{
			List<Trabalho> trabalhos = new ArrayList<Trabalho>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOTrilha daoTrilha = new DAOTrilha(); 

			while(rs.next()){

				Trabalho trabalho = new Trabalho();
				trabalho.setIdTrabalho(rs.getInt("idTrabalho"));
				trabalho.setTitulo(rs.getString("titulo"));
				trabalho.setPalavrasChaves(rs.getString("palavrasChaves"));
				trabalho.setResumo(rs.getString("resumo"));
				trabalho.setStatus(StatusTrabalho.valueOf(rs.getString("status")));
				/*N�o � para listar o conte�do dos trabalhos
				 * 
				 * 
				 * try {
					trabalho.setManuscrito(rs.getString("manuscrito")));
					trabalho.setVersaoFinal(FileManipulation.transStringToFile(rs.getString("versaofinal")));
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				trabalho.setTrilha(daoTrilha.getTrilha(rs.getInt("idTrilha")));

				trabalhos.add(trabalho);
			}

			rs.close();
			stmt.close();
			this.connection.close();
			return trabalhos;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Trabalho getTrabalho(int idTrabalho){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Trabalho where idTrabalho = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			ResultSet rs = stmt.executeQuery();
			DAOTrilha daoTrilha = new DAOTrilha();
			
			/*if(rs.next()){         Espera visualizar apenas de 1 trabalho*/

				Trabalho trabalho = new Trabalho();
				trabalho.setIdTrabalho(rs.getInt("idTrabalho"));
				trabalho.setTitulo(rs.getString("titulo"));
				trabalho.setPalavrasChaves(rs.getString("palavrasChaves"));
				trabalho.setResumo(rs.getString("resumo"));
				trabalho.setStatus(StatusTrabalho.valueOf(rs.getString("status")));
				trabalho.setEndereco(rs.getString("endereco"));
				trabalho.setTrilha(daoTrilha.getTrilha(rs.getInt("idTrilha")));				
				
				rs.close();
				stmt.close();
				this.connection.close();
				return trabalho;
				
			
				/* n�o tem resposta do if			}else{
				return null;*/
		}	
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(Trabalho trabalho){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "update sara.Trabalho set titulo = ?, palavrasChaves = ? resumo = ?, status = ?, endereco = ?, idTrilha = ?"
				+ " where idTrabalho  = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, trabalho.getTitulo());
			stmt.setString(2, trabalho.getPalavrasChaves());
			stmt.setString(3, trabalho.getResumo());
			stmt.setString(4, trabalho.getStatus().toString());
			stmt.setString(5, trabalho.getEndereco());
			stmt.setInt(6, trabalho.getTrilha().getIdTrilha());
			

			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(int idTrabalho){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "delete from sara.Usuario where idTrabalho = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
