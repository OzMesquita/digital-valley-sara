package br.com.n2s.sara.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.n2s.sara.model.AvaliaTrabalho;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.StatusTrabalho;

/*
 * A ideia aqui é verificar se um determinado critério está na tabela de critérios avaliados
 * Caso ele esteja, então o critério já foi avaliado
 * Caso contrario ele não foi avaliado.
 * Isso é importante pois no controller de RemoveCriterio e RemoverCriterioListado
 * Utilizamos esté DAO, para verificar está questão 
 * E assim gerar alertas diferentes.
 */
public class DAOCriteriosAvaliados extends DAO {

	public boolean exist(int idCriterio, Criterio criterio) {
		super.open();
		String sql = "select * from sara.criteriosavaliados where fk_criterio = ? and fk_item = ?";

		boolean flagRetorno = false;
		try {
			ResultSet rs = null;
			PreparedStatement stmt = super.getConnection().prepareStatement(sql);
			for (int i = 0; i < criterio.getItens().size(); i++) {
				stmt.setInt(1, idCriterio);
				stmt.setInt(2, criterio.getItens().get(i).getIdItem());
				rs = stmt.executeQuery();
				if (rs.next()) {

					flagRetorno = true;
					// String fkCriterio = rs.getString("fk_criterio");
					// String fkItem = rs.getString("fk_item");
				}
				rs.close();

			}

			stmt.close();

			return flagRetorno;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			super.close();
		}
	}
}
