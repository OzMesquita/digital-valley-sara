package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.CoordenacaoEvento;
import br.com.n2s.sara.model.StatusTrabalho;
import br.com.n2s.sara.model.Trabalho;
import br.com.n2s.sara.util.Facade;

public class DAOAvaliaTrabalho {

	private Connection connection;

	public DAOAvaliaTrabalho(){}

	public void create(AvaliaTrabalho avalia){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "insert into sara.avaliatrabalho"  
				+ "(idavaliador, idtrabalho, feedback, status)"
				+ "values (?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, avalia.getAvaliador().getCpf());
			stmt.setInt(2, avalia.getTrabalho().getIdTrabalho());
			stmt.setString(3, avalia.getFeedback());
			stmt.setString(4, avalia.getStatus().toString());

			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AvaliaTrabalho> read(){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Avaliatrabalho";

		try{
			List<AvaliaTrabalho> avaliacoes = new ArrayList<AvaliaTrabalho>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){				
				DAOUsuario daoUser = new DAOUsuario();
				DAOTrabalho daoTrab = new DAOTrabalho();

				AvaliaTrabalho avalia = new AvaliaTrabalho();
				avalia.setAvaliador(daoUser.getUsuario((rs.getString("idavaliador"))));
				avalia.setTrabalho(daoTrab.getTrabalho(rs.getInt("idtrabalho")));
				avalia.setFeedback(rs.getString("feedback"));
				avalia.setStatus(StatusTrabalho.valueOf(rs.getString("status")));

				avaliacoes.add(avalia);
			}

			rs.close();
			stmt.close();
			connection.close();
			return avaliacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}



	public List<Trabalho> read(String cpfAvaliador){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.AvaliaTrabalho where idAvaliador = ?";

		try{
			List<Trabalho> trabalhos = new ArrayList<Trabalho>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cpfAvaliador);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){				

				trabalhos.add(new DAOTrabalho().getTrabalho(rs.getInt("idTrabalho")));
			}

			rs.close();
			stmt.close();
			connection.close();
			return trabalhos;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public AvaliaTrabalho getAvaliaTrabalho(String idAvaliador){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Avaliatrabalho where idavaliador = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, idAvaliador);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				AvaliaTrabalho avalia = new AvaliaTrabalho();

				DAOUsuario daoUser = new DAOUsuario();
				DAOTrabalho daoTrab = new DAOTrabalho();

				avalia.setAvaliador(daoUser.getUsuario((rs.getString("idavaliador"))));
				avalia.setTrabalho(daoTrab.getTrabalho(rs.getInt("idtrabalho")));
				avalia.setFeedback(rs.getString("feedback"));
				avalia.setStatus(StatusTrabalho.valueOf(rs.getString("status")));

				rs.close();
				stmt.close();
				connection.close();
				return avalia;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public AvaliaTrabalho getAvaliaTrabalho(int idTrabalho){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Avaliatrabalho where idTrabalho = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idTrabalho);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				AvaliaTrabalho avalia = new AvaliaTrabalho();

				DAOUsuario daoUser = new DAOUsuario();
				DAOTrabalho daoTrab = new DAOTrabalho();

				avalia.setAvaliador(daoUser.getUsuario((rs.getString("idavaliador"))));
				avalia.setTrabalho(daoTrab.getTrabalho(rs.getInt("idtrabalho")));
				avalia.setFeedback(rs.getString("feedback"));
				avalia.setStatus(StatusTrabalho.valueOf(rs.getString("status")));
				

				rs.close();
				stmt.close();
				connection.close();
				return avalia;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(AvaliaTrabalho avaliaTrabalho){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "update sara.AvaliaTrabalho set idavaliador = ?, idtrabalho = ?, feedback = ?, status = ?" 
				+ " where idTrabalho = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, avaliaTrabalho.getAvaliador().getCpf());
			stmt.setInt(2, avaliaTrabalho.getTrabalho().getIdTrabalho());
			stmt.setString(3, avaliaTrabalho.getFeedback());
			stmt.setString(4, avaliaTrabalho.getStatus().toString());
			stmt.setInt(5, avaliaTrabalho.getTrabalho().getIdTrabalho());

			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(AvaliaTrabalho avaliaTrabalho){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "delete from sara.Usuario where idtrabalho = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, avaliaTrabalho.getTrabalho().getIdTrabalho());
			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


}