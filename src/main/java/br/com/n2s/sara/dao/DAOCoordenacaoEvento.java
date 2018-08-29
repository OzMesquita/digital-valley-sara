package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.CoordenacaoEvento;

public class DAOCoordenacaoEvento {

	private Connection connection;

	public DAOCoordenacaoEvento() {}

	public void create(CoordenacaoEvento coordenacaoEvento){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "insert into sara.CoordenacaoEvento"  
				+ "(cpfCoordenador, idEvento)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoEvento.getEvento().getIdEvento());

			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<CoordenacaoEvento> read(){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.CoordenacaoEvento";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
			this.connection.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<CoordenacaoEvento> read(String cpfCoordenador){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.CoordenacaoEvento where cpfCoordenador = ?";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
			this.connection.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<CoordenacaoEvento> read(int idEvento){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.CoordenacaoEvento where idEvento = ?";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
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
			this.connection.close();
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public CoordenacaoEvento getCoordenacaoEvento(String cpfCoordenador){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.CoordenacaoEvento where cpfCoordenador = ?";
		DAOUsuario usuarioController = new DAOUsuario();
		DAOEvento eventoController = new DAOEvento();

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){

				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoEvento.setEvento(eventoController.getEvento(rs.getInt("idEvento")));

				rs.close();
				stmt.close();
				this.connection.close();
				return coordenacaoEvento;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(CoordenacaoEvento coordenacaoEvento){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "update sara.CoordenacaoEvento set cpfCoordenador = ?, idEvento = ? " 
				+ " where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoEvento.getEvento().getIdEvento());
			stmt.setString(3, coordenacaoEvento.getCoordenador().getCpf());

			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(String cpfCoordenador){

		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "delete from sara.CoordenacaoEvento where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}