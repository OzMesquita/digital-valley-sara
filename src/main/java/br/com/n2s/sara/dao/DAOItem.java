package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Item;

public class DAOItem {
	
	private Connection connection;
	
	public DAOItem() {}
	
	public void create(Item item){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "insert into sara.Item"  
				+ "(descricao, peso,  idCriterio)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, item.getDescricao());
			stmt.setInt(2, item.getPeso());
			stmt.setInt(3, item.getCriterio().getIdCriterio());
			
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Item> read(){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.Item";

		try{
			List<Item> itens = new ArrayList<Item>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DAOCriterio daoCriterio = new DAOCriterio();
			
			while(rs.next()){

				Item item = new Item();

				item.setIdItem(rs.getInt("idItem"));
				item.setDescricao(rs.getString("descricao"));
				item.setPeso(rs.getInt("peso"));
				item.setCriterio(daoCriterio.getCriterio(rs.getInt("idCriterio")));
				
				itens.add(item);
			}

			rs.close();
			stmt.close();
			this.connection.close();
			return itens;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<Item> readById(int id){

		this.connection = new ConnectionFactory().getConnection();
		String sql = "select * from sara.Item where idCriterio = ?";

		try{
			List<Item> itens = new ArrayList<Item>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			DAOCriterio daoCriterio = new DAOCriterio();
			
			while(rs.next()){

				Item item = new Item();

				item.setIdItem(rs.getInt("idItem"));
				item.setDescricao(rs.getString("descricao"));
				item.setPeso(rs.getInt("peso"));
				item.setCriterio(daoCriterio.getCriterio(rs.getInt("idCriterio")));
				
				itens.add(item);

			}

			rs.close();
			stmt.close();
			this.connection.close();
			return itens;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Item getItem(int idItem){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "select * from sara.Item where idItem = ?";

		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idItem);
			ResultSet rs = stmt.executeQuery();
			DAOCriterio daoCriterio = new DAOCriterio();

			if(rs.next()){
				
				Item item = new Item();

				item.setIdItem(rs.getInt("idItem"));
				item.setDescricao(rs.getString("descricao"));
				item.setPeso(rs.getInt("peso"));
				item.setCriterio(daoCriterio.getCriterio(rs.getInt("idCriterio")));
			
				rs.close();
				stmt.close();
				this.connection.close();
				return item;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(Item item){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "update sara.Item set descricao = ? peso = ?, idCriterio = ?"
				+ " where idItem = ?";
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, item.getDescricao());
			stmt.setInt(2, item.getPeso());
			stmt.setInt(3, item.getCriterio().getIdCriterio());
			stmt.setInt(4, item.getIdItem());
			
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(int idItem){
		
		this.connection = new ConnectionFactory().getConnection(); 
		String sql = "delete from sara.Item where idItem = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idItem);
			stmt.execute();
			stmt.close();
			this.connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}