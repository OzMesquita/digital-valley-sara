package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.n2s.sara.model.CoordenacaoTrilha;

public class DAOCoordenacaoTrilha extends DAO {

	public DAOCoordenacaoTrilha() {}

	public void create(CoordenacaoTrilha coordenacaoTrilha){

		super.open();
		String sql = "insert into sara.CoordenacaoTrilha"  
				+ "(cpfCoordenador, idTrilha)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoTrilha.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoTrilha.getTrilha().getIdTrilha());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<CoordenacaoTrilha> read(){

		super.open();
		String sql = "select * from sara.CoordenacaoTrilha";

		try{

			List<CoordenacaoTrilha> coordenacoes = new ArrayList<CoordenacaoTrilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOUsuario usuarioController = new DAOUsuario();
			DAOTrilha trilhaController = new DAOTrilha();

			while(rs.next()){

				CoordenacaoTrilha coordenacaoTrilha = new CoordenacaoTrilha();
				coordenacaoTrilha.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoTrilha.setTrilha(trilhaController.getTrilha(rs.getInt("idTrilha")));
				coordenacoes.add(coordenacaoTrilha);
			}

			rs.close();
			stmt.close();
			super.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

		public List<CoordenacaoTrilha> readById(String id){

		super.open(); 
		String sql = "select * from sara.CoordenacaoTrilha where cpfCoordenador = ?";

		try{

			List<CoordenacaoTrilha> coordenacoes = new ArrayList<CoordenacaoTrilha>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			DAOUsuario usuarioController = new DAOUsuario();
			DAOTrilha trilhaController = new DAOTrilha();

			while(rs.next()){

				CoordenacaoTrilha coordenacaoTrilha = new CoordenacaoTrilha();
				coordenacaoTrilha.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoTrilha.setTrilha(trilhaController.getTrilha(rs.getInt("idTrilha")));
				coordenacoes.add(coordenacaoTrilha);
			}

			rs.close();
			stmt.close();
			super.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


	public CoordenacaoTrilha getCoordenacaoTrilha(String cpfCoordenador){

		super.open();
		String sql = "select * from sara.CoordenacaoTrilha where cpfCoordenador = ?";
		DAOUsuario usuarioController = new DAOUsuario();
		DAOTrilha trilhaController = new DAOTrilha();

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){

				CoordenacaoTrilha coordenacaoTrilha = new CoordenacaoTrilha();
				coordenacaoTrilha.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoTrilha.setTrilha(trilhaController.getTrilha(rs.getInt("idTrilha")));

				rs.close();
				stmt.close();
				super.close();
				return coordenacaoTrilha;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(CoordenacaoTrilha coordenacaoTrilha){

		super.open();
		String sql = "update sara.CoordenacaoTrilha set cpfCoordenador = ?, idTrilha = ? " 
				+ " where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoTrilha.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoTrilha.getTrilha().getIdTrilha());
			stmt.setString(3, coordenacaoTrilha.getCoordenador().getCpf());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(String cpfCoordenador){

		super.open();
		String sql = "delete from sara.CoordenacaoTrilha where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}