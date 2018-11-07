package br.com.n2s.sara.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.Item;

public class DAOItem extends DAO{

	public DAOItem() {}

	public void create(Item item){

		super.open();
		String sql = "insert into sara.Item"  
				+ "(descricao, peso,  idCriterio)"
				+ "values (?,?,?)";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, item.getDescricao());
			stmt.setInt(2, item.getPeso());
			stmt.setInt(3, item.getCriterio().getIdCriterio());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Item> read(){

		super.open();
		String sql = "select * from sara.Item";

		try{
			List<Item> itens = new ArrayList<Item>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
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
			super.close();
			return itens;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<Item> readById(int id){

		super.open();
		String sql = "select * from sara.Item where idCriterio = ?";

		try{
			List<Item> itens = new ArrayList<Item>();
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
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
			super.close();
			return itens;

		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Item getItem(int idItem){

		super.open();
		String sql = "select * from sara.Item where idItem = ?";

		try{
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
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
				super.close();
				return item;
			}else{
				return null;
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void update(Item item){

		super.open();
		String sql = "update sara.Item set descricao = ? peso = ?, idCriterio = ?"
				+ " where idItem = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setString(1, item.getDescricao());
			stmt.setInt(2, item.getPeso());
			stmt.setInt(3, item.getCriterio().getIdCriterio());
			stmt.setInt(4, item.getIdItem());

			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void delete(Item item){

		super.open();
		String sql = "delete from sara.Item where idItem = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, item.getIdItem());
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(int idItem){

		super.open();
		String sql = "delete from sara.Item where idItem = ?";

		try {
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			stmt.setInt(1, idItem);
			stmt.execute();
			stmt.close();
			super.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}