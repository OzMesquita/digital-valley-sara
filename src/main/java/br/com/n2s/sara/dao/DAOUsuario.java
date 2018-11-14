package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.NivelUsuario;
import br.com.n2s.sara.model.Usuario;

public class DAOUsuario extends DAO{
	public DAOUsuario(){}

	public void create(Usuario usuario){
		
		super.open(); 
		String sql = "insert into sara.Usuario"  
				+ "(cpf, nome, sobrenome, email, tipo)"
				+ "values (?,?,?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSobrenome());
			stmt.setString(4, usuario.getEmail());
			stmt.setString(5, usuario.getTipo().toString());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> read(){
		
		super.open();
		String sql = "select * from sara.Usuario";

		try{
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

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
			super.close();
			return usuarios;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Usuario getUsuario(String cpf){
		
		super.open(); 
		String sql = "select * from sara.Usuario where cpf = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(NivelUsuario.valueOf(rs.getString("tipo")));

				rs.close();
				stmt.close();
				super.close();
				return usuario;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	


	public void update(Usuario usuario){
		
		super.open();
		String sql = "update sara.Usuario set cpf = ?, nome = ?, sobrenome = ?, email = ?, tipo = ? " 
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
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public void delete(String cpf){
		
		super.open(); 
		String sql = "delete from sara.Usuario where cpf = ?";

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