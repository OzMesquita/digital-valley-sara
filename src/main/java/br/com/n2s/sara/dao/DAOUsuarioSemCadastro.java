package br.com.n2s.sara.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.n2s.sara.model.Usuario;

public class DAOUsuarioSemCadastro extends DAO{

	
	public void create(Usuario usuario) {
		super.open(); 
		String sql = "insert into sara.semcadastro"  
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
		String sql = "SELECT * FROM sara.semcadastro WHERE cpf=?";
		
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			super.close();
			if(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				rs.close();
				stmt.close();
				
				return usuario;
			}else {
				return null;
			}
		}catch (SQLException e ) {
			throw new RuntimeException("Erro ao buscar Usuario: "+id);
		}finally {
			super.close();
		}		
	}
	
	public void delete(String cpf){
		
		super.open(); 
		String sql = "delete from sara.semcadastro where cpf = ?";

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
	public Integer getQuantidadePorNome(String nome) {
		super.open();
		String SQL = "SELECT COUNT(*) AS quantidade FROM sara.semcadastro WHERE nome ILIKE ?";
		try {
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			super.close();
			if (rs.next()) {
				return rs.getInt("quantidade");
			} else {
				ps.close();
				rs.close();
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao buscar registro de pessoa, erro: " + e.getMessage());
		} finally {
			super.close();
		}
	}
}
