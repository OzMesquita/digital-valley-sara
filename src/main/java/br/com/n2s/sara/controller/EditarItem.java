package br.com.n2s.sara.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.n2s.sara.dao.DAOItem;
import br.com.n2s.sara.model.Criterio;
import br.com.n2s.sara.model.Item;
import br.com.n2s.sara.util.Constantes;

public class EditarItem extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			DAOItem daoItem = new DAOItem();
			Item item = (Item) session.getAttribute("item");
			
			int idItem = Integer.parseInt(request.getParameter("idItem"));
			String descricao = request.getParameter("descricao");
			int peso = Integer.parseInt(request.getParameter("peso"));

			item.setIdItem(idItem);
			item.setPeso(peso);
			item.setDescricao(descricao);
			
			daoItem.editar(item);
			
			session.setAttribute(Constantes.getSESSION_MGS(), "Item alterado com sucesso!");
			response.sendRedirect("eventosCoordenados.jsp");
			
			
		} catch (Exception e) {
			session.setAttribute(Constantes.getSESSION_MGS_ERROR(), "Erro ao alterar o Item: "+e.getMessage());

		}
	}

}
