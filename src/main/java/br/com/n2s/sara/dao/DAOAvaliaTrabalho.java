package br.com.n2s.sara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.util.Facade;

public class DAOAvaliaTrabalho {
	
	private Connection connection;

	public DAOAvaliaTrabalho(){}

	public void create(AvaliaTrabalho avalia){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "insert into sara.avaliatrabalho"  
				+ "(idavaliador, idtrabalho)"
				+ "values (?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, avalia.getAvaliador().getCpf());
			stmt.setString(2, avalia.getAvaliador().getCpf());
			
			stmt.execute();
			stmt.close();
			this.connection.close();

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
				
				avaliacoes.add(avalia);
			}

			rs.close();
			stmt.close();
			this.connection.close();
			return avaliacoes;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public AvaliaTrabalho getAvaliaTrabalho(String idavaliador){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Avaliatrabalho where idavaliador = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, idavaliador);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				AvaliaTrabalho avalia = new AvaliaTrabalho();

				DAOUsuario daoUser = new DAOUsuario();
				DAOTrabalho daoTrab = new DAOTrabalho();
				
				avalia.setAvaliador(daoUser.getUsuario((rs.getString("idavaliador"))));
				avalia.setTrabalho(daoTrab.getTrabalho(rs.getInt("idtrabalho")));
				
				
				rs.close();
				stmt.close();
				this.connection.close();
				return avalia;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(AvaliaTrabalho avalia){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "update sara.AvaliaTrabalho set idavaliador = ?, idtrabalho = ?" 
				+ " where idavaliador = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, avalia.getAvaliador().getCpf());
			stmt.setInt(2, avalia.getTrabalho().getIdTrabalho());
						
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public void delete(AvaliaTrabalho avalia){
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "delete from sara.Usuario where idtrabalho = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, avalia.getTrabalho().getIdTrabalho());
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	
}