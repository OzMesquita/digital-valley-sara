package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Periodo;
import br.com.n2s.sara.model.Submissao;
import br.com.n2s.sara.model.TipoAutor;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

public class DAOSubmissao extends DAO {

	public DAOSubmissao(){}

	public void create(Submissao submissao){
		
		super.open();
		String sql = "insert into sara.Submissao"  
				+ "(cpfautor, idtrabalho,tipoUsuario)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, submissao.getAutor().getCpf());
			stmt.setInt(2, submissao.getTrabalho().getIdTrabalho());
			stmt.setString(3, submissao.getTipoAutor().toString());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Submissao> read(){
		
		super.open();
		String sql = "select * from sara.Submissao";

		try{
			List<Submissao> submissoes = new ArrayList<Submissao>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOUsuario usuarioController = new DAOUsuario();

			while(rs.next()){

				Submissao submissao= new Submissao();
				submissao.setAutor(usuarioController.getUsuario(rs.getString("cpfautor")));
				submissao.setTrabalho(new DAOTrabalho().getTrabalho(rs.getInt("idtrabalho")));
				submissoes.add(submissao);
			}

			rs.close();
			stmt.close();
			super.close();
			return submissoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
		public List<Usuario> getAutores(int idTrabalho){
		
		super.open();
		String sql = "select * from sara.submissao where idtrabalho=?";
		try{
			List<Usuario> autores = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Usuario autor= new Usuario();
				autor.setCpf(rs.getString("cpfautor"));				
				autores.add(autor);
			}

			rs.close();
			stmt.close();
			super.close();
			return autores;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
public List<String> getCPFAutores(int idTrabalho){
		
		super.open();
		String sql = "select * from sara.submissao where idtrabalho=?";
		try{
			List<String> autores = new ArrayList<String>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			ResultSet rs = stmt.executeQuery();			

			while(rs.next()){
				autores.add(rs.getString("cpfautor"));
			}

			rs.close();
			stmt.close();
			super.close();
			return autores;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Submissao getSubmissao(int idtrabalho){
		
		super.open();
		String sql = "select * from sara.Submissao where idtrabalho = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idtrabalho);			
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				Submissao submissao = new Submissao();
				submissao.setAutor(Facade.pegarUsuario(rs.getString("cpfautor")));
				submissao.setTrabalho(new DAOTrabalho().getTrabalho(rs.getInt("idtrabalho")));
				submissao.setTipoAutor(TipoAutor.valueOf(rs.getString("tipoUsuario")));
				
				rs.close();
				stmt.close();
				super.close();
				return submissao;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(Submissao submissao){
		
		super.open();
		String sql = "update sara.Submissao set cpfautor = ?, idtrabalho = ? " 
				+ " where idtrabalho = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, submissao.getAutor().getCpf());
			stmt.setInt(2, submissao.getTrabalho().getIdTrabalho());
			stmt.setString(3, submissao.getTipoAutor().toString());
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(Submissao submissao){
		
		super.open();
		String sql = "delete from sara.Submissao where idtrabalho = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, submissao.getTrabalho().getIdTrabalho());
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	public void delete(int idTrabalho){
		
		super.open();
		String sql = "delete from sara.Submissao where idtrabalho = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	public List<Trabalho> readByAutor(String idAutor){
		
		super.open();
		String sql = "select * from sara.submissao where cpfautor = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, idAutor);			
			ResultSet rs = stmt.executeQuery();
			ArrayList<Trabalho> submissoes = new ArrayList<Trabalho>();
			Trabalho trabalho = new Trabalho();
			while(rs.next()){
				trabalho = new DAOTrabalho().getTrabalho(rs.getInt("idtrabalho")); 
				submissoes.add(trabalho);
			}
			rs.close();
			stmt.close();
			super.close();
			return submissoes;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
