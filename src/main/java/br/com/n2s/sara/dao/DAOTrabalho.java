package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.spi.DirStateFactory.Result;

import br.com.n2s.sara.model.StatusTrabalho;
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
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, trabalho.getTitulo());
			stmt.setString(2, trabalho.getPalavrasChaves());
			stmt.setString(3, trabalho.getResumo());
			stmt.setString(4, trabalho.getStatus().toString());
			stmt.setString(5, trabalho.getEndereco().toString());
			stmt.setInt(6, trabalho.getTrilha().getIdTrilha());

			int idTrabalho = stmt.executeUpdate(sql);
			stmt.close(); 
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
				ArrayList <Usuario> autores = pegarUsuarios(trabalho);
				trabalho.setAutor(autores.get(0));
				autores.remove(0);
				trabalho.setAutores(autores);
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
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return trabalhos;
	}
	private void adicionaAutores(Trabalho t) {
		this.connection = new ConnectionFactory().getConnection();
		String sql = "INSERT INTO sara.autorTrabalho" + "(idTrabalho, idAutor)"+"values(?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, t.getIdTrabalho());
			stmt.setString(2, t.getAutor().getCpf());
			for (Usuario u :t.getAutores()) {
				stmt.setInt(1, t.getIdTrabalho());
				stmt.setString(2,u.getCpf());
				stmt.executeQuery();
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ArrayList<Usuario> pegarUsuarios(Trabalho t){
		ArrayList<Usuario> autores = new ArrayList();
		String sql = "select * from sara.autorTrabalho where idTrabalho = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, t.getIdTrabalho());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				autores.add(Facade.buscarUsuarioPorCPF(rs.getString("idAutor")));
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		return autores;
	}

}
