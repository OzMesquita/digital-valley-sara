package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.CoordenacaoEvento;
import br.com.n2s.sara.model.Usuario;

public class DAOCoordenacaoEvento extends DAO {

	public DAOCoordenacaoEvento() {}

	public void create(CoordenacaoEvento coordenacaoEvento){

		super.open();
		String sql = "insert into sara.coordenacaoevento"  
				+ "(cpfCoordenador, idEvento)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoEvento.getEvento().getIdEvento());

			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<CoordenacaoEvento> read(){

		super.open(); 
		String sql = "select * from sara.coordenacaoevento";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
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
			
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<CoordenacaoEvento> read(String cpfCoordenador){
		
		super.open();
		String sql = "select * from sara.coordenacaoevento where cpfCoordenador = ?";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			ResultSet rs = stmt.executeQuery();
			super.close();
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
			
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	public List<CoordenacaoEvento> read(int idEvento){

		super.open();
		String sql = "select * from sara.coordenacaoevento where idEvento = ?";

		try{

			List<CoordenacaoEvento> coordenacoes = new ArrayList<CoordenacaoEvento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();
			super.close();
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
			
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	public List<Usuario> ListarCoordenadores(int idEvento){

		super.open();
		String sql = "select * from sara.coordenacaoevento where idEvento = ?";

		try{

			List<Usuario> coordenacoes = new ArrayList<Usuario>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();
			super.close();
			DAOUsuario usuarioController = new DAOUsuario();

			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario = usuarioController.getUsuario(rs.getString("cpfCoordenador"));
				coordenacoes.add(usuario);
			}

			rs.close();
			stmt.close();
			
			return coordenacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
	
	public CoordenacaoEvento getCoordenacaoEvento(String cpfCoordenador){

		super.open();
		String sql = "select * from sara.coordenacaoevento where cpfCoordenador = ?";
		DAOUsuario usuarioController = new DAOUsuario();
		DAOEvento eventoController = new DAOEvento();

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, cpfCoordenador);
			ResultSet rs = stmt.executeQuery();
			super.close();
			if(rs.next()){

				CoordenacaoEvento coordenacaoEvento = new CoordenacaoEvento();
				coordenacaoEvento.setCoordenador(usuarioController.getUsuario(rs.getString("cpfCoordenador")));
				coordenacaoEvento.setEvento(eventoController.getEvento(rs.getInt("idEvento")));

				rs.close();
				stmt.close();
				
				return coordenacaoEvento;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public void update(CoordenacaoEvento coordenacaoEvento){

		super.open(); 
		String sql = "update sara.coordenacaoevento set cpfCoordenador = ?, idEvento = ? " 
				+ " where cpfCoordenador = (select cpfCoordenador from sara.coordenacaoevento where idEvento = ?) "
				+ "and idEvento = ?";
	
		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.setInt(2, coordenacaoEvento.getEvento().getIdEvento());
			stmt.setInt(3, coordenacaoEvento.getEvento().getIdEvento());
			stmt.setInt(4, coordenacaoEvento.getEvento().getIdEvento());

			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}


	public void delete(CoordenacaoEvento coordenacaoEvento){

		super.open();
		String sql = "delete from sara.coordenacaoevento where cpfCoordenador = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, coordenacaoEvento.getCoordenador().getCpf());
			stmt.execute();
			stmt.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}

	}

}