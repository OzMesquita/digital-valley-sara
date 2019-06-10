package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.n2s.sara.model.AvaliaTrilha;
import br.com.n2s.sara.model.Trilha;
import br.com.n2s.sara.model.Usuario;

public class DAOAvaliaTrilha extends DAO {

	public DAOAvaliaTrilha(){}

	public void create(AvaliaTrilha avalia){

		super.open();
		String sql = "insert into sara.avaliatrilha(idavaliador, idtrilha)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, avalia.getAvaliador().getCpf());
			stmt.setInt(2, avalia.getTrilha().getIdTrilha());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AvaliaTrilha> read(){

		super.open();
		String sql = "select * from sara.avaliatrilha";

		try{
			List<AvaliaTrilha> avaliacoes = new ArrayList<AvaliaTrilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				AvaliaTrilha avalia = new AvaliaTrilha();
				avalia.setAvaliador(new DAOUsuario().getUsuario((rs.getString("idAvaliador"))));
				avalia.setTrilha(new DAOTrilha().getTrilha((rs.getInt("idTrilha"))));

				avaliacoes.add(avalia);
			}

			rs.close();
			stmt.close();
			super.close();
			return avaliacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public AvaliaTrilha getAvaliaTrilha(String cpfAvaliador, int idTrilha){

		super.open();
		String sql = "select * from sara.avaliatrilha where idAvaliador = ? and idTrilha = ?";

		try{
			
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfAvaliador);
			stmt.setInt(2, idTrilha);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){

				AvaliaTrilha avaliaTrilha = new AvaliaTrilha();
				avaliaTrilha.setAvaliador(new DAOUsuario().getUsuario((rs.getString("idAvaliador"))));
				avaliaTrilha.setTrilha(new DAOTrilha().getTrilha((rs.getInt("idTrilha"))));
				
				rs.close();
				stmt.close();
				super.close();
				return avaliaTrilha;

			}

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	public List<Usuario> getAvaliadores(int idTrilha){

		super.open();
		String sql = "select * from sara.avaliatrilha where idTrilha = ?";

		try{
			List<Usuario> avaliadores = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idTrilha);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				Usuario avaliador = new DAOUsuario().getUsuario((rs.getString("idavaliador")));
				avaliadores.add(avaliador);
			}

			rs.close();
			stmt.close();
			super.close();
			return avaliadores;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<Trilha> getTrilhasAvaliadas(String idAvaliador){

		super.open();
		String sql = "select * from sara.avaliatrilha where idAvaliador = ?";

		try{
			List<Trilha> trilhas = new ArrayList<Trilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, idAvaliador);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				Trilha trilha = new DAOTrilha().getTrilha((rs.getInt("idtrabalho")));
				trilhas.add(trilha);
			}

			rs.close();
			stmt.close();
			super.close();
			return trilhas;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(AvaliaTrilha avaliaTrilha){

		super.close();
		String sql = "update sara.avaliatrilha set idavaliador = ?, idtrilha = ?" 
				+ " where idavaliador = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, avaliaTrilha.getAvaliador().getCpf());
			stmt.setInt(2, avaliaTrilha.getTrilha().getIdTrilha());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(AvaliaTrilha avaliaTrilha){

		super.open();
		String sql = "delete from sara.avaliatrilha where idavaliador = ? and idTrilha = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, avaliaTrilha.getAvaliador().getCpf());
			stmt.setInt(2, avaliaTrilha.getTrilha().getIdTrilha());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}