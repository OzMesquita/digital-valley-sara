package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import br.com.n2s.sara.model.DescricaoPeriodo;
import br.com.n2s.sara.model.Evento;
import br.com.n2s.sara.model.TipoEvento;

public class DAOEvento extends DAO {
	public DAOEvento() {
	}

	public Evento create(Evento evento) {
		super.open();

		String sql = "insert into sara.Evento"
				+ "(nome, descricao, site, localizacao, dataInicial, dataFinal, divulgada, tipo_evento, excluido)"
				+ "values (?,?,?,?,?,?,?,?,?)";

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
			stmt.setBoolean(9, evento.getExcluido());

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
		} finally {
			super.close();
		}
	}

	public List<Evento> read() {
		super.open();

		String sql = "select * from sara.Evento";

		try {
			List<Evento> eventos = new ArrayList<Evento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			super.close();
			while (rs.next()) {

				Evento evento = new Evento();

				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				evento.setDivulgada(rs.getBoolean("divulgada"));
				evento.setDescriEvento(TipoEvento.valueOf(rs.getString("tipo_evento")));
				evento.setExcluido(rs.getBoolean("excluido"));

				if (!evento.getExcluido()) {
					eventos.add(evento);
				}
			}

			rs.close();
			stmt.close();

			return eventos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}
	
	/*
	 * Esse m�todo faz o mesmo que o m�todo acima (listar os eventos), mas, ao inv�s de listar todos os eventos,
	 * ele lista apenas os eventos que est�o ativos.
	 */
	public List<Evento> readAtivo() {
		super.open();

		String sql = "select * from sara.Evento";

		try {
			List<Evento> eventos = new ArrayList<Evento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			LocalDate hoje = LocalDate.now();
			super.close();
			while (rs.next()) {

				Evento evento = new Evento();

				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				evento.setDivulgada(rs.getBoolean("divulgada"));
				evento.setDescriEvento(TipoEvento.valueOf(rs.getString("tipo_evento")));
				evento.setExcluido(rs.getBoolean("excluido"));

				if (!evento.getExcluido()) {
					if ((hoje.isAfter(evento.getDataInicial()) || hoje.isEqual(evento.getDataInicial()))
							&& (hoje.isBefore(evento.getDataFinal()) || hoje.isEqual(evento.getDataFinal()))) {
						eventos.add(evento);
					}
				}
			}

			rs.close();
			stmt.close();

			return eventos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}
	
	public List<Evento> readInativos() {
		super.open();

		String sql = "SELECT * FROM sara.Evento WHERE not (? BETWEEN datainicial and datafinal) AND excluido = false";

		try {
			
			List<Evento> eventos = new ArrayList<Evento>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			//stmt.setDate(2, Date.valueOf(LocalDate.now()));
			//stmt.setDate(3, Date.valueOf(LocalDate.now()));
			ResultSet rs = stmt.executeQuery();
			super.close();
			while (rs.next()) {

				Evento evento = new Evento();

				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				evento.setDivulgada(rs.getBoolean("divulgada"));
				evento.setDescriEvento(TipoEvento.valueOf(rs.getString("tipo_evento")));
				evento.setExcluido(rs.getBoolean("excluido"));
				
				eventos.add(evento);
					
			}

			rs.close();
			stmt.close();

			return eventos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}
	
	public Evento getEvento(int idEvento) {
		super.open();
		String sql = "select * from sara.Evento where idEvento = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();
			super.close();
			if (rs.next()) {
				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNome(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setSite(rs.getString("site"));
				evento.setLocalizacao(rs.getString("localizacao"));
				evento.setDataInicial(rs.getDate("dataInicial").toLocalDate());
				evento.setDataFinal(rs.getDate("dataFinal").toLocalDate());
				evento.setDivulgada(rs.getBoolean("divulgada"));
				evento.setDescriEvento(TipoEvento.valueOf(rs.getString("tipo_evento")));
				evento.setExcluido(rs.getBoolean("excluido"));

				rs.close();
				stmt.close();
				return evento;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public void update(Evento evento) {

		super.open();
		String sql = "update sara.Evento set nome = ?, descricao = ?, "
				+ "site = ?, localizacao = ?, dataInicial = ?, dataFinal = ?, divulgada = ?, tipo_evento = ?, excluido = ? where idEvento = ?";

		try {

			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setString(3, evento.getSite());
			stmt.setString(4, evento.getLocalizacao());
			stmt.setDate(5, Date.valueOf(evento.getDataInicial()));
			stmt.setDate(6, Date.valueOf(evento.getDataFinal()));
			stmt.setBoolean(7, evento.getDivulgada());
			stmt.setString(8, evento.getDescriEvento().toString());
			stmt.setBoolean(9, evento.getExcluido());
			stmt.setInt(10, evento.getIdEvento());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public void updateExclusaoEvento(Evento evento) {

		super.open();
		String sql = "update sara.Evento set excluido = ? where idEvento = ?";

		try {

			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setBoolean(1, evento.getExcluido());
			stmt.setInt(2, evento.getIdEvento());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}

	public void delete(int idEvento) {
		super.open();
		String sql = "delete from sara.Evento where idEvento = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idEvento);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}
}