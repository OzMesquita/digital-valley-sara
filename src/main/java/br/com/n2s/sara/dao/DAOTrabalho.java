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

import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Submissao;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;
import br.com.n2s.sara.util.Facade;

public class DAOTrabalho extends DAO {

	public DAOTrabalho(){}

	public int create(Trabalho trabalho){

		try {
			super.open();
			String sql = "insert into sara.trabalho"  
					+ "(titulo, palavraschaves, resumo, status, endereco, idtrilha)"
					+ "values (?,?,?,?,?,?)";

			PreparedStatement stmt = null;
			stmt = super.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.getConnection().setAutoCommit(false);
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
			
			adicionaAutores(trabalho);
			stmt.getConnection().commit();
			stmt.getConnection().setAutoCommit( true );
			return idTrabalho;
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

	public List<Trabalho> read(){
		
		super.open();
		String sql = "select * from sara.trabalho";

		try{
			List<Trabalho> trabalhos = new ArrayList<Trabalho>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOTrilha daoTrilha = new DAOTrilha(); 

			while(rs.next()){

				Trabalho trabalho = new Trabalho();
				trabalho.setIdTrabalho(rs.getInt("idTrabalho"));
				trabalho.setTitulo(rs.getString("titulo"));
				trabalho.setPalavrasChaves(rs.getString("palavrasChaves"));
				trabalho.setResumo(rs.getString("resumo"));
				trabalho.setStatus(StatusTrabalho.valueOf(rs.getString("status")));
				trabalho.setTrilha(daoTrilha.getTrilha(rs.getInt("idTrilha")));
				ArrayList autores = (ArrayList) pegarUsuarios(trabalho);
				trabalho.setAutores( autores );
				trabalho.setAutor((Usuario) autores.get(0));
				trabalho.getAutores().remove(0);
				trabalhos.add(trabalho);
			}

			rs.close();
			stmt.close();
			super.close();
			return trabalhos;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Trabalho getTrabalho(int idTrabalho){
		
		super.open();
		String sql = "select * from sara.trabalho where idTrabalho = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
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
				super.close();
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
		
		super.open();
		String sql = "update sara.trabalho set titulo = ?, palavrasChaves = ?, resumo = ?, status = ?, endereco = ?, idTrilha = ?"
				+ " where idTrabalho  = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);

			stmt.setString(1, trabalho.getTitulo());
			stmt.setString(2, trabalho.getPalavrasChaves());
			stmt.setString(3, trabalho.getResumo());
			stmt.setString(4, trabalho.getStatus().toString());
			stmt.setString(5, trabalho.getEndereco());
			stmt.setInt(6, trabalho.getTrilha().getIdTrilha());
			stmt.setInt(7, trabalho.getIdTrabalho());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(int idTrabalho){
		
		super.open();
		String sql = "delete from sara.trabalho where idTrabalho = ?";

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
	public ArrayList<Trabalho> readTrilha(int idTrilha){
		ArrayList<Trabalho> trabalhos = new ArrayList<>();
		super.open();
		String sql = "SELECT * FROM sara.trabalho WHERE idtrilha = ?";
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
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
			super.close();
			return trabalhos;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private void adicionaAutores(Trabalho t) {
		try {
			DAOSubmissao daoSubmissao = new DAOSubmissao();
			Submissao submissao = new Submissao();
			submissao.setAutor(t.getAutor());
			submissao.setTrabalho(t);
			daoSubmissao.create(submissao);
			for (Usuario u : t.getAutores()) {
				if (Facade.isUsuarioCadastrado(u.getCpf())) {
					submissao.setAutor(u);
					daoSubmissao.create(submissao);
				}else {
					new DAOUsuarioSemCadastro().create(u);
					submissao.setAutor(u);
					daoSubmissao.create(submissao);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	private ArrayList<Usuario> pegarUsuarios(Trabalho t){
		ArrayList<Usuario> autores = new ArrayList<Usuario>();
		ArrayList<String> cpfs = (ArrayList<String>) new DAOSubmissao().getCPFAutores(t.getIdTrabalho());
		for (String s : cpfs) {
			Usuario user = new Usuario();
			if(Facade.isUsuarioCadastrado(s)) {
				user = Facade.buscarUsuarioGuardiao(s);
			}else {
				user = new DAOUsuarioSemCadastro().getUsuario(s);
				if (user !=null){
					user.setSobrenome("");
					user.setTipo(NivelUsuario.AUTOR);
				}
			}
			autores.add(user);
		}		
		return autores;
	}

}
