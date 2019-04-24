package br.com.n2s.sara.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.n2s.sara.model.Usuario;

public class DAOUsuarioSemCadastro extends DAO{

	
	public void create(Usuario usuario) {
		super.open(); 
		String sql = "insert into sara.SemCadastro"  
				+ "(cpf, nome, email)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.getConnection().setAutoCommit(false);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(4, usuario.getEmail());

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
		}finally {
			super.close();
		}
	}
	
	public Usuario getUsuario(String id) {
		
		super.open();
		String sql = "SELECT * FROM sara.SemCadastro WHERE cpf=?";
		
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				rs.close();
				stmt.close();
				super.close();
				return usuario;
			}else {
				return null;
			}
		}catch (SQLException e ) {
			throw new RuntimeException("Erro ao buscar Usuario: "+id);
		}		
	}
	
	public void delete(String cpf){
		
		super.open(); 
		String sql = "delete from sara.SemCadastro where cpf = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
