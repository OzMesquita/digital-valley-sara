package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.TipoEvento;

public class DAOEvento extends DAO {
	public DAOEvento(){}

	public Evento create(Evento evento){
		super.open();

		String sql = "insert into sara.Evento"  
				+ "(nome, descricao, site, localizacao, dataInicial, dataFinal, divulgada, tipo_evento)"
				+ "values (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = null;
			stmt = super.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setString(3, evento.getSite());
			stmt.setString(4, evento.getLocalizacao());
			stmt.setDate(5, Date.valueOf(evento.getDataInicial()));
			stmt.setDate(6, Date.valueOf(evento.getDataFinal()));
			stmt.setBoolean(7, evento.getDivulgada());
			stmt.setString(8, evento.getDescriEvento().toString());
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			super.close();
			if (rs.next()) {
				evento.setIdEvento(rs.getInt("idevento"));
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
			super.close();
			while(rs.next()){

				Evento evento = new Evento();

				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				evento.setDivulgada(rs.getBoolean("divulgada"));
				evento.setDescriEvento(  TipoEvento.valueOf(rs.getString("tipo_evento")));
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
			super.close();
			if(rs.next()){
				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				evento.setDivulgada(rs.getBoolean("divulgada"));
				evento.setDescriEvento(  TipoEvento.valueOf(rs.getString("tipo_evento")));
				
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

	public void update(Evento evento){

		super.open();
		String sql = "update sara.Evento set nome = ?, descricao = ?, " 
				+ "site = ?, localizacao = ?, dataInicial = ?, dataFinal = ?,divulgada=?,tipo_evento=? where idEvento = ?";

		try {

			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(2, evento.getNome());
			stmt.setString(3, evento.getDescricao());
			stmt.setString(4, evento.getSite());
			stmt.setString(5, evento.getLocalizacao());
			stmt.setDate(6, Date.valueOf(evento.getDataInicial()));
			stmt.setDate(7, Date.valueOf(evento.getDataFinal()));
			stmt.setBoolean(8, evento.getDivulgada());
			stmt.setString(9, evento.getDescriEvento().toString());
			stmt.setInt(10, evento.getIdEvento());
			
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