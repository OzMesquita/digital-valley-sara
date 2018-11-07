package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.CoordenacaoEvento;

public class DAOCoordenacaoEvento extends DAO {

	public DAOCoordenacaoEvento() {}

	public void create(CoordenacaoEvento coordenacaoEvento){

		super.open();
		String sql = "insert into sara.CoordenacaoEvento"  
				+ "(cpfCoordenador, idEvento)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoEvento.getEvento().getIdEvento());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<CoordenacaoEvento> read(){

		super.open(); 
		String sql = "select * from sara.CoordenacaoEvento";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOUsuario usuarioController = new DAOUsuario();
			DAOEvento eventoController = new DAOEvento();

			while(rs.next()){

				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoEvento.setEvento(eventoController.getEvento(rs.getInt("idEvento")));
				coordenacoes.add(coordenacaoEvento);
			}

			rs.close();
			stmt.close();
			super.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<CoordenacaoEvento> read(String cpfCoordenador){
		
		super.open();
		String sql = "select * from sara.CoordenacaoEvento where cpfCoordenador = ?";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			ResultSet rs = stmt.executeQuery();
			DAOUsuario usuarioController = new DAOUsuario();
			DAOEvento eventoController = new DAOEvento();

			while(rs.next()){

				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoEvento.setEvento(eventoController.getEvento(rs.getInt("idEvento")));
				coordenacoes.add(coordenacaoEvento);
			}

			rs.close();
			stmt.close();
			super.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<CoordenacaoEvento> read(int idEvento){

		super.open();
		String sql = "select * from sara.CoordenacaoEvento where idEvento = ?";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();
			DAOUsuario usuarioController = new DAOUsuario();
			DAOEvento eventoController = new DAOEvento();

			while(rs.next()){

				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoEvento.setEvento(eventoController.getEvento(rs.getInt("idEvento")));
				coordenacoes.add(coordenacaoEvento);
			}

			rs.close();
			stmt.close();
			super.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public CoordenacaoEvento getCoordenacaoEvento(String cpfCoordenador){

		super.open();
		String sql = "select * from sara.CoordenacaoEvento where cpfCoordenador = ?";
		DAOUsuario usuarioController = new DAOUsuario();
		DAOEvento eventoController = new DAOEvento();

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){

				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoEvento.setEvento(eventoController.getEvento(rs.getInt("idEvento")));

				rs.close();
				stmt.close();
				super.close();
				return coordenacaoEvento;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(CoordenacaoEvento coordenacaoEvento){

		super.open(); 
		String sql = "update sara.CoordenacaoEvento set cpfCoordenador = ?, idEvento = ? " 
				+ " where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoEvento.getEvento().getIdEvento());
			stmt.setString(3, coordenacaoEvento.getCoordenador().getCpf());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(CoordenacaoEvento coordenacaoEvento){

		super.open();
		String sql = "delete from sara.CoordenacaoEvento where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}