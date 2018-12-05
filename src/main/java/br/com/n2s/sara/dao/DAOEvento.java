package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Evento;

public class DAOEvento extends DAO {
	public DAOEvento(){}

	public Evento create(Evento evento){
		super.open();

		String sql = "insert into sara.Evento"  
				+ "(nome, descricao, site, localizacao, dataInicial, dataFinal)"
				+ "values (?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setString(3, evento.getSite());
			stmt.setString(4, evento.getLocalizacao());
			stmt.setDate(5, Date.valueOf(evento.getDataInicial()));
			stmt.setDate(6, Date.valueOf(evento.getDataFinal()));

			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				evento.setIdEvento(rs.getInt(1));
			}
			rs.close();
			stmt.close();
			return evento;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public List<Evento> read(){
		super.open();

		String sql = "select * from sara.Evento";

		try{
			List<Evento> eventos = new ArrayList<Evento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){

				Evento evento = new Evento();

				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				eventos.add(evento);

			}

			rs.close();
			stmt.close();
			return eventos;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public Evento getEvento(int idEvento){
		super.open();
		String sql = "select * from sara.Evento where idEvento = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());

				rs.close();
				stmt.close();
				return evento;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public int getLastId(){
		super.open();
		String sql = "Select max(idEvento) from sara.Evento";
		
		try{
			Statement stmt = super.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int lastId = rs.getInt(1);

			stmt.close();
			rs.close();
			return lastId;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}

	public void update(Evento evento){

		super.open();
		String sql = "update sara.Evento set nome = ?, descricao = ?, " 
				+ "site = ?, localizacao = ?, dataInicial = ?, dataFinal = ? where idEvento = ?";

		try {

			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(2, evento.getNome());
			stmt.setString(3, evento.getDescricao());
			stmt.setString(4, evento.getSite());
			stmt.setString(5, evento.getLocalizacao());
			stmt.setDate(6, Date.valueOf(evento.getDataInicial()));
			stmt.setDate(7, Date.valueOf(evento.getDataFinal()));
			stmt.setInt(8, evento.getIdEvento());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}


	public void delete(int idEvento){
		super.open();
		String sql = "delete from sara.Evento where idEvento = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			super.close();
		}
	}
}