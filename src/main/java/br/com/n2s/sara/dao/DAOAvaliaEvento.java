package br.com.n2s.sara.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import br.com.n2s.sara.model.Usuario;

public class DAOAvaliaEvento extends DAO{

	public DAOAvaliaEvento () {}
	
	public List<Usuario> buscarPorEvento(int idEvento){
		super.open();
		ArrayList<Usuario> avaliadores = new ArrayList<Usuario>();
		String sql = "select * from sara.avaliaevento where id_evento = ?";
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Usuario u = new DAOUsuario().getUsuario(rs.getString("id_avaliador"));
				avaliadores.add(u);
			}
			rs.close();
			stmt.close();
			
			return avaliadores;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	public void salvar(int idEvento, String cpf) {
		super.open();
		String sql = "insert into sara.avaliaevento(id_evento ,id_avaliador)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			stmt.setString(2, cpf);

			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
}
