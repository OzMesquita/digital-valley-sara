package br.com.n2s.sara.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Usuario;

public class DAOUsuario extends DAO {
	public DAOUsuario() {
	}

	public void create(Usuario usuario) {

		super.open();
		String sql = "insert into sara.usuario" + "(cpf, nome, sobrenome, email, tipo)" + "values (?,?,?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSobrenome());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getTipo().toString());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public List<Usuario> read() {

		super.open();
		String sql = "select * from sara.usuario";

		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
			while (rs.next()) {

				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(NivelUsuario.valueOf(rs.getString("tipo")));
				usuarios.add(usuario);
			}

			rs.close();
			stmt.close();

			return usuarios;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	
	public List<Usuario> readLeve() {

		super.open();
		String sql = " SELECT cpf, nome, email FROM sara.usuario ORDER BY nome ";

		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
			while (rs.next()) {

				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuarios.add(usuario);
			
			}

			rs.close();
			stmt.close();

			return usuarios;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}
	
	public List<Usuario> readByTipo(String tipo) {

		super.open();
		String sql = "select * from sara.usuario where tipo = ?";

		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, tipo);
			ResultSet rs = stmt.executeQuery();
			super.close();
			while (rs.next()) {

				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(NivelUsuario.valueOf(rs.getString("tipo")));
				usuarios.add(usuario);
			}

			rs.close();
			stmt.close();

			return usuarios;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public Usuario getUsuario(String cpf) {

		super.open(); 
		String sql = "select * from sara.usuario where cpf = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			super.close();
			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(NivelUsuario.valueOf(rs.getString("tipo")));

				rs.close();
				stmt.close();
				
				return usuario;
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao buscar registro de pessoa, erro: " + e.getMessage());
		} finally {
			super.close();
		}
	}

	public void update(Usuario usuario) {

		super.open();
		String sql = "update sara.usuario set cpf = ?, nome = ?, sobrenome = ?, email = ?, tipo = ? "
				+ " where cpf = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSobrenome());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getTipo().toString());
			stmt.setString(6, usuario.getCpf());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public void updatePermissao(Usuario usuario) {
		super.open();
		String sql = "update sara.usuario set tipo = ? where cpf = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, usuario.getTipo().toString());
			stmt.setString(2, usuario.getCpf());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public void delete(String cpf) {

		super.open();
		String sql = "delete from sara.usuario where cpf = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}

	}

	public Integer getQuantidadePorNome(String nome) {
		super.open();
		String SQL = "SELECT COUNT(*) AS quantidade FROM sara.usuario WHERE nome ILIKE ?";
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

	public List<Usuario> buscarPorNome(String nome, int inicio, int fim) {
		super.open();
		String SQL = "SELECT * FROM sara.usuario WHERE nome ILIKE ? ORDER BY nome ASC LIMIT ? OFFSET ?";
		try {
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setString(1, "%" + nome + "%");
			ps.setInt(2, fim - inicio);
			ps.setInt(3, inicio);
			ResultSet rs = ps.executeQuery();
			super.close();
			List<Usuario> pessoas = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(NivelUsuario.valueOf(rs.getString("tipo")));
				pessoas.add(usuario);
			}
			ps.close();
			rs.close();
			return pessoas;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao buscar registro de pessoa, erro: " + e.getMessage());
		} finally {
			super.close();
		}
	}
}