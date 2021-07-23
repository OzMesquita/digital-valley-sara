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
		
		try {
			super.open();
			String sql = "insert into sara.submissao"  
					+ "(cpfautor, idtrabalho,tipoUsuario)"
					+ "values (?,?,?)";

			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.getConnection().setAutoCommit(false);
			
			stmt.setString(1, submissao.getAutor().getCpf());
			stmt.setInt(2, submissao.getTrabalho().getIdTrabalho());
			stmt.setString(3, submissao.getTipoAutor().toString());

			stmt.execute();
			stmt.getConnection().commit();
			stmt.getConnection().setAutoCommit(true);
			stmt.close();

		} catch (SQLException e) {
			try {
				super.getConnection().rollback();
				super.getConnection().setAutoCommit(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<Submissao> read(){
		
		super.open();
		String sql = "select * from sara.submissao";

		try{
			List<Submissao> submissoes = new ArrayList<Submissao>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOUsuario usuarioController = new DAOUsuario();

			while(rs.next()){

				Submissao submissao= new Submissao();
				submissao.setAutor(usuarioController.getUsuario(rs.getString("cpfautor")));
				submissao.setTrabalho(new DAOTrabalho().getTrabalho(rs.getInt("idtrabalho")));
				submissoes.add(submissao);
			}

			rs.close();
			stmt.close();
			
			return submissoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
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
			super.close();
			while(rs.next()){
				Usuario autor= new Usuario();
				autor.setCpf(rs.getString("cpfautor"));				
				autores.add(autor);
			}

			rs.close();
			stmt.close();
			
			return autores;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
		public List<Usuario> getCoAutores(int idTrabalho){
			
			super.open();
			String sql = "select * from sara.submissao where idtrabalho=? and tipousuario = ? ";
			try{
				
				DAOUsuario daoUsuario = new DAOUsuario();
				List<Usuario> autores = new ArrayList<Usuario>();
				PreparedStatement stmt = super.getConnection().prepareStatement(sql);
				stmt.setInt(1, idTrabalho);
				stmt.setString(2, "COAUTOR");
				ResultSet rs = stmt.executeQuery();
				super.close();
				while(rs.next()){
					Usuario autor = daoUsuario.getUsuario(rs.getString("cpfautor"));
					autores.add(autor);
				}

				rs.close();
				stmt.close();
				
				return autores;

			}catch(SQLException e){
				throw new RuntimeException(e);
			}finally {
				super.close();
			}
		}
		public Usuario getOrientador(int idTrabalho) {
			super.open();
			String sql ="select * from sara.submissao where idtrabalho = ? and tipousuario= ? ";
			try{
				Usuario autor=null ;
				PreparedStatement stmt = super.getConnection().prepareStatement(sql);
				stmt.setInt(1, idTrabalho);
				stmt.setString(2, "ORIENTADOR");
				ResultSet rs = stmt.executeQuery();
				super.close();
				if(rs.next()){
					autor = Facade.pegarUsuario(rs.getString("cpfautor"));
				}
				rs.close();
				stmt.close();
				
				return autor;
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				super.close();
			}
			return null;
		}	
		public Usuario getAutorPrincipal(int idTrabalho) {
			super.open();
			String sql ="select * from sara.submissao where idtrabalho = ? and tipousuario= ? ";
			try{
				Usuario autor=null ;
				PreparedStatement stmt = super.getConnection().prepareStatement(sql);
				stmt.setInt(1, idTrabalho);
				stmt.setString(2, "AUTOR");
				ResultSet rs = stmt.executeQuery();
				super.close();
				if(rs.next()){
					autor = Facade.pegarUsuario(rs.getString("cpfautor"));
				}
				rs.close();
				stmt.close();
				
				return autor;
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				super.close();
			}
			return null;
		}
	
public List<String> getCPFAutores(int idTrabalho){
		
		super.open();
		String sql = "select * from sara.submissao where idtrabalho=?";
		try{
			List<String> autores = new ArrayList<String>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			ResultSet rs = stmt.executeQuery();			
			super.close();
			while(rs.next()){
				autores.add(rs.getString("cpfautor"));
			}

			rs.close();
			stmt.close();
			
			return autores;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public Submissao getSubmissao(int idtrabalho){
		
		super.open();
		String sql = "select * from sara.submissao where idtrabalho = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idtrabalho);			
			ResultSet rs = stmt.executeQuery();
			super.close();
			if(rs.next()){
				Submissao submissao = new Submissao();
				submissao.setAutor(Facade.pegarUsuario(rs.getString("cpfautor")));
				submissao.setTrabalho(new DAOTrabalho().getTrabalho(rs.getInt("idtrabalho")));
				submissao.setTipoAutor(TipoAutor.valueOf(rs.getString("tipoUsuario")));
				
				rs.close();
				stmt.close();
				
				return submissao;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public void update(Submissao submissao){
		
		super.open();
		String sql = "update sara.submissao set cpfautor = ?, idtrabalho = ? " 
				+ " where idtrabalho = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, submissao.getAutor().getCpf());
			stmt.setInt(2, submissao.getTrabalho().getIdTrabalho());
			stmt.setString(3, submissao.getTipoAutor().toString());
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}


	public void delete(Submissao submissao){
		
		super.open();
		String sql = "delete from sara.submissao where idtrabalho = ?";

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
		String sql = "delete from sara.submissao where idtrabalho = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			stmt.execute();
			stmt.close();
		

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}

	}
	public List<Trabalho> readByAutor(String idAutor){
		
		super.open();
		String sql = "select * from sara.submissao where cpfautor = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, idAutor);			
			ResultSet rs = stmt.executeQuery();
			super.close();
			ArrayList<Trabalho> submissoes = new ArrayList<Trabalho>();
			Trabalho trabalho = new Trabalho();
			while(rs.next()){
				trabalho = new DAOTrabalho().getTrabalho(rs.getInt("idtrabalho")); 
				submissoes.add(trabalho);
			}
			rs.close();
			stmt.close();
			
			return submissoes;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

}
