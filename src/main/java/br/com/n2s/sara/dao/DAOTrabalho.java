package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.spi.DirStateFactory.Result;

import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Submissao;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

public class DAOTrabalho {

	private Connection connection;

	public DAOTrabalho(){}

	public int create(Trabalho trabalho){

		this.connection = new ConnectionFactory().getConnection(); 	

		String sql = "insert into sara.Trabalho"  
				+ "(titulo, palavraschaves, resumo, status, endereco, idtrilha)"
				+ "values (?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = null;
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, trabalho.getTitulo());
			stmt.setString(2, trabalho.getPalavrasChaves());
			stmt.setString(3, trabalho.getResumo());
			stmt.setString(4, trabalho.getStatus().toString());
			stmt.setString(5, trabalho.getEndereco().toString());
			stmt.setInt(6, trabalho.getTrilha().getIdTrilha());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int idTrabalho= 0 ;
			if (rs.next()) {
				idTrabalho = rs.getInt("idtrabalho");
			}
			stmt.close();
			rs.close();
			trabalho.setIdTrabalho(idTrabalho);
			this.connection.close();
			adicionaAutores(trabalho);
			return idTrabalho;

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
				ArrayList autores = (ArrayList) pegarUsuarios(trabalho);
				trabalho.setAutores( autores );
				trabalho.setAutor((Usuario) autores.get(0));
				trabalho.getAutores().remove(0);
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
			if(rs.next()) {	
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
				ArrayList <Usuario> autores = pegarUsuarios(trabalho);
				trabalho.setAutor(autores.get(0));
				autores.remove(0);
				trabalho.setAutores(autores);
				this.connection.close();
				return trabalho;
			}else {
				return null;
			}
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
		String sql = "delete from sara.Trabalho where idTrabalho = ?";

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
	public ArrayList<Trabalho> readTrilha(int idTrilha){
		ArrayList<Trabalho> trabalhos = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		String sql = "SELECT * FROM sara.trabalho WHERE idtrilha = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,idTrilha);
			ResultSet rs = stmt.executeQuery();
			DAOTrilha daoTrilha = new DAOTrilha();
			Trilha trilha = daoTrilha.getTrilha(idTrilha);
			while(rs.next()){
				Trabalho trabalho = new Trabalho();
				trabalho.setIdTrabalho(rs.getInt("idTrabalho"));
				trabalho.setTitulo(rs.getString("titulo"));
				trabalho.setPalavrasChaves(rs.getString("palavrasChaves"));
				trabalho.setResumo(rs.getString("resumo"));
				trabalho.setStatus(StatusTrabalho.valueOf(rs.getString("status")));
				trabalho.setEndereco(rs.getString("endereco"));
				trabalho.setTrilha(trilha);				
				ArrayList <Usuario> autores = pegarUsuarios(trabalho);
				trabalho.setAutor(autores.get(0));
				autores.remove(0);
				trabalho.setAutores(autores);	
				trabalhos.add(trabalho);
			}
			stmt.close();
			rs.close();
			this.connection.close();
			return trabalhos;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private void adicionaAutores(Trabalho t) {
		DAOSubmissao daoSubmissao = new DAOSubmissao();
		Submissao submissao = new Submissao();
		submissao.setAutor(t.getAutor());
		submissao.setTrabalho(t);
		daoSubmissao.create(submissao);
		for (Usuario u : t.getAutores()) {
			submissao.setAutor(u);
			daoSubmissao.create(submissao);
		}
	}
	
	private ArrayList<Usuario> pegarUsuarios(Trabalho t){
		ArrayList<Usuario> autores = new ArrayList<Usuario>();
		DAOSubmissao daoSubmissao = new DAOSubmissao();
		autores = (ArrayList<Usuario>) daoSubmissao.getAutores(t.getIdTrabalho());	
		return autores;
	}

}
